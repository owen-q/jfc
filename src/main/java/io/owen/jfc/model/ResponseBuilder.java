package io.owen.jfc.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by owen_q on 2018. 7. 19..
 */
public class ResponseBuilder {

    // Essential
    private String messageText = "";
    private KeyboardType keyboardType = KeyboardType.NONE;

    // Selective
    // MessageButton
    private String label = "";
    private String url = "";

    // Text keyboard
    // None
    // Buttons Keyboard
    private List<String> buttons = null;

    public ResponseBuilder keyboardType(KeyboardType keyboardType){
        this.keyboardType = keyboardType;
        return this;
    }

    public ResponseBuilder message(String messageText){
        this.messageText = messageText;
        return this;
    }

    public ResponseBuilder buttons(List<String> buttons){
        this.buttons = buttons;
        return this;
    }

    public ResponseBuilder buttons(String[] buttons){
        return buttons(Arrays.asList(buttons));
    }

    public Response build(){
        if(keyboardType == KeyboardType.NONE)
            throw new IllegalArgumentException();

        if(messageText.equals(""))
            throw new IllegalArgumentException();

        if(keyboardType == KeyboardType.BUTTONS && buttons == null && buttons.size() == 0)
            throw new IllegalArgumentException();

        // Build message
        Message message = new Message();

        // Build keyboard
        Keyboard keyboard = null;
        if(keyboardType == KeyboardType.BUTTONS){
            keyboard = new ButtonsKeyboard();
        }
        else if(keyboardType == KeyboardType.TEXT){
            keyboard = new TextKeyboard();
        }

        // Build Response
        Response response = new Response(message, keyboard);

        return response;
    }
}
