/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.datasalapp;

/**
 *
 * @author ChristophJohnEric
 */
public class Edge<E> {
    private Node<E> node1;

    private Node<E> node2;

    private int weight;

    public Edge(Node<E> node1, Node<E> node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public Node<E> fromNode() {
        return node1;
    }

    public Node<E> toNode() {
        return node2;
    }

    public boolean isBetween(Node<E> node1, Node<E> node2) {
        return (this.node1 == node1 && this.node2 == node2);
    }
}
