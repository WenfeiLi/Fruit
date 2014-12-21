package org.fruit.blueberry.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class JsonUtil {
    static JsonDeserializer<Date> deserializer = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Date date = null;
            final String asString = element.getAsString();

            if (asString != null) {
                try {
                    date = DateUtil.parseDate(asString, DateUtil.DATE_TIME_PATTERN);
                    return date;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return date;
        }
    };

    private static class UtilDateSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {
        @Override
        public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(date.getTime());
        }

        @Override
        public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new Date(element.getAsJsonPrimitive().getAsLong());
        }
    }

    private static class UtilCalendarSerializer implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
        @Override
        public JsonElement serialize(Calendar cal, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(cal.getTimeInMillis());
        }

        @Override
        public Calendar deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(element.getAsJsonPrimitive().getAsLong());
            return cal;
        }
    }

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Calendar.class, new UtilCalendarSerializer())
            .enableComplexMapKeySerialization()
            .registerTypeAdapter(GregorianCalendar.class, new UtilCalendarSerializer())
            .setDateFormat(DateFormat.LONG).setPrettyPrinting()
            .registerTypeAdapter(Date.class, deserializer)
            .setDateFormat(DateUtil.DATE_TIME_PATTERN)
            .create();

    public static String toJson(Object obj) {
        String json = gson.toJson(obj);
        return json;
    }

    public static String toJson(Object obj, Type type) {
        String json = gson.toJson(obj, type);
        return json;
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        T t = gson.fromJson(json, classOfT);
        return t;
    }

    public static String bean2json(Object bean) {
        return gson.toJson(bean);
    }

    public static <T> T json2bean(String json, Type type) {
        return (T) gson.fromJson(json, type);
    }
}
