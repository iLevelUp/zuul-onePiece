package src;

import java.util.*;

public class Scenario{

    private HashMap<String,Room> rooms;
    private Room startRoom;
    private Room winRoom;

    public Scenario(){
        this.rooms=new HashMap<String,Room>();
        Room cocoyashi, nooberland, wanoKuni, water7, kalen, ortopia, alabasta, krakenland, amazoneLily, skypia,paris8, rafel, pontDuJoie, elMourouj, parcB, laMarsa, sidiBouSaid,theJackPot;
        
        TransporterRoom sakura;
        sakura =new TransporterRoom("Transporter room in this room you can be tranported to a random room", "src/images/teleport.gif");
        
        cocoyashi = new Room("Cocoyashi", "src/images/kokoyashi.png");
        nooberland = new Room("Nooberland", "src/images/Nooberland.png");
        wanoKuni = new Room("Wano_kuni", "src/images/wanokuni.png");
        water7 = new Room("Water7", "src/images/Water_Seven.png");
        kalen = new Room("Kalen", "src/images/kalen.png");
        ortopia = new Room("Ortopia", "src/images/Ortopia.png");
        alabasta = new Room("Alabasta if you give me gold i will get to room that can help you","src/images/Alabasta.png");
        krakenland = new Room("Krakenland", "src/images/Krakenland.png");
        amazoneLily = new Room("Amazone_lily", "src/images/AmazonLily.png");
        skypia = new Room("Skypia", "src/images/skypia.png");
        paris8 = new Room("Paris8, il semble que vous avez découvert une île absente sur votre carte, et si vous l'exploriez ?","src/images/paris8.png");
        rafel = new Room("Rafel, ~votre log pose n'arrête pas de s'agiter ...~", "src/images/raftel.png");
        pontDuJoie = new Room("Pont Du joie there a locked room over here ", "src/images/pontdujoie.png");
        elMourouj = new Room("It's a tramways that will get you to the other side \n but you have to pay the ticket or you will lose","src/images/elmourouj.jpg");
        parcB = new Room("Parc B c'est un parc de l'Esperance Sportif De Tunis fondé en 1919", "src/images/parcb.jpg");
        laMarsa = new Room("La marsa c'est la plage la plus douce ", "src/images/lamarsa.jpg");
        sidiBouSaid = new Room("Sidi bou Said c'est la meilleur vue du monde ", "src/images/sidibousaid.jpg");
        theJackPot=new Room("","src/images/win.png");

        // initialise room exits & items
        cocoyashi.setExits("north", nooberland);
        cocoyashi.addItems("gold", new Item("gold", "you can sell gold to get money", 10, 10));
        cocoyashi.addItems("silver", new Item("silver", "you can sell silver to get money", 50, 10));
        cocoyashi.addCharacters("cocoyahi-1", new Characters("Dali", "I can help you if you give me some money","Go to paris8 room you'll find a cookie eat it this make you bag bigger", new Item("gold", "you can sell gold to get money", 10, 10) ));
        cocoyashi.addEnemies("dalton", new Enemy("dalton","Hi if you won i'll give you and item ","let's fight",new Item("gold", "you can sell gold to get money", 10, 10), 50));
        cocoyashi.addCharacters("cocoyahi-2", new Characters("Sankou7", "I can help you if you give me gold","you find a sakura tree this can help you to grow you're crew", new Item("gold", "you can sell gold to get money", 10, 10)));
        cocoyashi.addItems("magicKey",new Item("magicKey", "One of the magic key", 0, 0));

        nooberland.setExits("east", water7);
        nooberland.setExits("south", cocoyashi);
        nooberland.setExits("west", wanoKuni);
        nooberland.setExits("northWest", kalen);
        nooberland.setExits("northEast", alabasta);
        nooberland.addItems("sakura", new Item("sakura", "this item give you power", 500, 10));
        nooberland.addItems("magicKey",new Item("magicKey", "One of the magic key", 0, 0));

        wanoKuni.setExits("east", nooberland);
        wanoKuni.addItems("fafa", new Item("fafa", "this item give you power", 50, 10));
        wanoKuni.addItems("apple", new Item("apple", "this item give you life ", 50, 10));
        wanoKuni.addItems("magicKey",new Item("magicKey", "One of the magic key", 0, 0));

        water7.setExits("west", nooberland);
        water7.setExits("east", sakura);
        water7.addItems("beamer", new Item("beamer", "this item can teleport you to a random room", 5, 10));
        water7.addItems("magicKey",new Item("magicKey", "One of the magic key", 0, 0));

        kalen.setExits("north", skypia);
        kalen.setExits("southEast", nooberland);
        kalen.addItems("ammo", new Item("ammo", "this item can charge your Beamer", 1, 10));

        ortopia.setExits("north", krakenland);
        ortopia.setExits("west", kalen);
        ortopia.setExits("northEast", amazoneLily);
        ortopia.addItems("money",new Item("money","money",1,500));
        
        alabasta.setExits("southWest", nooberland);

        krakenland.setExits("south", ortopia);
        krakenland.setExits("west", skypia);

        amazoneLily.setExits("southWest", ortopia);

        amazoneLily.setExits("northEast", laMarsa);

        laMarsa.setExits("northWest", elMourouj);

        parcB.setExits("northEast", rafel);
        parcB.setExits("southWest", sidiBouSaid);

        skypia.setExits("north", paris8);
        skypia.setExits("east", krakenland);
        skypia.setExits("south", kalen);
        skypia.setExits("northEast", rafel);

        paris8.setExits("south", skypia);
        paris8.addItems("cookie", new Item("cookie", "This magic cookie multiply your bag weight by 2", 250, 5));
        
        rafel.setExits("southWest", skypia);
        rafel.setExits("north", pontDuJoie);
        rafel.setExits("southEast", parcB);
        rafel.addItems("OtropiaKey", new Item("OtropiaKey", "this is a key of a room ", 50, 10));
        

        
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