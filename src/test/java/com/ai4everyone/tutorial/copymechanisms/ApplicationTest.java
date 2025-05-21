package com.ai4everyone.tutorial.copymechanisms;

import com.ai4everyone.tutorial.copymechanisms.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTest.class);

    @Test
    void givenAddressAndUser_whenModifyOriginalObject_thenShallowCopyObjectShouldChange() {
        LOGGER.info("ApplicationTest#givenAddressAndUser_whenModifyOriginalObject_thenShallowCopyObjectShouldChange");

        var address = new Address("city", "country");
        var user = new User("name", "family", address);

        var shallowCopyUser = new User(user.getName(), user.getFamily(), user.getAddress());
        address.setCity("newCity");
        address.setCountry("newCountry");

        assertAll(
                () -> assertEquals(user.getAddress().getCity(), shallowCopyUser.getAddress().getCity()),
                () -> assertEquals(user.getAddress().getCountry(), shallowCopyUser.getAddress().getCountry())
        );
    }

    @Test
    void givenAddressAndUser_whenModifyOriginalObject_thenDeepCopyObjectShouldChange() {
        LOGGER.info("ApplicationTest#givenAddressAndUser_whenModifyOriginalObject_thenDeepCopyObjectShouldChange");

        var address = new Address("city", "country");
        var user = new User("name", "family", address);

        var deepCopyUser = new User(user);
        address.setCity("newCity");
        address.setCountry("newCountry");

        assertAll(
                () -> assertNotEquals(user.getAddress().getCity(), deepCopyUser.getAddress().getCity()),
                () -> assertNotEquals(user.getAddress().getCountry(), deepCopyUser.getAddress().getCountry())
        );
    }

    @Test
    public void givenAccountAndCustomer_whenModifyingOriginalObject_thenCloneCopyShouldNotChange() {
        LOGGER.info("ApplicationTest#givenAccountAndCustomer_whenModifyingOriginalObject_thenCloneCopyShouldNotChange");

        var account = new Account("accountId", true);
        var customer = new Customer("name", "family", account);

        Customer deepCopyCustomer = (Customer) customer.clone();
        account.setAccountId("newAccountId");
        account.setActive(false);

        assertAll(
                () -> assertNotEquals(customer.getAccount().getAccountId(), deepCopyCustomer.getAccount().getAccountId()),
                () -> assertNotEquals(customer.getAccount().isActive(), deepCopyCustomer.getAccount().isActive())
        );
    }

    @Test
    public void givenAccountAndCustomer_whenModifyOriginalObject_thenCommonsCloneShouldNotChange() {
        LOGGER.info("ApplicationTest#givenAccountAndCustomer_whenModifyOriginalObject_thenCommonsCloneShouldNotChange");

        var account = new Account("accountId", true);
        var customer = new Customer("name", "family", account);

        Customer deepCopyCustomer = (Customer) SerializationUtils.clone(customer);
        account.setAccountId("newAccountId");
        account.setActive(false);

        assertAll(
                () -> assertNotEquals(customer.getAccount().getAccountId(), deepCopyCustomer.getAccount().getAccountId()),
                () -> assertNotEquals(customer.getAccount().isActive(), deepCopyCustomer.getAccount().isActive())
        );
    }

    @Test
    public void givenAccountAndCustomer_whenModifyOriginalObject_thenGsonCloneShouldNotChange() {
        LOGGER.info("ApplicationTest#givenAccountAndCustomer_whenModifyOriginalObject_thenGsonCloneShouldNotChange");

        var account = new Account("accountId", true);
        var customer = new Customer("name", "family", account);

        Gson gson = new Gson();
        Customer deepCopyCustomer = gson.fromJson(gson.toJson(customer), Customer.class);
        account.setAccountId("newAccountId");
        account.setActive(false);

        assertAll(
                () -> assertNotEquals(customer.getAccount().getAccountId(), deepCopyCustomer.getAccount().getAccountId()),
                () -> assertNotEquals(customer.getAccount().isActive(), deepCopyCustomer.getAccount().isActive())
        );
    }

    @Test
    public void givenAccountAndCustomer_whenModifyOriginalObject_thenJacksonCopyShouldNotChange() throws IOException {
        LOGGER.info("ApplicationTest#givenAccountAndCustomer_whenModifyOriginalObject_thenJacksonCopyShouldNotChange");

        var account = new Account("accountId", true);
        var customer = new Customer("name", "family", account);
        ObjectMapper objectMapper = new ObjectMapper();

        Customer deepCopyCustomer = objectMapper.readValue(objectMapper.writeValueAsString(customer), Customer.class);
        account.setAccountId("newAccountId");
        account.setActive(false);

        assertAll(
                () -> assertNotEquals(customer.getAccount().getAccountId(), deepCopyCustomer.getAccount().getAccountId()),
                () -> assertNotEquals(customer.getAccount().isActive(), deepCopyCustomer.getAccount().isActive())
        );
    }
}