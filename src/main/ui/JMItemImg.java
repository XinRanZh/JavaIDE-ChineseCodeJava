package ui;

import javax.swing.*;
import java.awt.*;

public class JMItemImg extends JMenuItem {
    private final Image image;

    public JMItemImg(String title, Image image) {
        super(title);
        this.image = image;
    }

    public Insets getInsets() {
        Insets insets = (Insets)super.getInsets().clone();
        insets.left = insets.left + image.getWidth(null);
        insets.top = insets.top + image.getHeight(null);
        return insets;
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (!(image == null)) {
            graphics.drawImage(image,0,0,null);
        }

    }
}
