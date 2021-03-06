package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GrowRes {

    //-----------------------------------------------------------------
    //                            COLORS
    //-----------------------------------------------------------------
    public final static Color GREEN = new Color(0x589a73);
    public final static Color WHITE = new Color(0xffffff);
    public final static Color GREY = new Color(0xabccb9);


    //-----------------------------------------------------------------
    //                            STRINGS
    //-----------------------------------------------------------------
    public final static String STR_SUCCES_BUTTON = "Quit  ";
    public final static String STR_FAILED_BUTTON = "Try again  ";
    public final static String STR_CONFIGURE_BUTTON = "Configure  ";
    public final static String STR_WAITING_LABEL = "<html><head><style type=\"text/css\">" +
            "body { font-family: \"Kontrapunkt Light\"; text-align:center; font-size:25; padding-left:15px; } </head><body >Please wait a little bit...</body></html>";
    public final static String STR_INTRO_LABEL = "<html><head><style type=\"text/css\">\n" +
            "body { font-family: \"Kontrapunkt Light\"; text-align:center; font-size:25; } </head><body><br>Hi! Welcome to the configuration panel!<br>Shall we start?<br></body></html>";

    public final static String STR_FAILED_LABEL = "<html><head><style type=\"text/css\">\n" +
            "body { font-family: \"Kontrapunkt Light\"; text-align:center; font-size:25; } </head><body>We're very sorry about that <br>but something went wrong while configuring your GrowStuff device!<br>...</body></html>";

    public final static String STR_SUCCES_LABEL = "<html><head><style type=\"text/css\">\n" +
            "body { font-family: \"Kontrapunkt Light\"; text-align:center; font-size:25; } </head><body>Yay ! You're ready to use you're GrowStuff device<br>Let's meet at you're dashboard:</body></html>";


    //-----------------------------------------------------------------
    //                            FONTS
    //-----------------------------------------------------------------
    public static Font mFontLight = null;
    public static Font mFontBold = null;


    public static Font getFont(int size, boolean bold) {
        try {
            if (bold) {
                if(mFontBold == null)
                    mFontBold = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/font_bold.ttf"));
                return mFontBold.deriveFont((float)size);
            } else {
                if(mFontLight == null)
                    mFontLight = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/font.ttf"));
                return mFontLight.deriveFont((float)size);
            }
        } catch (FontFormatException | IOException e) { }

        return null;
    }



    //-----------------------------------------------------------------
    //                            IMAGES
    //-----------------------------------------------------------------
    public static int LOGO= 0;
    public static int ARROW_GREEN = 1;
    public static int ARROW_WHITE = 2;
    public static int RELOAD = 3;
    public static int LOADING_1 = 4;
    public static int LOADING_2 = 5;
    public static int LOADING_3 = 6;
    public static int HIDE = 7;
    public static int SHOW = 8;
    public static int DROP = 9;
    public static int DROP_FOCUS = 10;

    private static BufferedImage mLogo = null;
    private static BufferedImage mArrowGreen = null;
    private static BufferedImage mArrowWhite = null;
    private static BufferedImage mReload = null;
    private static BufferedImage mHide = null;
    private static BufferedImage mShow = null;
    private static BufferedImage mLoading1 = null;
    private static BufferedImage mLoading2 = null;
    private static BufferedImage mLoading3 = null;
    private static BufferedImage mDrop = null;
    private static BufferedImage mDropFocus = null;

    private static boolean mLoaded = false;


    public static BufferedImage getImage(int imageID) {
        if (!mLoaded) {
            loadImages();
        }
        if (imageID == LOGO) {
            return mLogo;
        } else if (imageID == ARROW_GREEN) {
            return mArrowGreen;
        } else if (imageID == ARROW_WHITE) {
            return mArrowWhite;
        } else if (imageID == LOADING_1) {
            return mLoading1;
        } else if (imageID == LOADING_2) {
            return mLoading2;
        } else if (imageID == LOADING_3) {
            return mLoading3;
        } else if (imageID == HIDE) {
            return mHide;
        } else if (imageID == SHOW) {
            return mShow;
        } else if (imageID == RELOAD) {
            return mReload;
        } else if (imageID == DROP) {
            return mDrop;
        } else if (imageID == DROP_FOCUS) {
            return mDropFocus;
        }
        return null;
    }

    private static void loadImages() {
        mLoaded = true;
        try {
            mLogo = ImageIO.read(new File("res/growstuff_ico.png"));
            mArrowWhite = ImageIO.read(new File("res/arrow_ico_white.png"));
            mArrowGreen = ImageIO.read(new File("res/arrow_ico_green.png"));
            mReload = ImageIO.read(new File("res/reload_ico.png"));
            mLoading1 = ImageIO.read(new File("res/loading_1.png"));
            mLoading2 = ImageIO.read(new File("res/loading_2.png"));
            mLoading3 = ImageIO.read(new File("res/loading_3.png"));
            mShow = ImageIO.read(new File("res/show_ico.png"));
            mHide = ImageIO.read(new File("res/hide_ico.png"));
            mDrop = ImageIO.read(new File("res/drop_ico.png"));
            mDropFocus = ImageIO.read(new File("res/drop_focus_ico.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
