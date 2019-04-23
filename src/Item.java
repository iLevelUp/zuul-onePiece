package src;
/**
* This class is part of "World of Zuul". "World of Zuul" is a simple, 
* adventure game.
* This Item take a description,weight and a price which can help
* you during the game
* @author  Ghouibi Ghassen
* @version 2.0 (March 2019)
*/
public class Item {
	
	//hold the Item 
	private String name;
	private String description;
	private int weight;
	private int price;
	
	//Create a new Item
	public Item(String name,String description, int weight,int price) {
		this.name=name;
		this.description=description;
		this.weight=weight;
		this.price=price;
	}

	//set item Name
	public void setName(String name) {
		this.name=name;
	}
	//get item Name
	public String getName() {
		return this.name;
	}
	//set item Description
	public void setDescription(String description) {
		this.description = description;
	}
	//get item Description
	public String getDescription() {
		return this.description;
	}
	//set item price
	public void setPrice(int price) {
		this.price = price;
	}
	//get item price
	public int getPrice() {
		return this.price;
	}
	
	//set item weight
	public void setWeight(int price) {
		this.weight = price;
	}
	
	//get item weight
	public int getWeight() {
		return this.weight;
	}
}
