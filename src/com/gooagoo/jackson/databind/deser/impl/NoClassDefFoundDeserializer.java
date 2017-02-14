package com.gooagoo.jackson.databind.deser.impl;

import java.io.IOException;

import com.gooagoo.jackson.core.JsonParser;
import com.gooagoo.jackson.databind.DeserializationContext;
import com.gooagoo.jackson.databind.JsonDeserializer;

/**
 * A deserializer that stores a {@link NoClassDefFoundError} error
 * and throws the stored exception when attempting to deserialize
 * a value. Null and empty values can be deserialized without error.
 * 
 * @since 2.5
 */
public class NoClassDefFoundDeserializer<T> extends JsonDeserializer<T>
{
    private final NoClassDefFoundError _cause;

    public NoClassDefFoundDeserializer(NoClassDefFoundError cause)
    {
        _cause = cause;
    }

    @Override
    public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException
    {
        throw _cause;
    }
}
