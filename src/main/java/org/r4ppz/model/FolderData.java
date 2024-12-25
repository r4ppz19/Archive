package org.r4ppz.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FolderData {
    private final Map<String, List<String>> folderData = new HashMap<>();
    private static FolderData instance;

    private FolderData() {}

    public static FolderData getInstance() {
        if (instance == null) {
            instance = new FolderData();
        }
        return instance;
    }

    public void addFolderData(String folderName, List<String> files) {
        folderData.put(folderName, files);
    }

    public Set<String> getFolderName() {
        return folderData.keySet();
    }

    public List<String> getFile(String folderName) {
        return folderData.get(folderName);
    }
}
