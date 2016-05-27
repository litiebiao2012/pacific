package com.pacific.common.web;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

/**
 * Created by chengpanwang on 7/10/15.
 */
public class StringTrimmerEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(String.class, new StringTrimmerEditor(" \t\r\n\f", false));
    }
}
