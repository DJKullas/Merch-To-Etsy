import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by dkool on 9/10/2018.
 */
public class Printful {

    private ArrayList<Shirt> shirts = new ArrayList<Shirt>();
    private JFrame frame = new JFrame();
    private int count = 0;
    JButton next = new JButton("Next");
    JButton back = new JButton("Back");
    JTextField tags = new JTextField("", 50);
    ArrayList<String> tagsText = new ArrayList<String>();

    Printful() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/SHIRT_ART_IMAGE.png"));
        } catch (IOException e) {
        }

        Shirt s1 = new Shirt("Spaghetti Code", "bullet 1 test length", "bullet 2",
                img);

        img = null;
        try {
            img = ImageIO.read(new File("src/main/SHIRT_ART_IMAGE1.png"));
        } catch (IOException e) {
        }

        Shirt s2 = new Shirt("Locks", "bullet 1 locks", "bullet locks",
                img);

        shirts.add(s1);
        shirts.add(s2);
        shirts.add(s1);
    }

    Printful(ArrayList<Shirt> shirts) {
        this.shirts = shirts;
    }

    void inputTags() {


        shirtTagHelper();

        next.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                count += 1;
                shirts.get(count - 1).setTags(tags.getText());
                System.out.println(tags.getText());
                shirtTagHelper();
            }
        });

        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                count -= 1;
                shirtTagHelper();
            }
        });
    }

    void shirtTagHelper() {
        tags.setText(shirts.get(count).getTags());
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setTitle(shirts.get(count).getTitle());
        frame.add(tags, BorderLayout.SOUTH);
        frame.getContentPane().add(new JLabel(shirts.get(count).getBullet1() + "  " + shirts.get(count).getBullet2()), BorderLayout.CENTER);
        frame.getContentPane().add(new JLabel(new ImageIcon(shirts.get(count).getImage())), BorderLayout.NORTH);
        frame.add(next, BorderLayout.EAST);
        frame.add(back, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
    }

}
