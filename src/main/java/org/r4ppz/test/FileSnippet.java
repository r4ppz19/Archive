package org.r4ppz.test;

import java.io.File;

public class FileSnippet {
    public static void main(String[] args) {
        // Specify the parent folder path
        File parentFolder = new File("src/main/resources/org/r4ppz/uploads/");

        // Check if the path is a directory
        if (parentFolder.isDirectory()) {
            // List all files and directories inside the parent folder
            File[] files = parentFolder.listFiles();

            if (files != null) {
                System.out.println("Subfolders inside " + parentFolder.getName() + ":");
                for (File file : files) {
                    // Check if it is a directory
                    if (file.isDirectory()) {
                        System.out.println("- " + file.getName());
                    }
                }
            } else {
                System.out.println("The folder is empty or inaccessible.");
            }
        } else {
            System.out.println("The provided path is not a directory!");
        }
    }
}
