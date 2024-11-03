package book_managemnet_tree.Book;

public class BSTNode {
    public Book data;
    public BSTNode left, right;

    public BSTNode(Book data) {
        this.data = data;
        this.left = this.right = null;
    }
}
