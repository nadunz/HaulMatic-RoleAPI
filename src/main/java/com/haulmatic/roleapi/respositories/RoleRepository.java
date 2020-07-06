package com.haulmatic.roleapi.respositories;

import com.haulmatic.roleapi.enums.RoleType;
import com.haulmatic.roleapi.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoleRepository extends MongoRepository<Role, String> {

    public Role findByNic(String nic);

    public List<Role> findAllByOrganizationAndRoleType(String Organization, RoleType roleType);

    public Role deleteByNic(String nic);
}
