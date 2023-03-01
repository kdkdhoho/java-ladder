package techcourse.jcf.mission;

// TODO: 제네릭을 사용하지 않는다. String만 다루는 리스트를 구현한다.
public interface SimpleList {

    String[] EMPTY_ARRAY = {};

    boolean add(String value);

    void add(int index, String value);

    String set(int index, String value);

    String get(int index);

    boolean contains(String value);

    int indexOf(String value);

    int size();

    boolean isEmpty();

    boolean remove(String value);

    String remove(int index);

    void clear();

    static String[] of() {
        return EMPTY_ARRAY;
    }

    static String[] of(String s1) {
        return new String[]{s1};
    }

    static String[] of(String s1, String s2) {
        return new String[]{s1, s2};
    }

    static String[] of(String s1, String s2, String s3) {
        return new String[]{s1, s2, s3};
    }

    static String[] of(String s1, String s2, String s3, String s4) {
        return new String[]{s1, s2, s3, s4};
    }

    static String[] of(String s1, String s2, String s3, String s4, String s5) {
        return new String[]{s1, s2, s3, s4, s5};
    }

    static String[] of(String s1, String s2, String s3, String s4, String s5, String s6) {
        return new String[]{s1, s2, s3, s4, s5, s6};
    }

    static String[] of(String... strings) {
        if (strings.length == 0) {
            return EMPTY_ARRAY;
        }
        return strings;
    }
}
