package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList implements SimpleList {

    private static final String[] EMPTY_LIST = {};

    private String[] datas;

    public SimpleArrayList() {
        datas = EMPTY_LIST;
    }

    public SimpleArrayList(int size) {
        this.datas = new String[size];
    }

    public SimpleArrayList(String[] datas) {
        this.datas = datas;
    }

    @Override
    public boolean add(final String value) {
        String[] newDatas = Arrays.copyOf(datas, datas.length + 1);
        newDatas[newDatas.length - 1] = value;
        this.datas = newDatas;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
        if (index < 0 || index > datas.length) {
            throw new IndexOutOfBoundsException("[ERROR] 리스트의 범위를 벗어났습니다.");
        }

        if (index == 0) {
            String[] result = new String[datas.length + 1];
            result[0] = value;
            System.arraycopy(datas, 0, result, 1, datas.length);
            datas = result;
            return;
        }

        if (index == datas.length) {
            String[] result = new String[datas.length + 1];
            System.arraycopy(datas, 0, result, 0, datas.length);
            result[result.length - 1] = value;
            datas = result;
            return;
        }

        String[] result = new String[datas.length + 1];
        System.arraycopy(datas, 0, result, 0, index);
        result[index] = value;
        System.arraycopy(datas, index, result, index + 1, datas.length - index);
        datas = result;
    }

    @Override
    public String set(final int index, final String value) {
        return null;
    }

    @Override
    public String get(final int index) {
        if (index < 0 || index >= datas.length) {
            throw new IndexOutOfBoundsException("[ERROR] 리스트의 범위를 벗어났습니다.");
        }
        return datas[index];
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
