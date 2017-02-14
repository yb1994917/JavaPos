/**
 * Base classes used by concrete Parser and Generator implementations;
 * contain functionality that is not specific to JSON or input
 * abstraction (byte vs char).
 * Most formats extend these types, although it is also possible to
 * directly extend {@link com.gooagoo.jackson.core.JsonParser} or
 * {@link com.gooagoo.jackson.core.JsonGenerator}.
 */
package com.gooagoo.jackson.core.base;
