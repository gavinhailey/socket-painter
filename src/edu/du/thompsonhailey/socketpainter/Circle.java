package edu.du.thompsonhailey.socketpainter;

import java.awt.*;

public class Circle extends PaintingPrimitive {
    private Point center;
    private Point radiusPoint;

    public Circle(Color c, Point center, Point radiusPoint){
        super(c);
        this.center = center;
        this.radiusPoint = radiusPoint;
    }

    @Override
    public void drawGeometry(Graphics g) {
        int radius = (int) Math.abs(center.distance(radiusPoint));
        g.drawOval(center.x - radius, center.y - radius, radius*2, radius*2);
    }
}
