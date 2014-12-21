package org.fruit.blueberry.util;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by AFei on 2014/7/27.
 */
public class CookieUtil {
    public static String buildCookie(String id, Cookie[] cookies) {
        String fruitHistory = null;

        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if ("fruitHistory".equals(cookies[i].getName())) {
                fruitHistory = cookies[i].getValue();
                break;
            }
        }

        if (fruitHistory == null) {
            return id;
        }

        LinkedList<String> list = new LinkedList<String>(Arrays.asList(fruitHistory.split("\\,")));
        if (list.contains(id)) {
            list.remove(id);
        } else {
            if (list.size() >= 3) {
                list.removeLast();
            }
        }
        list.addFirst(id);

        StringBuffer stringBuffer = new StringBuffer();
        for (String s : list) {
            stringBuffer.append(s + ",");
        }

        return stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString();
    }
}
