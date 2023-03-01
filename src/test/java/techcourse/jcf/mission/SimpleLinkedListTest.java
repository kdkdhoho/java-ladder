package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

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
    void sizeTest() {
        int result = linkedList.size();
        assertThat(result).isEqualTo(3);
    }
}
