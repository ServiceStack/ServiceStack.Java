import com.google.common.base.Joiner;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Layoric on 2/04/2015.
 */
public class GradleBuildFileHelper {

    private Module module;

    public GradleBuildFileHelper(Module module) {
        this.module = module;
    }

    public boolean addDependency(String groupId, String packageName, String version) {
        VirtualFile moduleFile = module.getModuleFile();
        if(moduleFile == null) {
            return false;
        }
        String moduleDirectory = moduleFile.getParent().getPath();
        File file = new File(moduleDirectory);
        File[] matchingFiles = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("build.gradle");
            }
        });
        if(matchingFiles == null || matchingFiles.length == 0) {
            return false;
        }

        File gradleFile = matchingFiles[0];
        Integer dependenciesStartIndex = -1;
        Integer dependenciesEndIndex = -1;
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(gradleFile))) {
            int count = 0;
            for(String line; (line = br.readLine()) != null; ) {
                list.add(line);
                if(dependenciesStartIndex > -1 && line.startsWith("}")) {
                    dependenciesEndIndex = count;
                    break;
                }
                if(line.startsWith("dependencies {")) {
                    dependenciesStartIndex = count;
                }

                count++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(dependenciesStartIndex == -1 || dependenciesEndIndex == -1) {
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

        if(!dependencyRequired) {
            return false;
        }
        list.add(dependenciesEndIndex, "    compile '" + groupId + ":" + packageName + ":" + version + "'");
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
}
