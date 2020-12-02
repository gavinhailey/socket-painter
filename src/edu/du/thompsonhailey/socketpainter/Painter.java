package edu.du.thompsonhailey.socketpainter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Painter extends JFrame implements ActionListener, MouseListener, MouseMotionListener, WindowListener {
    private Color colorSelection;
    private String shapeSelection;
    private Point startPoint;
    private PaintingPanel paintingPanel;
    private String name;
    private ArrayList<String> chatText;
    private JTextArea chatArea;
    private JTextField messageField;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public Painter(String name) {
        this.shapeSelection = "line";
        this.colorSelection = Color.RED;
        this.name = name;
        chatText = new ArrayList<>();

        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel holder = new JPanel();
        holder.setLayout(new BorderLayout());

        // Create the paints
        JPanel paintPanel = new JPanel();
        paintPanel.setLayout(new GridLayout(3, 1));

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

        //create shapes panel
        JPanel shapePanel = new JPanel();
        shapePanel.setLayout(new GridLayout(1, 2));

        // add line button
        JButton lineButton = new JButton("Line");
        lineButton.setActionCommand("line");
        lineButton.addActionListener(this);
        shapePanel.add(lineButton);

        //add circle button
        JButton circleButton = new JButton("Circle");
        circleButton.setActionCommand("circle");
        circleButton.addActionListener(this);
        shapePanel.add(circleButton);

        //creates chat panel
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        //creates messagebox
        JLabel messageLabel = new JLabel("Message");
        messageLabel.setBorder(new EmptyBorder(3,3,3,3));
        messageField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.setActionCommand("send");
        sendButton.addActionListener(this);
        sendButton.setBorder(new EmptyBorder(3,3,3,3));
        chatArea = new JTextArea(3,100);
        chatArea.setRows(3);
        chatArea.setMaximumSize(new Dimension(500,100));
        JScrollPane chatScroll = new JScrollPane(chatArea);
        chatScroll.setVerticalScrollBarPolicy(20);
        chatScroll.setHorizontalScrollBarPolicy(31);
        chatScroll.setPreferredSize(new Dimension(500,100));
        chatScroll.setBorder(new TitledBorder("Chat"));
        chatPanel.add(messageLabel, BorderLayout.WEST);
        chatPanel.add(messageField, BorderLayout.CENTER);
        chatPanel.add(sendButton, BorderLayout.EAST);
        chatPanel.add(chatScroll, BorderLayout.NORTH);

        this.paintingPanel = new PaintingPanel();
        paintingPanel.addMouseListener(this);
        paintingPanel.addMouseMotionListener(this);

        // add the panels to the overall panel, holder
        holder.add(paintPanel, BorderLayout.WEST);
        holder.add(shapePanel, BorderLayout.NORTH);
        holder.add(chatPanel, BorderLayout.SOUTH);
        holder.add(paintingPanel, BorderLayout.CENTER);

        // Lastly, connect the holder to the JFrame
        setContentPane(holder);

        // And make it visible to layout all the components on the screen
        setVisible(true);

        try {
            socket = new Socket("localhost", 6969);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Action Event: " + actionEvent.getActionCommand());
        String s = actionEvent.getActionCommand();
        if (s.equals("red"))
            colorSelection = Color.RED;
        else if (s.equals("green"))
            colorSelection = Color.GREEN;
        else if (s.equals("blue"))
            colorSelection = Color.BLUE;
        else if (s.equals("line"))
            shapeSelection = "line";
        else if (s.equals("circle"))
            shapeSelection = "circle";
        else if (s.equals("send")) {
            String msg = name + ": " + messageField.getText() + "\n";
            chatText.add(msg);
            updateText();
            chatArea.updateUI();
            try {
                oos.writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            try {
                oos.writeObject((PaintingPrimitive) l);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (shapeSelection.equals("circle")) {
            System.out.println("adding circle");
            Circle c = new Circle(this.colorSelection, this.startPoint, mouseEvent.getPoint());
            paintingPanel.addPrimitive(c);
            try {
                oos.writeObject((PaintingPrimitive) c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid shape!");
        }
        paintingPanel.updateUI();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) { }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) { }

    @Override
    public void windowOpened(WindowEvent windowEvent) { }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            this.socket.close();
            this.oos.close();
            this.ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) { }

    @Override
    public void windowIconified(WindowEvent windowEvent) { }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) { }

    @Override
    public void windowActivated(WindowEvent windowEvent) { }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) { }

    private void updateText() {
        chatArea.setText("");
        for (String s : chatText)
            chatArea.append(s);
    }

    public void addPrimitive(PaintingPrimitive p) {
        paintingPanel.addPrimitive(p);
        paintingPanel.updateUI();
    }

    public void addText(String s) {
        chatText.add(s);
        updateText();
    }

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name:");
        if (name == null)
            System.exit(1);
        Painter painter = new Painter(name);
        try {
            Object in = painter.ois.readObject();
            if (in instanceof PaintingPanel)
                painter.paintingPanel.addAll((PaintingPanel) in);
            in = painter.ois.readObject();
            if (in instanceof ArrayList<?>)
                if (!((ArrayList<?>) in).isEmpty() && ((ArrayList<?>) in).get(0) instanceof String)
                painter.chatText.addAll((ArrayList<String>) in);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        painter.paintingPanel.updateUI();
        painter.updateText();
        painter.chatArea.updateUI();
        Thread t = new PainterListener(painter.ois, painter);
        t.start();
    }
}
