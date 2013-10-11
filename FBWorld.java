import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;

/**
 * Write a description of class FBWorld here.
 * 
 * @author Ben Cruz
 * @version User-testable prototype
 */
public class FBWorld extends World
{
    private Color babyblue = new Color(231,234,241);
    private Color darkblue = new Color(196,203,221);
    private int width = 100;
    private int height = 600;
    public static final int SCROLL_SPEED = 6;
    private static final int TILE_WIDTH = 3120;
    private static final int TILE_HEIGHT = 9980;
    private static final int RESET_DELAY = 930;
    private List<Actor> scrollList;
    private int leftX = TILE_WIDTH / 2; // leftmost x coordinate for a tile
    private int topY = -TILE_HEIGHT;    // highest y coordinate for a tile
    private GreenfootSound bgmusic = new GreenfootSound("howling_crystal.mp3");
    private int storyNum = 0;

    /**
     * Constructor for objects of class FBWorld.
     * 
     */
    public FBWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 640, 1, false);
        setup();
    }

    public void setup()
    {   
        getBackground().setColor(babyblue); // set drawing color for timeline background
        getBackground().fill();
        drawTimeline();
        addObject(new ProfileHeader(), getWidth()/2, 310);
        addObject(new Player(), 190,275);
        addObject(new ProfileFooter(), 530, 9800);
        int i = 0;
        while (i < 13)
        {
            storyNum = i;
            Story st = new Story(i);
            if ((i+2) % 2 == 1)
            {
                addObject(st,272,Greenfoot.getRandomNumber(40) + 2200 + (540*i));
            }
            else
            {
                addObject(st,689,Greenfoot.getRandomNumber(30)+4000 + (1340*i));
            }
            i++; }
        addPosts();addObject(new Instructions(),getWidth()/2,getHeight()/2);
        scrollList = getObjects(Post.class); // Save the list of background objects
    }

    private void drawTimeline()
    {
        for (int i = 0; i < 80; i++)
        {
            addObject(new TimelineBar(), getWidth()/2 - 3, 27 * i);
        }
    }

    public void addPosts() 
    {
        PostFriends postfriends = new PostFriends();
        addObject(postfriends, 689, 770);
        PostEvents postevents = new PostEvents();
        addObject(postevents,689, 1780);
        PostLikes postlikes = new PostLikes();
        addObject(postlikes, 272, 1100);
        PostLikes postlikes2 = new PostLikes();
        addObject(postlikes2, 272, 2040);
    }

    /**
     * Scroll the background of the world.
     */
    public void act() 
    {
        for (Actor a : scrollList) {
            int x = a.getX();
            int y;
            if (a.getX() < 400 ) {
                y = a.getY() - (SCROLL_SPEED - 1);
            }
            else {
                y = a.getY() - SCROLL_SPEED;
            }
            if (y > getHeight() + TILE_HEIGHT - 1) {
                y = topY;
            }
            a.setLocation(x, y);
        }
        bgmusic.playLoop();
    }

    public void stopMusic()
    {
        bgmusic.stop();
    }

    /**
     * This method restarts the game.
     */
    public void restartGame()
    {
        List actors = getObjects(Actor.class);
        removeObjects(actors);
        setup();
    }
}
