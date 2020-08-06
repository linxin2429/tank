package com.xldeng;

import java.io.IOException;
import java.util.Properties;

/**
 * @author 邓鑫林
 * @since 2020/8/6 23:07
 */
public class PropertyMgr {
    static Properties properties = new Properties();
    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (properties == null){
            return null;
        }
        return properties.get(key);
    }

    public static String getString(String key){
        Object value = get(key);
        if (value == null){
            throw new RuntimeException("Illegal parameter");
        }
        return (String) value;
    }

    public static Integer getInt(String key){
        return Integer.parseInt(getString(key));
    }
}