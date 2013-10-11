import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ProfileHeader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProfileHeader extends Actor
{
    private int headerMove = 300;
    /**
     * Act - do whatever the ProfileHeader wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

        move();
    }   

    public void move()
    {
        if (headerMove > 0)
        {
        setLocation(getX(),getY() - 4);
        headerMove--;
    }
    }
}
