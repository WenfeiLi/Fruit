package org.fruit.blueberry.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by AFei on 2014/7/27.
 */
public class PropertiesUtil {
    public static Properties getJDBCProperties() {
        String filePath = PropertiesUtil.class.getClassLoader().getResource("jdbc.properties").getPath();
        return getPropertiesByFileName(filePath);
    }

    public static Properties getDaoProperties() {
        String filePath = PropertiesUtil.class.getClassLoader().getResource("daoConfig.properties").getPath();
        return getPropertiesByFileName(filePath);
    }

    private static Properties getPropertiesByFileName(String fileName) {
        Properties properties = new Properties();
        File file = new File(fileName);

        if(file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
                properties.load(bufferedReader);
            } catch (IOException e) {
                throw new ExceptionInInitializerError("[Error] The format of file " + fileName + " is incorrect.");
            }
        }else {
            throw new RuntimeException(new FileNotFoundException("[Error] Configuration file not found."));
        }

        return properties;
    }
}
