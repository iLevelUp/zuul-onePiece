package src;

import java.util.*;

public class Scenario{

    private HashMap<String,Room> rooms;
    private Room startRoom;
    private Room winRoom;

    public Scenario(){
        this.rooms=new HashMap<String,Room>();
        Room cocoyashi, nooberland, wanoKuni, water7, kalen, ortopia, alabasta, krakenland, amazoneLily, skypia,paris8, rafel, pontDuJoie, elMourouj, parcB, laMarsa, sidiBouSaid,theJackPot,fedayn,tatami,darka,tonyMontana,yokoshima,pnl,philadelphia,tosoma;
        
        TransporterRoom sakura;
        sakura =new TransporterRoom("Transporter","Transporter room in this room you can be tranported to a random room", "src/images/teleport.gif");
        
        cocoyashi = new Room("Cocoyashi","This room is your start point cocoyashi", "src/images/cocoyashi.jpg");
        nooberland = new Room("Nooberland","is a famous gold ressources", "src/images/nooberland.jpg");
        wanoKuni = new Room("Wanokuni","In this island you find some interessting stuff" ,"src/images/wanokuni.jpg");
        water7 = new Room("Water7","There is some dangerous wave here pay attention", "src/images/water7.jpg");
        kalen = new Room("Kalen","This is an old army base","src/images/kalen.jpg");
        ortopia = new Room("Ortopia","Legacy of legend are here", "src/images/otropia.jpg");
        alabasta = new Room("Alabasta","Popular Room of vampires","src/images/alabasta.jpg");
        krakenland = new Room("Krakenland","was one of the leader bank in world a locked room here", "src/images/krakenland.jpg");
        amazoneLily = new Room("Amazone_lily","The forest on amazonia", "src/images/amazonelily.jpg");
        skypia = new Room("Skypia","The sky is a little bit nervous here", "src/images/skypia.jpg");
        paris8 = new Room("Paris8","One of the best university in the world","src/images/paris8.jpg");
        rafel = new Room("Rafel","this island was has been inhabited by Rafel of ninja Turtul", "src/images/raftel.jpg");
        pontDuJoie = new Room("Pont Du joie","Artistic room and there a locked room over here", "src/images/pontdujoie.jpg");
        elMourouj = new Room("El Mourouj","you'll find a subway in the sky you must pay to have an exit","src/images/elmourouj.jpg");
        parcB = new Room("Parc B","A football team live here", "src/images/parcb.jpg");
        laMarsa = new Room("La marsa", "a good beach over here","src/images/lamarsa.jpg");
        sidiBouSaid = new Room("Sidi bou Said","Sunshine and best view in the coin", "src/images/sidibousaid.jpg");
        theJackPot=new Room("Final","The monster Room there is the Dalton7","src/images/monster.gif");
        fedayn =new Room("fedayn","A support group lovely and helpful","src/images/fedayn.jpg");
        tatami =new Room("tatami","A crossline of many mysterious ways","src/images/tatami.jpg");
        darka =new Room("darka","A party island for having fun","src/images/darka.jpg");
        tonyMontana =new Room("tony Montana","An old friend of you lived here","src/images/tonyMontana.jpg");
        yokoshima =new Room("yokoshima","A japanis plate over here","src/images/yokoshima.jpg");
        pnl =new Room("pnl","A famous group of hip hop music","src/images/pnl.jpg");
        philadelphia=new Room("philadelphia","The downtown of rooms pnl and tosoma rooms","src/images/philadelphia.jpg");
        tosoma=new Room("tosoma","Tosoma is a big chinese town","src/images/tosoma.jpg");

        // initialise room exits & items
        cocoyashi.setExits("north", nooberland);
        cocoyashi.addItems("silver", new Item("silver", "you can sell silver to get money", 50, 10));
        cocoyashi.addCharacters("cocoyashi", new Characters("Dali", "I can help you if you give me some gold","Go north -> west -> west \nyou'll find a transporter room that take you to a random room", new Item("gold", "you can sell gold to get money", 10, 10) ));

        nooberland.setExits("east", water7);
        nooberland.setExits("south", cocoyashi);
        nooberland.setExits("west", wanoKuni);
        nooberland.setExits("northWest", kalen);
        nooberland.setExits("northEast", alabasta);
        nooberland.addItems("gold", new Item("gold", "you can sell gold to get money", 10, 10));
        nooberland.addItems("apple", new Item("apple", "you ", 50, 10));

        wanoKuni.setExits("east", nooberland);
        wanoKuni.addCharacters("wanoKuni", new Characters("Jêrome", "Hello i can if give me some money","Go east-> northWest -> north ->north \n you'll find a magic cookie", new Item("money","money ", 5, 15) ));
        wanoKuni.addItems("fafa", new Item("fafa", "this item give you power", 50, 10));
        wanoKuni.addItems("money", new Item("money", "this is money ", 50, 20));

        water7.setExits("west", nooberland);
        water7.setExits("east", sakura);
        water7.addItems("beamer", new Item("beamer", "this item can teleport you to a random room", 5, 10));
        water7.addEnemies("Dalton2", new Enemy("Dalton2","try to kill me","i'll kill you",new Item("apple","apple for life",20, 2),40));

        kalen.setExits("north", skypia);
        kalen.setExits("southEast", nooberland);
        kalen.addItems("ammo", new Item("ammo", "this item can charge your Beamer", 1, 10));
        kalen.addEnemies("Dalton5",new Enemy("Dalton5", "Dalton5","i'll kill you ",new Item("sakura","sakura flavour",40,10),20));

        ortopia.setExits("north", krakenland);
        ortopia.setExits("west", kalen);
        ortopia.setExits("northEast", amazoneLily);
        ortopia.addItems("magicKey",new Item("magicKey","magicKey",0,0));
        
        alabasta.setExits("southWest", nooberland);
        alabasta.setExits("east",fedayn);
        alabasta.addCharacters("alabsa",new Characters("mehdy", "hi give me sakura flavour i'll help you", "Go west-> southWest-> west \nyou'll find money",new Item("sakura","sakura flavour",40,10)));

        //krakenland.setExits("south", ortopia);
        krakenland.setExits("west", skypia);
        krakenland.addItems("watermelon",new Item("watermelon", "fruit is good",15, 20));

        amazoneLily.setExits("southWest", ortopia);
        amazoneLily.setExits("northEast", laMarsa);
        amazoneLily.addCharacters("amazoneLily",new  Characters("sarfati","give me fafa i'll help you","watch out don't go northEast",new Item("fafa","this item give you power",50,10)));
        amazoneLily.addEnemies("Dalton1",new Enemy("Dalton1","Dalton1","i'lll kill you",new Item("silver","you can sell silver",50,15),40));

        laMarsa.setExits("northWest", elMourouj);
        laMarsa.addItems("money", new Item("money","money",0,50));

        parcB.setExits("northWest", rafel);
        parcB.setExits("southEast", sidiBouSaid);
        parcB.addItems("magicKey",new Item("magicKey","magicKey",0,0));

        skypia.setExits("north", paris8);
        skypia.setExits("east", krakenland);
        skypia.setExits("south", kalen);
        skypia.setExits("northEast", rafel);
        skypia.addCharacters("safaran",new  Characters("safaran","give me fafa i'll help you","watch out don't go northEast enemy power is 80%",new Item("fafa","this item give you power",50,10)));

        paris8.setExits("south", skypia);
        paris8.addItems("cookie", new Item("cookie", "This magic cookie multiply your bag weight by 2", 250, 5));
        paris8.addItems("orange", new Item("orange","Fruit is cool",30,10));

        rafel.setExits("southWest", skypia);
        rafel.setExits("north", pontDuJoie);
        rafel.setExits("southEast", parcB);
        rafel.addItems("OrtopiaKey", new Item("OrtopiaKey", "this is a key of a room ", 50, 10));
        rafel.addEnemies("Dalton6",new Enemy("Dalton6","Dalton6","i'll kill you", new Item("gold","you can sell gold",50,35), 80));

        pontDuJoie.setExits("south", rafel);
        pontDuJoie.addCharacters("pontDuJoie",new Characters("Tabargi", "hi i can help if you give me 50$","enemies force is 100%", new Item("money","money",0,50)));

        theJackPot.addEnemies("Dalton7",new Enemy("Dalton7","Dalton7","i'll kill you", new Item("OnePiece","wining",0,0),100));

        sidiBouSaid.setExits("south", fedayn);
        sidiBouSaid.addItems("kiwi", new Item("kiwi","fruit is good",55,20));

        fedayn.setExits("northEast",tatami);
        fedayn.setExits("southEast",philadelphia);
        fedayn.addItems("avocat", new Item("avocat","fruit is good",40,25));
        
        sakura.addItems("sakura",new Item("sakura","this is sakura flavour",15,20));

        tatami.setExits("northEast",tonyMontana);
        tatami.setExits("east",yokoshima);
        tatami.setExits("west",sidiBouSaid);
        tatami.setExits("northWest",laMarsa);
        tatami.addItems("gold",  new Item("gold", "perious gold", 40,40));
        tatami.addItems("banana",  new Item("banana", "fruit is good", 40,10));
        
        yokoshima.setExits("west", tatami);
        yokoshima.addItems("yokoshima",new Item("amande", "amande for force", 50,40));
        yokoshima.addEnemies("Dalton3",new Enemy("Dalton3", "Hey you !","i'll kill you", new Item("banana", "fruit is good", 40, 20), 60));

        tonyMontana.setExits("southWest", tatami);
        tonyMontana.addCharacters("tonyMontata", new Characters("Paolo","sell me some gold i'll help you ", "Go southEast -> north", new Item("gold", "perious gold", 40,40) ) );

        darka.setExits("south",tatami);
        darka.addItems("magicKey",new Item("magicKey","magicKey",0,0));
        darka.addItems("kiwi", new Item("kiwi","fruit is good",15,5));

        philadelphia.setExits("east",pnl);
        philadelphia.setExits("south",tosoma);
        philadelphia.addEnemies("Dalton4",new Enemy("Dalton4", "Hey you !","i'll kill you", new Item("banana", "fruit is good", 40, 20), 60));
        philadelphia.addItems("darkaKey", new Item("darkaKey","key of a room",0,0));
        tosoma.setExits("north", philadelphia);
        tosoma.addItems("kiwi", new Item("kiwi","fruit is good",35,25));

        pnl.setExits("west",philadelphia);
        pnl.setExits("northWest", amazoneLily);
        pnl.addItems("magicKey",new Item("magicKey","magicKey",0,0));
        pnl.addItems("money",new Item("money","money $$$$$", 0, 1000));
        
        rooms.put("cocoyashi",cocoyashi);
        rooms.put("nooberland",nooberland);
        rooms.put("wanoKuni",wanoKuni);
        rooms.put("water7",water7);
        rooms.put("kalen",kalen);
        rooms.put("ortopia",ortopia);
        rooms.put("alabasta",alabasta);
        rooms.put("krakenland",krakenland);
        rooms.put("amazoneLily",amazoneLily);
        rooms.put("skypia",skypia);
        rooms.put("paris8",paris8);
        rooms.put("rafel",rafel);
        rooms.put("pontDuJoie",pontDuJoie);
        rooms.put("elMourouj",elMourouj);
        rooms.put("parcB",parcB);
        rooms.put("laMarsa",laMarsa);
        rooms.put("sidiBouSaid",sidiBouSaid);
        rooms.put("fedayn",fedayn);
        rooms.put("tatami",tatami);
        rooms.put("darka",darka);
        rooms.put("tonyMontana",tonyMontana);
        rooms.put("philadelphia",philadelphia);
        rooms.put("pnl",pnl);
        rooms.put("yokoshima",yokoshima);
        rooms.put("tosoma",tosoma);
        
        startRoom = cocoyashi;
        winRoom=theJackPot;
    }

    /**
    * @return  the start room for this scenario
    */
    public Room getStartRoom(){
        return this.startRoom;
    }
    
    public Room getWinRoom(){
        return this.winRoom;
    }
    public Room getRoomByName(String name){
      return rooms.get(name);
    }
    /**
    * @return  a random room from this scenario
    */
    public Room getRandomRoom(){
        Random generator = new Random();
        Object[] values = rooms.values().toArray();
        Room randomRoom = (Room) values[generator.nextInt(values.length)];
        return randomRoom;
    }

}