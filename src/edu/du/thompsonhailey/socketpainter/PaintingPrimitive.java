package edu.du.thompsonhailey.socketpainter;

import java.awt.*;

public abstract class PaintingPrimitive {
    Color c;
    public PaintingPrimitive(Color c){
        this.c = c;
    }

    public final void draw(Graphics g) {
        g.setColor(this.c);
        drawGeometry(g);
    }

    protected abstract void drawGeometry(Graphics g);
}
