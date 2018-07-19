package io.owen.jfc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by owen_q on 2018. 7. 19..
 */
@Data
@Getter
@Setter
@JsonInclude
public class Response implements Serializable {
    private Message message;

    private Keyboard keyboard;



    Response(Message message, Keyboard keyboard) {
        this.message = message;
        this.keyboard = keyboard;
    }


}
