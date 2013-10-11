import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.Scanner;
import java.awt.Color;

/**
 * Write a description of class Instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends Actor
{
    /**
     * Act - do whatever the Instructions wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Instructions()
    {
        InputStream istream =
            getClass().getResourceAsStream("instructions.txt");
        Scanner in = new Scanner(
                new BufferedReader(
                    new InputStreamReader(istream)));
        String text = "";
        while (in.hasNext()){
            text += in.nextLine() + "\n";
        }
        in.close();

        GreenfootImage label = new GreenfootImage(text,
                44, Color.WHITE, new Color(0, 0, 0, 120));
        setImage(label);
    }

    public void act() 
    {

        FBWorld fbworld = (FBWorld) getWorld();
        fbworld.removeObject(this);// Add your action code here.

    }
}