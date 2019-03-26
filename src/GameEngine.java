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

import java.util.Stack;

public class GameEngine{


    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;
    private Stack<Room> displacement;
        
    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() 
    {
        parser = new Parser();
        displacement = new Stack<Room>(); 
        createRooms();
    }
    /*
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
      
        Room cocoyashi, nooberland, wanoKuni, water7, kalen,ortopia,alabasta,krakenland,amazoneLily,skypia,paris8,rafel,pontDuJoie,elMourouj,parcB,laMarsa,sidiBouSaid;
              
        cocoyashi = new Room("Cocoyashi","src/imageskokoyashi.png");
        nooberland = new Room("Nooberland","src/imagesNooberland.png");
        wanoKuni = new Room("Wano_kuni","src/imageswanokuni.png");
        water7 = new Room("Water7","src/imagesWater_Seven.png");
        kalen = new Room("Kalen","src/imageskalen.png");
        ortopia = new Room("Ortopia","src/imagesOrtopia.png");
        alabasta = new Room("Alabasta","src/imagesAlabasta.png");
        krakenland = new Room("Krakenland","src/imagesKrakenland.png");
        amazoneLily = new Room("Amazone_lily","src/imagesAmazonLily.png");
        skypia = new Room("Skypia","src/imagesskypia.png");
        paris8 = new Room("Paris8, il semble que vous avez découvert une île absente sur votre carte, et si vous l'exploriez ?","src/imagesparis8.png");
        rafel = new Room("Rafel, ~votre log pose n'arrête pas de s'agiter ...~","src/imagesraftel.png");
        pontDuJoie = new Room("Pont Du joie ce pont fondé pour un but artistique ","src/imagespontdujoie.png");
        elMourouj= new Room("El Mourouj c'est un quartier populaire par ces créatures qui vont vous aidez ","src/imageselmourouj.jpg");
        parcB = new Room("Parc B c'est un parc de l'Esperance Sportif De Tunis fondé en 1919","src/imagesparcb.jpg");
        laMarsa = new Room("La marsa c'est la plage la plus douce ","src/imageslamarsa.jpg");
        sidiBouSaid = new Room("Sidi bou Said c'est la meilleur vue du monde ","src/imagessidibousaid.jpg");
        // initialise room exits & items
        cocoyashi.setExits("north",nooberland);
        cocoyashi.addItems("gold",new Item("this item have 2000 years",10,10));
        
        nooberland.setExits("east",water7);
        nooberland.setExits("south",cocoyashi);
        nooberland.setExits("west",wanoKuni);
        nooberland.setExits("northWest",kalen);
        nooberland.setExits("northEast",alabasta);
        nooberland.addItems("sakura",new Item("this item give you power",50,10));


        wanoKuni.setExits("east",nooberland);
        wanoKuni.addItems("fafa",new Item("this item give you power",50,10));
        wanoKuni.addItems("baba",new Item("this item give you life",50,10));
        wanoKuni.addItems("dada",new Item("this item give you nothing",50,10));

        water7.setExits("west",nooberland);
        water7.addItems("bar",new Item("this item make you rich",50,10));

        kalen.setExits("north",skypia);
        kalen.setExits("southEast",nooberland);
        kalen.addItems("foo",new Item("this item make you famous",50,10));

        ortopia.setExits("north",krakenland);
        ortopia.setExits("west",kalen);
        ortopia.setExits("northEast",amazoneLily);
        ortopia.addItems("dada",new Item("this item make you hungry",50,10));

        alabasta.setExits("southWest",nooberland);
        alabasta.addItems("dada",new Item("this item make you funny",50,10));

        krakenland.setExits("south",ortopia);
        krakenland.setExits("west",skypia);
        krakenland.addItems("dada",new Item("this item make you dumb",50,10));

        amazoneLily.setExits("southWest",ortopia);
        amazoneLily.setExits("northEast",laMarsa);
        amazoneLily.addItems("baba",new Item("this item give you life",50,10));

        laMarsa.setExits("northWest",elMourouj);
        wanoKuni.addItems("dada",new Item("this item give you nothing",50,10));

        elMourouj.setExits("southEast",laMarsa);
        elMourouj.setExits("southWest",krakenland);

        parcB.setExits("northEast",rafel);
        parcB.setExits("southWest",sidiBouSaid);

        skypia.setExits("north",paris8);
        skypia.setExits("east",krakenland);
        skypia.setExits("south",kalen);
        skypia.setExits("northEast",rafel);

        paris8.setExits("south",skypia);
        paris8.addItems("medaille",new Item("this item make you hero",50,10));

        rafel.setExits("southWest",skypia);
        rafel.setExits("north",pontDuJoie);
        rafel.setExits("southEast",parcB);
        rafel.addItems("PNL",new Item("this item make you the best",50,10));


        currentRoom = cocoyashi;  // start game outside
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go")) 
        	goRoom(command);
        else if (commandWord.equals("back"))
        	backRoom();
        else if (commandWord.equals("eat"))
            eat();
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
    }

    /*
    * Get you back to the room just before 
    */
    private void backRoom() {
    	if (displacement.isEmpty())
    		gui.println("You are at the start Point");
    	else {
    		currentRoom=displacement.pop();
    		gui.println("You back to "+currentRoom.getDescription());
    		gui.println("You Maybe Missed those items :"+currentRoom.getItemsDescription());
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
        gui.print("Your command words are: " + parser.showCommands());
    }


    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
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
            currentRoom = nextRoom;
            gui.println(currentRoom.getLongDescription());
            if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }
    /*
    * Print goodbye and enable the entry field
    */
    private void endGame() {
		gui.println("Thank you for playing.  Good bye !");
        gui.enable(false);
    }
    
    /*
    * Print the long description of the room
    */
    private void look(){
        gui.print(currentRoom.getLongDescription());
    }
    
    /*
    * Print eat
    */
    private void eat(){
        gui.println("You had eaten now and you are not hungry any more");
    }
    
}