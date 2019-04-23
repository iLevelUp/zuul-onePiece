package src;
/**
* This class contains characters in the game which allow the player to talk 
* with characters. In case the player give an item to a characters if it's 
* the correct the characters give help to player
* @author Ghouibi Ghassen
* @version 3.0 (April 2019) 
*/
public class Enemy extends Characters{
	
	private int     strength;
	//Constructor
	public Enemy(String name,String printHi,String printHelp,Item item,int strength){
        super(name, printHi, printHelp, item);
        this.strength=strength;
    }
    //get Strength
    public int getStrength(){
        return this.strength;
    }
    //set Strength
    public void setStrength(int strength){
        this.strength=strength;
    }
}