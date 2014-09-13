package org.springframework.spr12188.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SprConfiguration.class)
@WebAppConfiguration("classpath:")
public class SprControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void I_can_do_a_successful_request() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void I_attempt_a_successful_request_with_an_invalid_accept_type_and_get_a_not_acceptable_response()
            throws Exception {

        mockMvc.perform(get("/").accept("un/supported"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void I_can_do_a_failed_request() throws Exception {

        mockMvc.perform(get("/fail"))
                .andExpect(status().isGone())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    public void I_attempt_a_failed_request_with_an_invalid_accept_type_and_get_a_not_acceptable_response()
            throws Exception {

        // Unfortunately this test will fail because the Spr12188Exception is wrapped and rethrown.
        mockMvc.perform(get("/fail").accept("un/supported"))
                .andExpect(status().isNotAcceptable());
    }
}
