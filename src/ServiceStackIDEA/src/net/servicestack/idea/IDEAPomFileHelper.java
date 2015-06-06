package net.servicestack.idea;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Layoric on 10/05/2015.
 */
public class IDEAPomFileHelper implements IPomFileHelper {
    @Override
    public boolean addMavenDependencyIfRequired(File pomFile, String groupId, String packageId, String version) throws Exception {
        boolean noDependencyAdded = true;
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model pomModel;
        try {
            pomModel = reader.read(new FileReader(pomFile));
            final List<Dependency> dependencies= pomModel.getDependencies();
            boolean requiresPomDependency = true;
            for (Dependency dep : dependencies) {
                if(Objects.equals(dep.getGroupId(), groupId) && Objects.equals(dep.getArtifactId(), packageId)) {
                    requiresPomDependency = false;
                }
            }

            if(requiresPomDependency) {
                Dependency dependency = new Dependency();
                dependency.setGroupId(groupId);
                dependency.setArtifactId(packageId);
                dependency.setVersion(version);
                FileWriter writer = new FileWriter(pomFile.getAbsolutePath());
                pomModel.addDependency(dependency);
                new MavenXpp3Writer().write(writer, pomModel);
                noDependencyAdded = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Unable to process pom.xml to add " + groupId + ":" + packageId + ":" + version);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            throw new Exception("Unable to process pom.xml to add " + groupId + ":" + packageId + ":" + version);
        }
        return noDependencyAdded;
    }

    @Override
    public String findNearestModulePomFile(Module module) {
        PsiFile[] pomLibFiles = FilenameIndex.getFilesByName(module.getProject(), "pom.xml", GlobalSearchScope.allScope(module.getProject()));
        String pomFilePath = null;
        for(PsiFile psiPom : pomLibFiles) {
            if(Objects.equals(psiPom.getParent().getVirtualFile().getPath(), module.getModuleFile().getParent().getPath())) {
                pomFilePath = psiPom.getVirtualFile().getPath();
            }
        }
        return pomFilePath;
    }
}
