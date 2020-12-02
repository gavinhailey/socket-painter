package edu.du.thompsonhailey.socketpainter;

import java.awt.*;
import java.io.Serializable;

public abstract class PaintingPrimitive implements Serializable {
    protected Color c;
    public PaintingPrimitive(Color c){
        this.c = c;
    }

    public final void draw(Graphics g) {
        g.setColor(this.c);
        drawGeometry(g);
    }

    protected abstract void drawGeometry(Graphics g);
}
