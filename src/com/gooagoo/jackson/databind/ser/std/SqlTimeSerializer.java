package com.gooagoo.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;

import com.gooagoo.jackson.core.JsonGenerator;
import com.gooagoo.jackson.databind.*;
import com.gooagoo.jackson.databind.annotation.JacksonStdImpl;
import com.gooagoo.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.gooagoo.jackson.databind.jsonFormatVisitors.JsonValueFormat;

@JacksonStdImpl
@SuppressWarnings("serial")
public class SqlTimeSerializer
    extends StdScalarSerializer<java.sql.Time>
{
    public SqlTimeSerializer() { super(java.sql.Time.class); }

    @Override
    public void serialize(java.sql.Time value, JsonGenerator g, SerializerProvider provider) throws IOException
    {
        g.writeString(value.toString());
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode("string", true);
    }
    
    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint)
        throws JsonMappingException
    {
        visitStringFormat(visitor, typeHint, JsonValueFormat.DATE_TIME);
    }
}