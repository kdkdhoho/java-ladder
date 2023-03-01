package techcourse.jcf.mission;

import static java.util.Arrays.copyOf;

public class SimpleArrayList implements SimpleList {

    private static final String[] EMPTY_LIST = {};

    private String[] elements;

    public SimpleArrayList() {
        elements = EMPTY_LIST;
    }

    public SimpleArrayList(final String[] elements) {
        this.elements = elements;
    }

    @Override
    public boolean add(final String value) {
        String[] newElements = copyOf(elements, elements.length + 1);
        newElements[newElements.length - 1] = value;
        this.elements = newElements;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
        checkRangeToAdd(index);
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == elements.length) {
            add(value);
            return;
        }
        addMiddle(index, value);
    }

    @Override
    public String set(final int index, final String value) {
        return null;
    }

    @Override
    public String get(final int index) {
        checkRangeToGet(index);
        return elements[index];
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
        return elements.length;
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

    private void addFirst(final String value) {
        String[] newElements = new String[elements.length + 1];
        newElements[0] = value;
        System.arraycopy(elements, 0, newElements, 1, elements.length);
        elements = newElements;
    }

    private void addMiddle(final int index, final String value) {
        String[] newElements = new String[elements.length + 1];
        System.arraycopy(elements, 0, newElements, 0, index);
        newElements[index] = value;
        System.arraycopy(elements, index, newElements, index + 1, elements.length - index);
        elements = newElements;
    }

    private void checkRangeToAdd(final int index) {
        if (index < 0 || index > elements.length) {
            throw new IndexOutOfBoundsException("[ERROR] 리스트의 범위를 벗어났습니다.");
        }
    }

    private void checkRangeToGet(final int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("[ERROR] 리스트의 범위를 벗어났습니다.");
        }
    }
}
