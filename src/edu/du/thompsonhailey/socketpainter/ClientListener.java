package edu.du.thompsonhailey.socketpainter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientListener extends Thread{

    private Socket s;
    private ObjectInputStream ois;
    private Painter p;

    public ClientListener(Socket s, ObjectInputStream ois, Painter p) {
        this.s = s;
        this.ois = ois;
        this.p = p;
    }

    @Override
    public void run(){
        try {
            Object in = ois.readObject();

            if(in instanceof PaintingPanel){
                p.setPanel(in);
            }else if(in instanceof ArrayList){
                if(((ArrayList<?>) in).get(0) instanceof String)
                    p.setMsgs((ArrayList<String>)in);
            }

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
