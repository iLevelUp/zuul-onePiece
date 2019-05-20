package src.pkg_characters;
import src.pkg_items.*;
/**
* This class contains characters in the game which allow the player to talk 
* with characters. In case the player give an item to a characters if it's 
* the correct the characters give help to player
* @author Ghouibi Ghassen
* @version 3.0 (April 2019) 
*/
public class Characters{
	
	private String 	name;
	private String  printHi;
	private Item    item;
	private String  printHelp;
	
	//Constructor
	public Characters(String name,String printHi,String printHelp,Item item){
		this.name      =name;
		this.printHi   =printHi;
		this.printHelp =printHelp;
		this.item      =item;
	}
	//setName
	public void setName(String name) {
		this.name=name;
	}
	//getName
	public String getName() {
		return this.name;
	}
	//setHello
	public void setHello(String printHi){
		this.printHi=printHi;
	}
	//getHello
	public String getHello(){
		return this.printHi;
	}
	//setHelp
	public void setHelp(String printHelp){
		this.printHelp=printHelp;
	}
	//getHelp
	public String getHelp(){
		return this.printHelp;
	}
	//setItem
	public void setItem(Item item){
		this.item=item;
	}
	//getItem
	public Item getItem() {
		return this.item;
	}

}