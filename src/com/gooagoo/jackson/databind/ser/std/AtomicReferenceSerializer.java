package com.gooagoo.jackson.databind.ser.std;

import java.util.concurrent.atomic.AtomicReference;

import com.gooagoo.jackson.annotation.JsonInclude;

import com.gooagoo.jackson.databind.*;
import com.gooagoo.jackson.databind.jsontype.TypeSerializer;
import com.gooagoo.jackson.databind.type.ReferenceType;
import com.gooagoo.jackson.databind.util.NameTransformer;

public class AtomicReferenceSerializer
    extends ReferenceTypeSerializer<AtomicReference<?>>
{
    private static final long serialVersionUID = 1L;

    /*
    /**********************************************************
    /* Constructors, factory methods
    /**********************************************************
     */

    public AtomicReferenceSerializer(ReferenceType fullType, boolean staticTyping,
            TypeSerializer vts, JsonSerializer<Object> ser)
    {
        super(fullType, staticTyping, vts, ser);
    }

    protected AtomicReferenceSerializer(AtomicReferenceSerializer base, BeanProperty property,
            TypeSerializer vts, JsonSerializer<?> valueSer,
            NameTransformer unwrapper,
            JsonInclude.Include contentIncl)
    {
        super(base, property, vts, valueSer, unwrapper, contentIncl);
    }

    @Override
    protected AtomicReferenceSerializer withResolved(BeanProperty prop,
            TypeSerializer vts, JsonSerializer<?> valueSer,
            NameTransformer unwrapper,
            JsonInclude.Include contentIncl)
    {
        if ((_property == prop) && (contentIncl == _contentInclusion)
                && (_valueTypeSerializer == vts) && (_valueSerializer == valueSer)
                && (_unwrapper == unwrapper)) {
            return this;
        }
        return new AtomicReferenceSerializer(this, prop, vts, valueSer, unwrapper, contentIncl);
    }

    /*
    /**********************************************************
    /* Abstract method impls
    /**********************************************************
     */

    @Override
    protected boolean _isValueEmpty(AtomicReference<?> value) {
        return value.get() == null;
    }

    @Override
    protected Object _getReferenced(AtomicReference<?> value) {
        return value.get();
    }

    @Override
    protected Object _getReferencedIfPresent(AtomicReference<?> value) {
        return value.get();
    }
}
