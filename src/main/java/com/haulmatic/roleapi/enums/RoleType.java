package com.haulmatic.roleapi.enums;

/**
 * All the role types of the system
 */
public enum RoleType {

    DRIVER("Driver"), ASSISTANT("Assistant");

    public final String name;

    private RoleType(String name) {
        this.name = name;
    }

}

