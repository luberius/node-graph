package com.syahril;

public class Main {

    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        canvas.addNode(new Node(10, 139, 30, 30, "A"));
        canvas.addNode(new Node(120, 20, 30, 30, "B"));
        canvas.addNode(new Node(300, 300, 30, 30, "C"));
        canvas.addNode(new Node(200, 500, 30, 30, "D"));
        canvas.addNode(new Node(400, 10, 30, 30, "E"));
        canvas.addNode(new Node(700, 450, 30, 30, "F"));
        canvas.popup();
    }
}
