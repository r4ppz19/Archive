package org.r4ppz.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

public class RefresherService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startRefreshing(Runnable refresherTask, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(() -> Platform.runLater(refresherTask), initialDelay, period, unit);
    }

    public void stopRefreshing() {
        scheduler.shutdown();
    }
}
