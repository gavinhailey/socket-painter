package edu.du.thompsonhailey.socketpainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Painter extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private Color colorSelection;
    private String shapeSelection;
    private Point startPoint;
    private PaintingPanel paintingPanel;

    public Painter() {
        this.shapeSelection = "line";
        this.colorSelection = Color.RED;

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
        redPaint.setActionCommand("red");
        redPaint.addActionListener(this);
        paintPanel.add(redPaint);

        // add green paint button
        JButton greenPaint = new JButton();
        greenPaint.setBackground(Color.GREEN);
        greenPaint.setOpaque(true);
        greenPaint.setBorderPainted(false);
        greenPaint.setActionCommand("green");
        greenPaint.addActionListener(this);
        paintPanel.add(greenPaint);

        // add blue paint button
        JButton bluePaint = new JButton();
        bluePaint.setBackground(Color.BLUE);
        bluePaint.setOpaque(true);
        bluePaint.setBorderPainted(false);
        bluePaint.setActionCommand("blue");
        bluePaint.addActionListener(this);
        paintPanel.add(bluePaint);

        JPanel shapePanel = new JPanel();
        shapePanel.setLayout(new GridLayout(1, 2));

        JButton lineButton = new JButton("Line");
        lineButton.setActionCommand("line");
        lineButton.addActionListener(this);
        shapePanel.add(lineButton);

        JButton circleButton = new JButton("Circle");
        circleButton.setActionCommand("circle");
        circleButton.addActionListener(this);
        shapePanel.add(circleButton);

        this.paintingPanel = new PaintingPanel();
        paintingPanel.addMouseListener(this);
        paintingPanel.addMouseMotionListener(this);
        holder.add(paintingPanel, BorderLayout.CENTER);

        // add the panels to the overall panel, holder
        holder.add(paintPanel, BorderLayout.WEST);
        holder.add(shapePanel, BorderLayout.NORTH);


        //TODO: And later you will add the chat panel to the SOUTH

        // Lastly, connect the holder to the JFrame
        setContentPane(holder);

        // And make it visible to layout all the components on the screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Action Event: " + actionEvent.getActionCommand());
        String s = actionEvent.getActionCommand();
        if (s.equals("red"))
            this.colorSelection = Color.RED;
        else if (s.equals("green"))
            this.colorSelection = Color.GREEN;
        else if (s.equals("blue"))
            this.colorSelection = Color.BLUE;
        else if (s.equals("line"))
            this.shapeSelection = "line";
        else if (s.equals("circle"))
            this.shapeSelection = "circle";
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("Mouse Pressed! at "+mouseEvent.getPoint().toString());
        this.startPoint = mouseEvent.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        System.out.println("Mouse Released! at "+mouseEvent.getPoint().toString());
        if (shapeSelection.equals("line")) {
            System.out.println("adding line");
            Line l = new Line(this.colorSelection, this.startPoint, mouseEvent.getPoint());
            paintingPanel.addPrimitive(l);
        } else if (shapeSelection.equals("circle")) {
            System.out.println("adding circle");
            Circle c = new Circle(this.colorSelection, this.startPoint, mouseEvent.getPoint());
            paintingPanel.addPrimitive(c);
        }
        paintingPanel.updateUI();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public static void main(String[] args) {
        new Painter();
    }
}
