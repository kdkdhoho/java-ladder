package techcourse.jcf.mission;

public class SimpleLinkedList implements SimpleList {

    private Node<String> first = null;
    private Node<String> last = null;
    private int size = 0;

    public SimpleLinkedList(String... values) {
        for (String value : values) {
            add(value);
        }
    }

    @Override
    public boolean add(final String value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(final int index, final String value) {

    }

    private void addLast(final String value) {
        if (first == null || last == null) {
            Node<String> newNode = new Node<>(null, value, null);
            first = newNode;
            last = newNode;
            size++;
            return;
        }
        Node<String> lastNode = last;
        Node<String> newNode = new Node<>(lastNode, value, null);
        lastNode.next = newNode;
        last = newNode;
        size++;
    }

    @Override
    public String set(final int index, final String value) {
        return null;
    }

    @Override
    public String get(final int index) {
        return null;
    }

    public String getFirst() {
        if (first == null) {
            throw new IllegalStateException("[ERROR] 연결된 노드가 존재하지 않습니다.");
        }
        return first.value;
    }

    @Override
    public boolean contains(final String value) {
        return false;
    }

    @Override
    public int indexOf(final String value) {
        return 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(final String value) {
        return false;
    }

    @Override
    public String remove(final int index) {
        return null;
    }

    @Override
    public void clear() {
    }

    private static class Node<String> {
        String value;
        Node<String> next;
        Node<String> prev;

        Node(Node<String> prev, String value, Node<String> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
