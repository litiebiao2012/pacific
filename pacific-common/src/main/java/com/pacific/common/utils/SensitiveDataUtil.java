package com.pacific.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensitiveDataUtil {
    /** 是否进行敏感数据屏蔽的开关 */
    private static boolean       hideFlag          = true;

    /** 大陆身份证号正则表达式 */
    private static final String  ID_CARD_REGEXP    = "[0-9]{15}|[0-9]{18}|[0-9]{14}X|[0-9]{17}X";
    /** 银行卡号正则表达式 */
    private static final String  BANK_CARD_REGEXP  = "[0-9]{13,19}";
    /** 手机或者固定电话正则表达式 */
    private static final String  PHONE_TEL_REGEXP  = "[0-9]{3,4}[-]?[0-9]{7,8}";
    /** 大陆身份证号匹配模式 */
    private static final Pattern ID_CARD_PATTERN   = Pattern.compile(ID_CARD_REGEXP);
    /** 银行卡号匹配模式 */
    private static final Pattern BANK_CARD_PATTERN = Pattern.compile(BANK_CARD_REGEXP);
    /** 手机或者固定电话匹配模式 */
    private static final Pattern PHONE_TEL_PATTERN = Pattern.compile(PHONE_TEL_REGEXP);

    /**
     * 构造函数
     *
     * @param hideFlag
     *            是否需要进行屏蔽的开关；<code>ture</code>表示打开。
     */
    public SensitiveDataUtil(final boolean hideFlag) {
        SensitiveDataUtil.setHideFlag(hideFlag);
    }

    /**
     * 构造函数，默认开启屏蔽开关
     */
    public SensitiveDataUtil() {
        SensitiveDataUtil.setHideFlag(true);
    }

    /**
     * 通过是否含有@符号，简单判断是否是Email地址。
     *
     * @param email
     * @return
     */
    public static boolean isEmail(final String email) {
        return email.indexOf('@') > 0;
    }



    /**
     * 简单判断是否为空字符串
     *
     * @param str
     * @return
     */
    private static boolean isBlank(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过正则表达式"[0-9]{15}|[0-9]{18}|[0-9]{14}X|[0-9]{17}X"来判断是否是合法的大陆身份证号。
     *
     * @param idCardNo
     * @return
     */
    private static boolean isIdCardNo(final String idCardNo) {
        if (isBlank(idCardNo))
            return false;
        else {
            Matcher matcher = ID_CARD_PATTERN.matcher(idCardNo.trim());
            return matcher.matches();
        }
    }

    /**
     * 通过正则表达式"[0-9]{13,19}"来判断是否是合法的银行卡号。
     *
     * @param bankCardNo
     * @return 指示入参是否银行卡号的布尔值。
     */
    private static boolean isBankCardNo(final String bankCardNo) {
        if (isBlank(bankCardNo))
            return false;
        else {
            Matcher matcher = BANK_CARD_PATTERN.matcher(bankCardNo.trim());
            return matcher.matches();
        }
    }

    /**
     * 通过正则表达式"[0-9]{3,4}[-]?[0-9]{7,8}"来判断是否是合法的电话号码。
     *
     * @param phoneOrTelNo
     * @return 指示入参是否电话号码的布尔值。
     */
    public static boolean isPhoneOrTelNo(final String phoneOrTelNo) {
        if (isBlank(phoneOrTelNo))
            return false;
        else {
            Matcher matcher = PHONE_TEL_PATTERN.matcher(phoneOrTelNo.trim());
            return matcher.matches();
        }
    }

    /**
     * hideFlag setter
     *
     * @param hideFlag
     */
    public static void setHideFlag(final boolean hideFlag) {
        SensitiveDataUtil.hideFlag = hideFlag;
    }

    /**
     * hideFlag getter
     *
     * @return 是否需要进行隐藏屏蔽
     */
    public static boolean needHide() {
        return hideFlag;
    }
}
