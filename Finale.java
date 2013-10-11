import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

/**
 * The Finale is used to display the ending screen.
 */
public class Finale extends Actor
{
    public float FONT_SIZE = 48.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;
    private String lose = "Game Over!";
    private String again = "Click to try again.";
    /**
     * Create a score board for the final result.
     */
    public Finale()
    {
        makeImage(lose, again);
    }    
        public Finale(String congrats)
    {
        makeImage(congrats);
    }    

    /**
     * Make the winner's finale image.
     */
    private void makeImage(String title)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(255,255,255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        setImage(image);
    }
    
     /**
     * Make the "game over and try again" image.
     */
    private void makeImage(String title, String msg)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(255,255,255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        FONT_SIZE = 18.0f;
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.drawString(msg, 120,140);
        setImage(image);
    }

    /**
     * Restart the game when score board is clicked.
     */
    public void act()
    {
        if (Greenfoot.mousePressed(this))
        {
            FBWorld fbworld = (FBWorld) getWorld();
            fbworld.restartGame();
            fbworld.removeObject(this);
        }
    }
}
