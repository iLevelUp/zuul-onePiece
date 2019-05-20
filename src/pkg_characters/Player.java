package src.pkg_characters;
import src.pkg_items.*;
import src.pkg_utils.*;
/**
* This class contains player in the game the player have a crew to help him to find key and
* affront enemies the player have a boat to carry stuff found in the area the purpose of the player 
* is to find one piece legend that's allows him to win the game he can also buy thing
* @author Ghouibi Ghassen
* @version 3.0 (April 2019) 
*/
public class Player {
	
	private String 						name;
	private int 						bagWeight;
	private Room   						location;
	private ItemList					bag;
	private int 						solde;
	private int 						crew;
	private int 						magicKeys;
	private int 						strength;
	private int 						life;
	
	//Constuctor
	public Player(String name,int weight,Room actualRoom,int solde,int crew,int magicKeys,int strength,int life){
		this.name=name;
		this.solde=solde;
		this.bagWeight=weight;
		this.location=actualRoom;
		this.crew=crew;
		this.magicKeys=magicKeys;
		this.strength=strength;
		this.life=life;
		bag=new ItemList();
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setWeight(int weight) {
		this.bagWeight=weight;
	}
	
	public void setLocation(Room actualRoom) {
		this.location=actualRoom;
	}
	public void setSolde(int solde) {
		this.solde=solde;
	}
	public int getSolde() {
		return this.solde;
	}
	public String getName() {
		return this.name;
	}

	public int getWeight() {
		return this.bagWeight;
	}
	
	public Room getLocation() {
		return this.location;
	}
	
	public int getTotalWeight(){
		return bag.getTotalWeight();
	}

	public void addItemToBag(Player name,Item item){
		if(checkWeight(name,item)){
			name.setWeight(name.getWeight()-item.getWeight());
			bag.addItem(item.getName(),item);
		}else {
			System.out.println("You can't It's too heavy");
		}
	}

	public boolean checkWeight(Player name,Item item) {
		if(name.getWeight()-item.getWeight()>=0) {
			return true;
		}
		return false;
	}

	public void removeItemFromBag(String name){
		setWeight(getWeight()+bag.getItemWeight(name));
		bag.removeItem(name);
	}
	
	public Item checkItemInTheBag(String name) {
    	return bag.checkItemInList(name);
  	}
	
	public int getCrewNumber(){
		return this.crew;
	}

	public void setCrewNumber(int number){
		this.crew=number;
	}

	public int getMagicKeys(){
		return this.magicKeys;
	}
	public void setMagicKeys(int number){
		this.magicKeys=number;
	}

	public int getStrength(){
		return this.strength;
	}
	
	public void setStrength(int strength){
		this.strength=strength;
	}

	public int getLife(){
		return this.life;
	}

	public void setLife(int life){
		this.life=life;
	}

	public String showMyBag() {
		return "Your bag contain :" +bag.getItemsDescription()+" Totalise "+ bag.getTotalWeight()+"/"+getWeight()+" Kg";
	}

}