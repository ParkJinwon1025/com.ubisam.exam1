package com.ubisam.exam1.api.adressGroups;

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

import java.util.Map;

import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.delete;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressGroupTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressGroupDocs docs;

    @Test
    public void contextLoads() throws Exception {

        // Search
        mockMvc.perform(get("/api/addressGroups")).andExpect(is4xx());
        mockMvc.perform(post("/api/addressGroups/search"))
                .andExpect(is2xx());
        mockMvc.perform(get("/api/addressGroups/search"))
                .andExpect(is4xx());

        // CRUD - C
        mockMvc.perform(post("/api/addressGroups")
                .content(docs::newEntity, "친구1"))
                .andDo(print())
                .andExpect(is2xx())
                .andDo(result(docs::context, "group1"));

        mockMvc.perform(post("/api/addressGroups")
                .content(docs::newEntity, "친구2"))
                .andDo(print())
                .andExpect(is2xx())
                .andDo(result(docs::context, "group2"));

        mockMvc.perform(post("/api/addressGroups")
                .content(docs::newEntity, "친구3"))
                .andDo(print())
                .andExpect(is2xx())
                .andDo(result(docs::context, "group3"));

        mockMvc.perform(post("/api/addressGroups")
                .content(docs::newEntity, "친구4"))
                .andDo(print())
                .andExpect(is2xx())
                .andDo(result(docs::context, "group4"));

        // CRUD -R

        String url = docs.context("group1", "$._links.self.href");
        System.out.println(url);

        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(is2xx());

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(is4xx());

        // Update
        Map<String, Object> entity = docs.context("group1", "$");
        mockMvc.perform(put(url).content(docs::updateEntity, entity, "친구1234"))
                .andExpect(is2xx())
                .andDo(print())
                .andExpect(isJson("$.name", "친구1234"));

        // Update 후 단건 조회
        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(is2xx());

        // Delete
        mockMvc.perform(delete(url))
                .andDo(print())
                .andExpect(is2xx());

        // Delete 후 단건 조회
        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(is4xx());

    }

}
