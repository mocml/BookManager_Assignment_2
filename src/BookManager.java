
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 15/08/2021    1.0            Vanhv   
 */
/**
 *
 * @author vanhv
 */
public class BookManager {

    Node root;

    public BookManager() {
        root = null;
    }

    void clear() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }
//==================================

    void loadFile(String fname) throws IOException {
        BufferedReader br;
        try (FileReader fr = new FileReader(fname)) {
            br = new BufferedReader(fr);
            String s;
            String a[];
            Book book;
            String bcode, title;
            int quantity, lended;
            int price;
            while (true) {
                s = br.readLine();
                if (s == null || s.trim().length() < 3) {
                    break;
                }
                a = s.split("[|]");
                bcode = a[0].trim();
                title = a[1].trim();
                quantity = Integer.valueOf(a[2].trim());
                lended = Integer.valueOf(a[3].trim());
                price = Integer.valueOf(a[4].trim());
                book = new Book(bcode, title, quantity, lended, price);
                if (search(root, book) == null) {
                    insert(book);
                }
            }
        }
        br.close();
    }

    //------------------
    void loadSave(String fname) throws IOException {
        try (FileWriter fw = new FileWriter(fname); PrintWriter pw = new PrintWriter(fw)) {
            Node p = root;
            while (p != null) {
                String code = p.info.getbCode();
                String title = p.info.getTitle();
                int quantity = p.info.getQuantity();
                int lended = p.info.getLended();
                int price = p.info.getPrice();
                pw.printf("%s     %3s     %3s     %3s     %3s\r\n", code, title, quantity, lended, price);
                //   p = p.next;
                if (p.right == null) {
                    p = p.left;
                } else if (p.left == null) {
                    p = p.right;
                }
            }
        }
    }
