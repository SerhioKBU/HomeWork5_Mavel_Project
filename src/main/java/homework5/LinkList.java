package homework5;

public interface LinkList<T> {
    boolean add(T element);
    boolean remove(T element);
    T getFirstElement();
    T getLastElement();
    T get(int index);
    int size();
}
