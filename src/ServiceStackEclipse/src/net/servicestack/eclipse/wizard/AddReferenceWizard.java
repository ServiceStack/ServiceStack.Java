package net.servicestack.eclipse.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import net.servicestack.eclipse.maven.EclipseMavenHelper;
import net.servicestack.eclipse.nativetypes.INativeTypesHandler;
import net.servicestack.eclipse.nativetypes.JavaNativeTypesHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

public class AddReferenceWizard extends Wizard {
	private AddReferencePage _page;
	private IFolder _selection;
	private IPackageFragment _packageFragment;
	private IPath _projectPath;
	private IPath _packagePath;
	private boolean _hasPomFile;
	private File _pomFile;

	private String errorMessage;

	private static final String dependencyGroupId = "net.servicestack";
	private static final String dependencyPackageId = "android";
	private static final String dependencyVersion = "1.0.10";
	private static final String clientPackageId = "client";

	public AddReferenceWizard(IFolder selection,
			IPackageFragment packageFragment) {
		_selection = selection;
		_packageFragment = packageFragment;
		_projectPath = packageFragment.getJavaProject().getPath();
		_packagePath = packageFragment.getPath();

		File lastPath = new File(_packagePath.toString());
		File projectPath = new File(_projectPath.toString());
		if (!lastPath.isDirectory() || !projectPath.isDirectory()) {
			_hasPomFile = false;
		}
		while (!lastPath.getAbsolutePath()
				.equals(projectPath.getAbsolutePath())) {
			if (!hasChildPomFile(lastPath)) {
				lastPath = lastPath.getParentFile();
			} else {
				// POM found
				_hasPomFile = true;
				_pomFile = new File(lastPath.getAbsolutePath() + "/pom.xml");
			}
		}
	}

	private boolean hasChildPomFile(File filePath) {
		File[] matchingFiles = filePath.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith("pom") && name.endsWith("xml");
			}
		});
		return matchingFiles != null && matchingFiles.length > 0;
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		final String addressUrl = _page.getAddressUrl();
		final String fileName = _page.getFileName();
		try {
			getContainer().run(false, false, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					INativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
					try {
						String generatedCode = nativeTypesHandler
								.getUpdatedCode(addressUrl, null);
						if (generatedCode == null) {
							_page.setErrorMessage(errorMessage = "Invalid ServiceStack endpoint.");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						_page.setErrorMessage("Error occurred trying to validate the ServiceStack endpoint - "
								+ e1.getMessage());
						e1.printStackTrace();
						return;
					}

					String code = null;
					try {
						code = nativeTypesHandler.getUpdatedCode(addressUrl,
								null);
						IProject project = _packageFragment.getJavaProject()
								.getProject();
						IFile dtoFile = project.getFile(new Path(
								_packageFragment.getPath().makeAbsolute().toString() + "/"
										+ fileName));
						String contents = "Whatever";
						InputStream source = new ByteArrayInputStream(contents
								.getBytes());
						dtoFile.create(source, false, null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						_page.setErrorMessage("Error occurred trying to fetch ServiceStack DTOs - "
								+ e1.getMessage());
						e1.printStackTrace();
						return;
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						_page.setErrorMessage("Error occurred when trying to create DTO file - "
								+ e.getMessage());
					}
					if (_hasPomFile) {

						EclipseMavenHelper mavenHelper = new EclipseMavenHelper();
						try {
							if (mavenHelper.addMavenDependencyIfRequired(
									_pomFile, dependencyGroupId,
									clientPackageId, dependencyVersion)) {
								// TODO Show pom file/prompt to import changes
								// of pom file/perform maven task to download
								// dependencies
							}
						} catch (Exception e) {
							// TODO Warn maven dependency failed, continue as
							// DTOs were added.
							e.printStackTrace();
						}
					}

				}
			});
		} catch (InvocationTargetException | InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			_page.setErrorMessage("Failed get ServiceStack reference - "
					+ e2.getMessage());
		}

		return false;
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
		// TODO Auto-generated method stub
		return PlatformUI.getWorkbench();
	}

	@Override
	public void addPages() {
		super.addPages();

		_page = new AddReferencePage();
		String sessionName = _selection.getParent().getParent().getName()
				.toLowerCase();
		// _page.setPathToOutput(_outputFolder.getFolder(sessionName).getLocation().toOSString());
		// _page.setPathToInputFolder(_selection.getFolder(sessionName).getLocation().toOSString());
		addPage(_page);
	}

	@Override
	public boolean needsProgressMonitor() {
		return true;
	}

}
