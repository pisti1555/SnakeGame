package home.gyak.serialize;

import home.gyak.resource_handling.Score;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadHighestScore {
    public Score score;
    public LoadHighestScore() {
        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Public\\Documents\\score.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            score = (Score)in.readObject();
            in.close(); fileIn.close();
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }
}