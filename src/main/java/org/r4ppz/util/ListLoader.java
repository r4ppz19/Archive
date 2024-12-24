package org.r4ppz.util;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getFoldersToList(Path folderPath) {
        List<String> folders = new ArrayList<String>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
             for (Path content : stream) {
                if (Files.isDirectory(content)) {
                    String validFolder = content.getFileName().toString();
                    folders.add(validFolder);
                }
             }
        } catch (Exception e) {
            System.out.println("getFoldersToList Exception");
        }

        return folders;
    }
}

