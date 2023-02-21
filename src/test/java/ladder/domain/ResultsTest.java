package ladder.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import ladder.domain.linestrategy.CustomLineStrategy;
import ladder.domain.strategy.linestrategy.LineStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

public class ResultsTest {

    @Test
    @DisplayName("입력된 결과의 수가 인원 수와 다르면 IllegalArgumentException 예외가 발생한다.")
    void create_mismatchWithPlayerCount() {
        // given
        String[] inputResults = {"꽝", "꽝", "5000", "3000"};
        int playerCount = 3;

        // expect
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Results(inputResults, playerCount)
        ).withMessage("[ERROR] 입력된 결과의 수가 인원 수와 다를 수 없습니다.");
    }

    @Test
    @DisplayName("입력된 결과가 정상적으로 생성된다.")
    void create_success() {
        // given
        String[] inputResults = {"O", "X", "X", "X"};
        int playerCount = 4;

        // expect
        assertThatNoException().isThrownBy(() ->
                new Results(inputResults, playerCount)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"a:성공", "b:꽝", "c:성공", "d:꽝"}, delimiter = ':')
    void findResultTest(String name, String expect) {
        // given
        Players players = new Players(new String[]{"a", "b", "c", "d"});
        Ladder ladder = generateLadder(players);
        String[] inputResults = {"꽝", "성공", "꽝", "성공"};
        Results results = new Results(inputResults, players.getPlayersCount());

        // when
        String result = results.findResult(ladder.getLines(), players.findPosition(name));

        // then
        assertThat(result).isEqualTo(expect);
     }

    @Test
    @DisplayName("모든 참가자의 결과를 반환한다.")
    void findAllResult() {
        // given
        Players players = new Players(new String[]{"a", "b", "c", "d"});
        Ladder ladder = generateLadder(players);
        String[] inputResults = {"꽝", "성공", "꽝", "성공"};
        Results results = new Results(inputResults, players.getPlayersCount());

        // when
        List<String> allResult = results.findAllResult(ladder.getLines());

        // then
        assertThat(allResult).isEqualTo(List.of("성공", "꽝", "성공", "꽝"));
    }

     private Ladder generateLadder(Players players) {
         Height height = new Height(4);
         LineStrategy lineStrategy = new CustomLineStrategy(List.of(Step.EXIST, Step.EMPTY));
         LadderFactory ladderFactory = new LadderFactory(height, players, lineStrategy);
         return ladderFactory.makeLadder();
     }
}
