package com.el.cloud.designpattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman.Zhang
 * @date 2020/4/26
 * @description:
 */
public class Caretaker {
    private List<Memento> mementos = new ArrayList<>();


    public void add(Memento memento){
        mementos.add(memento);
    }

    public Memento getMemento(int index){
        return mementos.get(index);
    }
}
