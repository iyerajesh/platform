package com.xylia.platform.events.test;

import com.xylia.platform.events.event.CustomerCreatedEvent;
import com.xylia.platform.events.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerCreatedEventBuilder {

    public static CustomerCreatedEvent createSampleCustomerCreatedEvent() {

        Name name = new Name();
        name.setTitle("Mr.");
        name.setFirstName("John");
        name.setMiddleName("S.");
        name.setLastName("Doe");
        name.setSuffix("Jr.");

        Contact contact = new Contact();
        contact.setPrimaryPhone("555-666-7777");
        contact.setSecondaryPhone("555-444-9898");
        contact.setEmail("john.doe@internet.com");

        List<Address> addressList = new ArrayList<>();

        Address address = new Address();
        address.setType(AddressType.BILLING);
        address.setDescription("My cc billing address");
        address.setAddress1("123 Oak Street");
        address.setCity("Sunrise");
        address.setState("CA");
        address.setPostalCode("12345-6789");

        addressList.add(address);

        address = new Address();
        address.setType(AddressType.SHIPPING);
        address.setDescription("My home address");
        address.setAddress1("123 Oak Street");
        address.setCity("Sunrise");
        address.setState("CA");
        address.setPostalCode("12345-6789");

        addressList.add(address);

        List<CreditCard> creditCardList = new ArrayList<>();

        CreditCard creditCard = new CreditCard();
        creditCard.setType(CreditCardType.PRIMARY);
        creditCard.setDescription("VISA");
        creditCard.setNumber("1234-6789-0000-0000");
        creditCard.setExpiration("6/19");
        creditCard.setNameOnCard("John S. Doe");

        creditCardList.add(creditCard);

        creditCard = new CreditCard();
        creditCard.setType(CreditCardType.ALTERNATE);
        creditCard.setDescription("Corporate American Express");
        creditCard.setNumber("9999-8888-7777-6666");
        creditCard.setExpiration("3/20");
        creditCard.setNameOnCard("John Doe");

        creditCardList.add(creditCard);

        Customer customer = new Customer();
        customer.setName(name);
        customer.setContact(contact);
        customer.setAddresses(addressList);
        customer.setCreditCards(creditCardList);

        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        customerCreatedEvent.setId(customer.getId());
        customerCreatedEvent.setName(customer.getName().getFirstName());
        customerCreatedEvent.setContact(customer.getContact());
        customerCreatedEvent.setAddresses(customer.getAddresses());


        return customerCreatedEvent;
    }

}
