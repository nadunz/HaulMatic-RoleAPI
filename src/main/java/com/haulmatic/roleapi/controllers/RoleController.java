package com.haulmatic.roleapi.controllers;

import com.haulmatic.roleapi.enums.RoleType;
import com.haulmatic.roleapi.models.Role;
import com.haulmatic.roleapi.models.RoleSearch;
import com.haulmatic.roleapi.models.responses.Response;
import com.haulmatic.roleapi.services.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/haulmatic/api/roles")
@CrossOrigin(origins = "http://localhost:8080")
public class RoleController {

    private IRoleService iRoleService;

    @Autowired
    public void setIRoleService(IRoleService iRoleService) {
        this.iRoleService = iRoleService;
    }

    // Create Operation
    @PostMapping
    @ApiOperation(value = "Create a new role", produces = "application/json")
    public Response<Role> createRole(@RequestBody Role role) throws Exception {
        return new Response<>(iRoleService.createRole(role), HttpStatus.CREATED);
    }

    // Read operation by id
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a role by ID", produces = "application/json")
    public Response<Role> getRoleById(@PathVariable(value = "id") String id) throws Exception {
        return new Response<>(iRoleService.getRoleById(id), HttpStatus.OK);
    }

    // Read operation by nic
    @GetMapping("/nic/{nic}")
    @ApiOperation(value = "Get a role by NIC no", produces = "application/json")
    public Response<Role> getRoleByNIC(@PathVariable(value = "nic") String id) throws Exception {
        return new Response<>(iRoleService.getRoleByNic(id), HttpStatus.OK);
    }

    // Read operation for all
    @GetMapping
    @ApiOperation(value = "Get list of all roles", produces = "application/json")
    public Response<Page<Role>> getAllRoles(Pageable pageable) throws Exception {
        return new Response<>(iRoleService.getAllRoles(pageable), HttpStatus.OK);
    }

    // Update role operation by id
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a role by ID", produces = "application/json")
    public Response<Role> updateRoleById(@RequestBody Role role, @PathVariable(value = "id") String id) throws Exception {
        return new Response<>(iRoleService.updateRoleById(role, id), HttpStatus.OK);
    }

    // Delete role operation by id
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a role by ID", produces = "application/json")
    public Response<Role> deleteRoleById(@PathVariable(value = "id") String id) throws Exception {
        return new Response<>(iRoleService.deleteRoleById(id), HttpStatus.OK);
    }

    // Update role operation by nic
    @PutMapping("/nic/{nic}")
    @ApiOperation(value = "Update a role by NIC", produces = "application/json")
    public Response<Role> updateRoleByNic(@RequestBody Role role, @PathVariable(value = "nic") String nic) throws Exception {
        return new Response<>(iRoleService.updateRoleByNic(role, nic), HttpStatus.OK);
    }

    // Delete role operation by nic
    @DeleteMapping("/nic/{nic}")
    @ApiOperation(value = "Delete a role by NIC", produces = "application/json")
    public Response<Role> deleteRoleByNic(@PathVariable(value = "nic") String nic) throws Exception {
        return new Response<>(iRoleService.deleteRoleByNic(nic), HttpStatus.OK);
    }

    // Search role operation by organization name & role type
    @GetMapping("/search")
    @ApiOperation(value = "Get list of roles by name of organization and role type", produces = "application/json")
    public Response<List<RoleSearch>> searchRolesByOrgAndRoleType(@RequestParam(value = "organization") String organization,
                                                                  @RequestParam(value = "roletype") RoleType roleType) throws Exception {
        return new Response<>(iRoleService.searchRolesByOrganizationAndRoleType(organization, roleType), HttpStatus.OK);
    }


}
