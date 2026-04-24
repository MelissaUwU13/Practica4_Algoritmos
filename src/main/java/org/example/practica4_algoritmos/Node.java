package org.example.practica4_algoritmos;

import java.util.ArrayList;

public class Node {
    private Casilla casilla; // Agregamos referencia a la Casilla
    private Node up, down, left, right, downLeft, downRight, upLeft, upRight;

    public Node(Casilla casilla) {
        this.casilla = casilla;
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
        this.downLeft = null;
        this.downRight = null;
        this.upLeft = null;
        this.upRight = null;
    }

    public void delete() {
        // Elimina todas las referencias a este nodo desde sus vecinos
        if (up != null && up.down == this) up.down = null;
        if (down != null && down.up == this) down.up = null;
        if (left != null && left.right == this) left.right = null;
        if (right != null && right.left == this) right.left = null;
        if (upLeft != null && upLeft.downRight == this) upLeft.downRight = null;
        if (upRight != null && upRight.downLeft == this) upRight.downLeft = null;
        if (downLeft != null && downLeft.upRight == this) downLeft.upRight = null;
        if (downRight != null && downRight.upLeft == this) downRight.upLeft = null;
        // También limpia los enlaces del propio nodo
        up = down = left = right = upLeft = upRight = downLeft = downRight = null;
    }

    public boolean isNeighbor(Node input) {
        if (input == null) return false;
        // Devuelve true si input está en alguna de las 8 direcciones
        return (input == up) || (input == down) || (input == left) || (input == right) ||
                (input == upLeft) || (input == upRight) || (input == downLeft) || (input == downRight);
    }

    public boolean isMatchValue(Node input) {
        if (input == null) return false;
        int valor1 = this.casilla.getValor();
        int valor2 = input.casilla.getValor();
        return (valor1 == valor2) || (valor1 + valor2 == 10);
    }

    public ArrayList<Node> getNeighbors() {
        ArrayList<Node> neighbors = new ArrayList<>();
        if (up != null) neighbors.add(up);
        if (down != null) neighbors.add(down);
        if (left != null) neighbors.add(left);
        if (right != null) neighbors.add(right);
        if (upLeft != null) neighbors.add(upLeft);
        if (upRight != null) neighbors.add(upRight);
        if (downLeft != null) neighbors.add(downLeft);
        if (downRight != null) neighbors.add(downRight);
        return neighbors;
    }

    // Getters y setters
    public Casilla getCasilla() { return casilla; }
    public void setCasilla(Casilla casilla) { this.casilla = casilla; }

    public Node getUp() { return up; }
    public void setUp(Node up) { this.up = up; }
    public Node getDown() { return down; }
    public void setDown(Node down) { this.down = down; }
    public Node getLeft() { return left; }
    public void setLeft(Node left) { this.left = left; }
    public Node getRight() { return right; }
    public void setRight(Node right) { this.right = right; }
    public Node getDownLeft() { return downLeft; }
    public void setDownLeft(Node downLeft) { this.downLeft = downLeft; }
    public Node getDownRight() { return downRight; }
    public void setDownRight(Node downRight) { this.downRight = downRight; }
    public Node getUpLeft() { return upLeft; }
    public void setUpLeft(Node upLeft) { this.upLeft = upLeft; }
    public Node getUpRight() { return upRight; }
    public void setUpRight(Node upRight) { this.upRight = upRight; }
}