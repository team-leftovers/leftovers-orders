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
public class OrderItemsCrud {

    @Autowired
    public MockMvc mockMvc;

    @Test
    //tests if /orderitems is up and alive
    public void HeartbeatTest() throws Exception {
        assertThat(this.mockMvc.perform(get("/orderitems/heartbeat"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("true")));
    }
    @Test
    //only tests for a good return code, not the actual content returned
    public void ReadAllTest() throws Exception {
        assertThat(this.mockMvc.perform(get("/orderitems"))
                .andDo(print()).andExpect(status().isOk()));
    }
    @Test
    public void CreateOrderItemTest() throws Exception {
        //test the create
        this.mockMvc.perform(post("/orderitems")
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"orderId\":1," +
                                "\"foodId\":1," +
                                "\"quantity\":2," +
                                "\"additionalInstructions\":\"with fried leg\"" +
                                "}")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"orderId\":1," +
                                "\"order\":null," +
                                "\"foodId\":1," +
                                "\"price\":null," +
                                "\"quantity\":2," +
                                "\"additionalInstructions\":\"with fried leg\"," +
                                "\"foodRating\":null")));

        //clean up the item created for the test
        //find the newest item
        String newestId = this.mockMvc.perform(get("/orderitems/newest"))
                .andReturn().getResponse().getContentAsString();
        //actually delete the item
        this.mockMvc.perform(delete("/orderitems/" + newestId));
    }

    @Test
    public void ReadOrderItemTest() throws Exception {
        //create item to read
        this.mockMvc.perform(post("/orderitems")
                .contentType("application/json")
                .content(new String("{" +
                        "\"orderId\":1," +
                        "\"foodId\":1," +
                        "\"quantity\":2," +
                        "\"additionalInstructions\":\"with fried leg\"" +
                        "}")));

        //find the newest item
        String newestId = this.mockMvc.perform(get("/orderitems/newest"))
                .andReturn().getResponse().getContentAsString();

        //actually test the read
        this.mockMvc.perform(get("/orderitems/" + newestId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"orderId\":1," +
                                "\"order\":1," +
                                "\"foodId\":1," +
                                "\"price\":13.99," +
                                "\"quantity\":2," +
                                "\"additionalInstructions\":\"with fried leg\"," +
                                "\"foodRating\":null}")));

        //clean up the item created for the test
        this.mockMvc.perform(delete("/orderitems/" + newestId));
    }

    @Test
    public void UpdateOrderItemFullDtoTest() throws Exception {
        //create item to Update
        this.mockMvc.perform(post("/orderitems")
                .contentType("application/json")
                .content(new String("{" +
                        "\"orderId\":1," +
                        "\"foodId\":1," +
                        "\"quantity\":2," +
                        "\"additionalInstructions\":\"with fried leg\"" +
                        "}")));

        //find the newest item
        String newestId = this.mockMvc.perform(get("/orderitems/newest"))
                .andReturn().getResponse().getContentAsString();

        //actually test the update
        this.mockMvc.perform(put("/orderitems/" + newestId)
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"foodId\":3," +
                                "\"quantity\":6," +
                                "\"additionalInstructions\":\"Extra Dirt\"," +
                                "\"foodRating\":5" +
                                "}")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"orderId\":1," +
                                "\"order\":1," +
                                "\"foodId\":3," +
                                "\"price\":13.99," +
                                "\"quantity\":6," +
                                "\"additionalInstructions\":\"Extra Dirt\"," +
                                "\"foodRating\":5")));

        //clean up the item created for the test
        this.mockMvc.perform(delete("/orderitems/" + newestId));
    }

    @Test
    public void UpdateOrderItemPartialDtoTest() throws Exception {
        //create item to Update
        this.mockMvc.perform(post("/orderitems")
                .contentType("application/json")
                .content(new String("{" +
                        "\"orderId\":1," +
                        "\"foodId\":1," +
                        "\"quantity\":2," +
                        "\"additionalInstructions\":\"with fried leg\"" +
                        "}")));

        //find the newest item
        String newestId = this.mockMvc.perform(get("/orderitems/newest"))
                .andReturn().getResponse().getContentAsString();

        //actually test the update
        this.mockMvc.perform(put("/orderitems/" + newestId)
                        .contentType("application/json")
                        .content(new String("{" +
                                "\"foodId\":3," +
                                "\"additionalInstructions\":\"Extra Dirt\"" +
                                "}")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "\"orderId\":1," +
                                "\"order\":1," +
                                "\"foodId\":3," +
                                "\"price\":13.99," +
                                "\"quantity\":2," +
                                "\"additionalInstructions\":\"Extra Dirt\"," +
                                "\"foodRating\":null")));

        //clean up the item created for the test
        this.mockMvc.perform(delete("/orderitems/" + newestId));
    }

    @Test
    public void DeleteOrderItemTest() throws Exception {
        //create item to Delete
        this.mockMvc.perform(post("/orderitems")
                .contentType("application/json")
                .content(new String("{" +
                        "\"orderId\":1," +
                        "\"foodId\":1," +
                        "\"quantity\":2," +
                        "\"additionalInstructions\":\"with fried leg\"" +
                        "}")));

        //find the newest item
        String newestId = this.mockMvc.perform(get("/orderitems/newest"))
                .andReturn().getResponse().getContentAsString();

        //actually test the delete
        this.mockMvc.perform(delete("/orderitems/" + newestId))
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
