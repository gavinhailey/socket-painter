package edu.du.thompsonhailey.socketpainter;

import javax.swing.*;
import java.awt.*;

public class Painter extends JFrame {
    public Painter() {
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel holder = new JPanel();
        holder.setLayout(new BorderLayout());

        // Create the paints
        JPanel paintPanel = new JPanel();
        paintPanel.setLayout(new GridLayout(3, 1)); // 3 by 1

        // add red paint button
        JButton redPaint = new JButton();
        redPaint.setBackground(Color.RED);
        redPaint.setOpaque(true);
        redPaint.setBorderPainted(false);
        paintPanel.add(redPaint);

        // add green paint button
        JButton greenPaint = new JButton();
        greenPaint.setBackground(Color.GREEN);
        greenPaint.setOpaque(true);
        greenPaint.setBorderPainted(false);
        paintPanel.add(greenPaint);

        // add blue paint button
        JButton bluePaint = new JButton();
        bluePaint.setBackground(Color.BLUE);
        bluePaint.setOpaque(true);
        bluePaint.setBorderPainted(false);
        paintPanel.add(bluePaint);

        JPanel shapePanel = new JPanel();
        shapePanel.setLayout(new GridLayout(1, 2));
        JButton lineButton = new JButton("Line");
        shapePanel.add(lineButton);
        JButton circleButton = new JButton("Circle");
        shapePanel.add(circleButton);

        // add the panels to the overall panel, holder
        holder.add(paintPanel, BorderLayout.WEST);
        holder.add(shapePanel, BorderLayout.NORTH);

        //TODO: after finishing the PaintingPanel class (described later) add a
        // new object of this class as the CENTER panel

        //TODO: And later you will add the chat panel to the SOUTH

        // Lastly, connect the holder to the JFrame
        setContentPane(holder);

        // And make it visible to layout all the components on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        new Painter();
    }
}
