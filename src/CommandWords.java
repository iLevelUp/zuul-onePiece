package src;

/**
* This class is part of the "World of Zuul" application. 
* "World of Zuul" is a very simple, text based adventure game.  
* 
* This class holds an enumeration of all command words known to the game.
* It is used to recognise commands as they are typed in.
*
* @author  Michael Kolling and David J. Barnes
* @version 2006.03.30
*/
import java.util.*;
public class CommandWords
{
    // a constant array that holds all valid command words
	private HashMap<String,CommandWord> validCommands;
    //"go", "quit", "help","look","eat","back","test","take","drop","check"
   

     /**
     * Constructor - initialise the command words.
     */
    public CommandWords(){
    	validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }      
    }
    
    /**
    * Find the CommandWord associated with a command word.
    * @param commandWord The word to look up.
    * @return The CommandWord corresponding to commandWord, or UNKNOWN
    *         if it is not a valid command word.
    */
    public CommandWord getCommandWord(String commandWord){
    	
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
    * Check whether a given String is a valid command word. 
    * @return true if it is, false if it isn't.
    */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public StringBuilder getCommandList() {
    	StringBuilder returnString= new StringBuilder();
        for(String command : validCommands.keySet()) {
           returnString.append(" "+command);
        }
        return returnString;
    }
}
