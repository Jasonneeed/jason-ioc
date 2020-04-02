package com.jason.helper;

import com.jason.ConfigConstant;
import com.jason.utils.PropsUtils;

import java.util.Properties;

/**
 * @author Jason
 * @version 1.0
 * @createTime 2020/4/2 11:49
 * @modify 2020/4/2 11:49
 */
public final class ConfigHelper {

    private static final Properties configProps = PropsUtils.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getJdbcDriver() {
        return PropsUtils.getString(configProps, ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return PropsUtils.getString(configProps, ConfigConstant.JDBC_URL);
    }

    public static String getJdbcUsername() {
        return PropsUtils.getString(configProps, ConfigConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return PropsUtils.getString(configProps, ConfigConstant.JDBC_PASSWORD);
    }

    public static String getBasePackage(){
        return PropsUtils.getString(configProps, ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getJspPath(){
        return PropsUtils.getString(configProps, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view");
    }

    public static String getAssetPath(){
        return PropsUtils.getString(configProps, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
}
