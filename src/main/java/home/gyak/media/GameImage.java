package home.gyak.media;

import home.gyak.resource_handling.Locator;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameImage {
    Locator locator = new Locator();

    //Menu icons
    public ImageIcon xIcon;
    public ImageIcon checkIcon;

    public ImageIcon menuImage;
    //GameOver icons
    public ImageIcon catIcon;
    public ImageIcon mbdSlide;
    public ImageIcon mbdFlyout;
    public ImageIcon mbdCockbite;
    public ImageIcon justDoIt;

    public ImageIcon getCheckIcon() {
        URL url = locator.getUrl("/checkIcon.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        checkIcon = new ImageIcon(img);
        return checkIcon;
    }

    public ImageIcon getXIcon() {
        URL url = locator.getUrl("/xIcon.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        xIcon = new ImageIcon(img);
        return xIcon;
    }

    public ImageIcon getCatIcon() {
        URL url = locator.getUrl("/cat.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        catIcon = new ImageIcon(img);
        return catIcon;
    }

    public ImageIcon getMenuImageIcon() {
        URL url = locator.getUrl("/menuImg.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        menuImage = new ImageIcon(img);
        return menuImage;
    }

    public ImageIcon getMenuImageIconFull() {
        URL url = locator.getUrl("/menuImgFull.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        menuImage = new ImageIcon(img);
        return menuImage;
    }

    //Game over images
    public ImageIcon getMbdSlide() {
        URL url = locator.getUrl("/mbfSlide.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        mbdSlide = new ImageIcon(img);
        return mbdSlide;
    }

    public ImageIcon getMbdFlyout() {
        URL url = locator.getUrl("/mbfFlyOut.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        mbdFlyout = new ImageIcon(img);
        return mbdFlyout;
    }

    public ImageIcon getMbdCockbite() {
        URL url = locator.getUrl("/mbfCockBite.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        mbdCockbite = new ImageIcon(img);
        return mbdCockbite;
    }

    public ImageIcon getJustDoIt() {
        URL url = locator.getUrl("/justdoit.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        justDoIt = new ImageIcon(img);
        return justDoIt;
    }
}