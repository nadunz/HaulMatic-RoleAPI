package com.haulmatic.roleapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haulmatic.roleapi.enums.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "role") // name of the collection
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {

    @Id
    @ApiModelProperty(value = "ID of the role", position = 0, readOnly = true)
    private String id;

    @ApiModelProperty(example = "Haulmatic", value = "Name of the organization", position = 1)
    private String organization;

    @ApiModelProperty(example = "Suresh", notes = "First name", position = 2)
    private String firstName;

    @ApiModelProperty(example = "Priyalal", value = "Last name", position = 3)
    private String lastName;

    @ApiModelProperty(example = "940568978V", value = "NIC number", position = 4)
    private String nic;

    @ApiModelProperty(example = "DRIVER", value = "Type of the role", allowableValues = "DRIVER,ASSISTANT", position = 5)
    private RoleType roleType;

    @ApiModelProperty(value = "Created time of the role", readOnly = true, position = 6)
    @CreatedDate
    private Date createdDate;

    @ApiModelProperty(value = "Last modified time of the role", readOnly = true, position = 7)
    @LastModifiedDate
    private Date lastModifiedDate;

    /**
     * This method returns a simple string format of this role instance
     *
     * @return string of this role
     */
    @Override
    public String toString() {
        return String.format("Role " +
                        "[id = %s, firstName = '%s', lastName = '%s', " +
                        "organization = '%s', nic = '%s', roleType = '%s', " +
                        "createdDate = '%s' lastModifiedDate = '%s']",
                id, firstName, lastName, organization, nic,
                roleType, createdDate, lastModifiedDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
