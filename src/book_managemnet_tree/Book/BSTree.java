package book_managemnet_tree.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BSTree {
    BSTNode root;

    public BSTree() {
        root = null;
    }

    // Load data from file and add to the tree
    public void loadDataFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Book book = new Book(parts[0], parts[1], parts[2], parts[3], parts[4], 
                    Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]));
                add(book);  // Requirement 1: Adding a book to the BST
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Validate and add a book to the tree
    public void add(Book book) {
        if (validateBookData(book)) {
            root = addRecursive(root, book);  // Requirement 1: Adding books recursively
        } else {
            System.out.println("Invalid book data, cannot add to the tree.");
        }
    }

    private boolean validateBookData(Book book) {
        return book != null && book.bcode != null && !book.bcode.isEmpty() && book.price >= 0;
    }

    private BSTNode addRecursive(BSTNode node, Book book) {
        if (node == null) {
            return new BSTNode(book);
        }
        if (book.bcode.compareTo(node.data.bcode) < 0) {
            node.left = addRecursive(node.left, book);
        } else if (book.bcode.compareTo(node.data.bcode) > 0) {
            node.right = addRecursive(node.right, book);
        }
        return node;
    }

    // In-order traversal to display all books
    public void inOrderTraversal() {
        inOrderRecursive(root);  // Requirement 2: Display books in sorted order
    }

    private void inOrderRecursive(BSTNode node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.println(node.data);
            inOrderRecursive(node.right);
        }
    }

    // Save all books to file
    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            saveInOrder(root, bw);  // Requirement 3: Save books in sorted order to file
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void saveInOrder(BSTNode node, BufferedWriter bw) throws IOException {
        if (node != null) {
            saveInOrder(node.left, bw);
            bw.write(node.data.toString());
            bw.newLine();
            saveInOrder(node.right, bw);
        }
    }

    // Search for a book by bcode
    public Book search(String bcode) {
        return searchRecursive(root, bcode);  // Requirement 4: Search by bcode
    }

    private Book searchRecursive(BSTNode node, String bcode) {
        if (node == null) {
            return null;
        }
        if (bcode.equals(node.data.bcode)) {
            return node.data;
        }
        if (bcode.compareTo(node.data.bcode) < 0) {
            return searchRecursive(node.left, bcode);
        } else {
            return searchRecursive(node.right, bcode);
        }
    }

    // Delete a book by bcode using Copying method
    public void deleteByCopying(String bcode) {
        Book book = search(bcode);
        if (book != null && book.lended == 0) {  // Requirement 5: Only delete if not lent out
            root = deleteByCopyingRecursive(root, bcode);
        } else {
            System.out.println("Book cannot be deleted. Either not found or currently lent out.");
        }
    }

    private BSTNode deleteByCopyingRecursive(BSTNode node, String bcode) {
        if (node == null)
            return null;

        if (bcode.compareTo(node.data.bcode) < 0) {
            node.left = deleteByCopyingRecursive(node.left, bcode);
        } else if (bcode.compareTo(node.data.bcode) > 0) {
            node.right = deleteByCopyingRecursive(node.right, bcode);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            BSTNode largest = findLargest(node.left);
            node.data = largest.data;
            node.left = deleteByCopyingRecursive(node.left, largest.data.bcode);
        }
        return node;
    }

    // Delete a book by bcode using Merging method
    public void deleteByMerging(String bcode) {
        Book book = search(bcode);
        if (book != null && book.lended == 0) {  // Requirement 5: Only delete if not lent out
            root = deleteByMergingRecursive(root, bcode);
        } else {
            System.out.println("Book cannot be deleted. Either not found or currently lent out.");
        }
    }

    private BSTNode deleteByMergingRecursive(BSTNode node, String bcode) {
        if (node == null)
            return null;

        if (bcode.compareTo(node.data.bcode) < 0) {
            node.left = deleteByMergingRecursive(node.left, bcode);
        } else if (bcode.compareTo(node.data.bcode) > 0) {
            node.right = deleteByMergingRecursive(node.right, bcode);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            BSTNode leftSubtree = node.left;
            BSTNode rightSubtree = node.right;
            node = leftSubtree;
            BSTNode largestInLeft = findLargest(leftSubtree);
            largestInLeft.right = rightSubtree;
        }
        return node;
    }

    private BSTNode findLargest(BSTNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // Balance the tree
    public void balanceTree() {
        ArrayList<Book> books = new ArrayList<>();
        storeInOrder(root, books);
        root = buildBalancedTree(books, 0, books.size() - 1);  // Requirement 6: Balance the tree
    }

    private void storeInOrder(BSTNode node, ArrayList<Book> books) {
        if (node != null) {
            storeInOrder(node.left, books);
            books.add(node.data);
            storeInOrder(node.right, books);
        }
    }

    private BSTNode buildBalancedTree(ArrayList<Book> books, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        BSTNode node = new BSTNode(books.get(mid));

        node.left = buildBalancedTree(books, start, mid - 1);
        node.right = buildBalancedTree(books, mid + 1, end);

        return node;
    }

    // Breadth-first traversal of the tree
    public void breadthFirstTraversal() {
        if (root == null)
            return;

        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BSTNode node = queue.poll();
            System.out.println(node.data);  // Requirement 7: Display books in breadth-first order

            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }

    // Count the total number of books in the tree
    public int countBooks() {
        return countBooksRecursive(root);  // Requirement 8: Count total books
    }

    private int countBooksRecursive(BSTNode node) {
        if (node == null)
            return 0;
        return 1 + countBooksRecursive(node.left) + countBooksRecursive(node.right);
    }

    // Search for a book by title
    public Book searchByTitle(String title) {
        return searchByTitleRecursive(root, title);  // Requirement 9: Search by title
    }

    private Book searchByTitleRecursive(BSTNode node, String title) {
        if (node == null)
            return null;
        if (node.data.title.equalsIgnoreCase(title))
            return node.data;

        Book leftSearch = searchByTitleRecursive(node.left, title);
        if (leftSearch != null)
            return leftSearch;

        return searchByTitleRecursive(node.right, title);
    }

    // List unreturned lending info by bcode
    public Book searchLendedByBcode(String bcode) {
        Book book = search(bcode);  // Requirement 10: Display unreturned lending info by bcode
        if (book == null) {
            System.out.println("Book not found.");
            return null;
        }
        if (book.lended > 0) {
            System.out.println("Book is currently lent out with " + book.lended + " copies on loan.");
        } else {
            System.out.println("Book is available.");
        }
        return book;
    }
}
