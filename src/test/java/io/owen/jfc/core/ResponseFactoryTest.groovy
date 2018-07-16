package io.owen.jfc.core

import com.fasterxml.jackson.databind.JsonNode
import spock.lang.Specification
/**
 * Created by owen_q on 2018. 7. 10..
 */
class ResponseFactoryTest extends Specification {
    
    
    def "test createMessageButtonNode"() {
        given:
        ResponseFactory responseFactory = ResponseFactory.getInstance();
        String givenLabel = "test label"
        String givenStrUrl = "https://daum.net"
        
        when:
        JsonNode messageButtonNode = responseFactory.createMessageButtonNode(givenLabel, givenStrUrl)

        then:
        messageButtonNode.get("label").asText() == givenLabel
    }

    def "test createMessageNode"() {
        given:
        ResponseFactory responseFactory = ResponseFactory.getInstance();

        String givenText = "안녕하세요"
        JsonNode givenMessageButton

        when:
        JsonNode messageButtonNode = responseFactory.createMessageNode(givenText, givenMessageButton)

        then:
        messageButtonNode.get("label").asText() == givenLabel
    }

}
