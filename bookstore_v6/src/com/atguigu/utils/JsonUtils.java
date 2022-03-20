package com.atguigu.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonUtils {

    /**
     * 将javabean对象转换为json字符串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String javaBean2JsonStr(T obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串转换为javabean对象
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonStr2JavaBean(String jsonStr, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取请求正文中的json字符串，将json字符串转换为javabean对象
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T request2JavaBean(HttpServletRequest request, Class<T> clazz) {

        try {
            //①获取请求正文中的json字符串
            BufferedReader bufferedReader = request.getReader();
            String content = null;
            StringBuilder sb = new StringBuilder();
            while ((content = bufferedReader.readLine()) != null) {
                sb.append(content);
            }
            String inputJsonStr = sb.toString();
            System.out.println("inputJsonStr = " + inputJsonStr);
            //②将json字符串转换为javabean对象
            T t = JsonUtils.jsonStr2JavaBean(inputJsonStr, clazz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将javabean对象转换为json字符串,将json字符串作为响应正文
     * @param t
     * @param response
     * @param <T>
     */
    public static<T> void javaBean2ResponseText(HttpServletResponse response ,T t ){
        try {
            response.setContentType("application/json;charset=utf-8");
            //①将javabean对象转换为json字符串
            String jsonStr = JsonUtils.javaBean2JsonStr(t);
            //②将json字符串作为响应正文
            response.getWriter().write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
