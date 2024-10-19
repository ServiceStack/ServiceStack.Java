package net.servicestack.eclipse.wizard;

import net.servicestack.eclipse.maven.EclipseMavenHelper;
import net.servicestack.eclipse.nativetypes.INativeTypesHandler;
import net.servicestack.eclipse.nativetypes.JavaNativeTypesHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.*;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.progress.UIJob;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class AddReferenceWizard extends Wizard {
    private AddReferencePage _page;
    private IFolder _selection;
    private IPackageFragment _packageFragment;
    private boolean _hasPomFile;
    private File _pomFile;
    public String packageName;
    private IProject currentProject;
    boolean packageSelected = false;

    private static final String dependencyGroupId = "net.servicestack";
    private static final String dependencyVersion = "1.1.1";
    private static final String clientPackageId = "client";

    private static final String gsonDependencyGroupId = "com.google.code.gson";
    private static final String gsonPackageId = "gson";
    private static final String gsonDependencyVersion = "2.11.0";

    boolean success = false;

    public AddReferenceWizard(IFolder selection,
                              IStructuredSelection packageSelection) {
        IResource selectedResource = extractSelection(packageSelection);
        currentProject = selectedResource.getProject();
        _selection = selection;
        if (packageSelection != null) {
            Object firstElement = packageSelection.getFirstElement();
            if (firstElement instanceof IPackageFragment) {
                _packageFragment = (IPackageFragment) firstElement;
                packageName = _packageFragment.toString()
                        .substring(0, _packageFragment.toString().indexOf("["))
                        .trim();

            }
        }

        IPath _projectPath;
        if (_packageFragment == null) {
            _projectPath = currentProject.getFullPath();
            discoverPom(selection.getProjectRelativePath().toString(),
                    _projectPath.toString());
        } else {
            _projectPath = _packageFragment.getJavaProject().getPath();
            IPath _packagePath = _packageFragment.getPath();
            packageSelected = true;
            discoverPom(_packagePath.toString(), _projectPath.toString());
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
                String pomPath = stripFirstDirectoryFromPath(
                        new Path(lastPath.toString() + File.separator
                                + "pom.xml")).toString();
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
        IAdaptable adaptable = (IAdaptable) element;
        Object adapter = adaptable.getAdapter(IResource.class);
        return (IResource) adapter;
    }

    private boolean hasChildPomFile(String filePath) {
        IProject project = currentProject;
        IPath pomPath = stripFirstDirectoryFromPath(new Path(filePath
                + File.separator + "pom.xml"));
        IFile file = project.getFile(pomPath);
        return file.exists();
    }

    @Override
    public boolean performFinish() {
        _page.canFinish = false;
        success = false;
        final String addressUrl = _page.getAddressUrl();
        final String fileName = _page.getFileName();
        boolean fileNameContainsDot = fileName.indexOf('.') > 0;
        int endSubString = fileNameContainsDot ? fileName.indexOf('.') : fileName.length();
        final String globalNamespace = fileName.substring(0, endSubString);
        packageName = _page.getPackageName();
        try {
            getContainer().run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor)
                        throws InvocationTargetException, InterruptedException {
                    monitor.beginTask("Fetching ServiceStack Reference", 10);
                    monitor.worked(2);
                    String code;
                    try {

                        code = fetchDto(addressUrl, globalNamespace);
                        if (code == null) {
                            UIJob uiJob = new UIJob("set error") {
                                @Override
                                public IStatus runInUIThread(
                                        IProgressMonitor monitor) {
                                    _page.setErrorMessage("Invalid ServiceStack endpoint.");
                                    return Status.OK_STATUS;
                                }
                            };
                            uiJob.schedule();
                            return;
                        }
                    } catch (final IOException e1) {
                        UIJob uiJob = new UIJob("set error") {
                            @Override
                            public IStatus runInUIThread(
                                    IProgressMonitor monitor) {
                                _page.setErrorMessage(
                                        "Error occurred trying to validate the ServiceStack endpoint. Check the address provided. - "
                                                + e1.getMessage());
                                e1.printStackTrace();
                                return Status.OK_STATUS;
                            }
                        };
                        uiJob.schedule();
                        return;
                    }

                    monitor.worked(2);
                    try {
                        addDtoFileWithCode(fileName, code);
                    } catch (final CoreException e) {
                        e.printStackTrace();
                        UIJob uiJob = new UIJob("set error") {
                            @Override
                            public IStatus runInUIThread(
                                    IProgressMonitor monitor) {
                                _page.setErrorMessage("Error occurred when trying to create DTO file - "
                                        + e.getMessage());
                                return Status.OK_STATUS;
                            }
                        };
                        uiJob.schedule();
                        monitor.done();
                        return;
                    }
                    monitor.worked(2);
                    if (_hasPomFile) {
                        try {
                            updatePomFile();
                        } catch (Exception e) {
                            // TODO Workout how to show some kind of warning dialog like
                            // IDEA, doesn't seem to be one for Eclipse..
                            //Conitinue as DTOs were successfully added
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
        _page.canFinish = true;
        getContainer().updateButtons();
        return success;
    }

    private void updatePomFile() throws Exception {
        EclipseMavenHelper mavenHelper = new EclipseMavenHelper();
        IProject project = currentProject;
        IPath pomPath = new Path(_pomFile.getPath());
        IFile file = project.getFile(pomPath);
        if (mavenHelper.addMavenDependencyIfRequired(file, dependencyGroupId, clientPackageId, dependencyVersion) ||
            mavenHelper.addMavenDependencyIfRequired(file, gsonDependencyGroupId, gsonPackageId , gsonDependencyVersion)) {
            file.refreshLocal(IResource.DEPTH_ZERO, null);
            openEditor(file, null);
        }
    }

    private String fetchDto(final String addressUrl, final String globalNamespace) throws IOException {
        String code;
        INativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
        Map<String, String> options = new HashMap<>();
        options.put("GlobalNamespace", globalNamespace);
        if (packageSelected) {
            options.put("Package", packageName);
        }

        code = nativeTypesHandler.getUpdatedCode(addressUrl, options);
        return code;
    }

    private void addDtoFileWithCode(final String fileName, String code)
            throws CoreException {
        String currentPackagePath = stripFirstDirectoryFromPath(
                _selection.getFullPath()).toString();
        Path filePath = new Path(currentPackagePath + File.separator + fileName);
        IProject project = currentProject;
        IFile dtoFile = project.getFile(filePath);
        InputStream source = new ByteArrayInputStream(code.getBytes());
        dtoFile.create(source, true, null);
    }

    private IPath stripFirstDirectoryFromPath(IPath path) {
        String constructedPath = "";
        // Skip first segment of project path as we want path relative to
        // project.
        for (int i = 1; i < path.segmentCount(); i++) {
            constructedPath += "/" + path.segment(i);
        }
        if (constructedPath.equals("")) {
            constructedPath = "/";
        }
        return new Path(constructedPath);
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
        _page.setPackageName(packageName);
        Shell shell = this.getShell();
        shell.setMinimumSize(550,450);
        Display display = shell.getDisplay();
        Monitor primaryMonitor = display.getPrimaryMonitor();
        Rectangle bounds = primaryMonitor.getBounds();
        Rectangle rect = shell.getBounds();
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        shell.setLocation(x, y);
    }

    @Override
    public boolean needsProgressMonitor() {
        return true;
    }

}
