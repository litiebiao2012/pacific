package com.pacific.domain.dto;

import com.pacific.domain.entity.ApplicationUserConfig;

/**
 * Created by Fe on 16/6/12.
 */
public class ApplicationUserConfigDto extends ApplicationUserConfig {
    private String phone;

    private String email;

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
}
