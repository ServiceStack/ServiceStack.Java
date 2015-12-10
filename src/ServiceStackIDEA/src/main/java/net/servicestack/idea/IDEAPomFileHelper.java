package net.servicestack.idea;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.DocumentRunnable;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.GenericDomValue;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class IDEAPomFileHelper {

    public boolean addMavenDependency(final Module module,File pomFile, String groupId, String packageId, String version) throws Exception {
        boolean dependencyAdded = false;

        try {
            if(!pomHasMavenDependency(pomFile,groupId,packageId,version)) {
                PomAppendDependency(module, pomFile,groupId,packageId,version);
                dependencyAdded = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Unable to process pom.xml to add " + groupId + ":" + packageId + ":" + version);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new Exception("Unable to process pom.xml to add " + groupId + ":" + packageId + ":" + version);
        }

        return dependencyAdded;
    }


    public String findNearestModulePomFile(Module module) {
        PsiFile[] pomLibFiles = FilenameIndex.getFilesByName(module.getProject(), "pom.xml", GlobalSearchScope.allScope(module.getProject()));
        String pomFilePath = null;
        for(PsiFile psiPom : pomLibFiles) {
            if(psiPom.getParent().getVirtualFile().getPath().equals(module.getModuleFile().getParent().getPath())) {
                pomFilePath = psiPom.getVirtualFile().getPath();
            }
        }
        return pomFilePath;
    }

    private void PomAppendDependency(final Module module, final File pomFile, String groupId, String packageId, String version) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        final Document doc = docBuilder.parse(pomFile);
        Node root = doc.getFirstChild();

        if (!pomHasDependenciesNode(doc)) {
            root.appendChild(doc.createElement("dependencies"));
        }

        Node dependenciesNode = getMavenDependenciesNode(doc);
        Node newDepNode = doc.createElement("dependency");
        Node groupNode = doc.createElement("groupId");
        groupNode.appendChild(doc.createTextNode(groupId));
        Node artifactNode = doc.createElement("artifactId");
        artifactNode.appendChild(doc.createTextNode(packageId));
        Node versionNode = doc.createElement("version");
        versionNode.appendChild(doc.createTextNode(version));
        newDepNode.appendChild(groupNode);
        newDepNode.appendChild(artifactNode);
        newDepNode.appendChild(versionNode);
        dependenciesNode.appendChild(newDepNode);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        final StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));

        final Project project = module.getProject();
        final VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByIoFile(pomFile);
        final com.intellij.openapi.editor.Document document = FileDocumentManager.getInstance().getDocument(virtualFile);
        final PsiFile psiPomFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
        final FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
        fileDocumentManager.saveDocument(document); //when file is edited and editor is closed, it is needed to save the text
        PsiDocumentManager.getInstance(project).commitDocument(document);
        PsiDocumentManager.getInstance(module.getProject()).commitAllDocuments();
        ApplicationManager.getApplication().runWriteAction(new DocumentRunnable(document, null) {
            @Override public void run() {
                PsiDocumentManager.getInstance(module.getProject())
                        .doPostponedOperationsAndUnblockDocument(document);
                document.setText(writer.getBuffer().toString().replaceAll("\r\n","\n"));
                fileDocumentManager.saveDocument(document);
                PsiDocumentManager.getInstance(project).commitDocument(document);
            }
        });
    }

    private boolean pomHasDependenciesNode(Document document) {
        return getMavenDependenciesNode(document) != null;
    }

    private Node getMavenDependenciesNode(Document document) {
        Node rootNode = document.getFirstChild();
        NodeList firstChildern = rootNode.getChildNodes();
        Node result = null;
        for(int i = 0; i < firstChildern.getLength(); i++) {
            Node child = firstChildern.item(i);
            if(child == null) {
                continue;
            }
            if(child.getNodeName().equals("dependencies")) {
                result = child;
                break;
            }
        }
        return result;
    }

    private boolean pomHasMavenDependency(File pomFile, String groupId, String packageId, String version) throws ParserConfigurationException, IOException, SAXException {
        boolean hasDependency = false;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(pomFile);

        if(!pomHasDependenciesNode(doc)) {
            return false;
        }

        Node dependencies = getMavenDependenciesNode(doc);
        NodeList depElements = dependencies.getChildNodes();
        for(int i = 0; i < depElements.getLength(); i++) {
            Node dependencyElement = depElements.item(i);
            if(pomDependencyElementMatch(dependencyElement, groupId,packageId,version)) {
                hasDependency = true;
                break;
            }
        }
        return hasDependency;
    }

    private boolean pomDependencyElementMatch(Node dependencyElement, String groupId, String packageId, String version) {
        boolean groupIdMatch = false;
        boolean artifactIdMatch = false;
        boolean versionMatch = false;
        NodeList dependencyProperties = dependencyElement.getChildNodes();
        for(int j = 0; j < dependencyProperties.getLength(); j++) {
            Node depProp = dependencyProperties.item(j);
            if(depProp.getNodeName().equals("groupId") && depProp.getNodeValue() != null && depProp.getNodeValue().equals(groupId)) {
                groupIdMatch = true;
            }
            if(depProp.getNodeName().equals("artifactId") && depProp.getNodeValue() != null && depProp.getNodeValue().equals(packageId)) {
                artifactIdMatch = true;
            }
            if(depProp.getNodeName().equals("version") && depProp.getNodeValue() != null && depProp.getNodeValue().equals(version)) {
                versionMatch = true;
            }
        }
        return groupIdMatch && artifactIdMatch && versionMatch;
    }
}
