package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Save {

    public Save() {

    }

    public void save(String elementTosave) {
        try (FileWriter writer = new FileWriter("savedGames/actualGame.txt", true);
                BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(elementTosave + "\n");

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public void rename(String fileName) {
        File f1 = new File("savedGames/actualGame.txt");
        File f2 = new File("savedGames/"+fileName);
        boolean b = f1.renameTo(f2);
        if (b) {
            System.out.println("Saved");
        } else {
            System.out.println("Error");
        }
    }


    public void clearFile(){
        PrintWriter writer=null;
        try {
            writer = new PrintWriter("savedGames/actualGame.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
    }

}