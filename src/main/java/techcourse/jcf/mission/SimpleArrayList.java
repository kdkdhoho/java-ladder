package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList implements SimpleList {

    private String[] datas = {};

    @Override
    public boolean add(final String value) {
        String[] newDatas = Arrays.copyOf(datas, datas.length + 1);
        newDatas[newDatas.length - 1] = value;
        this.datas = newDatas;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
    }

    @Override
    public String set(final int index, final String value) {
        return null;
    }

    @Override
    public String get(final int index) {
        return null;
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
        return datas.length;
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
}
