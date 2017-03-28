package com.bp.micro.svc.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Arnab
 *
 */
public final class JSONHelper {
	private JSONHelper(){}

	public static String objectToJSON(Object toConvert) throws JSONException {
		try {
			Writer stringWriter = new StringWriter();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(stringWriter, toConvert);
			return stringWriter.toString();
		} catch (JsonGenerationException e) {
			throw new JSONException(e);
		} catch (JsonMappingException e) {
			throw new JSONException(e);
		} catch (IOException e) {
			throw new JSONException(e);
		}
	}
	public static <T> T jsonToObject(String json, Class<T> type) throws JSONException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			T parsed = mapper.readValue(json, type);
			return parsed;
		} catch (JsonParseException e) {
			throw new JSONException(e);
		} catch (JsonMappingException e) {
			throw new JSONException(e);
		} catch (IOException e) {
			throw new JSONException(e);
		}
	}
}
