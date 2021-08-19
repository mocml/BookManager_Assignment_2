/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 15/08/2021    1.0            Vanhv   
 */


import java.util.*;

/**
 *
 * @author vanhv
 */
public class MyQueue {

    LinkedList<Node> t;

    public MyQueue() {
        t = new LinkedList<>();
    }

    void clear() {
        t.clear();
    }

    boolean isEmpty() {
        return t.isEmpty();
    }

    // add entity to queue
    void enqueue(Node p) {
        t.addLast(p);
    }

    // get entity from queue
    //First in last out, so get last entity and remove this entity into queue
    Node dequeue() {
        if (isEmpty()) {
            return null;
        }
        return t.removeFirst();
    }
}
