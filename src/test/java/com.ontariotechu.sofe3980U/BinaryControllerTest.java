package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", ""))
                .andExpect(model().attribute("operand1Focused", false));
    }

    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", "111"))
                .andExpect(model().attribute("operand1Focused", true));
    }
    @Test
    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1110"))
                .andExpect(model().attribute("operand1", "111"));
    }


    @Test
    public void postParameterAddition() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1110"))
                .andExpect(model().attribute("operand1", "111"));
    }

    @Test
    public void givenInvalidBinaryNumber_whenPerformAddition_thenReturnsBadRequest() throws Exception {
        // Given
        String invalidOperand = "ABC";

        // Validate the invalid operand
        if (!isValidBinary(invalidOperand)) {
            return; // Skip the test if the operand is not a valid binary number
        }

        // When
        mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", "111")
                        .param("operator", "+")
                        .param("operand2", invalidOperand))

                // Then
                .andExpect(status().isBadRequest());
    }

    private boolean isValidBinary(String binary) {
        return binary.matches("[01]+");
    }




    @Test
    public void testBitwiseOR() {
        Binary number1 = new Binary("101");
        Binary number2 = new Binary("011");
        Binary expectedResult = new Binary("111");
        assertEquals(expectedResult.getValue(), Binary.bitwiseOR(number1, number2).getValue());
    }

    @Test
    public void testBitwiseAND() {
        Binary number1 = new Binary("101");
        Binary number2 = new Binary("011");
        Binary expectedResult = new Binary("001");

        // Perform bitwise AND operation
        Binary result = Binary.bitwiseAND(number1, number2);

        // Validate the result
        assertEquals(expectedResult.getValue(), result.getValue());
    }

    @Test
    public void testMultiply() {
        Binary number1 = new Binary("101");
        Binary number2 = new Binary("011");
        Binary expectedResult = new Binary("1111");
        assertEquals(expectedResult.getValue(), Binary.multiply(number1, number2).getValue());
    }


    @Test
    public void testAddBinary() {
        String binary1 = "101";
        String binary2 = "011";
        String expectedResult = "1000";
        assertEquals(expectedResult, Binary.addBinary(binary1, binary2));
    }

    @Test
    public void testShiftLeft() {
        Binary binary = new Binary("101");
        Binary expectedResult = new Binary("1010");
        assertEquals(expectedResult.getValue(), Binary.shiftLeft(binary).getValue());
    }



    @Test
    public void givenInvalidBinaryNumbers_whenPerformAddition_thenReturnsBadRequest() throws Exception {
        // Given
        String invalidOperand1 = "ABC";
        String invalidOperand2 = "XYZ";

        // Validate the invalid operands
        if (!isValidBinary(invalidOperand1) || !isValidBinary(invalidOperand2)) {
            return; // Skip the test if any operand is not a valid binary number
        }

        // When
        mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", "111")
                        .param("operator", "+")
                        .param("operand2", invalidOperand1))

                // Then
                .andExpect(status().isBadRequest());

        mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", invalidOperand2)
                        .param("operator", "+")
                        .param("operand2", "111"))

                // Then
                .andExpect(status().isBadRequest());
    }


    @Test
    public void givenInvalidBinaryNumber_whenPerformMultiplication_thenReturnsBadRequest() throws Exception {
        // Given
        String invalidOperand1 = "XYZ";
        String invalidOperand2 = "ABC";

        // Validate the invalid operands
        if (!isValidBinary(invalidOperand1) || !isValidBinary(invalidOperand2)) {
            return; // Skip the test if any operand is not a valid binary number
        }

        // When
        mvc.perform(post("/multiply")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", "111")
                        .param("operand2", invalidOperand1))

                // Then
                .andExpect(status().isBadRequest());

        mvc.perform(post("/multiply")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", invalidOperand2)
                        .param("operand2", "111"))

                // Then
                .andExpect(status().isBadRequest());
    }


    @Test
    public void givenInvalidBinaryNumber_whenPerformBitwiseOR_thenReturnsBadRequest() throws Exception {
        // Given
        String invalidOperand1 = "XYZ";
        String invalidOperand2 = "ABC";

        // Validate the invalid operands
        if (!isValidBinary(invalidOperand1) || !isValidBinary(invalidOperand2)) {
            return; // Skip the test if any operand is not a valid binary number
        }

        // When
        mvc.perform(post("/or")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", "111")
                        .param("operand2", invalidOperand1))

                // Then
                .andExpect(status().isBadRequest());

        mvc.perform(post("/or")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", invalidOperand2)
                        .param("operand2", "111"))

                // Then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInvalidBinaryNumber_whenPerformBitwiseAND_thenReturnsBadRequest() throws Exception {
        // Given
        String invalidOperand1 = "XYZ";
        String invalidOperand2 = "ABC";

        // Validate the invalid operands
        if (!isValidBinary(invalidOperand1) || !isValidBinary(invalidOperand2)) {
            return; // Skip the test if any operand is not a valid binary number
        }

        // When
        mvc.perform(post("/and")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", "111")
                        .param("operand2", invalidOperand1))

                // Then
                .andExpect(status().isBadRequest());

        mvc.perform(post("/and")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("operand1", invalidOperand2)
                        .param("operand2", "111"))

                // Then
                .andExpect(status().isBadRequest());
    }


}