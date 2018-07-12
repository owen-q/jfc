package io.owen.postit.commands;

/**
 * Created by owen_q on 2018. 7. 10..
 */
public enum UserState {
    NONE("메인", -1),

    HOME("메인", 0),

    ADD("추가하기", 1),
    ADD_ENTER("입력", 10),
    ADD_CONFIRM("확인", 100),
    ADD_SAVE("저장", 1000),

    SELECT("조회하기", 2),
    SELECT_YESTERDAY("어제", 20),
    SELECT_TODAY("오늘", 40),

    INPUT("값", 9);

    private String value;
    private int id;

    UserState(String value, int id) {
        this.value = value;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserState{" +
                "value='" + value + '\'' +
                '}';
    }
}
