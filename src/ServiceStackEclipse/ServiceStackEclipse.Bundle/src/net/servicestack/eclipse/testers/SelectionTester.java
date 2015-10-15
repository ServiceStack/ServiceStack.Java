package net.servicestack.eclipse.testers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.servicestack.eclipse.handlers.UpdateReferenceCurrentSelection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class SelectionTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if ("hasNonEmptyTextSelection".equals(property)) {
			if(!(receiver instanceof ITreeSelection)) {
				return false;
			}
			
			TreeSelection treeSelection = (TreeSelection)receiver;
	        if(!(treeSelection.getFirstElement() instanceof ICompilationUnit)) {
	        	return false;
	        }

	        ICompilationUnit compilationUnit = (ICompilationUnit)treeSelection.getFirstElement();
	    	IResource resource = extractSelection(treeSelection);
	    	IProject project = resource.getProject();
	        IImportDeclaration importDeclaration = compilationUnit.getImport("net.servicestack.client");
	        if(importDeclaration == null) {
	        	return false;
	        }
	        
	        IFile file = project.getFile(stripFirstDirectoryFromPath(compilationUnit.getPath()));
	        InputStream contents = null;
			try {
				contents = file.getContents();
				InputStreamReader is = new InputStreamReader(contents);
				BufferedReader br = new BufferedReader(is);
				String read;
				try {
					for(int i = 0; i < 10; i++) {
						String line = br.readLine();
						if(line.startsWith("/* Options:")) {
							UpdateReferenceCurrentSelection.getInstance().UpdateReferenceFile = file;
							    return true;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(contents == null) {
				return false;
			}
	        System.out.println("File selected");
			 
		}
		return true;
	}
	
    IResource extractSelection(ITreeSelection sel) {
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
		Path result = new Path(constructedPath);
		return result;
	}

}