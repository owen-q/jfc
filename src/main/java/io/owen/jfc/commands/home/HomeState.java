package io.owen.jfc.commands.home;

import com.fasterxml.jackson.databind.JsonNode;
import io.owen.jfc.commands.Command;
import io.owen.jfc.commands.CommandHandler;
import io.owen.jfc.commands.UserState;
import io.owen.jfc.model.KeyboardType;
import io.owen.jfc.model.Response;
import io.owen.jfc.model.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by owen_q on 2018. 7. 16..
 */
@Component
@Command(state = UserState.HOME, availableNextState = {UserState.AUTH_BANNER, UserState.MATCH_LIST, UserState.HOME})
public class HomeState implements CommandHandler {
    private Logger logger = LoggerFactory.getLogger(HomeState.class);

    @Override
    public Response handleInput(String userKey, Map<String, Object> attrs) {
        generateResponse();

        return null;
    }

    @Override
    public Response handleCommand(String userKey, Map<String, Object> attrs) {
        Response result = generateResponse();

        stateManager.change(userKey, UserState.HOME);
        return result;
    }

    @Override
    public Response generateResponse() {
        Command command = this.getClass().getDeclaredAnnotation(Command.class);

        UserState[] availableStates = command.availableNextState();

        JsonNode messageNode = responseFactory.createMessageNode("메인화면", null);

        List<String> stateNameArr = Stream.of(availableStates).map(userState -> userState.getValue()).collect(Collectors.toList());
        JsonNode keyboardNode = responseFactory.createButtonsKeyboardNode(stateNameArr);

        Response response = new ResponseBuilder().keyboardType(KeyboardType.BUTTONS).buttons(stateNameArr).message("메인입니다").build();

        return response;
    }
}

