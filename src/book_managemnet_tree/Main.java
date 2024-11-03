/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package book_managemnet_tree;

import java.io.IOException;

/**
 *
 * @author HA DUC
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        MyBSTree bookTree = new MyBSTree();

        // Input and add a book
        bookTree.inputAndAddToTree();
        // Load data from file
        bookTree.loadDataFromFile("Resourse/books.txt");

        // Display all books using in-order traversal
        bookTree.inorder();

        // Save the tree back to the file
        try {
            bookTree.saveToFile("Resourse/books.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Search for a book by bcode
        Book book = bookTree.search("B123");
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found.");
        }

        // Delete a book by bcode
        bookTree.deleteByBcode("B123");
    }
}
