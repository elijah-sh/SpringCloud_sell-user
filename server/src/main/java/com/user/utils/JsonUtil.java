package com.user.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/***
 * 实体类转json
 */
@Slf4j
public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 转换为json字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}


    /**
     * json转对象
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
	public static <T> T fromJson(String json, Class<T> valueType) {

		try {
			return objectMapper.readValue(json, valueType);
		} catch (Exception e) {
			log.error("ERROR:", e);
		}
		return null;
	}

    /**
     * json转List对象
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, TypeReference typeReference) {

        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("ERROR:", e);
        }
        return null;
    }

}
