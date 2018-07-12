package io.owen.postit.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.owen.postit.commands.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by owen_q on 2018. 7. 10..
 */
@Component
public class ResponseFactory {
    private Logger logger = LoggerFactory.getLogger(ResponseFactory.class);

    private JsonNodeFactory jsonNodeFactory;

    public static ResponseFactory getInstance() {
        return Holder.INSTANCE;
    }

    public ResponseFactory() {
        this.jsonNodeFactory = new JsonNodeFactory(false);
    }

    public JsonNode createMessageButtonNode(String label, String url){
        JsonNode messageButtonNode = new ObjectNode(this.jsonNodeFactory);
        ((ObjectNode) messageButtonNode).put("label", label);
        ((ObjectNode) messageButtonNode).put("url", url);

        return messageButtonNode;
    }

    public JsonNode createMessage(String text, JsonNode messageButtonNode){
        JsonNode messageNode = new ObjectNode(this.jsonNodeFactory);

        ((ObjectNode) messageNode).put("text", text);
        ((ObjectNode) messageNode).set("message_button", messageButtonNode);

        return messageNode;
    }

    public JsonNode createResult(JsonNode messageNode){
        JsonNode resultNode = new ObjectNode(this.jsonNodeFactory);

        ((ObjectNode) resultNode).set("message", messageNode);
        return resultNode;
    }

    public JsonNode createTextKeyboard(){
        JsonNode keyboardNode = new ObjectNode(this.jsonNodeFactory);
        ((ObjectNode) keyboardNode).put("type", "text");
        return keyboardNode;
    }

    public ArrayNode createButtonsKeyboard(){
        ArrayNode buttons = new ArrayNode(this.jsonNodeFactory);

        for (UserState userState : UserState.values()){
            buttons.add(userState.getValue());
        }

        return buttons;
    }

    public JsonNode createObjectNode(String key, String value){
        JsonNode jsonNode = new ObjectNode(this.jsonNodeFactory);

        ((ObjectNode) jsonNode).put(key, value);
        return jsonNode;
    }


    public JsonNode createArrayNode(){
        JsonNode arrayNode = new ArrayNode(this.jsonNodeFactory);
        return arrayNode;
    }

    private static class Holder {
        private static ResponseFactory INSTANCE = new ResponseFactory();
    }
}
