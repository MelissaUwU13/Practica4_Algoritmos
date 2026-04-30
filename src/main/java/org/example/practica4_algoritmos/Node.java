package org.example.practica4_algoritmos;

public class Node<T> {
    private T content;
    private Node<T> up, down, left, right, downLeft, downRight, upLeft, upRight;

    public Node(T content) {
        this.content = content;
    }

    // Getters y setters
    public T getContent() { return content; }
    public void setContent(T content) { this.content = content; }

    public Node<T> getUp() { return up; }
    public void setUp(Node<T> up) { this.up = up; }
    public Node<T> getDown() { return down; }
    public void setDown(Node<T> down) { this.down = down; }
    public Node<T> getLeft() { return left; }
    public void setLeft(Node<T> left) { this.left = left; }
    public Node<T> getRight() { return right; }
    public void setRight(Node<T> right) { this.right = right; }
    public Node<T> getDownLeft() { return downLeft; }
    public void setDownLeft(Node<T> downLeft) { this.downLeft = downLeft; }
    public Node<T> getDownRight() { return downRight; }
    public void setDownRight(Node<T> downRight) { this.downRight = downRight; }
    public Node<T> getUpLeft() { return upLeft; }
    public void setUpLeft(Node<T> upLeft) { this.upLeft = upLeft; }
    public Node<T> getUpRight() { return upRight; }
    public void setUpRight(Node<T> upRight) { this.upRight = upRight; }
}