package com.ubisam.exam1.api.addresses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.is4xx;
import static io.u2ware.common.docs.MockMvcRestDocs.isJson;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static io.u2ware.common.docs.MockMvcRestDocs.get;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.delete;

import java.util.Map;

// import com.ubisam.exam1.domain.Address;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private AddressDocs addressDocs;

        @Test
        public void contextLoads() throws Exception {

                // Search (임시)
                mockMvc.perform(get("/api/addresses"))
                                .andDo(print())
                                .andExpect(is2xx());

                mockMvc.perform(post("/api/addresses/search"))
                                .andDo(print())
                                .andExpect(is4xx());

                mockMvc.perform(get("/api/addresses/search"))
                                .andDo(print())
                                .andExpect(is4xx());

                // Create
                mockMvc.perform(post("/api/addresses").content(addressDocs::newEntity, "주소1"))
                                .andDo(print())
                                .andExpect(is2xx())
                                .andDo(result(addressDocs::context, "address1"));

                mockMvc.perform(post("/api/addresses").content(addressDocs::newEntity, "주소2"))
                                .andDo(print())
                                .andExpect(is2xx())
                                .andDo(result(addressDocs::context, "address2"));

                mockMvc.perform(post("/api/addresses").content(addressDocs::newEntity, "주소3"))
                                .andDo(print())
                                .andExpect(is2xx())
                                .andDo(result(addressDocs::context, "address3"));

                mockMvc.perform(post("/api/addresses").content(addressDocs::newEntity, "주소4"))
                                .andDo(print())
                                .andExpect(is2xx())
                                .andDo(result(addressDocs::context, "address4"));

                // Read
                String url = addressDocs.context("address1", "$._links.self.href");
                System.out.println("url : " + url);

                mockMvc.perform(get(url))
                                .andDo(print())
                                .andExpect(is2xx());

                mockMvc.perform(post(url))
                                .andDo(print())
                                .andExpect(is4xx());

                // Update
                Map<String, Object> entity = addressDocs.context("address1", "$");
                mockMvc.perform(put(url).content(addressDocs::updateEntity, entity, "주소11234"))
                                .andDo(print())
                                .andExpect(is2xx())
                                .andDo(print())
                                .andExpect(isJson("$.name", "주소11234"));

                // Update 후 단건 체크
                mockMvc.perform(get(url))
                                .andExpect(is2xx())
                                .andDo(print());

                // Delete
                mockMvc.perform(delete(url))
                                .andDo(print())
                                .andExpect(is2xx());

                // Delete 후 단건 체크
                mockMvc.perform(get(url))
                                .andDo(print())
                                .andExpect(is4xx());
        }
}
