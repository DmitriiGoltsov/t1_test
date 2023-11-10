package com.goltsov.test_t1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class TestT1ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String STRING_URL = "/strings";
    private static final String SIMPLE_STRING = "aabbbdcqDqqq";
    private static final String PATTERN_VIOLATION_STRING = "aghD=+*~woet2345";

    @Test
    public void testWithSimpleString() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(
                        post(STRING_URL)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .queryParam("customString", SIMPLE_STRING))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentType()).isEqualTo("text/html;charset=UTF-8");

        final String expectedContent =
                Files.readString(Paths.get("src/test/resources/actualIndex.html"), StandardCharsets.UTF_8);

        assertThat(expectedContent).isEqualTo(response.getContentAsString());
    }

    @Test
    public void testWithBlankString() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(
                        post(STRING_URL)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .queryParam("customString", ""))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentType()).isEqualTo("text/html;charset=UTF-8");

        assertThat(response.getContentAsString()).contains("Your string cannot be blank");
    }

    @Test
    public void testWithViolatingPatterString() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(
                        post(STRING_URL)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .queryParam("customString", PATTERN_VIOLATION_STRING))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentType()).isEqualTo("text/html;charset=UTF-8");

        assertThat(response.getContentAsString())
                .contains("Your string may contain only letters, digits, underscores, dashes and dots");
    }

    @Test
    public void testWithTooLongString() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(
                        post(STRING_URL)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .queryParam("customString", "a".repeat(257)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentType()).isEqualTo("text/html;charset=UTF-8");

        assertThat(response.getContentAsString())
                .contains("Your string has to have from 1 to 255 characters");
    }

}
