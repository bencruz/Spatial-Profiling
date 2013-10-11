import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MotionBlur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MotionBlur extends Actor
{
    private GreenfootImage image;
    private int fade;
    public static final int DEFAULT_DURATION = 2;
    private int actCount = -1; // current act method count
    private int currImage = 0; // current image index
    private int imageDuration = DEFAULT_DURATION;
    private ArrayList<GreenfootImage> blurAnimList = new ArrayList<GreenfootImage>();
    private static GreenfootImage[] blurImages;
    private GreenfootImage standImage = new GreenfootImage("fallingblur_0000.png");
    private Animation fall;
    private static final int ANIM_COUNT = 24;

    public static void initializeImages()
    {
        if (blurImages == null)
        {
            blurImages = new GreenfootImage[ANIM_COUNT];
            for (int i = 0; i < blurImages.length; i++)
            {
                String fileName = "fallingblur_000" + (i + 1) + ".png";
                blurImages[i] = new GreenfootImage(fileName);
            }
        }
    }

    public MotionBlur()
    {
        initializeImages();
        fall = new Animation();
        fall.addImage(standImage);
        for (int i = 0; i < ANIM_COUNT; i++)
        {
            fall.addImage(blurImages[i]);

        }
        //image = getImage();
        fade = Greenfoot.getRandomNumber(4) + 1;
        if (fade > 3) {
            fade = 2;
        }
    }

    /**
     * Act - do whatever the MotionBlur wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        fall.update();
        setImage(fall.getImage());
        shrink();
    }  

    public void shrink() {
        if(getImage().getWidth() < 130) {
            getWorld().removeObject(this);
        }
        else {
            GreenfootImage img = new GreenfootImage(standImage);
            img.scale ( getImage().getWidth()-fade, getImage().getHeight()-fade );
            img.setTransparency ( getImage().getTransparency() - (fade*20) );
            setImage (img);
        }
    }
}
