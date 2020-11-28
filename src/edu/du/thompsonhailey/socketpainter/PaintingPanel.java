package edu.du.thompsonhailey.socketpainter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintingPanel extends JPanel {
    private ArrayList<PaintingPrimitive> list = new ArrayList<>();

    public PaintingPanel(){
        this.setBackground(Color.WHITE);
    }

    public void addPrimitive(PaintingPrimitive obj) {
        this.list.add(obj);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(PaintingPrimitive obj : list) {
            obj.draw(g);
        }
    }

}
