/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book_managemnet_tree.Book;

/**
 *
 * @author HA DUC
 */
public class Book {
    String bcode;
    String title;
    String author;
    String isbn;
    String publisher;
    int quantity;
    int lended;
    double price;

    public Book(String bcode, String title, String author, String isbn, String publisher, int quantity, int lended, double price) {
        this.bcode = bcode;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.quantity = quantity;
        this.lended = lended;
        this.price = price;
    }

    @Override
    public String toString() {
        return bcode + "," + title + "," + author + "," + isbn + "," + publisher + "," + quantity + "," + lended + "," + price;
    }
}
