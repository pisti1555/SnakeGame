package home.gyak.serialize;

import home.gyak.resource_handling.Score;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class NewHighestScore {

    public NewHighestScore(Score score, int applesEaten) {
        score.highestScore = applesEaten;
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("C:\\Users\\Public\\Documents\\score.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(score);
            out.close();
            fileOut.close();
            System.out.println("Object info saved");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}