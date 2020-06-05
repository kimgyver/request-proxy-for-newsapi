package com.jason.requestproxy.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Articles<T>{
    // Class property cannot be called "return" because it is Java reserved name.
    @JsonProperty("articles")
    private T[] array;

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }
}