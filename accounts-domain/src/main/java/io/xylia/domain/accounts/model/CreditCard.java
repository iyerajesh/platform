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
public class CreditCard {

    @NotNull
    private CreditCardType type;
    @NotNull
    private String description;
    @NotNull
    private String number;
    @NotNull
    private String expiration;
    @NotNull
    private String nameOnCard;

    public void setType(CreditCardType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
}
