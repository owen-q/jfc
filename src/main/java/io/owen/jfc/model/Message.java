package io.owen.jfc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by owen_q on 2018. 7. 19..
 */

@Data
@Getter
@Setter
@JsonInclude
public class Message {
    private String text;


}
