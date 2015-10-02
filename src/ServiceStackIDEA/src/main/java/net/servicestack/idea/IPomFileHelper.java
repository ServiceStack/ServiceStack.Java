package net.servicestack.idea;

import com.intellij.openapi.module.Module;

import java.io.File;

/**
 * Created by Layoric on 10/05/2015.
 */
public interface IPomFileHelper {
    boolean addMavenDependencyIfRequired(File pomFile, String groupId, String packageId, String version) throws Exception;
    String findNearestModulePomFile(Module module);
}
