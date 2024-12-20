package org.r4ppz.model;

import java.io.File;
public class TestMethod {
        public static void main(String[] args) {
            loadFolderToButtons();
        }

        private static void loadFolderToButtons() {
        File mainFolder = new File("src/main/resources/org/r4ppz/uploads/");

        if (mainFolder.isDirectory()) {
            File[] filesInside = mainFolder.listFiles();
            if (filesInside != null) {
                for (File files : filesInside) {
                    if (files.isDirectory()) {
                        

                        System.out.println(files.getName());
                    }
                }
            } else {
                System.out.println("Files inside is null");
            }
        } else {
            System.out.println("Main Folder is null");
        }
    }
}
