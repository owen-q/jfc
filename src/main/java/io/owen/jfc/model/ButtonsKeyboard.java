package io.owen.jfc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by owen_q on 2018. 7. 19..
 */
@Getter
@Setter
public class ButtonsKeyboard implements Keyboard{
    private KeyboardType type = KeyboardType.BUTTONS;
    private List<String> buttons;


    public List<String> getButtons() {
        return buttons;
    }

    public void setButtons(List<String> buttons) {
        this.buttons = buttons;
    }

    public String getType() {
        return type.getValue();
    }

    @Override
    public String toString() {
        return "ButtonsKeyboard{" +
                "type=" + type.getValue() +
                ", buttons=" + buttons +
                '}';
    }
}
