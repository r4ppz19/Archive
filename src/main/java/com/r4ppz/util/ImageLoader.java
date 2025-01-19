package com.r4ppz.util;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

import javafx.scene.image.Image;

public class ImageLoader {

    private static ImageLoader instance;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    private final Map<String, Image> imageCache = new HashMap<>();
    /**
     * Loads an image from the specified path. If the image has been previously loaded and cached,
     * it retrieves the image from the cache. Otherwise, it loads the image from the given path,
     * caches it, and then returns the loaded image.
     *
     * @param imagePath the path to the image file to be loaded
     * @return the loaded Image object
     * @throws NullPointerException if the imagePath is null or the resource cannot be found
     */
    public Image loadImage(String imagePath) {
        if (imageCache.containsKey(imagePath)) {
            return imageCache.get(imagePath);
        }

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        imageCache.put(imagePath, image);

        return image;
    }
}
