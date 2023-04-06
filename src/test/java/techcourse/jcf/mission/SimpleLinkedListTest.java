package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SimpleLinkedListTest {

    private SimpleLinkedList linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new SimpleLinkedList("1", "2", "3");
    }

    @Test
    @DisplayName("아무 노드가 없을 때 add() 테스트")
    void addTest_Empty() {
        linkedList = new SimpleLinkedList();

        linkedList.add("4");

        assertThat(linkedList.getFirst()).isEqualTo("4");
    }

    @Test
    @DisplayName("노드가 1개 이상 존재할 때 add() 테스트")
    void addTest_Exist() {
        linkedList.add("4");

        assertThat(linkedList.getLast()).isEqualTo("4");
    }

    @Test
    @DisplayName("노드가 존재하지 않을 때 getFirst() 시 예외가 발생한다.")
    void getFirst_Empty_Exception() {
        linkedList = new SimpleLinkedList();

        assertThatThrownBy(() -> linkedList.getFirst())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 연결된 노드가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("노드가 존재할 때 getFirst() 테스트")
    void getFirstTest_Exist_Success() {
        assertThat(linkedList.getFirst()).isEqualTo("1");
    }

    @Test
    @DisplayName("노드가 존재하지 않을 때 getLast() 시 예외가 발생한다.")
    void getLastTest_Empty_Exception() {
        linkedList = new SimpleLinkedList();

        assertThatThrownBy(() -> linkedList.getLast())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 연결된 노드가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("노드가 존재할 때 getLast() 테스트")
    void getLastTest_Exist_Success() {
        assertThat(linkedList.getLast()).isEqualTo("3");

    }

    @Test
    void sizeTest() {
        int result = linkedList.size();
        assertThat(result).isEqualTo(3);
    }

    @ParameterizedTest(name = "범위를 벗어난 인덱스를 인자로 넘길 경우 예외가 발생한다. 입력값: \"{0}\"")
    @ValueSource(ints = {-1, 4})
    void 범위를_벗어난_인덱스를_인자로_넘길_경우_예외가_발생한다(int index) {
        assertThatThrownBy(() -> linkedList.get(index))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("[ERROR] 크기를 벗어나는 index를 입력했습니다. 입력값: " + index);
    }

    @ParameterizedTest(name = "get(int index)에 대한 경계값 테스트. 입력값: {0}")
    @ValueSource(ints = {0, 3})
    void 사이즈의_경계값_테스트(int index) {
        assertThatNoException().isThrownBy(
                () -> linkedList.get(index)
        );
    }

    @Test
    void 원하는_데이터가_조회되는지_확인한다() {
        assertAll(
                () -> assertThat(linkedList.get(0)).isEqualTo("1"),
                () -> assertThat(linkedList.get(1)).isEqualTo("1"),
                () -> assertThat(linkedList.get(2)).isEqualTo("2"),
                () -> assertThat(linkedList.get(3)).isEqualTo("3"),
                () -> assertThat(linkedList.getLast()).isEqualTo("3")
        );
    }

    @ParameterizedTest(name = "범위를 벗어나는 index를 이용해 add() 했을 때 예외가 발생한다. 입력값: \"{0}\"")
    @ValueSource(ints = {-1, 4})
    void addTest_With_Index_Exception(int index) {
        assertThatThrownBy(() -> linkedList.add(index, "addTest"))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("[ERROR] 크기를 벗어나는 index를 입력했습니다. 입력값: " + index);
    }

    @Test
    @DisplayName("index에 add했을 때 제대로 들어가는 지 확인한다.")
    void addTest_With_Index() {
        linkedList.add(2, "4");

        assertAll(
                () -> assertThat(linkedList.getFirst()).isEqualTo("1"),
                () -> assertThat(linkedList.get(1)).isEqualTo("1"),
                () -> assertThat(linkedList.get(2)).isEqualTo("4"),
                () -> assertThat(linkedList.get(3)).isEqualTo("2"),
                () -> assertThat(linkedList.get(4)).isEqualTo("3"),
                () -> assertThat(linkedList.getLast()).isEqualTo("3")
        );
    }

    @ParameterizedTest(name = "set()에 범위를 벗어난 인덱스 전달시 예외가 발생한다. 입력값: \"{0}\"")
    @ValueSource(ints = {-1, 4})
    void SET_범위를_벗어난_인덱스_전달시_예외가_발생한다(int index) {
        assertThatThrownBy(() -> linkedList.set(index, "value"))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("[ERROR] 크기를 벗어나는 index를 입력했습니다. 입력값: " + index);
    }

    @Test
    @DisplayName("set(int index, String value) 성공 테스트")
    void setTest_success() {
        linkedList.set(0, "4");

        assertThat(linkedList.getFirst()).isEqualTo("4");
    }

    @Test
    @DisplayName("포함되지 않는 값인 경우 false 반환")
    void containsTest_false() {
        assertThat(linkedList.contains("4")).isFalse();
    }

    @ParameterizedTest(name = "포함된 값인 경우 true 반환. 입력값: {0}")
    @ValueSource(strings = {"1", "2", "3"})
    void containsTest_true(String value) {
        assertThat(linkedList.contains(value)).isTrue();
    }

    @Test
    @DisplayName("indexOf(String value) 시 값이 존재하지 않는다면 예외를 발생한다.")
    void indexOfTest_not_found() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> linkedList.indexOf("4")
        ).withMessage("[ERROR] 존재하지 않는 값입니다.");
    }

    @ParameterizedTest(name = "indexOf(String value) 성공 테스트. 입력값: {0}")
    @CsvSource(value = {"1:0", "2:1", "3:2"}, delimiter = ':')
    void indexOfTest_success(String value, int expect) {
        assertThat(linkedList.indexOf(value)).isEqualTo(expect);
    }

    @Test
    @DisplayName("값이 존재하는 경우 isEmpty() 호출 시 false를 반환한다.")
    void isEmptyTest_false() {
        assertThat(linkedList.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("값이 존재하지 않은 경우 isEmpty() 호출 시 true를 반환한다.")
    void isEmptyTest_true() {
        var linkedList = new SimpleLinkedList();

        assertThat(linkedList.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("clear() 시 리스트가 초기화된다.")
    void clearTest() {
        linkedList.clear();

        assertAll(
                () -> assertThat(linkedList.size()).isEqualTo(0),
                () -> assertThatIllegalStateException().isThrownBy(
                        () -> linkedList.getFirst()
                ),
                () -> assertThatIllegalStateException().isThrownBy(
                        () -> linkedList.getLast()
                )
        );
    }

    @Test
    @DisplayName("remove(String value)에 없는 요소 삭제한 경우 테스트")
    void removeTest_does_not_exist() {
        assertThat(linkedList.remove("4")).isFalse();
    }

    @Test
    @DisplayName("remove(String value) 테스트")
    void removeTest_using_value_first() {
        linkedList.remove("1");

        assertAll(
                () -> assertThat(linkedList.size()).isEqualTo(2),
                () -> assertThat(linkedList.getFirst()).isEqualTo("2"),
                () -> assertThat(linkedList.getLast()).isEqualTo("3")
        );
    }

    @Test
    @DisplayName("remove(String value) 테스트")
    void removeTest_using_value_middle() {
        linkedList.remove("2");

        assertAll(
                () -> assertThat(linkedList.size()).isEqualTo(2),
                () -> assertThat(linkedList.getFirst()).isEqualTo("1"),
                () -> assertThat(linkedList.getLast()).isEqualTo("3")
        );
    }

    @Test
    @DisplayName("remove(String value) 테스트")
    void removeTest_using_value_last() {
        linkedList.remove("3");

        assertAll(
                () -> assertThat(linkedList.size()).isEqualTo(2),
                () -> assertThat(linkedList.getFirst()).isEqualTo("1"),
                () -> assertThat(linkedList.getLast()).isEqualTo("2")
        );
    }

    @ParameterizedTest(name = "remove(int index)시 범위를 벗어난 index 전달 시 예외 발생. 입력값: {0}")
    @ValueSource(ints = {-1, 4})
    void removeTest_out_of_range_index_exception(final int index) {
        assertThatThrownBy(
                () -> linkedList.remove(index)
        ).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("[ERROR] 크기를 벗어나는 index를 입력했습니다. 입력값: " + index);
    }

    @Test
    @DisplayName("remove(int index) 테스트")
    void removeTest_using_index_first() {
        linkedList.remove(0);

        assertAll(
                () -> assertThat(linkedList.size()).isEqualTo(2),
                () -> assertThat(linkedList.getFirst()).isEqualTo("2"),
                () -> assertThat(linkedList.getLast()).isEqualTo("3")
        );
    }

    @Test
    @DisplayName("remove(int index) 테스트")
    void removeTest_using_index_middle() {
        linkedList.remove(1);

        assertAll(
                () -> assertThat(linkedList.size()).isEqualTo(2),
                () -> assertThat(linkedList.getFirst()).isEqualTo("1"),
                () -> assertThat(linkedList.getLast()).isEqualTo("3")
        );
    }

    @Test
    @DisplayName("remove(int index) 테스트")
    void removeTest_using_index_last() {
        linkedList.remove(2);

        assertAll(
                () -> assertThat(linkedList.size()).isEqualTo(2),
                () -> assertThat(linkedList.getFirst()).isEqualTo("1"),
                () -> assertThat(linkedList.getLast()).isEqualTo("2")
        );
    }

    @Test
    @DisplayName("3개에서 요소 2개를 지운 케이스 테스트")
    void removeTest_Three_To_Two() {
        linkedList.remove(0);
        linkedList.remove(0);

        assertAll(
                () -> assertThat(linkedList.size()).isEqualTo(1),
                () -> assertThat(linkedList.getFirst()).isEqualTo("3")
        );
    }

    @Test
    @DisplayName("3개에서 요소 3개를 지운 케이스 테스트")
    void removeTest_all_remove() {
        linkedList.remove(0);
        linkedList.remove(0);
        linkedList.remove(0);

        assertThat(linkedList.isEmpty()).isTrue();
    }
}
