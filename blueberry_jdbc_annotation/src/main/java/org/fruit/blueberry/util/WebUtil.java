package org.fruit.blueberry.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.fruit.blueberry.dto.RegisterForm;
import org.fruit.blueberry.model.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by AFei on 2014/7/26.
 */
public class WebUtil {
    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {
            T bean = beanClass.newInstance();
            Enumeration<String> enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getParameter(name).trim();
                BeanUtils.setProperty(bean, name, value);
            }

            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyBean(RegisterForm src, User dest) {
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                if (value == null) {
                    return null;
                }

                String str = (String) value;
                if (str.trim().equals("")) {
                    return null;
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return df.parse(str);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }, Date.class);

        try {
            BeanUtils.copyProperties(dest, src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
