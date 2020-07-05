package com.haulmatic.roleapi.services;

import com.haulmatic.roleapi.models.Role;
import com.haulmatic.roleapi.enums.RoleType;
import com.haulmatic.roleapi.models.RoleSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoleService {

    public Role createRole(Role role) throws Exception;

    public Role getRoleById(String id) throws Exception;

    public Role getRoleByNic(String nic) throws Exception;

    public Page<Role> getAllRoles(Pageable pageable);

    public Role updateRoleById(Role role, String id) throws Exception;

    public Role deleteRoleById(String id) throws Exception;

    public Role updateRoleByNic(Role role, String id) throws Exception;

    public Role deleteRoleByNic(String id) throws Exception;

    public List<RoleSearch> searchRolesByOrganizationAndRoleType(String organization, RoleType roleType) throws Exception;
}
