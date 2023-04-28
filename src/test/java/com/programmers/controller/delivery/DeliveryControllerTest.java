package com.programmers.controller.delivery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmers.domain.delivery.Delivery;
import com.programmers.repository.delivery.DeliveryRepository;
import com.programmers.service.delivery.DeliveryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class DeliveryControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryService deliveryService;

    @BeforeEach
    void clean() {
        mockMvc = MockMvcBuilders.standaloneSetup(new DeliveryController(deliveryService)).build();
        deliveryRepository.deleteAll();
    }

    @Test
    void saveDelivery() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        Delivery delivery = basicDeliveryData();
        Delivery savedDelivery = deliveryRepository.save(delivery);

        String json = objectMapper.writeValueAsString(savedDelivery);

        //when,then
        mockMvc.perform(post("/deliveries/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void searchDeliveryById() throws Exception {
        //given
        Delivery delivery = basicDeliveryData();
        Delivery savedDelivery = deliveryRepository.save(delivery);

        //when,then

        mockMvc.perform(get("/deliveries/{deliveryId}", savedDelivery.getDeliveryId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.deliveryId").value(String.valueOf(savedDelivery.getDeliveryId())));
    }

    private Delivery basicDeliveryData() {
        return Delivery.builder()
                .build();
    }
}
