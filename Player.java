import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int count = 2;
    private int slidecount = 55;
    private GreenfootSound explosion = new GreenfootSound("explosion.wav");
    public static final int DEFAULT_DURATION = 2;
    private int actCount = -1; // current act method count
    private int currImage = 0; // current image index
    private int imageDuration = DEFAULT_DURATION;
    private ArrayList<GreenfootImage> playerAnimList = new ArrayList<GreenfootImage>();
    private static GreenfootImage[] playerImages;
    private GreenfootImage standImage = new GreenfootImage("fallingman_0000.png");
    private Animation fall;
    private static final int ANIM_COUNT = 48;

    public static void initializeImages()
    {
        if (playerImages == null)
        {
            playerImages = new GreenfootImage[ANIM_COUNT];
            for (int i = 0; i < playerImages.length; i++)
            {
                String fileName = "fallingman_000" + (i + 1) + ".png";
                playerImages[i] = new GreenfootImage(fileName);
            }
        }
    }

    public Player()
    {
        initializeImages();
        fall = new Animation();
        fall.addImage(standImage);
        for (int i = 0; i < ANIM_COUNT; i++)
        {
            fall.addImage(playerImages[i]);
        }
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkKeyPress();
        moveAtLeftEdge();
        moveAtRightEdge();
        slideHoriz();
        randomTurn();
        fall.update();
        setImage(fall.getImage());
        addBlur();
        checkHit();
    }    

    public void slideHoriz()
    {
        if ( slidecount > 0 )
        {
            setLocation(getX(), getY()-2);
            slidecount--;
        }
        move(Greenfoot.getRandomNumber(11) - 5);
    }

    public void randomTurn()
    {
        if ( Greenfoot.getRandomNumber(50) < 1 ) {
            turn( Greenfoot.getRandomNumber(6)-3 ); }
    }

    public void moveAtLeftEdge()
    {
        World w = getWorld();
        if ( atWorldLeftEdge() )
        {
            setLocation(w.getWidth()-30,getY());
        }
    }

    public void moveAtRightEdge()
    {
        World w = getWorld();
        if ( atWorldRightEdge() )
        {
            setLocation(30,getY());
        }
    }

    public boolean atWorldLeftEdge()
    {
        World w = getWorld();
        if (getX() < 30 )
        {
            return true;
        }
        return false;
    }

    public boolean atWorldRightEdge()
    {
        World w = getWorld();
        if (getX() > w.getWidth() - 30)
        {
            return true;
        }
        return false;
    }

    /**
     * Check if a control key has been pressed and react.
     */
    public void checkKeyPress() {
        if (Greenfoot.isKeyDown("left"))
        {
            setRotation(5);
            setLocation(getX()-18,getY());
        }
        if (Greenfoot.isKeyDown("right"))
        {
            setRotation(0);
            move(18);
        }
        if (Greenfoot.isKeyDown("down"))
        {
            setRotation(5);
            setLocation(getX(),getY()+3);
        }
        if (Greenfoot.isKeyDown("up"))
        {
            setRotation(50);
            setLocation(getX(),getY()-3);
        }
    }

    private void addBlur()
    {
        count--;
        if (count == 0) {
            getWorld().addObject ( new MotionBlur(), getX(), getY());
            count = 2;
        }
    }

    private void checkHit()
    {
        //int range = 200;
        //List<Post> posts =
        //    getObjectsInRange(range, Post.class);
        Post posts = (Post) getOneIntersectingObject(Post.class);
        ProfileFooter finale =
            (ProfileFooter) getOneIntersectingObject(ProfileFooter.class);
        if (posts != null) {
            hit();
        }
        //for (Post p : posts)
        //{
        //    hit();
        //}
        if (finale != null) {
            win();
        }
    }

    public void hit()
    {
        World w = getWorld();
        w.removeObject(this);
        explosion.play();
        FBWorld fbworld = (FBWorld) w;
        fbworld.stopMusic();
        w.addObject(new Finale(), w.getWidth()/2, w.getHeight()/2);
        // Greenfoot.stop();
    }

    private void win()
    {
        World w = getWorld();
        w.removeObject(this);
        FBWorld fbworld = (FBWorld) w;
        w.addObject(new Finale("Congrats!"), w.getWidth()/2, w.getHeight()/2);
        Greenfoot.stop();
    }
}
