import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by dkool on 9/4/2018.
 */
public class Shirt {
    private String title;
    private String bullet1;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBullet1() {
        return bullet1;
    }

    public void setBullet1(String bullet1) {
        this.bullet1 = bullet1;
    }

    public String getBullet2() {
        return bullet2;
    }

    public void setBullet2(String bullet2) {
        this.bullet2 = bullet2;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    private String bullet2;
    private String tags;
    private BufferedImage image;


    public Shirt(String title, String bullet1, String bullet2, BufferedImage img) {
        this.title = title;
        this.bullet1 = bullet1;
        this.bullet2 = bullet2;

        BufferedImage resizedImage = new BufferedImage(400, 500, TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(img, 0, 0, 400, 500, null);
        g.dispose();

        this.image = resizedImage;
    }

    public String toString() {
        return "Title: " + this.title + "\n Bullet 1: " + this.bullet1
                + "\n Bullet 2: " + this.bullet2 + "\n Tags: " + this.tags + "\n";
    }
}
