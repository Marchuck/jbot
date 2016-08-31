package me.ramswaroop.jbot.core.slack;

/**
 * @author Lukasz Marczak
 * @since 8/31/16
 */
public class MessageQueue<T> {

    private static class Node<T> {
        Node<T> next;
        T value;

        Node(T t) {
            this.value = t;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public void push(T t) {
        if (size == 0) {
            first = last = new Node<T>(t);
        } else {
            Node<T> tNode = new Node<T>(t);
            last.next = tNode;
            last = tNode;
        }
        ++size;
    }

    public T pop() {
        T value = first.value;
        if (size == 1) {
            first = last = null;
        } else {
            first = first.next;
        }
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
