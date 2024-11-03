
import book_managemnet_tree.Book;
import book_managemnet_tree.Node;
import java.io.*;
import java.util.*;

public class MyBSTree {

    Node root;

    public MyBSTree() {
        root = null;
    }

    // 1.1 Load data from file
    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");
                Book book = new Book(
                        bookData[0], bookData[1], bookData[2], bookData[3],
                        bookData[4], Integer.parseInt(bookData[5]), Integer.parseInt(bookData[6]),
                        Double.parseDouble(bookData[7])
                );
                add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1.2 Input & add to tree
    public void add(Book book) {
        root = insert(root, book);
    }

    private Node insert(Node node, Book book) {
        if (node == null) {
            return new Node(book);
        }
        if (book.bcode.compareTo(node.info.bcode) < 0) {
            node.left = insert(node.left, book);
        } else if (book.bcode.compareTo(node.info.bcode) > 0) {
            node.right = insert(node.right, book);
        }
        return node;
    }

    // 1.3 Display data by in-order traversal
    public void displayInOrder() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.info);
            inOrderTraversal(node.right);
        }
    }

    // 1.4 Save book tree to file by in-order traversal
    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            saveInOrder(root, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveInOrder(Node node, BufferedWriter bw) throws IOException {
        if (node != null) {
            saveInOrder(node.left, bw);
            bw.write(node.info.toString());
            bw.newLine();
            saveInOrder(node.right, bw);
        }
    }

    // 1.5 Search by bcode
    public Book search(String bcode) {
        return searchByBcode(root, bcode);
    }

    private Book searchByBcode(Node node, String bcode) {
        if (node == null) {
            return null;
        }
        if (bcode.equals(node.info.bcode)) {
            return node.info;
        }
        if (bcode.compareTo(node.info.bcode) < 0) {
            return searchByBcode(node.left, bcode);
        } else {
            return searchByBcode(node.right, bcode);
        }
    }

    // 1.6 Delete by bcode by copying
    public void deleteByBcode(String bcode) {
        root = deleteByCopying(root, bcode);
    }

    private Node deleteByCopying(Node node, String bcode) {
        if (node == null) {
            return null;
        }
        if (bcode.compareTo(node.info.bcode) < 0) {
            node.left = deleteByCopying(node.left, bcode);
        } else if (bcode.compareTo(node.info.bcode) > 0) {
            node.right = deleteByCopying(node.right, bcode);
        } else {
            // Found the node
            if (node.info.lended > 0) {
                System.out.println("Book is currently lended, cannot delete.");
                return node;  // Do not delete if lended > 0
            }
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // Node with two children: copy data from the right subtree's min value
                Node minNode = findMin(node.right);
                node.info = minNode.info;
                node.right = deleteByCopying(node.right, minNode.info.bcode);  // Delete copied node
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
