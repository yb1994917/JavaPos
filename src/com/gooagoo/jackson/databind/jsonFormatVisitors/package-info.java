/**
 * Classes used for exposing logical structure of POJOs as Jackson
 * sees it, and exposed via
 * {@link com.gooagoo.jackson.databind.ObjectMapper#acceptJsonFormatVisitor(Class, JsonFormatVisitorWrapper)}
 * and
 * {@link com.gooagoo.jackson.databind.ObjectMapper#acceptJsonFormatVisitor(com.gooagoo.jackson.databind.JavaType, JsonFormatVisitorWrapper)}
 * methods.
 *<p>
 * The main entrypoint for code, then, is {@link com.gooagoo.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper} and other
 * types are recursively needed during traversal.
 */
package com.gooagoo.jackson.databind.jsonFormatVisitors;
