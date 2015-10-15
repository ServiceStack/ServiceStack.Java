package net.servicestack.eclipse.maven;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
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
        boolean dependencyAdded = false;
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
            
            String content = null;
            if(requiresPomDependency) {
                Dependency dependency = new Dependency();
                dependency.setGroupId(groupId);
                dependency.setArtifactId(packageId);
                dependency.setVersion(version);
                pomModel.addDependency(dependency);
                StringWriter strWriter = new StringWriter();
                new MavenXpp3Writer().write(strWriter, pomModel);
                content = strWriter.toString();
                InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
                pomFile.setContents(stream, false, false, null);
                dependencyAdded = true;
            }

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            throw new Exception("Unable to process pom.xml to add " + groupId + ":" + packageId + ":" + version);
        }
        return dependencyAdded;
    }
}
