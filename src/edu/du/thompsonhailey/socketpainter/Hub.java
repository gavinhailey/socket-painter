package edu.du.thompsonhailey.socketpainter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Hub {
    ServerSocket ss;

    {
        try {
            ss = new ServerSocket(6969);

            while(true){
                Socket s = ss.accept();

                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                Object in = ois.readObject();

                if(in instanceof PaintingPrimitive){

                }else if(in instanceof String){

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
