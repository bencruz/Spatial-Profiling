import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story extends Post
{
    private int count;
    private GreenfootImage[] storyImage = new GreenfootImage[13];
    //image1, image2, image3, image4, image5, image6, image7, image8,image 9, image10, image11, image12, image13;
    /**
     * Act - do whatever the Story wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Story()
    {
        this(0);
    }

    public Story(int i) 
    {
        String fileName = "story_" + i + ".png";
        storyImage[i] = new GreenfootImage(fileName);
        setImage(storyImage[i]);

    }
}
