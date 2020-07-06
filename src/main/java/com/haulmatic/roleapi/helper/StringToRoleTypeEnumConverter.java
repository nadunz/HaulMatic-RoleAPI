package com.haulmatic.roleapi.helper;

import com.haulmatic.roleapi.enums.RoleType;
import org.springframework.core.convert.converter.Converter;

public class StringToRoleTypeEnumConverter implements Converter<String, RoleType> {
    @Override
    public RoleType convert(String source) {
        try {
            return RoleType.valueOf(source.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
