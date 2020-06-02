/*
 *
 *
 *
 */
package com.meamei.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;

/**
 * Utils - JSON
 */
@Slf4j
public final class JsonUtils {

    /**
     * ObjectMapper
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 不可实例化
     */
    private JsonUtils() {
    }

    /**
     * 构造类型
     *
     * @param type 类型
     * @return 类型
     */
    public static JavaType constructType(Type type) {
        Assert.notNull(type, "类型不能为空");

        return TypeFactory.defaultInstance().constructType(type);
    }

    /**
     * 构造类型
     *
     * @param typeReference 类型
     * @return 类型
     */
    public static JavaType constructType(TypeReference<?> typeReference) {
        Assert.notNull(typeReference, "类型不能为空");

        return TypeFactory.defaultInstance().constructType(typeReference);
    }

    /**
     * 通过HTTP response返回json
     *
     * @param response Http servlet response
     * @param obj      对象
     */
    public static void responseJson(HttpServletResponse response, Object obj) {
        // 设置状态码
        response.setStatus(HttpStatus.OK.value());
        // 设置ContentType
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 避免乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write(JsonUtils.toJson(obj));
        } catch (IOException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param value 对象
     * @return JSOn字符串
     */
    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 通过HTTP response返回json
     *
     * @param response ServletResponse
     * @param obj      对象
     */
    public static void responseJson(ServletResponse response, Object obj) throws Exception {
        // 设置ContentType
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 避免乱码
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(JsonUtils.toJson(obj));
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json          JSON字符串
     * @param typeReference 对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, TypeReference<?> typeReference) {
        Assert.hasText(json, "Json不能为空");
        Assert.notNull(typeReference, "类型不能为空");
        try {
            return (T) mapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json     JSON字符串
     * @param javaType 对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, JavaType javaType) {
        Assert.hasText(json, "Json不能为空");
        Assert.notNull(javaType, "类型不能为空");
        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json      JSON字符串
     * @param valueType 对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        Assert.hasText(json, "Json不能为空");
        Assert.notNull(valueType, "类型不能为空");
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将对象转换为JSON流
     *
     * @param writer writer
     * @param value  对象
     */
    public static void writeValue(Writer writer, Object value) {
        try {
            mapper.writeValue(writer, value);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}