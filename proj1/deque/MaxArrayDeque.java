package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> cmp;
    public MaxArrayDeque(Comparator<T> c){
        cmp = c;
    }

    public T max(Comparator<T> c){
        T returnMax = this.get(0);
        if (isEmpty()){
            return null;
        }
        for (T i: this){
            if (c.compare(i,returnMax)>0){
                returnMax = i;
            }
        }
        return returnMax;
    }

    public T max(){
        T returnMax = this.get(0);
        if (isEmpty()){
            return null;
        }
        for (T i: this){
            if (cmp.compare(i,returnMax)>0){
                returnMax = i;
            }
        }
        return returnMax;
    }

}
