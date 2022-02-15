package com.juara.yayasan.Utils;

import java.util.ArrayList;
import java.util.List;

public class GenericData<T> {
    List<T> list = new ArrayList<T>();

    public GenericData() {

    }

    public void add(T t) {
        list.add(t);
    }

    public void remove(T t){
        list.remove(t);
    }

    public int size(){
        return list.size();
    }
}
