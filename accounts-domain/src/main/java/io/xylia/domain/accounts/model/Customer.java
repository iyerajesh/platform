package io.xylia.domain.accounts.model;


import io.xylia.domain.accounts.annotations.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer.accounts")
@Aggregate
public class Customer {

    @Id
    private String id;
    @NotNull
    private Name name;
    @NotNull
    private Contact contact;
    @NotNull
    private List<Address> addresses;
    @NotNull
    private List<CreditCard> creditCards;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public String getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

}
