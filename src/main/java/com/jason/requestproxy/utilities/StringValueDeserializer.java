package com.jason.requestproxy.utilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class StringValueDeserializer  extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectCodec codec = parser.getCodec();
        TreeNode node = codec.readTree(parser);
        JsonNode value = (JsonNode)node.get("value");

        if (value != null){
            return value.asText();
        }
        return null;
    }
}