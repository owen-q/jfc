package io.owen.jfc.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by owen_q on 2018. 7. 19..
 */
@Getter
@Setter
public class ButtonsKeyboard implements Keyboard{
    private KeyboardType type = KeyboardType.BUTTONS;
    private String[] buttons;

}
