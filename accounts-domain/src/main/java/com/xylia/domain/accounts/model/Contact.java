package com.xylia.domain.accounts.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Contact {

    @NotNull
    private String primaryPhone;

    private String secondaryPhone;

    @NotNull
    private String email;

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
