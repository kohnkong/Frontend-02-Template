package io.kohnkong.spring02.aop2;

import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 09:17
 */
public class I18nProperties {
    private static final String strPro = "properties.msg";

    /**
     * 获取国际化字符串
     * @param strKey
     * @return
     */
    public static String getI18nString(String strKey)
    {
        Locale cn = Locale.CHINA;//中文
        Locale us = Locale.US;//英文

        String lang = "cn";
        try {
            ResourceBundle strI18n = ResourceBundle.getBundle(strPro, cn);
            if (!StringUtils.isEmpty(lang) && "English(en-US)".contains(lang)) {
                strI18n = ResourceBundle.getBundle(strPro, us);
            }
            return strI18n.getString(strKey);
        } catch (Exception e) {
            return strKey;
        }
    }
}
