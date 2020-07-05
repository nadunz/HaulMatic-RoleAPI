package com.haulmatic.roleapi.respositories;

import com.haulmatic.roleapi.models.Role;
import com.haulmatic.roleapi.enums.RoleType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    public Role findByNic(String nic);

    public List<Role> findAllByOrganizationAndRoleType(String Organization, RoleType roleType);
}
