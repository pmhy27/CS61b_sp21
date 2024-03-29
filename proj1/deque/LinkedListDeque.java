package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private class Node {
        private T items;
        private Node next;
        private Node previous;

        private Node(T i, Node d, Node p) {
            items = i;
            next = d;
            previous = p;
        }
    }

    private final Node frontSentinel;
    private final Node endSentinel;
    private int size;

    public LinkedListDeque() {
        frontSentinel = new Node(null, null, null);
        endSentinel = new Node(null, null, null);
        frontSentinel.next = endSentinel;
        frontSentinel.previous = endSentinel;
        endSentinel.next = frontSentinel;
        endSentinel.previous = frontSentinel;
        size = 0;
    }

    /***/
    public void addFirst(T i) {
        Node newNode = new Node(i, null, null);
        newNode.next = frontSentinel.next;
        frontSentinel.next.previous = newNode;
        frontSentinel.next = newNode;
        newNode.previous = frontSentinel;
        size++;
    }
    public void addLast(T i) {
        Node newNode = new Node(i, null, null);
        newNode.previous = endSentinel.previous;
        endSentinel.previous.next = newNode;
        endSentinel.previous = newNode;
        newNode.next = endSentinel;
        size++;
    }


    public int size(){
        return size;
    }

    public void printDeque() {
        Node pointer = frontSentinel.next;
        while (pointer != endSentinel){
            System.out.print(pointer.items);
            System.out.print(" ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size > 0) {
            T removing = frontSentinel.next.items;
            frontSentinel.next.next.previous = frontSentinel;
            frontSentinel.next = frontSentinel.next.next;
            size--;
            return removing;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (size > 0) {
            T removing = endSentinel.previous.items;
            endSentinel.previous.previous.next = endSentinel;
            endSentinel.previous = endSentinel.previous.previous;
            size--;
            return removing;
        } else {
            return null;
        }
    }

    public T get(int x) {
        if (x > size){
            return null;
        } else {
            Node pointer = frontSentinel.next;
            for (int i = 0; i < x; i++) {
                pointer = pointer.next;
            }
            return pointer.items;
        }
    }

    public T getRecursive(int x){
        if (x > size){
            return null;
        } else {
            return helper_getRecursive(frontSentinel.next,x);
        }
    }
    private T helper_getRecursive(Node n, int x){
        if (x == 0) {
            return n.items;
        } else {
            return helper_getRecursive(n.next,x-1);
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private Node nextNode;
        public LinkedListDequeIterator(){
            nextNode = frontSentinel.next;
        }

        @Override
        public boolean hasNext() {
            return (nextNode != endSentinel);
        }

        @Override
        public T next() {
            T returnNode = nextNode.items;
            nextNode = nextNode.next;
            return returnNode;
        }
    }

    public boolean equals(Object o){
        if( o instanceof Deque){
            if( this.size == ((Deque<T>) o).size() ){
                for (int i = 0; i < size; i++){
                    if( !this.get(i).equals(((Deque<T>) o).get(i))){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        LinkedListDeque<Integer> aa = new LinkedListDeque<>();
            aa.addFirst(3);
            aa.addFirst(2);
            aa.addFirst(1);
            aa.addLast(4);
            aa.addLast(5);
            aa.addLast(6);
            aa.printDeque();

//      Iterator<Integer> seer = aa.iterator();
//      while (seer.hasNext()){
//          System.out.println(seer.next());
//      }

        for (int i: aa){
            System.out.println(i);
        }
    }





}
