package io.owen.jfc.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owen_q on 2018. 7. 10..
 */
public enum UserState {
    HOME("홈", 0),

    AUTH_BANNER("인증", 1),
    AUTH_INPUT("인증-입력",10),

    MATCH_LIST("경기일정",2),
    MATCH_ATTEND("참석", 20),
    MATCH_NONATTEND("불참", 200);

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

    private static List<UserState> userStateList = new ArrayList<>();

    public static List<UserState> getUserStateList(){
        return userStateList;
    }



}
