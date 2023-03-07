package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

    @ParameterizedTest(name = "범위를 벗어난 인덱스를 인자로 넘길 경우 예외가 발생한다. 입력값: \"{0}\"")
    @ValueSource(ints = {-1, 4})
    void 범위를_벗어난_인덱스를_인자로_넘길_경우_예외가_발생한다(int index) {
        assertThatThrownBy(() -> linkedList.get(index))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("[ERROR] 크기를 벗어나는 index를 입력했습니다. 입력값: " + index);
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

    @Test
    void sizeTest() {
        int result = linkedList.size();
        assertThat(result).isEqualTo(3);
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
}
