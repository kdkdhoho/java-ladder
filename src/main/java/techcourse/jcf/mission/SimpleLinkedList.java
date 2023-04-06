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
        validateRange(index);

        if (index == size) {
            addLast(value);
            return;
        }
        addBefore(value, getNode(index));
    }

    private void addBefore(final String value, final Node<String> node) {
        Node<String> prevNode = node.prev;

        Node<String> newNode = new Node<>(prevNode, value, node);
        prevNode.next = newNode;
        node.prev = newNode;

        size++;
    }

    private Node<String> getNode(final int index) {
        Node<String> node = first;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void validateRange(final int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("[ERROR] 크기를 벗어나는 index를 입력했습니다. 입력값: " + index);
        }
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
        validateRange(index);

        Node<String> node = getNode(index);
        node.value = value;

        return value;
    }

    @Override
    public String get(final int index) {
        validateRange(index);

        Node<String> node = first;
        for (int i = 1; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    public String getFirst() {
        if (first == null) {
            throw new IllegalStateException("[ERROR] 연결된 노드가 존재하지 않습니다.");
        }
        return first.value;
    }

    public String getLast() {
        if (last == null) {
            throw new IllegalStateException("[ERROR] 연결된 노드가 존재하지 않습니다.");
        }
        return last.value;
    }

    @Override
    public boolean contains(final String value) {
        Node<String> iterator = first;

        while (iterator != null) {
            if (iterator.value.equals(value)) {
                return true;
            }
            iterator = iterator.next;
        }
        return false;
    }

    @Override
    public int indexOf(final String value) {
        int index = 0;

        Node<String> iterator = first;

        while (iterator != null) {
            if (iterator.value.equals(value)) {
                return index;
            }
            iterator = iterator.next;
            index++;
        }

        throw new IllegalArgumentException("[ERROR] 존재하지 않는 값입니다.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean remove(final String value) {
        Node<String> iterator = first;

        while (iterator != null) {
            if (iterator.value.equals(value)) {
                if (size == 1) {
                    clear();
                    return true;
                }
                if (iterator.prev == null) {
                    iterator.next.prev = null;
                    first = iterator.next;
                    size--;
                    return true;
                }
                if (iterator.next == null) {
                    iterator.prev.next = null;
                    last = iterator.prev;
                    size--;
                    return true;
                }
                iterator.prev.next = iterator.next;
                iterator.next.prev = iterator.prev;
                size--;
                return true;
            }
            iterator = iterator.next;
        }

        return false;
    }

    @Override
    public String remove(final int index) {
        validateRange(index);

        Node<String> iterator = first;
        for (int i = 0; i < index; i++) {
            iterator = iterator.next;
        }

        String value = iterator.value;
        remove(value);
        return value;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
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
