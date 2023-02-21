# java-ladder

사다리 타기 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 요구사항 목록

### 주요 객체 속성, 역할
#### Player
- [X] 이름을 가진다.
- [X] 참여할 사람에 null 값이 주어지면 `IllegalArgumentException` 예외가 발생한다.
- [X] 참여할 사람의 이름이 공백이면 `IllegalArgumentException` 예외가 발생한다.
- [X] 5자리를 초과하면 `IllegalArgumentException` 예외가 발생한다.

#### Players
- [X] `Player`들을 가진다.
- [X] 참여할 사람은 최소 2명에서 최대 13명이다.
- [X] 정해진 인원수 범위를 벗어나면 `IllegalArgumentException` 예외가 발생한다.
- [X] 참여할 사람의 이름이 중복되면 `IllegalArgumentException` 예외가 발생한다.

#### LadderFactory
- [x] (사용자가 입력한) 높이를 가진다.
  - [x] 최소 사다리의 높이는 참여하는 사람의 인원 수 이다.
  - [x] 최대 사다리의 높이는 참여하는 사람의 인원 수의 2배이다.
  - [X] 입력되는 사다리의 높이가 최소, 최대 범위를 벗어나면 `IllegalArgumentException` 예외가 발생한다.
- [x] 폭(참여 인원)을 가진다.
- [x] LineStrategy를 가진다.
- [x] 높이와 폭을 이용해 Ladder를 만들어 반환한다.


#### Ladder
- [X] 여러 개의 `Line`을 가진다.

#### Line
- [x] 여러 개의 `Step`을 가진다.
- [x] 건널 수 있는 `Step`이 연달아 존재하지 않는다.
  - [x] 연달아 존재한다면 `IllegalArgumentException` 예외가 발생한다.

#### Height
- [X] 양의 정수 외의 값이 입력되면 `IllegalArgumentException` 예외가 발생한다.

#### Step
- `　　　　　` 또는 `-----` 를 가진다. (`String shape`)

### 입력 요구사항
- [X] 게임을 시작하면 참여할 사람 이름을 입력 받는다.
  - [X] 참여할 사람의 이름은 `,`로 구분된다.
- [X] 최대 사다리의 높이를 입력 받는다.

### 출력 요구사항
- [X] 참여한 사람의 이름과, 사다리의 결과를 출력한다.


### 미션을 진행하며 궁금한 사항
1. 실패하는 테스트가 있어면 커밋을 해선 안된다고 들었는데, TDD의 경우 실패하는 테스트를 만들어야 한다고 강의에서 배웠습니다. 이때, 실패하는 테스트를 구현한 뒤 커밋을 할지, 아니면 전체 기능을 전부 구현한 뒤 커밋할 지 궁금합니다.
-> `test, feat`을 하나로 묶어 커밋하면 실패하는 테스트를 커밋하지 않을 수 있다. 또한 실무에서는 `test-feat-refactor`의 단위보다 하나의 기능 단위로 커밋한다.
2. 현재 생성자를 통해 `Height`와 `Players` 객체를 받고, 각 객체에서 `get()` 으로 값을 꺼내와, 입력받은 높이가 올바른 범위(`players <= height && height <= players * 2`)인지 검증하는 코드인데요. `LadderFactory`가 `Height`와 `Players`를 알고 있지만, 직접 값을 꺼내오는 것이 맘에 들지 않았습니다. 따라서 생성자에서 원시값으로 값을 받아 검증 후에 객체를 생성하는 방식 (`this.height = new Height(height)`) 으로 수정을 하려고 하는데요. 외부에서 원시값을 받고 도메인 내부에서 객체를 생성해도 괜찮은 지 여쭤보고 싶습니다.
