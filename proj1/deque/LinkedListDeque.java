package deque;

public class LinkedListDeque<T> {
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
    int size;

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

    public boolean isEmpty() {
        return frontSentinel.next == endSentinel;
    }

    private int size(){
        return size;
    }

    public void printDeque() {
        Node pointer = frontSentinel.next;
        while (pointer != endSentinel){
            System.out.println(pointer.items);
            pointer = pointer.next;
        }
        System.out.println();
    }

    public void removeFirst() {
        if (size > 0) {
            frontSentinel.next.next.previous = frontSentinel;
            frontSentinel.next = frontSentinel.next.next;
            size--;
        }
            }

    public void removeLast() {
        if (size > 0) {
            endSentinel.previous.previous.next = endSentinel;
            endSentinel.previous = endSentinel.previous.previous;
            size--;
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
    public T helper_getRecursive(Node n, int x){
        if (x == 0) {
            return n.items;
        } else {
            return helper_getRecursive(n.next,x-1);
        }
    }

    public static void main(String[] args){
            LinkedListDeque<Integer> aa = new LinkedListDeque<>();
            aa.addFirst(3);
            aa.addFirst(2);
            aa.addFirst(1);
            aa.addLast(4);
            aa.addLast(5);
            aa.addLast(6);
            System.out.println(aa.getRecursive(2));
        }

}
