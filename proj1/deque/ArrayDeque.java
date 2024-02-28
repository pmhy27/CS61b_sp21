package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    public  int size;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    public void resize(int x){
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
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        if(nextFirst > 0 ) {
            nextFirst = nextFirst - 1;
        } else {
            nextFirst = items.length - 1;
        }
        size++;
    }

    public void addLast(T x){
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        if(nextLast < items.length - 1 ) {
            nextLast = nextLast + 1;
        } else {
            nextLast = 0;
        }
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
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


    public static void main(String[] args) {
        ArrayDeque<Integer> aa = new ArrayDeque<Integer>();
        aa.addFirst(2);
        aa.addFirst(1);
        aa.addLast(3);
        aa.addLast(4);
        aa.addLast(5);
        aa.addLast(6);
        aa.addLast(7);

//        Iterator<Integer> seer = aa.iterator();
//
//        while (seer.hasNext()){
//            System.out.println(seer.next());
//        }

        for(int i : aa){
            System.out.println(i);
        }



    }

}
