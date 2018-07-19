package io.owen.jfc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Created by owen_q on 2018. 7. 19..
 */
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResponseTest {

    @Test
    public void 응답생성_keyboardType누락(){
        org.junit.jupiter.api.function.Executable executable = ()->{
            Response.Builder builder = new Response.Builder();

            builder.build();
        };

        Assertions.assertThrows(Exception.class, executable);
    }

    @Test
    public void 응답생성_메세지누락(){
        Executable executable = () -> {
            Response.Builder builder = new Response.Builder();

            builder.keyboardType(KeyboardType.TEXT)
                    .build();
        };

        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    public void 응답생성_textKeyboard생성() {
        // Given:
        Response.Builder builder = new Response.Builder();
        KeyboardType keyboardType = KeyboardType.TEXT;
        String message = "테스트 메세지";

        // When:
        Executable executable = ()->{
            builder.keyboardType(keyboardType)
                    .message(message)
                    .build();
        };
        
        // Then:
        Assertions.assertDoesNotThrow(executable);
    }

    @Test
    public void 응답생성_buttonKeyboard생성() {
        // Given:
        Response.Builder builder = new Response.Builder();
        KeyboardType keyboardType = KeyboardType.BUTTONS;
        String message = "테스트 메세지";
        String[] buttons = {"d","a"};

        // When:
        Executable executable = ()->{
            builder.keyboardType(keyboardType)
                    .message(message)
                    .buttons(buttons)
                    .build();
        };

        // Then:
        Assertions.assertDoesNotThrow(executable);
    }

}