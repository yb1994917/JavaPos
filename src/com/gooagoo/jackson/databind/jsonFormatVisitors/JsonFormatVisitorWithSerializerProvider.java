/**
 * 
 */
package com.gooagoo.jackson.databind.jsonFormatVisitors;

import com.gooagoo.jackson.databind.SerializerProvider;

/**
 * @author jphelan
 */
public interface JsonFormatVisitorWithSerializerProvider {
    public SerializerProvider getProvider();
    public abstract void setProvider(SerializerProvider provider);
}
