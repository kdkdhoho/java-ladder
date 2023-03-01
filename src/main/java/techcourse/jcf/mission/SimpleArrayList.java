package techcourse.jcf.mission;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.copyOfRange;

import java.util.Arrays;

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
        checkRangeToGetOrSet(index);
        elements[index] = value;
        return value;
    }

    @Override
    public String get(final int index) {
        checkRangeToGetOrSet(index);
        return elements[index];
    }

    @Override
    public boolean contains(final String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(final String value) {
        for (int index = 0; index < elements.length; index++) {
            if (elements[index].equals(value)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return elements.length == 0;
    }

    @Override
    public boolean remove(final String value) {
        int index = indexOf(value);
        if (index == -1) {
            throw new IllegalStateException("[ERROR] 리스트에 요소가 존재하지 않습니다.");
        }
        if (index == 0) {
            elements = copyOfRange(elements, 1, elements.length);
            return true;
        }
        if (index == elements.length - 1) {
            elements = copyOfRange(elements, 0, elements.length - 1);
            return true;
        }
        removeBetween(index);
        return true;
    }

    @Override
    public String remove(final int index) {
        return null;
    }

    @Override
    public void clear() {
        elements = EMPTY_LIST;
    }

    private void addFirst(final String value) {
        String[] newElements = new String[elements.length + 1];
        newElements[0] = value;
        arraycopy(elements, 0, newElements, 1, elements.length);
        elements = newElements;
    }

    private void addMiddle(final int index, final String value) {
        String[] newElements = new String[elements.length + 1];
        arraycopy(elements, 0, newElements, 0, index);
        newElements[index] = value;
        arraycopy(elements, index, newElements, index + 1, elements.length - index);
        elements = newElements;
    }

    private void removeBetween(final int index) {
        String[] newElements = new String[elements.length - 1];
        arraycopy(elements, 0, newElements, 0, index);
        arraycopy(elements, index + 1, newElements, index, elements.length - index - 1);
        elements = newElements;
    }

    private void checkRangeToAdd(final int index) {
        if (index < 0 || index > elements.length) {
            throw new IndexOutOfBoundsException("[ERROR] 리스트의 범위를 벗어났습니다.");
        }
    }

    private void checkRangeToGetOrSet(final int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("[ERROR] 리스트의 범위를 벗어났습니다.");
        }
    }
}
