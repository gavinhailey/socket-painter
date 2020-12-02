package edu.du.thompsonhailey.socketpainter;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HubThread extends Thread {
    private Socket s;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private PaintingPanel panel;
    private ArrayList<String> msgs;

    public HubThread(Socket s, ObjectInputStream ois, ObjectOutputStream oos, PaintingPanel panel, ArrayList<String> msgs) {
        this.s = s;
        this.ois = ois;
        this.oos = oos;
        this.panel = panel;
        this.msgs = msgs;
    }

    @Override
    public void run() {
        boolean run = true;
        try {
            oos.writeObject(panel);
            oos.writeObject(msgs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (run) {
            try {
                Object in = ois.readObject();
                if (in instanceof PaintingPrimitive) {
                    Hub.addPrimitive((PaintingPrimitive) in, this);
                    oos.writeObject(panel);
                } else if (in instanceof String) {
                    Hub.addMsg((String) in, this);
                    oos.writeObject(msgs);
                }
            } catch (EOFException e) {
                try {
                    System.out.println("Closing connection");
                    ois.close();
                    oos.close();
                    s.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                run = false;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendObject(Object o) {
        try {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
