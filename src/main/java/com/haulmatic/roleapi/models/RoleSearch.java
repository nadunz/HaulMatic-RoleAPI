package com.haulmatic.roleapi.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@NoArgsConstructor
public class RoleSearch {

    @ApiModelProperty(value = "First Name", example = "Suresh", position = 0)
    private String firstName;

    @ApiModelProperty(value = "Last Name", example = "Perera", position = 1)
    private String lastName;

    @ApiModelProperty(value = "NIC No", example = "940289656V", position = 2)
    private String nic;

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
}
