package io.xylia.domain.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Address {

    @NotNull
    private AddressType type;

    @NotNull
    private String description;

    @NotNull
    private String address1;

    public void setType(AddressType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    private String address2;

    @NotNull
    private String city;

    @NotNull
    private String state;

    private String postalCode;


}
