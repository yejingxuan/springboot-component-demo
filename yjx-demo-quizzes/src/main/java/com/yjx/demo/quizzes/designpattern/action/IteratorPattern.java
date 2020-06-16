package com.yjx.demo.quizzes.designpattern.action;

import java.util.Iterator;
import lombok.Data;

public class IteratorPattern {

    public static void main(String[] args) {
        Users users = new Users();
        users.add(new Item("1","abc"));
        users.add(new Item("2","dfg"));

        UserIterator iterator = users.createIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }


    static class Users {

        private Item[] items = new Item[5];
        private int position = 0;

        public void add(Item item) {
            items[position++] = item;
        }

        public UserIterator createIterator() {
            return new UserIterator(items);
        }
    }


    static class UserIterator implements Iterator<Item> {

        private Item[] items;
        private int position = 0;

        public UserIterator(Item[] items) {
            this.items = items;
        }

        @Override
        public boolean hasNext() {
            return position < items.length;
        }

        @Override
        public Item next() {
            return items[position++];
        }
    }

    @Data
    static class Item {

        private String name;
        private String value;

        public Item(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public Item() {
            super();
        }
    }
}
