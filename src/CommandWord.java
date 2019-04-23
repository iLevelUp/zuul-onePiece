package src;
/**
* Representations for all the valid command words for the game. 
* @author Michael Kolling and David J. Barnes
* @version 2006.03.30
*/
public enum CommandWord
{
    // A value for each command word, plus one for unrecognised
    // commands.
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), BACK("back"), TEST("test"), TAKE("take"), DROP("drop"), CHECK("check"),OPEN("open"),PAY("pay"),CHARGE("charge"),FIRE("fire"),TALK("talk"),GIVE("give"),ATTACK("attack"),HIRE("hire"),UNKNOWN("?");
	
	//The command string
    private String commandString;
    
    /**
    * Initialise with the corresponding command word.
    * @param commandWord The command string.
    */ 
    CommandWord(String commandString){
        this.commandString=commandString;
    }
    public static void translateCommand(CommandWord command,String string) {
    	command.commandString=string;
    }
    /**
     * @return The command word as a string.
     */
    public String toString(){
        return commandString;
    }
}