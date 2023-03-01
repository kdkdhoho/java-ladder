package techcourse.jcf.mission;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.copyOfRange;

public class SimpleArrayList implements SimpleList {

    private String[] elements;

    public SimpleArrayList() {
        elements = SimpleList.EMPTY_ELEMENTS;
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
        addInMiddle(index, value);
    }

    private void checkRangeToAdd(final int index) {
        if (index < 0 || index > elements.length) {
            throw new IndexOutOfBoundsException("[ERROR] 리스트의 범위를 벗어났습니다.");
        }
    }

    private void addFirst(final String value) {
        String[] newElements = new String[elements.length + 1];
        newElements[0] = value;
        arraycopy(elements, 0, newElements, 1, elements.length);
        elements = newElements;
    }

    private void addInMiddle(final int index, final String value) {
        String[] newElements = new String[elements.length + 1];
        arraycopy(elements, 0, newElements, 0, index);
        newElements[index] = value;
        arraycopy(elements, index, newElements, index + 1, elements.length - index);
        elements = newElements;
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

    private void checkRangeToGetOrSet(final int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("[ERROR] 리스트의 범위를 벗어났습니다.");
        }
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
        remove(index);
        return true;
    }

    @Override
    public String remove(final int index) {
        checkRangeToGetOrSet(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == elements.length - 1) {
            return removeLast();
        }
        return removeInMiddle(index);
    }

    private String removeFirst() {
        String target = elements[0];
        elements = copyOfRange(elements, 1, elements.length);
        return target;
    }

    private String removeLast() {
        String target = elements[elements.length - 1];
        elements = copyOfRange(elements, 0, elements.length - 1);
        return target;
    }

    private String removeInMiddle(final int index) {
        String target = elements[index];
        String[] newElements = new String[elements.length - 1];
        arraycopy(elements, 0, newElements, 0, index);
        arraycopy(elements, index + 1, newElements, index, elements.length - index - 1);
        elements = newElements;
        return target;
    }

    @Override
    public void clear() {
        elements = SimpleList.EMPTY_ELEMENTS;
    }
}
