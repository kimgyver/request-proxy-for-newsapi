package com.jason.requestproxy.utilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringArrayValueDeserializer  extends JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        List<String> ret = new ArrayList<>();

        ObjectCodec codec = parser.getCodec();
        TreeNode node = codec.readTree(parser);

        if (node.isArray()){
            for (JsonNode n : (ArrayNode)node){
                JsonNode value = n.get("value");
                if (value != null){
                    ret.add(value.asText());
                }
            }
        }
        return ret;
    }
}