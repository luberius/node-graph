package com.syahril;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JFrame implements MouseWheelListener, MouseMotionListener, MouseListener {
    private List<Node> nodes = new ArrayList<>();
    private float zoom = 1;
    private int cameraPosX = 0, cameraPosY = 0;

    private int initialX, initialY;
    private int lastX, lastY;

    public Canvas() {
        setTitle(DefaultConfig.TITLE);
        setSize(DefaultConfig.FRAME_WIDTH, DefaultConfig.FRAME_HEIGHT);
        getContentPane().setBackground(Color.decode("0xF5F6FA"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.scale(zoom, zoom);
        for (Node node : nodes) {
            g.setColor(node.getColor());
            g.fillOval(node.getX() + cameraPosX, node.getY() + cameraPosY, node.getWidth(), node.getHeight());
            g.setColor(Color.decode("0x2f3640"));
            FontMetrics fm = g.getFontMetrics();
            double textWidth = fm.getStringBounds(node.getLabel(), g).getWidth();
            g.drawString(
                    node.getLabel(),
                    (int) ((node.getX() + (node.getWidth() / 2) - (textWidth / 2)) + cameraPosX),
                    (node.getY() + (node.getHeight() / 2) + (fm.getMaxAscent() / 2)) + cameraPosY
            );
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialX = e.getX();
        initialY = e.getY();
        //System.out.println("initial x: " + initialX + ", initial y:" + initialY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("last x: " + lastX + ", last y:" + lastY);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        moveCamera();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            zoomIn();
        } else {
            zoomOut();
        }
    }

    public void popup() {
        setVisible(true);
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    private void zoomIn() {
        if (zoom < 10f) {
            zoom += 0.05;
            repaint();
        }
    }

    private void zoomOut() {
        if (zoom > 0.3) {
            zoom -= 0.05;
            repaint();
        }
    }

    private void moveCamera() {
        int finalX = (initialX - lastX) * -1;
        int finalY = (initialY - lastY) * -1;
        cameraPosX += (finalX / 20);
        cameraPosY += (finalY / 20);
//        System.out.println("=====================================================");
//        System.out.println("initial x: " + initialX + ", initial y:" + initialY);
//        System.out.println("last x: " + lastX + ", last y:" + lastY);
//        System.out.println("final x: " + finalX + ", final y:" + finalY);
//        System.out.println("camera x: " + cameraPosX + ", camera y:" + cameraPosY);
//        System.out.println("=====================================================\n");
        repaint();
    }
}
