package org.r4ppz.util;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListLoader {

    public static List<String> getFilesTolist(Path folderPath) {
        List<String> fileList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                fileList.add(fileName);
            }
        } catch (Exception e) {
            System.out.println("I DONT FUCKING KNOW");
        }

        return fileList;
    }

    public static Map<String, List<String>> getFolderToFileMap(Path rootPath) {
        Map<String, List<String>> folderToFileMap = new HashMap<>();
    
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(rootPath)) {
            for (Path folder : stream) {
                if (Files.isDirectory(folder)) {
                    String folderName = folder.getFileName().toString();
                    List<String> files = new ArrayList<>();
                    try (DirectoryStream<Path> fileStream = Files.newDirectoryStream(folder)) {
                        for (Path file : fileStream) {
                            if (!Files.isDirectory(file)) { // Ensure it's a file
                                files.add(file.getFileName().toString());
                            }
                        }
                    }
                    folderToFileMap.put(folderName, files); // Map folder to its files
                }
            }
        } catch (Exception e) {
            System.err.println("Error in getFolderToFileMap: " + e.getMessage());
            e.printStackTrace();
        }
    
        return folderToFileMap;
    }
    
}

