package com.gooagoo.jackson.databind.deser.std;

import java.io.IOException;

import com.gooagoo.jackson.core.*;
import com.gooagoo.jackson.databind.DeserializationContext;
import com.gooagoo.jackson.databind.JavaType;
import com.gooagoo.jackson.databind.jsontype.TypeDeserializer;

/**
 * Base class for deserializers that handle types that are serialized
 * as JSON scalars (non-structured, i.e. non-Object, non-Array, values).
 */
public abstract class StdScalarDeserializer<T> extends StdDeserializer<T>
{
    private static final long serialVersionUID = 1L;

    protected StdScalarDeserializer(Class<?> vc) { super(vc); }
    protected StdScalarDeserializer(JavaType valueType) { super(valueType); }

    // since 2.5
    protected StdScalarDeserializer(StdScalarDeserializer<?> src) { super(src); }
    
    @Override
    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromScalar(jp, ctxt);
    }
}