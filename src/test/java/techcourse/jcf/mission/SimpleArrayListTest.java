package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {
    private SimpleArrayList simpleArrayList;

    @BeforeEach
    void beforeEach() {
        this.simpleArrayList = new SimpleArrayList();
    }

    @Test
    void addTest() {
        // given
        String data = "1";

        // when
        boolean result = simpleArrayList.add(data);

        // expect
        assertThat(simpleArrayList.size()).isEqualTo(1);
    }

    @Test
    void addTest_WithIndex() {
        // given

        // when

        // then
     }
}
