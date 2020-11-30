package edu.du.thompsonhailey.socketpainter;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class CleintPainter extends Thread{

    private Socket s;
    private ObjectOutputStream oos;
    private Painter p;

    public CleintPainter(Socket s, ObjectOutputStream oos, Painter p) {
        this.s = s;
        this.oos = oos;
        this.p = p;
    }

    @Override
    public void run(){

    }
}
