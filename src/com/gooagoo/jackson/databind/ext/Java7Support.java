package com.gooagoo.jackson.databind.ext;

import com.gooagoo.jackson.databind.JsonDeserializer;
import com.gooagoo.jackson.databind.JsonSerializer;
import com.gooagoo.jackson.databind.PropertyName;
import com.gooagoo.jackson.databind.introspect.Annotated;
import com.gooagoo.jackson.databind.introspect.AnnotatedParameter;

/**
 * To support Java7-incomplete platforms, we will offer support for JDK 7
 * annotations through this class, loaded dynamically; if loading fails,
 * support will be missing. This class is the non-JDK-7-dependent API,
 * and {@link Java7SupportImpl} is JDK7-dependent implementation of
 * functionality.
 */
public abstract class Java7Support
{
    private final static Java7Support IMPL;
    
    static {
        Java7Support impl = null;
        try {
            Class<?> cls = Class.forName("com.gooagoo.jackson.databind.ext.Java7SupportImpl");
            impl = (Java7Support) cls.newInstance();
        } catch (Throwable t) {
            // 24-Nov-2015, tatu: Should we log or not?
            java.util.logging.Logger.getLogger(Java7Support.class.getName())
                .warning("Unable to load JDK7 types (annotations, java.nio.file.Path): no Java7 support added");
        }
        IMPL = impl;
    }

    public static Java7Support instance() {
        return IMPL;
    }
    
    public abstract Boolean findTransient(Annotated a);

    public abstract Boolean hasCreatorAnnotation(Annotated a);

    public abstract PropertyName findConstructorName(AnnotatedParameter p);

    public abstract Class<?> getClassJavaNioFilePath();

    public abstract JsonDeserializer<?> getDeserializerForJavaNioFilePath(Class<?> rawType);

    public abstract JsonSerializer<?> getSerializerForJavaNioFilePath(Class<?> rawType);
}
