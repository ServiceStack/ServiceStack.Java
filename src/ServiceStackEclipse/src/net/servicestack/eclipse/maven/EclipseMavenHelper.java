package net.servicestack.eclipse.maven;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.core.resources.IFile;

public class EclipseMavenHelper {
	public boolean addMavenDependencyIfRequired(IFile pomFile, String groupId, String packageId, String version) throws Exception {
        boolean noDependencyAdded = true;
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model pomModel;
        try {
            pomModel = reader.read(new FileReader(new File(pomFile.getLocationURI())));
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
                FileWriter writer = new FileWriter(pomFile.getLocationURI().getPath());
                pomModel.addDependency(dependency);
                new MavenXpp3Writer().write(writer, pomModel);
                noDependencyAdded = false;
            }

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            throw new Exception("Unable to process pom.xml to add " + groupId + ":" + packageId + ":" + version);
        }
        return noDependencyAdded;
    }
}
