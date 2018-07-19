package io.owen.jfc.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by owen_q on 2018. 7. 19..
 */
public class KeyboardTypeSerializer extends StdSerializer {
    private Logger logger = LoggerFactory.getLogger(KeyboardTypeSerializer.class);

    public KeyboardTypeSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
    }
}
