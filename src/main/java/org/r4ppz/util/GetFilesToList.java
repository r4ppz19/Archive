package org.r4ppz.util;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GetFilesToList {

    public static List<String> getFilesTolist(Path uploadsDirectory) {
        List<String> fileList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadsDirectory)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                fileList.add(fileName);
            }
        } catch (Exception e) {
            System.out.println("I DONT FUCKING KNOW");
        }

        return fileList;
    }
}

