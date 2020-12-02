package edu.du.thompsonhailey.socketpainter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class PainterListener extends Thread {
    private ObjectInputStream ois;
    private Painter p;

    public PainterListener(ObjectInputStream ois, Painter p) {
        this.ois = ois;
        this.p = p;
    }

    @Override
    public void run(){
        while (true) {
            try {
                Object in = ois.readObject();
                System.out.println(in);
                if (in instanceof PaintingPrimitive) {
                    p.addPrimitive((PaintingPrimitive) in);
                } else if (in instanceof String) {
                    p.addText((String) in);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
