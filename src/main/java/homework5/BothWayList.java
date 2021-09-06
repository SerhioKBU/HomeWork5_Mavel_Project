package homework5;

import lombok.Data;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BothWayList<T> implements LinkList<T>, Iterable<T> {
    private ListElement<T> head;
    private ListElement<T> tail;
    private int size = 0;
    private int modCount = 0;

    @Override
    public boolean add(T element) {
        if (head == null) {
            head = new ListElement<>(element);
            tail = head;
        } else {
            tail.next = new ListElement<>(element);
            tail.next.prev = tail;
            tail = tail.next;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(T value){
        if (value == null){
            for (ListElement<T> current = head; current != null; current = current.next) {
                if (current.value == null) {
                    delete(current);
                    return true;
                }
            }
        } else {
            for (ListElement<T> current = head; current != null; current = current.next) {
                if (current.value.equals(value)) {
                    delete(current);
                    return true;
                }
            }
        } return false;
    }

    private T delete(ListElement<T> element) {
        T elementValue = element.value;
        ListElement<T> prev = element.prev;
        ListElement<T> next = element.next;

        if (prev == null) {
            this.head = next;
        } else {
            prev.next = next;
            element.prev = null;
        }
        if (next == null) {
            this.tail = prev;
        } else {
            next.prev = prev;
            element.next = null;
        }
        element.value = null;
        this.size--;
        return elementValue;
    }

    public boolean contains(Object o) {
        for (T t : this) {
            if (t.equals(o)) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public T get(int index) {
        ListElement<T> current = head;
        for (int i = 0; i < index && current!=null; i++) {
            current = current.next;
        } return current!=null ? current.value : null;
    }

    @Override
    public T getLastElement() {
        if (tail == null)
            throw new NoSuchElementException();
        return tail.value;
    }

    @Override
    public T getFirstElement() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ListElement<T> current = head;
            @Override
            public boolean hasNext() {
                return current!=null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    public Iterator<T> reverseIterator() {
        return new Iterator<T>() {
            ListElement<T> current = tail;
            @Override
            public boolean hasNext() {
                return current!=null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.prev;
                return value;
            }
        };
    }

    @Data
        private static class ListElement<T> {
            T value;
            ListElement<T> next;
            ListElement<T> prev;

        public ListElement(T value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("--------ADD-----------");
        BothWayList<String> bothWayList = new BothWayList();
        bothWayList.add("Di Caprio");
        bothWayList.add("Bred Pitt");
        bothWayList.add("Bruce Lee");

        Iterator<String> iterator = bothWayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Size: " + bothWayList.size());
        System.out.println("--------REMOVE and REVERSE---------");
        bothWayList.remove("Bred Pitt");

        Iterator<String> iterator2 = bothWayList.reverseIterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
        System.out.println("Size: " + bothWayList.size());
        System.out.println("--------ADD AGAIN--------");

        bothWayList.add("Angelina Joly");
        Iterator<String> reverseIterator = bothWayList.reverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
        System.out.println("Size: " + bothWayList.size());

        System.out.println("--------Some stuff---------");

        System.out.println("First element: " + bothWayList.getFirstElement() + "\n"
                + "Last element: " + bothWayList.getLastElement() + "\n"
                + "Element by index: " + bothWayList.get(1) + "\n"
                + "Contains: " + bothWayList.contains("Di Caprio") + "\n"
                + "Is empty?: " + bothWayList.isEmpty() + "\n");

        System.out.println("-------------------------");

    }
}