//===============================

    Node search(Node p, Book info) {
        if (p == null) {
            return null;
        }
        if (p.info.getbCode().equals(info.getbCode())) {
            return p;
        }
        if (p.info.getbCode().compareTo(info.getbCode()) > 0) {
            return search(p.left, info);
        } else {
            return search(p.right, info);
        }
    }

    void insert(Book info) {
        if (isEmpty()) {
            root = new Node(info);
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info == info) {
                System.out.println("The key " + info + " already exists !");
                return;
            }
            f = p;
            if (p.info.getbCode().compareTo(info.getbCode()) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (f.info.getbCode().compareTo(info.getbCode()) > 0) {
            f.left = new Node(info);
        } else {
            f.right = new Node(info);
        }
    }

    void insertMany(String[] bCode, String[] title, int[] quantity, int[] lended, int[] price) {
        for (int i = 0; i < bCode.length; i++) {
            insert(new Book(bCode[i], title[i], quantity[i], lended[i], price[i]));
        }
    }

    void visit(Node p) {
        if (p != null) {
            System.out.print(p.info + " ");
        }
        System.out.println();
    }

    //add entity to queue tree
    void breath(Node p) {
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            visit(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
        System.out.println();
    }

    /**
     * browse by previous root -> left -> right
     *
     * @param p
     */
    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    /**
     * browse by between left -> root -> right
     *
     * @param p
     */
    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    /**
     * browse by next left -> right -> root
     *
     * @param p
     */
    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    void deleteByCopy(String code) {
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.getbCode().equals(code)) {
                break;
            }
            f = p;
            if (p.info.getbCode().compareTo(code) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        //not found
        if (p == null) {
            return;
        }
        //p is leaf-node
        if (p.left == null && p.right == null) {
            if (f == null) { //p = root
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
                return;
            }
        }
        //p has left son only.
        if (p.left != null && p.right == null) {
            if (f == null) { //p = root
                root = p.left;
            } else {
                if (f.left == p) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }
        //p has right son only.
        if (p.left == null && p.right != null) {
            if (f == null) { //p = root
                root = p.right;
            } else {
                if (f.left == p) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }
        //p has both sons
        if (p.left != null && p.right != null) {
            //find the right-most node in the left sub-tree
            Node q = p.left;
            Node frp, rp;
            frp = null;
            rp = q;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            //rp now is the right-most node
            p.info = rp.info;
            if (frp == null) {//q is the right-most node
                p.left = q.left;
            } else {
                frp.right = rp.left;
            }
        }
    }
    String file1 = "book/book.txt";

    void count(Node p) {
        if (p == null) {
            System.out.println("Have " + 0 + " books");
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node r;
        int k = 0;
        while (!q.isEmpty()) {
            //remove node when take first
            r = q.dequeue();
            k++;
            //add last to queue
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }

        System.out.println("Have " + k + " books");
    }

    void balance(ArrayList<Book> t, int i, int j) {
        if (i > j) {
            return;
        }
        Book x;
        int k = (i + j) / 2;
        x = t.get(k);
        insert(x);
        balance(t, i, k - 1);
        balance(t, k + 1, j);
    }

    void balance(ArrayList<Book> t, Node p) {
        if (p == null) {
            return;
        }
        balance(t, p.left);
        t.add(p.info);
        balance(t, p.right);
    }

    void balance() {
        ArrayList<Book> t = new ArrayList<>();
        balance(t, root);
        clear();
        int n = t.size();
        balance(t, 0, n - 1);
    }
//================================
    Scanner sc = new Scanner(System.in);
    Validation v = new Validation();

    Book getBook() {
        String bcode = v.getString("Code : ", "Please enter character!", "[a-zA-Z0-9]+");
        String title = v.getString("Title : ", "Please enter character!", "[a-zA-Z0-9]+");
        int quantity = v.getInt_2("Quantity : ", "Quantity must be greater than 0");
        int lended = v.getInt_2("Lending : ", "Lending must be greater than 0");
        int price = v.getInt_2("Price : ", "Quantity must be greater than 0");

        Book book = new Book(bcode, title, quantity, lended, price);
        return book;
    }

    //=========================
    void f1() throws IOException {
        clear();
        loadFile(file1);
        breath(root);
    }

    void f2() {
        Book b = getBook();
        if (searchByCode(root, b.getbCode()) == null) {
            insert(b);
            System.err.println("Add Success !!");
            return;
        }
        System.err.println("Book is exist !");
    }

    void f3() {
        if (isEmpty()) {
            System.err.println("Data is Empty");
            return;
        }
        inOrder(root);
    }

    void f4() {
        if (isEmpty()) {
            System.err.println("Data is Empty");
            return;
        }
        breath(root);
    }
////////////////==============

    static void viewFile(String fname) throws Exception {
        File g = new File(fname);
        if (!g.exists()) {
            System.out.println(" The file " + fname + " does not exist!");
            return;
        }
        RandomAccessFile f;
        String s;
        f = new RandomAccessFile(fname, "rw");
        System.out.println(" Content of the file " + fname + ":");
        while ((s = f.readLine()) != null) {
            System.out.println("  " + s);
        }
        f.close();
    }

    void inOrder(ArrayList<Book> list, Node p) {
        if (p == null) {
            return;
        }
        inOrder(list, p.left);
        list.add(p.info);
        inOrder(list, p.right);
    }

    void inOrderToFile() throws IOException {
        try (FileWriter fw = new FileWriter("book/bookS.txt"); PrintWriter pw = new PrintWriter(fw)) {
            ArrayList<Book> list = new ArrayList<>();
            inOrder(list, root);
            for (int i = 0; i < list.size(); i++) {
                Book x = list.get(i);
                pw.printf("%s     %3s     %3s     %3s     %3s\r\n",
                        x.getbCode(), x.getTitle(), x.getQuantity(), x.getLended(), x.getPrice());
            }
        }
    }

    void f5() throws Exception {
        inOrderToFile();
        viewFile("book/bookS.txt");
        System.out.println("Save to file success!");
    }
////////////////==============

    Node searchByCode(Node p, String code) {
        if (p == null) {
            return null;
        }
        if (p.info.getbCode().equals(code)) {
            return p;
        }
        if (p.info.getbCode().compareTo(code) > 0) {
            return searchByCode(p.left, code);
        } else {
            return searchByCode(p.right, code);
        }
    }

    void f6() {
        if (isEmpty()) {
            System.err.println("Data is Empty");
            return;
        }
        visit(searchByCode(root, "2"));
    }

    void f7() {
        if (isEmpty()) {
            System.err.println("Data is Empty");
            return;
        }
        deleteByCopy("3");
        breath(root);
    }

    void f8() {
        if (isEmpty()) {
            System.err.println("Data is Empty");
            return;
        }
        balance();
        breath(root);
    }

    void f9() {
        count(root);
    }

    void f10() {

    }

    void f11() {

    }

    void f12() {

    }

    void f13() {

    }

    void f14() {

    }

    void f15() {

    }
}
