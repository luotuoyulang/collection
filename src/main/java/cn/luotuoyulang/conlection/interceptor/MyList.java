package cn.luotuoyulang.conlection.interceptor;

public interface MyList<E> {

    int size();

    boolean add(E e);

    E get(int index);

}
