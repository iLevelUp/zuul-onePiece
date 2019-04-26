package src;
/**
* This class contains Enemies in the game which try to kill the player  
* The player must be stronger to kill them in the final room he have to kill 
* the enemy to pick up the coin named "One Piece"
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