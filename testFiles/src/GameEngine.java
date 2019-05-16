/**
*  This class is part of the "World of Zuul" application. 
*  "World of Zuul" is a very simple, text based adventure game.
* 
*  This class creates all rooms, creates the parser and starts
*  the game.  It also evaluates and executes the commands that 
*  the parser returns.
* 
* @author  Ghouibi Ghassen
* @version 3.0 (April 2019)
*/
package src;

import java.util.Stack;
import java.time.LocalTime;
import java.io.*;

public class GameEngine {

    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;
    private Stack<Room> displacement;
    private Player player;
    private Room beamerCharged;
    private Scenario scenario;
    private Save save;

    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() {
        parser = new Parser();
        displacement = new Stack<Room>();
        player = new Player("sangoku", 250, currentRoom, 50, 50, 4, 800, 3);
        scenario = new Scenario();
        currentRoom = scenario.getStartRoom();
        save = new Save();
    }

    /**
     * Set the user Interface
     */
    public void setGUI(UserInterface userInterface) {
        gui = userInterface;
    }

    /***
     * The play function that allow to get real time the player will have 20 minute
     * to win or he will die the crew also die every 1 minutes if he hav'nt
     * resources he must eat
     * 
     * @param userInterface
     */
    public void play(UserInterface userInterface) {
        setGUI(userInterface);
        printWelcome();
        int finish = 0;
        // Maybe giving some extra time
        LocalTime time = LocalTime.now();
        LocalTime localTime3 = time.plusMinutes(10);
        LocalTime breakfeastTime = time.plusMinutes(1);

        do {
            LocalTime currentTime = LocalTime.now();
            gui.sound("src/music/Pirate.wav");
            if (currentTime.getMinute() == localTime3.getMinute()) {
                endGame();
                finish = 1;
            }
            if (currentTime.getMinute() == breakfeastTime.getMinute()) {
                player.setStrength(player.getStrength() - 10);
                gui.setStrength(player.getStrength());
                breakfeastTime = currentTime.plusMinutes(1);
            }

            if (player.getStrength() <= 0 || player.getLife() == 0) {
                endGame();
                finish = 1;
            }
            if (player.checkItemInTheBag("OnePiece") != null) {
                winGame();
                finish = 1;
            }
        } while (finish != 1);
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        gui.print("\n");
        gui.println("Welcome to One Piece treasure cruise!");
        gui.println("One Piece is a Fantastic adventure game.");
        gui.println("You must find ou 4 key to get to the final room and kill the boss\nif you pick up the coin you'll win");
        gui.println("Type 'help' if you need help.");
        gui.println(currentRoom.getLongDescription());
        gui.showImage(currentRoom.getImageName());

    }

