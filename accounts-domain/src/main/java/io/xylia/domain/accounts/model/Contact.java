package io.xylia.domain.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
