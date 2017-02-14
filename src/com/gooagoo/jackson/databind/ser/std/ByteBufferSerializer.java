package com.gooagoo.jackson.databind.ser.std;

import java.io.*;
import java.nio.ByteBuffer;

import com.gooagoo.jackson.core.*;
import com.gooagoo.jackson.databind.*;
import com.gooagoo.jackson.databind.util.ByteBufferBackedInputStream;

@SuppressWarnings("serial")
public class ByteBufferSerializer extends StdScalarSerializer<ByteBuffer>
{
    public ByteBufferSerializer() { super(ByteBuffer.class); }

    @Override
    public void serialize(ByteBuffer bbuf, JsonGenerator gen, SerializerProvider provider) throws IOException
    {
        // first, simple case when wrapping an array...
        if (bbuf.hasArray()) {
            gen.writeBinary(bbuf.array(), 0, bbuf.limit());
            return;
        }
        // the other case is more complicated however. Best to handle with InputStream wrapper.
        // But should we rewind it; and/or make a copy?
        ByteBuffer copy = bbuf.asReadOnlyBuffer();
        if (copy.position() > 0) {
            copy.rewind();
        }
        InputStream in = new ByteBufferBackedInputStream(copy);
        gen.writeBinary(in, copy.remaining());
        in.close();
    }
}
