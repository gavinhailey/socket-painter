package edu.du.thompsonhailey.socketpainter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Hub {

    private static PaintingPanel panel = new PaintingPanel();
    private static ArrayList<String> msgs = new ArrayList<>();
    private static ArrayList<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(6969);

            while (true) {
                Socket s = ss.accept();

                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                Thread t = new HubThread(s, ois, oos, panel, msgs);

                t.start();
            }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    public static synchronized void addPrimative(PaintingPrimitive p){
        panel.addPrimitive(p);
    }

    public static synchronized void addMsg(String msg){
        msgs.add(msg);
    }

}
