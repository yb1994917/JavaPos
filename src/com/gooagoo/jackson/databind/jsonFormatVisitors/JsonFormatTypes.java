package com.gooagoo.jackson.databind.jsonFormatVisitors;

import java.util.*;

import com.gooagoo.jackson.annotation.JsonCreator;
import com.gooagoo.jackson.annotation.JsonValue;

public enum JsonFormatTypes
{
	STRING,
	NUMBER,
	INTEGER,
	BOOLEAN,
	OBJECT,
	ARRAY,
	NULL,
	ANY;

	private static final Map<String,JsonFormatTypes> _byLCName = new HashMap<String,JsonFormatTypes>();
	static {
	    for (JsonFormatTypes t : values()) {
	        _byLCName.put(t.name().toLowerCase(), t);
	    }
	}
	
	@JsonValue
	public String value() {
		return name().toLowerCase();
	}
	
	@JsonCreator
	public static JsonFormatTypes forValue(String s) {
		return _byLCName.get(s);
	}
}