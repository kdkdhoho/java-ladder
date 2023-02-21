package ladder.domain.strategy.linestrategy;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ResultTest {

    @ParameterizedTest
    @DisplayName("이름이 공백이면 IllegalArgumentException 예외가 발생한다.")
    @ValueSource(strings = {"", " ", "\n", "    "})
    void create_blank(String input) {
        // expect
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Result(input)
        ).withMessage("[ERROR] 결과는 공백일 수 없습니다.");
     }

     @Test
     @DisplayName("이름에 null이 들어가면 IllegalArgumentException 예외가 발생한다.")
     void create_null() {
         // given
         String input = null;

         // expect
         assertThatIllegalArgumentException().isThrownBy(() ->
                 new Result(input)
         ).withMessage("[ERROR] 이름에 null이 들어갈 수 없습니다.");
      }
}