package edu.du.thompsonhailey.socketpainter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private PaintingPanel panel = new PaintingPanel();
    private ArrayList<String> msgs = new ArrayList<>();

    public static void main(String[] args) {

        try {
            Socket s = new Socket("localhost", 6969);
            Painter painter;

            Thread paintThread = new CleintPainter(s)

            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
