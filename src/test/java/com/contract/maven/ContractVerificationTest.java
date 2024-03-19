//package com.contract.maven;
//
//import au.com.dius.pact.provider.junit5.PactVerificationContext;
//import au.com.dius.pact.provider.junitsupport.Provider;
//import au.com.dius.pact.provider.junitsupport.State;
//import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
//import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import model.Order;
//import repository.OrdersRepository;
//import org.junit.jupiter.api.TestTemplate;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@Provider("order_provider")
//@PactBroker
//public class ContractVerificationTest {
//    @MockBean
//    private OrdersRepository ordersRepository;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @TestTemplate
//    @ExtendWith(PactVerificationSpringProvider.class)
//    void pactVerificationTestTemplate(PactVerificationContext context) {
//        context.verifyInteraction();
//    }
//
//    @State("there are orders")
//    public void thereAreOrders() throws IOException {
//        Mockito.when(ordersRepository.getOrders()).thenReturn(getOrdersFromFile("orders.json"));
//    }
//
//    @State("there are no orders")
//    public void thereAreNoOrders() throws IOException {
//        Mockito.when(ordersRepository.getOrders()).thenReturn(getOrdersFromFile("no_orders.json"));
//    }
//
//    private List<Order> getOrdersFromFile(String filename) throws IOException {
//        URL resource = getClass().getClassLoader().getResource(filename);
//        return objectMapper.readValue(resource, new TypeReference<>() {
//        });
//    }
//}


package com.contract.maven;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;

import com.contract.maven.model.Order;
import com.contract.maven.repository.OrdersRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("order_provider")
@PactFolder("pacts")
public class ContractVerificationTest {
    @MockBean
    private OrdersRepository ordersRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String noOrdersJson = "[]";

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("there are orders")
    public void thereAreOrders() throws IOException {
        Mockito.when(ordersRepository.getOrders()).thenReturn(getOrdersFromFile("orders.json"));
    }
    
//    @State("there are no orders")
//    public void thereAreNoOrders() throws Exception {
//        // Deserialize the JSON string into an empty list
//        List<Order> noOrders = objectMapper.readValue(noOrdersJson, new TypeReference<>() {});
//        Mockito.when(ordersRepository.getOrders()).thenReturn(noOrders);
//    }

    @State("there are no orders")
    public void thereAreNoOrders() throws IOException {
        Mockito.when(ordersRepository.getOrders()).thenReturn(getOrdersFromFile("no_orders.json"));
    }
    

    private List<Order> getOrdersFromFile(String filename) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filename);
        System.out.println("resource");
        System.out.println(resource);
        return objectMapper.readValue(resource, new TypeReference<>() {
        });
    }
}


