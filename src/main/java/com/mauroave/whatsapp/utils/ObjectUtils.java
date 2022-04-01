package com.mauroave.whatsapp.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public ObjectUtils() {
    }

    public static <T> T convertValue(Object from, Class<T> toClass) throws RuntimeException {
        try {
            return mapper.convertValue(from, toClass);
        } catch (Exception var3) {
            var3.printStackTrace();
            if (from != null) {
                throw new RuntimeException("Error al intentar mappear un objeto de la clase " + from.getClass() + " a la clase " + toClass, var3);
            } else {
                throw new RuntimeException("Error al intentar mappear un objeto a la clase " + toClass, var3);
            }
        }
    }

    public static <T> List<T> convertValue(List from, Class<T> toClass) throws RuntimeException {
        try {
            List<T> list = new ArrayList();
            Iterator var4 = from.iterator();

            while(var4.hasNext()) {
                Object element = var4.next();
                list.add(convertValue(element, toClass));
            }

            return list;
        } catch (Exception var5) {
            throw new RuntimeException("Error al intentar mappear un objeto de la clase " + from.getClass() + "a la clase " + toClass, var5);
        }
    }

    public static <T> T convertValue(String from, Class<T> toClass) throws RuntimeException {
        try {
            if (toClass.equals(String.class)) {
                return (T) from;
            } else {
                JsonParser parser = mapper.getFactory().createParser(from);

                try {
                    parser.nextToken();
                    return mapper.readValue(from, toClass);
                } catch (JsonParseException var4) {
                    return mapper.convertValue(from, toClass);
                }
            }
        } catch (Exception var5) {
            throw new RuntimeException("Error al intentar mappear un objeto de la clase " + from.getClass() + "a la clase " + toClass, var5);
        }
    }

    public static <T> T convertValue(String from, TypeReference<T> toClass) throws RuntimeException {
        try {
            return toClass.equals(String.class) ? (T) from : mapper.readValue(from, toClass);
        } catch (Exception var3) {
            throw new RuntimeException("Error al intentar mappear un objeto de la clase " + from.getClass() + "a la clase " + toClass, var3);
        }
    }
}
