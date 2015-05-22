package net.servicestack.eclipse.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import net.servicestack.eclipse.maven.EclipseMavenHelper;
import net.servicestack.eclipse.nativetypes.INativeTypesHandler;
import net.servicestack.eclipse.nativetypes.JavaNativeTypesHandler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

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
	
	public AddReferenceWizard(IFolder selection, IPackageFragment packageFragment) {
		_selection = selection;
		_packageFragment = packageFragment;
		_projectPath = packageFragment.getJavaProject().getPath();
		_packagePath = packageFragment.getPath();
		
		File lastPath = new File(_packagePath.toString());
		File projectPath = new File(_projectPath.toString());
		if(!lastPath.isDirectory() || !projectPath.isDirectory()) {
			_hasPomFile = false;
		}
		while(!lastPath.getAbsolutePath().equals(projectPath.getAbsolutePath())) {
			if(!hasChildPomFile(lastPath)) {
				lastPath = lastPath.getParentFile();
			} else {
				//POM found
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
			getShell().getDisplay().asyncExec(new Runnable() {
			      public void run(){
			         
			         INativeTypesHandler nativeTypesHandler = new JavaNativeTypesHandler();
			         try {
						boolean validUrl = nativeTypesHandler.validateServiceStackEndpoint(addressUrl);
		         		if(!validUrl) {
		         			_page.setErrorMessage(errorMessage = "Invalid ServiceStack endpoint.");
				         }
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						_page.setErrorMessage("Error occurred trying to validate the ServiceStack endpoint - " + e1.getMessage());
						e1.printStackTrace();
						return;
					}
			       
			         String code = null;
			         try {
			        	 code = nativeTypesHandler.getUpdatedCode(addressUrl, null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						_page.setErrorMessage("Error occurred trying to fetch ServiceStack DTOs - " + e1.getMessage());
						e1.printStackTrace();
						return;
					}
			         if(_hasPomFile) {
			        	
			        	 EclipseMavenHelper mavenHelper = new EclipseMavenHelper();
							try {
//								if(mavenHelper.addMavenDependencyIfRequired(_pomFile, dependencyGroupId, clientPackageId, dependencyVersion)) {
//									//TODO Show pom file/prompt to import changes of pom file/perform maven task to download dependencies
//								}
							} catch (Exception e) {
								//TODO Warn maven dependency failed, continue as DTOs were added.
								e.printStackTrace();
							} 
			         }
			        
			      }
			   });
			
			
		} catch (Exception e) {
			_page.setErrorMessage("Failed " + e.getMessage());
		}
		
		return false;
	}
	
	@Override
	public void addPages() {
		super.addPages();
		
		_page = new AddReferencePage();
		String sessionName = _selection.getParent().getParent().getName().toLowerCase();
//		_page.setPathToOutput(_outputFolder.getFolder(sessionName).getLocation().toOSString());
//		_page.setPathToInputFolder(_selection.getFolder(sessionName).getLocation().toOSString());
		addPage(_page);
	}
	
	@Override
	public boolean needsProgressMonitor() {
		return true;
	}

}
