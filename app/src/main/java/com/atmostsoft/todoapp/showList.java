package com.atmostsoft.todoapp;

import java.util.ArrayList;

public class showList {
    String listName;
    ArrayList<String> item;

    public showList() {
    }

    public showList(String listName, ArrayList<String> item) {
        this.listName = listName;
        this.item = item;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ArrayList<String> getItem() {
        return item;
    }

    public void setItem(ArrayList<String> item) {
        this.item = item;
    }

    public String getItemFromIndex(int index)
    {
        return item.get(index);
    }
}
