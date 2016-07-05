package com.pacific.domain.dto;

import com.pacific.domain.enums.RoleTypeEnums;
import com.pacific.domain.enums.StateEnums;

import java.util.Date;

/**
 * Created by Fe on 16/6/7.
 */
public class UserDto {
    private Long id;

    private String userName;

    private String phone;

    private String email;

    private String wechatOpenId;

    private Date createTime;

    private Date updateTime;

    private String roleType;

    private String state;

    private String stateText;

    private String roleTypeText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateText() {
        return StateEnums.fromCode(this.state).getText();
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getRoleTypeText() {
        return RoleTypeEnums.fromCode(this.roleType).getCode();
    }

    public void setRoleTypeText(String roleTypeText) {
        this.roleTypeText = roleTypeText;
    }
}
