package com.haulmatic.roleapi.helper;

import com.haulmatic.roleapi.enums.RoleType;
import com.haulmatic.roleapi.models.Role;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {

    /**
     * This method checks whether the given NIC is in valid format
     *
     * @param nic the provided nic number
     * @return true if valid, otherwise false
     */
    public boolean isValidNIC(String nic) {
        if (!nic.isEmpty() && nic.length() == 10) {
            Pattern p = Pattern.compile("[0-9]{9}[vV]");
            Matcher m = p.matcher(nic);
            return m.find();
        }
        return false;
    }

    public boolean isAvailableRoleType(String roleType) {
        return roleType.equalsIgnoreCase(RoleType.DRIVER.toString())
                || roleType.equalsIgnoreCase(RoleType.ASSISTANT.toString());
    }

    /**
     * This method checks whether one of given role attributes are empty or not
     *
     * @return true if all attribute values are not empty, otherwise false
     */
    public boolean isEmpty(Role role) {
        return role.getOrganization().isEmpty() || role.getFirstName().isEmpty()
                || role.getLastName().isEmpty() || role.getRoleType().toString().isEmpty()
                || role.getNic().isEmpty();
    }

}
