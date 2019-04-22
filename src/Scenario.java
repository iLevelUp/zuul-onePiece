package src;

import java.util.*;

public class Scenario{

    private ArrayList<Room> rooms;
    private Room startRoom;

    public Scenario(){
        this.rooms=new ArrayList<Room>();
        Room cocoyashi, nooberland, wanoKuni, water7, kalen, ortopia, alabasta, krakenland, amazoneLily, skypia,paris8, rafel, pontDuJoie, elMourouj, parcB, laMarsa, sidiBouSaid;
        
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
        pontDuJoie = new Room("Pont Du joie ce pont fondé pour un but artistique ", "src/images/pontdujoie.png");
        elMourouj = new Room("It's a tramways that will get you to the other side \n but you have to pay the ticket or you will lose","src/images/elmourouj.jpg");
        parcB = new Room("Parc B c'est un parc de l'Esperance Sportif De Tunis fondé en 1919", "src/images/parcb.jpg");
        laMarsa = new Room("La marsa c'est la plage la plus douce ", "src/images/lamarsa.jpg");
        sidiBouSaid = new Room("Sidi bou Said c'est la meilleur vue du monde ", "src/images/sidibousaid.jpg");
        
        // initialise room exits & items
        cocoyashi.setExits("north", nooberland);
        cocoyashi.addItems("gold", new Item("gold", "you can sell gold to get money", 10, 10));
        cocoyashi.addItems("silver", new Item("silver", "you can sell silver to get money", 50, 10));
        cocoyashi.addCharacters("cocoyahi", new Characters("Dali", "Good moring i'm dali i can help you if you give me some money","Go to paris8 room you'll find a cookie eat it this make you bag bigger", new Item("gold","gold", 10,10) ));

        nooberland.setExits("east", water7);
        nooberland.setExits("south", cocoyashi);
        nooberland.setExits("west", wanoKuni);
        nooberland.setExits("northWest", kalen);
        nooberland.setExits("northEast", alabasta);
        nooberland.addItems("sakura", new Item("sakura", "this item give you power", 500, 10));

        wanoKuni.setExits("east", nooberland);
        wanoKuni.addItems("fafa", new Item("fafa", "this item give you power", 50, 10));
        wanoKuni.addItems("apple", new Item("apple", "this item give you life ", 50, 10));

        water7.setExits("west", nooberland);
        water7.setExits("east", sakura);
        water7.addItems("beamer", new Item("beamer", "this item can teleport you to a random room", 5, 10));
        
        kalen.setExits("north", skypia);
        kalen.setExits("southEast", nooberland);
        kalen.addItems("ammo", new Item("ammo", "this item can charge your Beamer", 1, 10));

        ortopia.setExits("north", krakenland);
        ortopia.setExits("west", kalen);
        ortopia.setExits("northEast", amazoneLily);

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
        paris8.addItems("cookie", new Item("cookie", "This magic cookie multiply your bag weight by 2", 250, 0));
        
        rafel.setExits("southWest", skypia);
        rafel.setExits("north", pontDuJoie);
        rafel.setExits("southEast", parcB);
        rafel.addItems("OtropiaKey", new Item("OtropiaKey", "this is a key of a room ", 50, 10));
        
        rooms.add(cocoyashi);
        rooms.add(nooberland);
        rooms.add(wanoKuni);
        rooms.add(water7);
        rooms.add(kalen);
        rooms.add(ortopia);
        rooms.add(alabasta);
        rooms.add(krakenland);
        rooms.add(amazoneLily);
        rooms.add(skypia);
        rooms.add(paris8);
        rooms.add(rafel);
        rooms.add(pontDuJoie);
        rooms.add(elMourouj);
        rooms.add(parcB);
        rooms.add(laMarsa);
        rooms.add(sidiBouSaid);
        startRoom = cocoyashi;
    }

    /**
    * @return  the start room for this scenario
    */
    public Room getStartRoom(){
        return this.startRoom;
    }
    
    /**
    * @return  a random room from this scenario
    */
    public Room getRandomRoom(){
        int random = (int)(Math.random() * rooms.size() + 1);
        return rooms.get(random);
    }













}