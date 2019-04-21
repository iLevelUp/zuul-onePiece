package src;


public class Characters {
	
	private String 	name;
	private String  printHi;
	private Item    item;
	private String  printHelp;
	
	public Characters(String name,String printHi,String printHelp,Item item){
		this.name=name;
		this.printHi=printHi;
		this.printHelp=printHelp;
		this.item=item;
		
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setHello(String printHi) {
		this.printHi=printHi;
	}
	
	public String getHello(){
		return this.printHi;
	}

	public void setHelp(String printHelp) {
		this.printHelp=printHelp;
	}
	
	public String getHelp() {
		return this.printHelp;
	}
	
	public void setItem(Item item) {
		this.item=item;
	}
	
	public Item getItem() {
		return this.item;
	}
	
}