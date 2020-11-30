package edu.du.thompsonhailey.socketpainter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HubThread extends Thread{

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
            try {
                Object in = ois.readObject();

                if (in instanceof PaintingPrimitive) {
                    Hub.addPrimative((PaintingPrimitive) in);
                    oos.writeObject(panel);
                } else if (in instanceof String) {
                    Hub.addMsg((String) in);
                    oos.writeObject(msgs);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        try {
            ois.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addPrimative(PaintingPrimitive p){
        panel.addPrimitive(p);
    }

}
