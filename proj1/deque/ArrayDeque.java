package deque;

public class ArrayDeque<T> {
    private T[] items;
    public  int size;

    public ArrayDeque(){
        items = (T[]) new Object[10];
        size = 0;
    }

    public void resize(int x){
        T[] newArray = (T[]) new Object[x];
        System.arraycopy(items,0,newArray,0,x);
        items = newArray;
    }

    public void addFirst(T x){


    }

}
