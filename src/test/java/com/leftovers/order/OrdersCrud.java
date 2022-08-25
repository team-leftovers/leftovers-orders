package com.leftovers.order;

import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class OrdersCrud {

    @Autowired
    public MockMvc mockMvc;

    @Test
    //tests if /orders is up and alive
    public void HeartbeatTest() throws Exception {
        assertThat(this.mockMvc.perform(get("/orders/heartbeat"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("true")));
    }

    @Test
    //only tests for a good return code, not the actual content returned
    public void ReadAllTest() throws Exception {
        assertThat(this.mockMvc.perform(get("/orders"))
                .andDo(print()).andExpect(status().isOk()));
    }
    @Test
    public void CreateOrderTest() throws Exception {
        //test the create
        this.mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"driverId\":3," +
                                "\"customerId\":5," +
                                "\"restaurantId\":2," +
                                "\"discountId\":1," +
                                "\"status\":\"pending\"," +
                                "\"price\": 55.25" +
                                "}")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"customerId\":5," +
                        "\"customer\":null," +
                        "\"restaurantId\":2," +
                        "\"restaurant\":null," +
                        "\"driverId\":3," +
                        "\"driver\":null," +
                        "\"discountId\":null," +
                        "\"discount\":null," +
                        "\"status\":\"pending\"," +
                        "\"deliveryTime\":null," +
                        "\"totalPrice\":55.25," +
                        "\"driverRating\":null}")));

        //clean up the item created for the test
        //find the newest item
        String newestId = this.mockMvc.perform(get("/orders/newest"))
                .andReturn().getResponse().getContentAsString();
        //actually delete the item
        this.mockMvc.perform(delete("/orders/" + newestId));
    }

    @Test
    public void ReadOrderTest() throws Exception {
        //create item to read
        this.mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(new String("{" +
                        "\"driverId\":3," +
                        "\"customerId\":5," +
                        "\"restaurantId\":2," +
                        "\"discountId\":1," +
                        "\"status\":\"pending\"," +
                        "\"price\": 55.25" +
                        "}")))
                .andExpect(status().is2xxSuccessful());

        //find the newest item
        String newestId = this.mockMvc.perform(get("/orders/newest"))
                .andReturn().getResponse().getContentAsString();

        //actually test the read
        this.mockMvc.perform(get("/orders/" + newestId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                "\"customerId\":5," +
                        "\"customer\":5," +
                        "\"restaurantId\":2," +
                        "\"restaurant\":2," +
                        "\"driverId\":3," +
                        "\"driver\":3," +
                        "\"discountId\":null," +
                        "\"discount\":null," +
                        "\"status\":\"pending\"," +
                        "\"deliveryTime\":null," +
                        "\"totalPrice\":55.25," +
                        "\"driverRating\":null}")));

        //clean up the item created for the test
        this.mockMvc.perform(delete("/orders/" + newestId))
                .andExpect(status().is2xxSuccessful());;
    }

    @Test
    public void UpdateOrderItemFullDtoTest() throws Exception {
        //create item to update
        this.mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"driverId\":3," +
                                "\"customerId\":5," +
                                "\"restaurantId\":2," +
                                "\"discountId\":1," +
                                "\"status\":\"pending\"," +
                                "\"price\": 55.25" +
                                "}")))
                .andExpect(status().is2xxSuccessful());

        //find the newest item
        String newestId = this.mockMvc.perform(get("/orders/newest"))
                .andReturn().getResponse().getContentAsString();

        //actually test the update
        this.mockMvc.perform(put("/orders/" + newestId)
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"driverId\":2," +
                                "\"discountId\":3," +
                                "\"status\":\"waiting\"," +
                                "\"deliveryTime\":\"01:01:01\"," +
                                "\"driverRating\":4" +
                                "}")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"customerId\":5," +
                                "\"customer\":5," +
                                "\"restaurantId\":2," +
                                "\"restaurant\":2," +
                                "\"driverId\":2," +
                                "\"driver\":3," +
                                "\"discountId\":3," +
                                "\"discount\":null," +
                                "\"status\":\"waiting\"," +
                                "\"deliveryTime\":\"01:01:01\"," +
                                "\"totalPrice\":55.25," +
                                "\"driverRating\":4}")));

        //clean up the item created for the test
        this.mockMvc.perform(delete("/orders/" + newestId))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void UpdateOrderItemPartialDtoTest() throws Exception {
        //create item to update
        this.mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"driverId\":3," +
                                "\"customerId\":5," +
                                "\"restaurantId\":2," +
                                "\"discountId\":1," +
                                "\"status\":\"pending\"," +
                                "\"price\": 55.25" +
                                "}")))
                .andExpect(status().is2xxSuccessful());

        //find the newest item
        String newestId = this.mockMvc.perform(get("/orders/newest"))
                .andReturn().getResponse().getContentAsString();

        //actually test the update
        this.mockMvc.perform(put("/orders/" + newestId)
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"discountId\":1," +
                                "\"status\":\"delivered\"," +
                                "\"deliveryTime\":\"01:01:05\"" +
                                "}")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"customerId\":5," +
                                "\"customer\":5," +
                                "\"restaurantId\":2," +
                                "\"restaurant\":2," +
                                "\"driverId\":3," +
                                "\"driver\":3," +
                                "\"discountId\":1," +
                                "\"discount\":null," +
                                "\"status\":\"delivered\"," +
                                "\"deliveryTime\":\"01:01:05\"," +
                                "\"totalPrice\":55.25," +
                                "\"driverRating\":null}")));

        //clean up the item created for the test
        this.mockMvc.perform(delete("/orders/" + newestId))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void DeleteOrderItemTest() throws Exception {
        //create item to delete
        this.mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"driverId\":3," +
                                "\"customerId\":5," +
                                "\"restaurantId\":2," +
                                "\"discountId\":1," +
                                "\"status\":\"pending\"," +
                                "\"price\": 55.25" +
                                "}")))
                .andExpect(status().is2xxSuccessful());

        //find the newest item
        String newestId = this.mockMvc.perform(get("/orders/newest"))
                .andReturn().getResponse().getContentAsString();

        //actually test the delete
        this.mockMvc.perform(delete("/orders/" + newestId))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(""));
    }
}
//"{"id":1,"orderId":1,"order":1,"foodId":1,"price":5.75,"quantity":5,"additionalInstructions":"Extra Dirt","foodRating":5}"


//    @Test
//    public void ReadOrderItemTest() throws Exception {
//        int testInt = 0;
//        String testString = "";
//        StringWriter stringWriter = new StringWriter();
//        //this.mockMvc.perform(get("/orderitems/newest")).andDo(print(stringWriter));
//        testString = this.mockMvc.perform(get("/orderitems/newest")).andReturn().getResponse().getContentAsString();
//        //testInt = Integer.parseInt(stringWriter.toString());
//        //testString = "/orderitems/" + stringWriter.toString();
//        testString = "/orderitems/" + testString;
//        //this.mockMvc.perform(get("/orderitems/" + stringWriter.toString()))
//        this.mockMvc.perform(get(testString))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString(
//                        "\"orderId\":2," +
//                                "\"order\":2," +
//                                "\"foodId\":1," +
//                                "\"price\":13.99," +
//                                "\"quantity\":2," +
//                                "\"additionalInstructions\":\"with fried leg\"," +
//                                "\"foodRating\":null}")));
//    }
