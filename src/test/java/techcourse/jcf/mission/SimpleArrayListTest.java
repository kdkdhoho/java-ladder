package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SimpleArrayListTest {
    private SimpleArrayList simpleArrayList;

    @BeforeEach
    void beforeEach() {
        this.simpleArrayList = new SimpleArrayList();
    }

    @Test
    void addTest_WithoutIndex() {
        // given
        String data = "1";

        // when
        boolean result = simpleArrayList.add(data);

        // expect
        assertThat(simpleArrayList.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:2", "2:4", "3:3"}, delimiter = ':')
    @DisplayName("index와 함께 원하는 위치에 add() 테스트")
    void addTest_WithIndex(int index, String expect) {
        // given
        simpleArrayList = new SimpleArrayList(SimpleList.of("1", "2", "3"));
        String value = "4";

        // when
        simpleArrayList.add(index, value);

        // then
        assertThat(simpleArrayList.get(2)).isEqualTo(expect);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    @DisplayName("리스트의 범위를 벗어난 index를 get() 하는 경우 IndexOutOfBoundsException이 발생한다.")
    void getTest_IndexOutOfBoundsException(int index) {
        // given
        simpleArrayList = new SimpleArrayList(SimpleList.of("1", "2", "3"));

        // expect
        assertThatThrownBy(() -> simpleArrayList.get(index))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("[ERROR] 리스트의 범위를 벗어났습니다.");
    }

    @Test
    @DisplayName("올바른 index로 get() 하는 경우 테스트")
    void getTest_Success() {
        // given
        simpleArrayList = new SimpleArrayList(SimpleList.of("1", "2", "3"));

        // when
        String result = simpleArrayList.get(0);

        // then
        assertThat(result).isEqualTo("1");
     }
}
