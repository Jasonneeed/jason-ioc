package com.jason.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 13:10
 * @modify 2020/4/2 13:10
 */
public final class CastUtil {

    public static String castString(Object o) {
        return castString(o, "");
    }

    public static String castString(Object o, String defaultValue) {
        return o != null ? String.valueOf(o) : defaultValue;
    }

    public static int castInt(Object o) {
        return castInt(o, 0);
    }

    public static int castInt(Object o, int intValue) {
        int value = intValue;
        if (o != null) {
            String strValue = castString(o);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    value = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    value = intValue;
                }
            }
        }
        return value;
    }

    public static double castDouble(Object o) {
        return castDouble(o, 0);
    }

    public static double castDouble(Object o, double defaultValue) {
        double value = defaultValue;
        if (o != null) {
            String strValue = castString(o);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static long castLong(Object o) {
        return castLong(o, 0);
    }

    public static long castLong(Object o, long longValue) {
        long value = longValue;
        if (o != null) {
            String strValue = castString(o);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    value = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    value = longValue;
                }
            }
        }
        return value;
    }

    public static boolean castBoolean(Object o) {
        return castBoolean(o, false);
    }

    public static boolean castBoolean(Object o, boolean b) {
        boolean value = b;
        if (o != null) {
            String strValue = castString(o);
            if (StringUtils.isNotEmpty(strValue)) {
                value = Boolean.parseBoolean(strValue);
            }
        }
        return value;
    }
}
