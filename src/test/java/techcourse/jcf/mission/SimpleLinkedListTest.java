package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
