import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A class that represents a sequence of images.
 *
 * @author Ed Parrish
 * @version 1.6 05/04/12
 */
public class Animation
{
    public static final int DEFAULT_DURATION = 5;
    private ArrayList<GreenfootImage> images; // image sequence
    private int actCount = -1;  // current act method count
    private int currImage = 0;  // current frame index
    private int imageDuration = DEFAULT_DURATION; // per image count

    /**
     * Construct an empty animation.
     */
    public Animation()
    {
        images = new ArrayList<GreenfootImage>();
    }

    /**
     * Add a new image frame to this Animation.
     *
     * @param img The image to display
     */
    public void addImage(GreenfootImage img)
    {
        images.add(img);
    }

    /**
     * Gets the current image in the sequence.
     *
     * @return Current image or null if there are no images.
     */
    public GreenfootImage getImage()
    {
        if (images.size() == 0)
        {
            return null;
        }
        else
        {
            return (images.get(currImage));
        }
    }

    /**
     * Decide which image to display.
     */
    public void update()
    {
        if (images.size() > 1)
        {
            actCount++;
            if (actCount >= images.size() * imageDuration)
            {
                actCount = 0;
            }
        }
        // Find the current image frame to display
        currImage = actCount / imageDuration;
    }

    /**
     * Returns true when an animation has finished.
     *
     * @return true when finished; othersie returns false.
     */
    public boolean isFinished()
    {
        if (actCount == images.size() * imageDuration - 1)
        {
            return true;
        }
        return false;
    }

    /**
     * Sets the current duration that each image is displayed.
     *
     * @param perImageCount Number of counts that every image is displayed.
     * The value must be greater than zero.
     */
    public void setImageDuration(int perImageCount)
    {
        if (perImageCount > 0)
        {
            imageDuration = perImageCount;
        }
    }

    /**
     * Returns the number of update() calls that each image is displayed.
     *
     * @return Number of counts that every image is displayed.
     */
    public int getImageDuration()
    {
        return imageDuration;
    }

    /**
     * Returns the number of images displayed for this Animation.
     *
     * @return Number of images displayed for this Animation.
     */
    public int getImageCount()
    {
        return images.size();
    }

    /**
     * Cause the image sequence to start over.
     */
    public void reset()
    {
        actCount = -1;
    }

    /**
     * Returns the interesting data about this Animation for debugging
     * purposes.
     *
     * @return The interesting data about this Animation.
     */
    public String toString()
    {
        String data = "";
        for (GreenfootImage img : images)
        {
            data += img + "\n";
        }
        return data;
    }
}
