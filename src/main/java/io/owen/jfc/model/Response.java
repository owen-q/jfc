package io.owen.jfc.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by owen_q on 2018. 7. 19..
 */
@Data
public class Response implements Serializable {
    private Message message;

    private Keyboard keyboard;

    Response(Message message, Keyboard keyboard) {
        this.message = message;
        this.keyboard = keyboard;
    }


}
