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
class Node {

    Book info;
    Node left;
    Node right;

    public Node() {
    }

    public Node(Book info, Node left, Node right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    Node(Book info) {
        this.info = info;
        left = right = null;
    }

}
