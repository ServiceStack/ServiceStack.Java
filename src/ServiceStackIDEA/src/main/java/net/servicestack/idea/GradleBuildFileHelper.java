package net.servicestack.idea;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Layoric on 2/04/2015.
 * Methods to help insert gradle dependency
 */
public class GradleBuildFileHelper {

    private static final String kotlinGroupName = "org.jetbrains.kotlin";

    public static boolean addDependency(Module module,String groupId, String packageName, String version) throws FileNotFoundException {
        File gradleFile = getGradleBuildFile(module);
        if(gradleFile == null) {
            return false;
        }
        Integer dependenciesStartIndex = -1;
        Integer dependenciesEndIndex = -1;
        List<String> list = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(gradleFile));
        try {
            int count = 0;
            for(String line; (line = br.readLine()) != null; ) {
                list.add(line);
                if(dependenciesStartIndex > -1 && line.startsWith("}") && dependenciesEndIndex == -1) {
                    dependenciesEndIndex = count;
                }
                if(dependenciesStartIndex == -1 && line.startsWith("dependencies {")) {
                    dependenciesStartIndex = count;
                }
                count++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dependenciesStartIndex == -1 || dependenciesEndIndex == -1) {
            return false;
        }

        boolean dependencyRequired = true;
        //Check if groupId + package already listed as dependency
        for (int i = dependenciesStartIndex; i < dependenciesEndIndex; i++) {
            String dependencyLoC = list.get(i);
            if(dependencyLoC.contains(groupId + ":" + packageName)) {
                dependencyRequired = false;
                break;
            }
        }

        if (!dependencyRequired) {
            return false;
        }
        list.add(dependenciesEndIndex, "    implementation '" + groupId + ":" + packageName + ":" + version + "'");
        try {
            PrintWriter writer = new PrintWriter(gradleFile);
            for(String item : list) {
                writer.println(item);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Boolean isGradleModule(Module module) {
        return getGradleBuildFile(module) != null;
    }

    public static Boolean isDartProject(Module module) {
        return getDartPubspec(module) != null;
    }

    public static Boolean isUsingKotlin(Module module){
        if (!isGradleModule(module)) {
            return false;
        }
        File buildFile = getGradleBuildFile(module);
        if (buildFile == null) {
            return false;
        }
        boolean result = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(buildFile));
            for (String line; (line = br.readLine()) != null; ) {
                if(line.contains(kotlinGroupName)) {
                    result = true;
                    break;
                }
            }
            br.close();
        }  catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static File getGradleBuildFile(Module module) {
        VirtualFile moduleFile = module.getModuleFile();
        if (moduleFile == null) {
            return null;
        }
        String moduleDirectory = moduleFile.getParent().getPath();
        File file = new File(moduleDirectory);
        File[] matchingFiles = file.listFiles((dir, name) -> name.startsWith("build.gradle"));
        return matchingFiles == null || matchingFiles.length == 0
            ? null
            : matchingFiles[0];
    }

    public static File getDartPubspec(Module module) {
        VirtualFile moduleFile = module.getModuleFile();
        if (moduleFile == null) {
            return null;
        }
        String moduleDirectory = moduleFile.getParent().getPath();
        File file = new File(moduleDirectory);
        File[] matchingFiles = file.listFiles((dir, name) -> name.startsWith("pubspec.yaml"));
        return matchingFiles == null || matchingFiles.length == 0
            ? null
            : matchingFiles[0];
    }
}
