package org.r4ppz.test;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestMethod {
    public static void main(String[] args) {
        loadFolderToButtons();
        scheduledExecutorServiceTest();
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

    private static void scheduledExecutorServiceTest() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule a task to run every 5 seconds
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Hello, World!");
        }, 0, 5, TimeUnit.SECONDS);
    }
}
