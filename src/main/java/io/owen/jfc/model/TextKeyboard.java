package io.owen.jfc.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by owen_q on 2018. 7. 19..
 */

@Getter
@Setter
public class TextKeyboard implements Keyboard{
    private KeyboardType type = KeyboardType.TEXT;
}
