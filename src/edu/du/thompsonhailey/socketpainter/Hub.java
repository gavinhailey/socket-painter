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
    private static ArrayList<HubThread> threads = new ArrayList<>();

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(6969);

            while (true) {
                Socket s = ss.accept();
                System.out.println("Connection Established");
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                HubThread t = new HubThread(s, ois, oos, panel, msgs);
                threads.add(t);

                t.start();
            }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    public static synchronized void addPrimitive(PaintingPrimitive p, HubThread t){
        panel.addPrimitive(p);
        sendObject(p, t);
    }

    public static synchronized void addMsg(String msg, HubThread t){
        msgs.add(msg);
        sendObject(msg, t);
    }

    public static synchronized void sendObject(Object o, HubThread th) {
        for (HubThread t : threads) {
            if (!t.equals(th))
                t.sendObject(o);
        }
    }

}
