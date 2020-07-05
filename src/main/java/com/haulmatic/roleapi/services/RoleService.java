package com.haulmatic.roleapi.services;

import com.haulmatic.roleapi.exceptions.NICAlreadyExistsException;
import com.haulmatic.roleapi.exceptions.ResourceNotFoundException;
import com.haulmatic.roleapi.exceptions.UnavailableRoleTypeException;
import com.haulmatic.roleapi.helper.Validator;
import com.haulmatic.roleapi.models.Role;
import com.haulmatic.roleapi.models.RoleSearch;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.haulmatic.roleapi.enums.RoleType;
import com.haulmatic.roleapi.exceptions.DataValidationException;
import com.haulmatic.roleapi.respositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RoleService implements IRoleService {

    private RoleRepository roleRepository;
    private Validator validator;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public Role createRole(Role role) throws Exception {

        // Check whether one of attribute in the role variable is empty
        if (validator.isEmpty(role)) {
            throw new DataValidationException("Empty values in role");
        }
        if (!validator.isAvailableRoleType(role.getRoleType().toString())) {
            throw new UnavailableRoleTypeException("Given role type is unavailable");
        }
        Optional<Role> foundRole = Optional.ofNullable(roleRepository.findByNic(role.getNic()));
        if (foundRole.isPresent()) {
            throw new NICAlreadyExistsException("NIC number already exists");
        }

        if (!validator.isValidNIC(role.getNic())) {
            throw new DataValidationException("Invalid NIC number");
        }

        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(String id) throws Exception {
        id = id.trim();
        if (id.isEmpty()) {
            throw new DataValidationException("Empty role Id");
        }
        Optional<Role> foundRole = roleRepository.findById(id);
        if(foundRole.isPresent()) {
            return foundRole.get();
        } else {
            throw new ResourceNotFoundException("Role Id doesn't exists");
        }
    }

    @Override
    public Role getRoleByNic(String nic) throws Exception {
        nic = nic.trim();
        if (nic.isEmpty()) {
            throw new DataValidationException("Empty NIC number");
        }
        if (!validator.isValidNIC(nic)) {
            throw new DataValidationException("Invalid NIC number");
        }
        Optional<Role> foundRole = Optional.ofNullable(roleRepository.findByNic(nic));
        if(foundRole.isPresent()) {
            return foundRole.get();
        } else {
            throw new ResourceNotFoundException("Role NIC doesn't exists");
        }
    }

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Role updateRoleById(Role role, String id) throws Exception {

        id = id.trim();
        if (id.isEmpty()) {
            throw new DataValidationException("Empty role Id");
        }
        if (validator.isEmpty(role)) {
            throw new DataValidationException("Empty values in role");
        }
        Optional<Role> foundRole = roleRepository.findById(id);
        foundRole.ifPresent(r -> {
            if (!role.getFirstName().isEmpty()) r.setFirstName(role.getFirstName());
            if (!role.getLastName().isEmpty()) r.setLastName(role.getLastName());
            if (!role.getOrganization().isEmpty()) r.setOrganization(role.getOrganization());
            if (!role.getNic().isEmpty()) r.setNic(role.getNic());
            if (!role.getRoleType().name().isEmpty()) r.setRoleType(role.getRoleType());
            r.setLastModifiedDate(new Date());
        });
        foundRole.orElseThrow(() -> new ResourceNotFoundException("Role Id doesn't exists"));

        return roleRepository.save(foundRole.get());
    }

    @Override
    public Role deleteRoleById(String id) throws Exception {
        id = id.trim();
        if (id.isEmpty()) {
            throw new DataValidationException("Empty role Id");
        }

        Optional<Role> foundRole = roleRepository.findById(id);
        foundRole.ifPresent( r -> {
            roleRepository.delete(r);
        });
        foundRole.orElseThrow(() -> new ResourceNotFoundException("Role Id doesn't exists"));
        return foundRole.get();
    }

    @Override
    public Role updateRoleByNic(Role role, String nic) throws Exception {
        nic = nic.trim();
        if (nic.isEmpty()) {
            throw new DataValidationException("Empty NIC number");
        }
        if (validator.isEmpty(role)) {
            throw new DataValidationException("Empty values in role");
        }
        if (!validator.isValidNIC(nic)) {
            throw new DataValidationException("Invalid NIC number");
        }
        Optional<Role> foundRole = Optional.ofNullable(roleRepository.findByNic(nic));
        foundRole.ifPresent(r -> {
            if (!role.getFirstName().isEmpty()) r.setFirstName(role.getFirstName());
            if (!role.getLastName().isEmpty()) r.setLastName(role.getLastName());
            if (!role.getOrganization().isEmpty()) r.setOrganization(role.getOrganization());
            if (!role.getNic().isEmpty()) r.setNic(role.getNic());
            if (!role.getRoleType().name().isEmpty()) r.setRoleType(role.getRoleType());
            r.setLastModifiedDate(new Date());
        });
        foundRole.orElseThrow(() -> new ResourceNotFoundException("Role NIC doesn't exists"));
        return roleRepository.save(foundRole.get());
    }

    @Override
    public Role deleteRoleByNic(String nic) throws Exception {
        nic = nic.trim();
        if (nic.isEmpty()) {
            throw new DataValidationException("Empty NIC number");
        }
        if (!validator.isValidNIC(nic)) {
            throw new DataValidationException("Invalid NIC number");
        }
        Optional<Role> foundRole = Optional.ofNullable(roleRepository.findByNic(nic));
        foundRole.ifPresent( r -> {
            roleRepository.delete(r);
        });
        foundRole.orElseThrow(()-> new ResourceNotFoundException("Role NIC doesn't exists"));
        return foundRole.get();
    }

    @Override
    public List<RoleSearch> searchRolesByOrganizationAndRoleType(String organization, RoleType roleType) throws Exception {
        organization = organization.trim();
        if (organization.isEmpty()) {
            throw new DataValidationException("Empty name for organization");
        }
        List<RoleSearch> roleSearch = new ArrayList<>();
        Optional<List<Role>> roleList = Optional.ofNullable(roleRepository.findAllByOrganizationAndRoleType(organization, roleType));
        if (roleList.isPresent()) {
            roleList.get().forEach(role -> {
                RoleSearch rs = new RoleSearch();
                rs.setFirstName(role.getFirstName());
                rs.setLastName(role.getLastName());
                rs.setNic(role.getNic());
                roleSearch.add(rs);
            });
            return roleSearch;
        } else {
            throw new ResourceNotFoundException("No roles found for given organization and role type");
        }
    }

}
