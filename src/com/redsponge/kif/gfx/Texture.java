package com.redsponge.kif.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Texture {

    private BufferedImage img;
    private static final HashMap<String, BufferedImage> images = new HashMap<>();

    public Texture(String path) {
        load(path);
    }

    private void load(String path) {
        if(images.get(path) != null) img = images.get(path);
        else {
            try {
                img = ImageIO.read(getClass().getResourceAsStream(path));
                images.put(path, img);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage getImg() {
        return img;
    }
}
