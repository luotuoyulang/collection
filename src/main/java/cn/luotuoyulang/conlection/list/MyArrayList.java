package cn.luotuoyulang.conlection.list;

import cn.luotuoyulang.conlection.interceptor.MyList;

import java.util.Arrays;

/**
 * 数组的特征：保证我们的有序性，随机查询效率非常高，缺点：增删改效率非常低
 * List Set Map 底层 都会采用数组存放我们的数据。数组 + 链表 + 红黑树
 * @param <E>
 */
public class MyArrayList<E> implements MyList<E> {

    /**
     * 创建无参构造函数时给数组容量复制为空
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    //  2的32次方 - 8
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private int size;

    protected transient int modCount = 0;

    /**
     * Default initial capacity.
     * 当我们添加元素时，我们的默认容量为10
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * elementData 数组存放我们ArrayList 所有的数据 transient 作用不能序列化
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer. Any
     * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will be expanded to DEFAULT_CAPACITY when the first element is added.
     */
  //  transient Object[] elementData; // non-private to simplify nested class access

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //  从 1 开始复制
        elementData[size++] = e;
        return true;
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E)elementData(index);
    }
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
//        高并发
//        第几次扩容
//        get 方法
        modCount++;

        // overflow-conscious code  判断我们的数据是否需要继续扩容
        if (minCapacity - elementData.length > 0)

         // 对数组进行扩容
         grow(minCapacity);
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        // 获取我们的数据的长度old 原来的 new 新的 原理的数组容量 0;
        int oldCapacity = elementData.length;
        //  新的容量 = 原来的容量 + 原来的容量 / 2
        //  newCapacity = oldCapacity + oldCapacity / 2
        int newCapacity = oldCapacity + (oldCapacity >> 1);
//        注意：第一次
        if (newCapacity - minCapacity < 0)
            // 新的容量 = 10 作用：第一次对我们数组作初始化容量的操作
            newCapacity = minCapacity;

        //  2的32次方 - 8
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        //  开始对我们的数组实现扩容
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}
