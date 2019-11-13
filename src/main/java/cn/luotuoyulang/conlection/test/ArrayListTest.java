package cn.luotuoyulang.conlection.test;

import cn.luotuoyulang.conlection.list.MyArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
//        List list = new ArrayList();
        MyArrayList<String> myArrayList = new MyArrayList();
        boolean add = myArrayList.add("12345467");
        System.out.println(myArrayList.get(0));
    }
}