/**
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */
package src;

import java.util.Stack;
import java.io.*;
public class GameEngine{


    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;
    private Stack<Room> displacement;
    private Player player;
    private int counter; 
    private Room cocoyashi, nooberland, wanoKuni, water7, kalen,ortopia,alabasta,krakenland,amazoneLily,skypia,paris8,rafel,pontDuJoie,elMourouj,parcB,laMarsa,sidiBouSaid;

    /**
    * Create the game and initialise its internal map.
    */
    public GameEngine() 
    {
        parser = new Parser();
        displacement = new Stack<Room>(); 
        player = new Player("sangoku",250,currentRoom,50);
        createRooms();
    }
    /**
    * Set the user Interface 
    */
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.print("\n");
        gui.println("Welcome to the One Piece treasure cruise!");
        gui.println("One Piece is a new, incredibly boring adventure game.");
        gui.println("Type 'help' if you need help.");
        gui.print("\n");
        gui.println(currentRoom.getLongDescription());
        gui.showImage(currentRoom.getImageName());

    }
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
      
              
        cocoyashi = new Room("Cocoyashi","src/images/kokoyashi.png");
        nooberland = new Room("Nooberland","src/images/Nooberland.png");
        wanoKuni = new Room("Wano_kuni","src/images/wanokuni.png");
        water7 = new Room("Water7","src/images/Water_Seven.png");
        kalen = new Room("Kalen","src/images/kalen.png");
        ortopia = new Room("Ortopia","src/images/Ortopia.png");
        alabasta = new Room("Alabasta if you give me gold i will get to room that can help you","src/images/Alabasta.png");
        krakenland = new Room("Krakenland","src/images/Krakenland.png");
        amazoneLily = new Room("Amazone_lily","src/images/AmazonLily.png");
        skypia = new Room("Skypia","src/images/skypia.png");
        paris8 = new Room("Paris8, il semble que vous avez découvert une île absente sur votre carte, et si vous l'exploriez ?","src/images/paris8.png");
        rafel = new Room("Rafel, ~votre log pose n'arrête pas de s'agiter ...~","src/images/raftel.png");
        pontDuJoie = new Room("Pont Du joie ce pont fondé pour un but artistique ","src/images/pontdujoie.png");
        elMourouj= new Room("It's a tramways that will get you to the other side \n but you have to pay the ticket or you will lose","src/images/elmourouj.jpg");
        parcB = new Room("Parc B c'est un parc de l'Esperance Sportif De Tunis fondé en 1919","src/images/parcb.jpg");
        laMarsa = new Room("La marsa c'est la plage la plus douce ","src/images/lamarsa.jpg");
        sidiBouSaid = new Room("Sidi bou Said c'est la meilleur vue du monde ","src/images/sidibousaid.jpg");
        // initialise room exits & items
        cocoyashi.setExits("north",nooberland);
        cocoyashi.addItems("gold",new Item("gold","you can sell gold to get money",10,10));
        cocoyashi.addItems("silver",new Item("silver","you can sell silver to get money",50,10));

        nooberland.setExits("east",water7);
        nooberland.setExits("south",cocoyashi);
        nooberland.setExits("west",wanoKuni);
        nooberland.setExits("northWest",kalen);
        nooberland.setExits("northEast",alabasta);
        nooberland.addItems("sakura",new Item("sakura","this item give you power",500,10));

        wanoKuni.setExits("east",nooberland);
        wanoKuni.addItems("fafa",new Item("fafa","this item give you power",50,10));
        wanoKuni.addItems("apple",new Item("apple","this item give you life ",50,10));

        water7.setExits("west",nooberland);

        kalen.setExits("north",skypia);
        kalen.setExits("southEast",nooberland);

        ortopia.setExits("north",krakenland);
        ortopia.setExits("west",kalen);
        ortopia.setExits("northEast",amazoneLily);

        alabasta.setExits("southWest",nooberland);

        krakenland.setExits("south",ortopia);
        krakenland.setExits("west",skypia);

        amazoneLily.setExits("southWest",ortopia);
        
        amazoneLily.setExits("northEast",laMarsa);

        laMarsa.setExits("northWest",elMourouj);
        
        
        parcB.setExits("northEast",rafel);
        parcB.setExits("southWest",sidiBouSaid);

        skypia.setExits("north",paris8);
        skypia.setExits("east",krakenland);
        skypia.setExits("south",kalen);
        skypia.setExits("northEast",rafel);

        paris8.setExits("south",skypia);
        paris8.addItems("cookie",new Item("cookie","This magic cookie multiply your bag weight by 2",250,0));
        rafel.setExits("southWest",skypia);
        rafel.setExits("north",pontDuJoie);
        rafel.setExits("southEast",parcB);
        rafel.addItems("OtropiaKey",new Item("OtropiaKey","this is a key of a room ",50,10));


        currentRoom = cocoyashi;  // start game outside
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(Command commandLine) 
    {
    	//counter
    	if(counter>100) {
    		gui.print("You Lose !\n");
    		endGame();
    		return;
    	}
    		
        counter++;
        CommandWord commandWord = commandLine.getCommandWord();
        
        switch(commandWord) {
     
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
        		testFile(commandLine);
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
        	case QUIT:
                if(commandLine.hasSecondWord())
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
    * Get you back to the room just before 
    */
    private void backRoom() {
    	if (displacement.isEmpty())
    		gui.println("You are at the start Point");
    	else {
    		currentRoom=displacement.pop();
    		gui.println("You back to "+currentRoom.getDescription());
    		gui.println("You Maybe Missed those "+currentRoom.getItemsDescription());
    		if(currentRoom.getImageName()!=null)
    			gui.showImage(currentRoom.getImageName());
    	}
    }
    
    
    /**
    * Print out some help information.
    * Here we print some stupid, cryptic message and a list of the 
    * command words.
    */
    private void printHelp() 
    {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around at Saint Denis, University Campus." + "\n");
        gui.print(parser.showCommands());
    }

	
    /** 
    * Tests for the game with files
    * this function will excute all command present in the file line by line 
    * @param  Command the command enter by th user
    */
    private void testFile(Command command){
    	if(!command.hasSecondWord()) {
    		gui.println("Test what ?\n<usage> you have to put a file name ");
    		return;
    	}    	
        String line = null;
        String fileName="testFiles/"+command.getSecondWord();
        try {
     
            BufferedReader bufferedReader =  new BufferedReader(new FileReader(fileName));
            while((line = bufferedReader.readLine()) != null) {
                interpretCommand(parser.getCommand(line));
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
            if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }
    
    
    /**
    * This function allow to the player to pick up things in the room 
    * @param Command enter by the user 
    */
    private void take(Command command) {
    	if(!command.hasSecondWord()) {
    		gui.print("take What ?");
    		return;
    	}
        String newItem=command.getSecondWord();
        if(currentRoom.checkItemInTheRoom(newItem)!=null){
        	if(player.checkWeight(newItem, currentRoom.checkItemInTheRoom(newItem) )) {
        		player.addItemToBag(newItem,  currentRoom.checkItemInTheRoom(newItem));
                currentRoom.removeItems(newItem);

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
        }
        else{
            gui.print("Item does'nt present in your bag");
            gui.print("\n");
        }

    }
    /**
    * Print goodbye and enable the entry field
    */
    private void endGame() {
		gui.println("Thank you for playing.  Good bye !");
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
    * This function allows the player to pay bills to have exits room
    * other case he lose
    */
    private void pay() {
    	if(player.getLocation()==elMourouj) {
    		if(player.getSolde()-10>0) {
    			gui.print("Thank you it's 10$\n");
    			elMourouj.setExits("southEast",laMarsa);
            	elMourouj.setExits("southWest",krakenland);
    		}else {
    			counter=101;
    		}
    	}
    }
    /**
    * This function allow the player to open a new exits for a room
    */
    private void openRoom() {
    	
    	if(player.checkItemInTheBag("OtropiaKey")!=null){
    		if(player.getLocation()!= ortopia) {
    			gui.print("You don't have keys or this key is not for this room");
    		}
    		else {
	    		player.removeItemFromBag("OtropiaKey");
	            kalen.setExits("east",ortopia);
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
        String[] validItemToEat = {"cookie","apple"};
        for(int i=0;i<validItemToEat.length;i++){
            if(eatItem.equals(validItemToEat[i])){
            	if(player.checkItemInTheBag(eatItem)!=null){
            		player.removeItemFromBag(eatItem);
                    player.setWeight((player.getWeight()*2));	
                    gui.println("You had eaten a "+eatItem);
                }
            	else {
            		gui.print("Item does'nt exist in you bag");
            	}
            }
        }
    }
    
}