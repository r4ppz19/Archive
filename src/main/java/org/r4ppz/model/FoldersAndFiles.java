package org.r4ppz.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FoldersAndFiles {
    // Sample data: Folders and their files
    private static Map<String, List<String>> folderData = new HashMap<>();

    public static void addFolderData(String folderName, List<String> files) {
        folderData.put(folderName, files);
    }

    public static Set<String> getFolderName() {
        return folderData.keySet();
    }

    public static List<String> getFiles(String folderName) {
        return folderData.get(folderName);
    }
}
