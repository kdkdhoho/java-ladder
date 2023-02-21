package ladder.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Ladder {
    private final List<Line> lines;

    public Ladder(List<Line> lines) {
        this.lines = lines;
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