    /**
     * Given a command, process (that is: execute) the command. If this command ends
     * the game, true is returned, otherwise false is returned.
     */
    public void interpretCommand(Command commandLine) {
        CommandWord commandWord = commandLine.getCommandWord();

        save.save(commandWord.toString() + " " + commandLine.getSecondWord());

        switch (commandWord) {

        case HELP:
            printHelp();
            break;
        case GO:
            goRoom(commandLine);
            break;
        case BACK:
            backRoom();
            break;
        case EAT:
            eat(commandLine);
            break;
        case LOOK:
            look();
            break;
        case TEST:
            testFile(commandLine, "testFiles/");
            break;
        case TAKE:
            take(commandLine);
            break;
        case CHECK:
            check();
            break;
        case DROP:
            drop(commandLine);
            break;
        case PAY:
            pay();
            break;
        case OPEN:
            openRoom();
            break;
        case HIRE:
            hire();
            break;
        case CHARGE:
            charge();
            break;
        case FIRE:
            fire();
            break;
        case TALK:
            talk();
            break;
        case ATTACK:
            attack(commandLine);
            break;
        case GIVE:
            give(commandLine);
            break;
        case SAVE:
            if (commandLine.getSecondWord() == null)
                gui.print("<usage> you must write a file name");
            else
                save.rename(commandLine.getSecondWord());
            break;
        case RECOVER:
            testFile(commandLine, "savedGames/");
            break;

        case QUIT:
            if (commandLine.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
            break;
        default:
            gui.println("I don't know what you mean...");
            break;
        }

    }

    /**
     * this function attack enemies if you kill enemies you can pick up stuff to eat
     * or other
     * 
     * @param command
     */
    private void attack(Command command) {

        if (!command.hasSecondWord()) {
            gui.print("attack Who ?");
            return;
        }
        String name = command.getSecondWord();
        if ((currentRoom.checkEnemiesInTheRoom(name)).getStrength() < player.getStrength()) {
            currentRoom.addItems(currentRoom.checkEnemiesInTheRoom(name).getItem().getName(),
                    currentRoom.checkEnemiesInTheRoom(name).getItem());
            currentRoom.removeEnemy(currentRoom.checkEnemiesInTheRoom(name).getName());
            gui.println("Enemy killed");
        } else {
            if (player.getCrewNumber() - 10 <= 0) {
                player.setLife(player.getLife() - 1);
                gui.setLife(player.getLife());
            } else {
                player.setCrewNumber(player.getCrewNumber() - 10);
                gui.setCrew(player.getCrewNumber());
                gui.println("Enemy kill some of your crew\n");
            }
        }
    }

    /**
     * Get you back to the room just before
     */
    private void backRoom() {
        if (displacement.isEmpty()) {
            gui.println("You are at the start Point");
            gui.setButtonColor(currentRoom.getExitButton());
        } else {
            currentRoom = displacement.pop();
            gui.println("You back to " + currentRoom.getDescription());
            gui.println("You Maybe Missed those " + currentRoom.getItemsDescription());
            gui.setButtonColor(currentRoom.getExitButton());
            if (currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }

    /**
     * This function charge your beamer that allow's you to remeber this room
     */
    private void charge() {
        if (player.checkItemInTheBag("beamer") != null) {
            if (player.checkItemInTheBag("ammo") != null) {
                player.removeItemFromBag("ammo");
                beamerCharged = currentRoom;
            } else {
                gui.println("You are OUT OF AMMO");
            }
        } else {
            gui.println("You must have a beamer first");
        }
    }

    /**
     * Print out some help information. Here we print some stupid, cryptic message
     * and a list of the command words.
     */
    private void printHelp() {
        gui.println("You are lost. You are alone. You wander");
        gui.print(parser.showCommands());
    }

    /**
     * Tests for the game with files this function will excute all command present
     * in the file line by line
     * 
     * @param Command the command enter by th user
     */
    private void testFile(Command command, String path) {
        if (!command.hasSecondWord()) {
            gui.println("\n<usage> you have to put a file name ");
            return;
        }
        String line = null;
        String fileName = path + command.getSecondWord();
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((line = bufferedReader.readLine()) != null) {
                interpretCommand(parser.getCommand(line));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
         
            }  
            bufferedReader.close();         
        }
        catch(FileNotFoundException e) {
            gui.println("Unable to open file >" + fileName);                
        }
        catch(IOException e) {
        	gui.println("Error reading file >" + fileName);
        }
    	
    }
    /** 
    * Try to go to one direction. If there is an exit, enter the new
    * room, otherwise print an error message.
    */
    private void goRoom(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        displacement.push(currentRoom);
        if (nextRoom == null)
            gui.println("There is no door!");
        else {
        	player.setLocation(nextRoom);
            currentRoom = nextRoom;
            gui.println(currentRoom.getLongDescription());
            
            gui.setButtonColor(currentRoom.getExitButton());
            gui.setTitle(currentRoom.getName());
            gui.showImage(currentRoom.getImageName());
            if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }
    
    
    /**
    * This function allow to the player to pick up things in the room 
    * @param Command enter by the user 
    */
    private void take(Command command){
    	if(!command.hasSecondWord()) {
    		gui.print("take What ?");
    		return;
    	}
        String newItem=command.getSecondWord();
        if(currentRoom.checkItemInTheRoom(newItem)!=null){
        	if(player.checkWeight(player, currentRoom.checkItemInTheRoom(newItem))){
                if(currentRoom.checkItemInTheRoom(newItem).getName()=="money"){
                    player.setSolde(currentRoom.checkItemInTheRoom(newItem).getPrice()+player.getSolde());
                    gui.setSolde(player.getSolde());
                    gui.print("You are rich !");
                }
                else if(currentRoom.checkItemInTheRoom(newItem).getName()=="magicKey"){
                    player.setMagicKeys(player.getMagicKeys()+1);
                    gui.setKeys(player.getMagicKeys());
                    currentRoom.removeItems(newItem);
                    gui.println("Good find out other key");
                }
                else{
        		    player.addItemToBag(player, currentRoom.checkItemInTheRoom(newItem));
                    currentRoom.removeItems(newItem);
                    gui.setBagContain(player.getTotalWeight(),player.getWeight()+player.getTotalWeight());
                    gui.println("You just took a "+newItem);
                }

        	}else {
                gui.print("You cannot pick up this item it's too heavy");
                gui.print("\n");
        	}
        }else{
            gui.print("You cannot carry this item maybe does'nt exist");
            gui.print("\n");
        }    	
    }
    /**
    * This function allow to the player to drop things from his bag in the acual room 
    * @param command enter the user
    */
    private void drop(Command command) {
    	if(!command.hasSecondWord()) {
    		gui.print("Drop What ?");
    		return;
    	}
    	String dropItem=command.getSecondWord();
        if(player.checkItemInTheBag(dropItem)!=null){
        	currentRoom.addItems(dropItem, player.checkItemInTheBag(dropItem));
            player.removeItemFromBag(dropItem);
            gui.setBagContain(player.getTotalWeight(),player.getWeight()+player.getTotalWeight());
            gui.println("You dropped "+dropItem);
        }
        else{
            gui.print("Item does'nt present in your bag");
            gui.print("\n");
        }

    }
    /**
    * This function give characters money or stuff to help you 
    * @param command
    */
    private void give(Command command){
        if(!command.hasSecondWord()){
            gui.print("Give What ?");
            return;
        }
        String givenItem=command.getSecondWord();
        if(currentRoom.checkCharatersInTheRoom("wanoKuni")!=null){
            if(player.getSolde()- currentRoom.checkCharatersInTheRoom("wanoKuni").getItem().getPrice() >0){
                player.setSolde(player.getSolde()- currentRoom.checkCharatersInTheRoom("wanoKuni").getItem().getPrice());
                gui.setSolde(player.getSolde());
                gui.println(currentRoom.checkCharatersInTheRoom("wanoKuni").getHelp());
            }else{
                gui.println("You don't have enough money Sorry");
            }
        }
        else if(currentRoom.checkCharatersInTheRoom("pontDuJoie")!=null){
            if(player.getSolde()- currentRoom.checkCharatersInTheRoom("pontDuJoie").getItem().getPrice() >0){
                player.setSolde(player.getSolde()- currentRoom.checkCharatersInTheRoom("pontDuJoie").getItem().getPrice());
                gui.setSolde(player.getSolde());
                gui.println(currentRoom.checkCharatersInTheRoom("pontDuJoie").getHelp());
            }else{
                gui.println("You don't have enough money Sorry");
            }
        }
        else{
            if(player.checkItemInTheBag(givenItem)!=null){
                gui.print(currentRoom.giveCharactersItem(player.checkItemInTheBag(givenItem)));
                player.removeItemFromBag(givenItem);
                gui.setBagContain(player.getTotalWeight(),player.getWeight()+player.getTotalWeight());
            }
            else{
                gui.print("You don't have this item in your bag !");
            }
        }
    }
    /**
    * Print goodbye and enable the entry field
    */
    private void endGame() {
        save.clearFile();
        currentRoom.setImageName("src/images/lose.jpg");
        gui.showImage(currentRoom.getImageName());
		gui.println("Youu Loose !\nThank you for playing.  Good bye !");
        gui.enable(false);
    }
    /**
    * Print wining case and enable the entry fields 
    */
    private void winGame() {
        save.clearFile();
        currentRoom.setImageName("src/images/win.jpg");
        gui.showImage(currentRoom.getImageName());
		gui.println("Youu WINNNNNNNN!\nThank you for playing.  Good bye !");
        gui.enable(false);
    }

    /**
    * Print the long description of the room
    */
    private void look(){
        gui.print(currentRoom.getLongDescription());
        gui.print("\n");
    }
    /**
    * This function print the things present in the bag
    */
    private void check(){
        gui.print(player.showMyBag());
        gui.print("\n");
    }
    /**
    * This function hire new crew to your ship 
    */
    private void hire(){
        if((player.getSolde()-10)>0 ){
            player.setSolde(player.getSolde()-10);
            gui.setSolde(player.getSolde());
            player.setCrewNumber(player.getCrewNumber()+10);
            gui.setCrew(player.getCrewNumber());
            player.setStrength(player.getStrength()+5);
            gui.setStrength(player.getStrength());
            gui.print("Hiring new persons in da crew\n");
        }
        else{
            gui.print("You don't have enough money\n");
        }
    }
    /**
    * This function allows the player to talk with characters 
    */
    private void talk(){
        gui.println(currentRoom.getCharactersHi());
    }
    /**
    * This function allows the player to pay bills to have exits room
    * other case he lose
    */
    private void pay() {
        if(player.getLocation()==scenario.getRoomByName("elMourouj")){
            if(player.getSolde()-10>=0){
                player.setSolde(player.getSolde()-10);
                gui.setSolde(player.getSolde());
                gui.println("Thank you see you soon");
                scenario.getRoomByName("elMourouj").setExits("southEast", scenario.getRoomByName("laMarsa"));
                scenario.getRoomByName("elMourouj").setExits("southWest", scenario.getRoomByName("rafel"));
                gui.setButtonColor(currentRoom.getExitButton());


            }else{
                gui.println("You don't have enough money sorry");
                endGame();
            }
        }
    }
    /**
    * This function allow the player to open a new exits for a room
    */
    private void openRoom(){
        if(player.getLocation()==scenario.getRoomByName("pontDuJoie")){
            if(player.getMagicKeys()==4){
                scenario.getRoomByName("pontDuJoie").setExits("east",scenario.getWinRoom());
                gui.println("Door Opening ...");
                gui.setButtonColor(currentRoom.getExitButton());

            }else{
                gui.println("You must have 4 key before \n");
            }
        }
        //tatami.setExits("north",darka);
        else if(player.getLocation()==scenario.getRoomByName("tatami")){
            if(player.checkItemInTheBag("darkaKey")!=null){
                scenario.getRoomByName("tatami").setExits("north",scenario.getRoomByName("darka"));
                gui.println("Cool you got the key there a new room north");
                gui.setButtonColor(currentRoom.getExitButton());

            }
        }
        else if(player.getLocation()==scenario.getRoomByName("krakenland")){
            if(player.checkItemInTheBag("OrtopiaKey")!=null){
                scenario.getRoomByName("krakenland").setExits("south", scenario.getRoomByName("ortopia"));
                gui.println("Door south opened ");
                gui.setButtonColor(currentRoom.getExitButton());

            }
        }
    }
    /**
    * This function allow the user to eat things eatable of course in his bag
    * @param Command command enter by the user 
    */
    private void eat(Command command){
        if(!command.hasSecondWord()) {
    		gui.print("Eat What ?");
    		return;
    	}
    	String eatItem=command.getSecondWord();
        String[] validItemToEat = {"cookie","apple","kiwi","banana","amande","avocat","orange","watermelon"};
        for(int i=0;i<validItemToEat.length;i++){
            if(eatItem.equals(validItemToEat[i])){
            	if(player.checkItemInTheBag(eatItem)!=null){
                    if(player.checkItemInTheBag(eatItem).getName()=="cookie"){
                        gui.println("You eaten a "+eatItem);
                        player.setStrength(player.getStrength()+(player.checkItemInTheBag("cookie")).getPrice());
                        gui.setStrength(player.getStrength()+(player.checkItemInTheBag("cookie")).getPrice());
                        player.removeItemFromBag(eatItem);
                        player.setWeight((player.getWeight()*2));	
                        gui.setBagContain(player.getTotalWeight(),player.getWeight());
                    }
                    else{
                        gui.println("You had eaten a "+eatItem);
                        player.setStrength(player.getStrength()+(player.checkItemInTheBag(eatItem)).getPrice());
                        gui.setStrength(player.getStrength());
                        player.removeItemFromBag(eatItem);
                        gui.setBagContain(player.getTotalWeight(),player.getWeight()+player.getTotalWeight());
                    }
            		
                }
            	else {
            		gui.print("Item does'nt exist in you bag");
            	}
            }
        }
    }
    /**
    * this function take you to the room where you charge the beamer last time 
    */
    private void fire(){
        if(beamerCharged!=null){
            currentRoom=beamerCharged;
            gui.println("You was teleported to "+currentRoom.getDescription());
            gui.println("You Maybe Missed those "+currentRoom.getItemsDescription());
            if(currentRoom.getImageName()!=null){
                gui.showImage(currentRoom.getImageName());
            }
        }
        else{
            gui.println("charge your beamer before please");
        }
    }
    
}