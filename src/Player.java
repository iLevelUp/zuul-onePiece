package src;


public class Player {
	
	private String 						name;
	private int 						bagWeight;
	private Room   						location;
	private ItemList					bag;
	private int 						solde;
	
	public Player(String name,int weight,Room actualRoom,int solde){
		this.name=name;
		this.solde=solde;
		this.bagWeight=weight;
		this.location=actualRoom;
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
	public void addItemToBag(String name,Item item){
		System.out.println(item.getWeight()+" "+getWeight()+" "+(getWeight()-item.getWeight()));
		if(checkWeight(name,item)){
			setWeight(getWeight()-item.getWeight());
			bag.addItem(name,item);
		}else {
			System.out.println("You can't It's too heavy");
		}
	}
	public boolean checkWeight(String name,Item item) {
		if(getWeight()-item.getWeight()>=0) {
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
	
	public String showMyBag() {
		return "Your bag contain :" +bag.getItemsDescription()+" Totalise "+ bag.getTotalWeight()+"/"+getWeight()+" Kg";
  }
	
}