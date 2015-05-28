package net.servicestack.eclipse.wizard;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.servicestack.eclipse.maven.EclipseMavenHelper;
import net.servicestack.eclipse.nativetypes.INativeTypesHandler;
import net.servicestack.eclipse.nativetypes.JavaNativeTypesHandler;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class AddReferenceWizard extends Wizard {
	private AddReferencePage _page;
	private IFolder _selection;
	private IPackageFragment _packageFragment;
	private IPath _projectPath;
	private IPath _packagePath;
	private boolean _hasPomFile;
	private File _pomFile;
	public String packageName;
	private IProject currentProject;
	boolean packageSelected = false;

	private static final String dependencyGroupId = "net.servicestack";
	private static final String dependencyVersion = "1.0.10";
	private static final String clientPackageId = "client";
	boolean success = false;

	public AddReferenceWizard(IFolder selection,
			IStructuredSelection packageSelection) {
		IResource selectedResource = extractSelection(packageSelection);
		currentProject = selectedResource.getProject();
		_selection = selection;
		if(packageSelection != null) {
			Object firstElement = packageSelection.getFirstElement();
			if (firstElement instanceof IPackageFragment) {
				_packageFragment = (IPackageFragment) firstElement;
				packageName = _packageFragment.toString().substring(0,_packageFragment.toString().indexOf("[")).trim();
			}
		}
		
		
		if(_packageFragment == null) {
			_projectPath = currentProject.getFullPath();
			discoverPom(selection.getProjectRelativePath().toString(),_projectPath.toString());
		} else {
			_projectPath = _packageFragment.getJavaProject().getPath();
			_packagePath = _packageFragment.getPath();
			packageSelected = true;
			discoverPom(_packagePath.toString(),_projectPath.toString());
		}
	}

	private void discoverPom(String folderPath, String projectPath) {
		File lastPath = new File(folderPath);
		File projectPathFile = new File(projectPath);
		if (!lastPath.isDirectory() || !projectPathFile.isDirectory()) {
			_hasPomFile = false;
		}
		while (!lastPath.toString().equals(File.separator)) {
			if (hasChildPomFile(lastPath.toString())) {
				// POM found
				_hasPomFile = true;
				String pomPath = stripFirstDirectoryFromPath(new Path(lastPath.toString() + File.separator + "pom.xml")).toString();
				_pomFile = new File(pomPath);
				break;
			}
			lastPath = lastPath.getParentFile();
		}
	}
	
	IResource extractSelection(ISelection sel) {
	      if (!(sel instanceof IStructuredSelection))
	         return null;
	      IStructuredSelection ss = (IStructuredSelection) sel;
	      Object element = ss.getFirstElement();
	      if (element instanceof IResource)
	         return (IResource) element;
	      if (!(element instanceof IAdaptable))
	         return null;
	      IAdaptable adaptable = (IAdaptable)element;
	      Object adapter = adaptable.getAdapter(IResource.class);
	      return (IResource) adapter;
	   }

	private boolean hasChildPomFile(String filePath) {
		IProject project = currentProject;
		IPath pomPath = stripFirstDirectoryFromPath(new Path(filePath + File.separator + "pom.xml"));
		IFile file = project.getFile(pomPath);
		return file.exists();
	}

	@Override
	public boolean performFinish() {
		final String addressUrl = _page.getAddressUrl();
		final String fileName = _page.getFileName();
		success = false;
		try {
			getContainer().run(false, false, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					monitor.beginTask("Fetching ServiceStack Reference", 10);
					monitor.worked(2);
					String code = null;
					try {

						code = fetchDto(addressUrl);
						if (code == null) {
							_page.setErrorMessage("Invalid ServiceStack endpoint.");
							return;
						}
					} catch (IOException e1) {
						_page.setErrorMessage("Error occurred trying to validate the ServiceStack endpoint - "
								+ e1.getMessage());
						e1.printStackTrace();
						return;
					}

					monitor.worked(2);
					try {
						addDtoFileWithCode(fileName, code);
					} catch (CoreException e) {
						e.printStackTrace();
						_page.setErrorMessage("Error occurred when trying to create DTO file - "
								+ e.getMessage());
						monitor.done();
						return;
					}
					monitor.worked(2);
					if (_hasPomFile) {
						try {
							updatePomFile();
						} catch (Exception e) {
							// TODO Warn maven dependency failed, continue as
							// DTOs were added.
							e.printStackTrace();
						}
						monitor.worked(2);
					}
					monitor.worked(2);
					monitor.done();
					success = true;
				}
			});
		} catch (InvocationTargetException | InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			_page.setErrorMessage("Failed get ServiceStack reference - "
					+ e2.getMessage());
		}

		return success;
	}
	
	private void updatePomFile() throws Exception {
		EclipseMavenHelper mavenHelper = new EclipseMavenHelper();
		IProject project = currentProject;
		IPath pomPath = new Path(_pomFile.getPath());
		IFile file = project.getFile(pomPath);
		if (mavenHelper.addMavenDependencyIfRequired(file,
				dependencyGroupId, clientPackageId,
				dependencyVersion)) {
			// TODO Show pom file/prompt to import changes
			// of pom file/perform maven task to download
			// dependencies
		}
	}

	private String fetchDto(final String addressUrl) throws IOException {
		String code = null;
		INativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
		Map<String, String> options = null;
		if(packageSelected) {
			options = new HashMap<String,String>();
			options.put("Package", packageName);
		}
		code = nativeTypesHandler.getUpdatedCode(addressUrl, options);
		return code;
	}
	
	/**
     * The method returns the current Path information as List<String>.
     * <ol>
     * <li>0 - Project name</li>
     * <li>1 - SRC Folder name</li>
     * <li>2 - Package name</li>
     * </ol>
     *
     * @return List<Object>
     */
    public static List<Object> getSelectedObjectPath() {
        // the current selection in the entire page
        final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        final IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
        final Object o = selection.getFirstElement();
        final List<Object> a = new ArrayList<Object>(4);
        IJavaElement obj = (IJavaElement) o;
        if (o == null) {
            return null;
        }
        while (obj != null) {
            a.add(0, obj);
            obj = obj.getParent();
        }
        // remove JavaModel
        a.remove(0);
        return a;
    }

	private void addDtoFileWithCode(final String fileName, String code)
			throws CoreException {
		String constructedPath = stripFirstDirectoryFromPath(_selection.getFullPath()).toString();
		String currentPackagePath = constructedPath;
		Path filePath = new Path(currentPackagePath + File.separator + fileName);
		IProject project = currentProject;
		IFile dtoFile = project.getFile(filePath);
		String contents = code;
		InputStream source = new ByteArrayInputStream(contents.getBytes());
		dtoFile.create(source, true, null);
	}
	
	private IPath stripFirstDirectoryFromPath(IPath path) {
		String constructedPath = "";
		// Skip first segment of project path as we want path relative to
		// project.
		for (int i = 1; i < path.segmentCount(); i++) {
			constructedPath += "/" + path.segment(i);
		}
		if(constructedPath.equals("")) {
			constructedPath = "/";
		}
		Path result = new Path(constructedPath);
		return result;
	}

	public boolean canFinish() {
		return _page.canFinish;
	}

	protected void openEditor(IFile file, String editorID)
			throws PartInitException {
		IEditorRegistry editorRegistry = getWorkbench().getEditorRegistry();
		if (editorID == null || editorRegistry.findEditor(editorID) == null) {
			editorID = getWorkbench().getEditorRegistry()
					.getDefaultEditor(file.getFullPath().toString()).getId();
		}

		IWorkbenchPage page = getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
		page.openEditor(new FileEditorInput(file), editorID, true,
				IWorkbenchPage.MATCH_ID);
	}

	private IWorkbench getWorkbench() {
		return PlatformUI.getWorkbench();
	}

	@Override
	public void addPages() {
		super.addPages();

		_page = new AddReferencePage();
		addPage(_page);
	}

	@Override
	public boolean needsProgressMonitor() {
		return true;
	}

}
