package io.owen.jfc.model;

/**
 * Created by owen_q on 2018. 7. 19..
 */
public enum KeyboardType {
    NONE("none"),
    TEXT("text"),
    BUTTONS("buttons");

    private String value;

    KeyboardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
