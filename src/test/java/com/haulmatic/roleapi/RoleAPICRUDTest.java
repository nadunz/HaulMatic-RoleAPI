package com.haulmatic.roleapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.haulmatic.roleapi.enums.RoleType;
import com.haulmatic.roleapi.models.Role;
import com.haulmatic.roleapi.respositories.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RoleAPIApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleAPICRUDTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    RoleRepository roleRepository;

    private MockMvc mvc;
    private Role role;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        role = new Role();
        role.setNic("930233545V");
        role.setFirstName("Nadun");
        role.setLastName("Adikari");
        role.setOrganization("Haulmatic");
        role.setRoleType(RoleType.DRIVER);
    }

    @Test
    public void createRole() throws Exception {
        String dataInputJson = objectToJson(role);

        // if exists the role nic delete that role
        roleRepository.deleteByNic(role.getNic());

        String url = "http://localhost:8080/haulmatic/api/roles";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(dataInputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        JsonObject data = jsonObject.get("data").getAsJsonObject();
        assertEquals(data.get("nic").getAsString(), role.getNic());
        assertEquals(data.get("firstName").getAsString(), role.getFirstName());
        assertEquals(data.get("lastName").getAsString(), role.getLastName());
        assertEquals(data.get("organization").getAsString(), role.getOrganization());
        assertEquals(data.get("roleType").getAsString(), role.getRoleType().toString());
    }

    @Test
    public void readRole() throws Exception {
        // if exists the role nic delete that role
        roleRepository.deleteByNic(role.getNic());
        roleRepository.save(role);
        String url = "http://localhost:8080/haulmatic/api/roles/nic/" + role.getNic();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        JsonObject data = jsonObject.get("data").getAsJsonObject();
        assertEquals(data.get("nic").getAsString(), role.getNic());
        assertEquals(data.get("firstName").getAsString(), role.getFirstName());
        assertEquals(data.get("lastName").getAsString(), role.getLastName());
        assertEquals(data.get("organization").getAsString(), role.getOrganization());
        assertEquals(data.get("roleType").getAsString(), role.getRoleType().toString());
    }

    @Test
    public void updateRole() throws Exception {

        String firstNameNew = "Kasun";
        String lastNameNew = "Perera";

        // if exists the role nic delete that role
        roleRepository.deleteByNic(role.getNic());
        roleRepository.save(role);

        role.setFirstName(firstNameNew);
        role.setLastName(lastNameNew);

        String dataInputJson = objectToJson(role);

        String url = "http://localhost:8080/haulmatic/api/roles/nic/" + role.getNic();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(dataInputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        JsonObject data = jsonObject.get("data").getAsJsonObject();
        assertEquals(data.get("nic").getAsString(), role.getNic());
        assertEquals(data.get("firstName").getAsString(), role.getFirstName());
        assertEquals(data.get("lastName").getAsString(), role.getLastName());
        assertEquals(data.get("organization").getAsString(), role.getOrganization());
        assertEquals(data.get("roleType").getAsString(), role.getRoleType().toString());
    }

    @Test
    public void deleteRole() throws Exception {

        // if exists the role nic delete that role
        roleRepository.deleteByNic(role.getNic());
        roleRepository.save(role);

        String url = "http://localhost:8080/haulmatic/api/roles/nic/" + role.getNic();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        Optional<Role> actualRole = Optional.ofNullable(roleRepository.findByNic(role.getNic()));
        assertEquals(Optional.empty(), actualRole);
    }



    @Test
    public void searchRoles() throws Exception {

        // if exists the role nic delete that role
        roleRepository.deleteByNic(role.getNic());
        roleRepository.save(role);

        String url = "http://localhost:8080/haulmatic/api/roles/search?organization=" + role.getOrganization() +
                "&roletype=" + role.getRoleType().toString();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        JsonArray dataArr = jsonObject.get("data").getAsJsonArray();
        for (int i = 0; i < dataArr.size(); i++) {
            JsonObject data = dataArr.get(i).getAsJsonObject();
            String nic = data.get("nic").getAsString();
            Role foundRole = roleRepository.findByNic(nic);
            assertEquals(foundRole.getOrganization(), role.getOrganization());
            assertEquals(foundRole.getRoleType(), role.getRoleType());
        }
    }

    private String objectToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsString(obj);
    }
}