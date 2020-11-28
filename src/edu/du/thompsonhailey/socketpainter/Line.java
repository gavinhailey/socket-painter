import java.awt.*;

public class Line extends PaintingPrimitive{
    private Point start;
    private Point end;

    public Line(Color c, Point start, Point end){
        super(c);
        this.start = start;
        this.end = end;
    }

    @Override
    protected void drawGeometry(Graphics g) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }
}
