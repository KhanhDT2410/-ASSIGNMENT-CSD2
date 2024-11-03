/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book_managemnet_tree;

/**
 *
 * @author HA DUC
 */
class Node {

    Book data;
    Node left, right;

    public Node(Book book) {
        this.data = book;
        this.left = null;
        this.right = null;
    }
}


