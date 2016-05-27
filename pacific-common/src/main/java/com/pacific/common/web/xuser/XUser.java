package com.pacific.common.web.xuser;


import com.pacific.common.Constants;
import com.pacific.common.utils.CryptoUtil;

/**
 * Created by panwang.chengpw on 5/8/15.
 */
public class XUser {

    /**
     * 用户名
     */
    private String  userName = "";
    /**
     * 密码
     */
    private String  password = "";
    /**
     * 用户id
     */
    private Long    uid;
    /**
     * 是否登录
     */
    private boolean isSignedIn;
    /**
     * 是否自动登录
     */
    private boolean isSavePass;

    public XUser() {
    }

    public XUser(Long uid, String userName, String password, boolean isSavePass) {
        this.isSavePass = isSavePass;
        this.password = password;
        this.uid = uid;
        this.userName = userName;
    }

    public void setIsSignedIn(boolean isSignedIn) {
        this.isSignedIn = isSignedIn;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getIsSavePass() {
        return isSavePass;
    }

    public void setIsSavePass(boolean isSavePass) {
        this.isSavePass = isSavePass;
    }

    public boolean getIsSignedIn() {
        return isSignedIn;
    }

    public boolean isSignedIn() {
        return isSignedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecPassword() {
        return CryptoUtil.encryptAES(getPassword(), Constants.COOKIE_CRYPTO_PASS);
    }
}
