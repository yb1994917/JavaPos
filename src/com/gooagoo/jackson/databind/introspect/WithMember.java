package com.gooagoo.jackson.databind.introspect;

public interface WithMember<T>
{
    public T withMember(AnnotatedMember member);
}
