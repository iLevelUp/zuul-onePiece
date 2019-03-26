
public class Player {
	private String Name;
	private Room currentRoom;
	//private Stack <Room> pPreviousRooms;
	private double totalWeight;

	public Player(final String pName, final double pMaxWeight) {
		this.Name = pName;
		//this.currentRoom = pcurrentRoom;
		this.totalWeight = pMaxWeight;
	}
	
	
	public void setCurrentRoom(final Room pCurrentRoom) {
		this.currentRoom = pCurrentRoom;
	}
	
	public void setMaxWeight(final double pWeight) {
		this.totalWeight = pWeight;
	
	}
	
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
	public String getName() {
		return this.Name;
	}
	public double getMaxWeight() {
		return this.totalWeight;
	}
}