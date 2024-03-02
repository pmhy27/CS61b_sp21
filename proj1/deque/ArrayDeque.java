package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    private void resize(int x){
        T[] newArray = (T[]) new Object[x];
        if(nextFirst + 1 <= nextLast - 1){
            System.arraycopy(items, nextFirst + 1,newArray,0,size);
        } else {
            System.arraycopy(items, nextFirst + 1,newArray,0, items.length -1 -nextFirst);
            System.arraycopy(items,0,newArray,items.length -1 -nextFirst, nextLast);
        }
        nextFirst = newArray.length - 1;
        nextLast = size;
        items = newArray;
    }

    public void addFirst(T x){
        items[nextFirst] = x;
        if(nextFirst > 0 ) {
            nextFirst = nextFirst - 1;
        } else {
            nextFirst = items.length - 1;
        }
        size++;

        if (size >= items.length - 1) {
            resize(size * 2);
        }
    }

    public void addLast(T x){

        items[nextLast] = x;
        if(nextLast < items.length - 1 ) {
            nextLast = nextLast + 1;
        } else {
            nextLast = 0;
        }
        size++;

        if (size >= items.length - 1) {
            resize(size * 2);
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(get(i));
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T removedItem;
        if(nextFirst != (items.length - 1) ) {
            removedItem = items[nextFirst + 1];
            nextFirst = nextFirst + 1;
        } else {
            removedItem = items[0];
            nextFirst = 0;
        }

        items[nextFirst] = null;
        size--;

        if (size > 8 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        T removedItem;
        if(nextLast != 0 ) {
            removedItem = items[nextLast - 1];
            nextLast = nextLast - 1;
        } else {
            removedItem = items[items.length - 1];
            nextLast = items.length - 1;
        }

        items[nextLast] = null;
        size--;

        if (items.length > 8 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    public T get(int i){
        if( i + 1 > size){
            return null;
        }
        if (nextFirst + 1 + i < items.length){
            return (items[nextFirst + i + 1]);
        } else {
            return items[i - (items.length - (nextFirst + 1))];
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int nextPos;
        public ArrayDequeIterator(){
            nextPos = nextFirst + 1;
            if(nextPos == items.length){
                nextPos = 0;
            }
        }

        @Override
        public boolean hasNext() {
            return nextPos != nextLast;
        }

        @Override
        public T next() {
            T returnItem = items[nextPos];
            nextPos += 1;
            if(nextPos == items.length){
                nextPos = 0;
            }
            return returnItem;
        }
    }

    public boolean equals(Object o){
        if( o instanceof ArrayDeque){
            if( this.size == ((ArrayDeque<T>) o).size ){
                for (int i = 0; i < size; i++){
                    if( !this.get(i).equals(((ArrayDeque<T>) o).get(i))){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<Integer>();
        ArrayDeque.addLast(0);
        ArrayDeque.addLast(1);
        ArrayDeque.addFirst(2);
        ArrayDeque.addLast(3);
        ArrayDeque.removeFirst();
        ArrayDeque.get(2);
        ArrayDeque.addLast(6);
        ArrayDeque.get(3);
        ArrayDeque.addFirst(8);
        ArrayDeque.removeLast();
        ArrayDeque.removeFirst();
        ArrayDeque.get(1);
        ArrayDeque.addLast(12);
        ArrayDeque.removeLast();
        ArrayDeque.get(1);
        ArrayDeque.addFirst(15);
        ArrayDeque.addFirst(16);
        ArrayDeque.addFirst(17);
        ArrayDeque.addLast(18);
        ArrayDeque.addFirst(19);
        ArrayDeque.get(7);


//        Iterator<Integer> seer = aa.iterator();
//
//        while (seer.hasNext()){
//            System.out.println(seer.next());
//        }

        for(int i : ArrayDeque){
            System.out.println(i);
        }




    }

}
