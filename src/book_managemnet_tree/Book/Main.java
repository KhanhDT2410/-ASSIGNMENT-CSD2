package book_managemnet_tree;

import static book_managemnet_tree.ReaderBST.addReader;
import static book_managemnet_tree.ReaderBST.deleteByRcode;
import static book_managemnet_tree.ReaderBST.displayPreOrder;
import static book_managemnet_tree.ReaderBST.loadFromFile;
import static book_managemnet_tree.ReaderBST.saveToFile;
import static book_managemnet_tree.ReaderBST.searchByRcode;
import static book_managemnet_tree.ReaderBST.searchReaderByName;

public class Main {

    public static void menu2() {

        while (true) {
            System.out.println("====Reader Management======");
            System.out.println("1.Load data from file");
            System.out.println("2.Input and add to tree");
            System.out.println("3.Display data by pre-order traversal");
            System.out.println("4.Save reader tree to file by pre-order traversal");
            System.out.println("5.Search by rcode");
            System.out.println("6.Delete by copying rcode");
            System.out.println("7.Search by name");
            System.out.println("8.Search lending books by rcode");
            System.out.println("9.Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = Validate.checkInputIntLimit(1, 9);
            switch (choice) {
                case 1:
                    loadFromFile("Resourse\\readers.txt");
                    break;

                case 2:
                    addReader();

                    break;
                case 3:
                    displayPreOrder();
                    break;

                case 4:
                    saveToFile("Resourse\\readers.txt");
                    break;

                case 5:
                    System.out.print("Enter rcode to search: ");
                    String rcode = Validate.checkInputString();
                    searchByRcode(rcode);
                    break;
                case 6:
                    System.out.print("Enter rcode to delete: ");
                    rcode = Validate.checkInputString();
                    deleteByRcode(rcode);
                    break;
                case 7:

                    searchReaderByName();
                    break;
                case 8:

                case 9:
                    return;

            }

        }

    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("====Library Management Program====");
            System.out.println("1.Book");
            System.out.println("2.Reader");
            System.out.println("3.Lending");
            System.out.println("4.Exit");
            System.out.println();
            System.out.print("Enter your choice");
            int choice = Validate.checkInputIntLimit(1, 4);
            switch (choice) {
                case 1:
                    menu1();
                    break;
                case 2:
                    menu2();
                    break;
                case 3:
                    menu3();
                    break;
                case 4:
                    return;

            }

        }
    }

}
