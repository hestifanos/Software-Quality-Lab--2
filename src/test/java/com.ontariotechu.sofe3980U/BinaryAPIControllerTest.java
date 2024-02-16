package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Mock
    BinaryAPIController controller;

    @Autowired
    private MockMvc mvc;

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }


    @Test
    public void addStringTest() {
        String result = BinaryAPIController.addString("111", "1010");
        assertEquals("10001", result);
    }

    @Test
    public void addJSONTest() {
        BinaryAPIResult result = BinaryAPIController.addJSON("111", "1010");
        assertEquals("111", result.getOperand1());
        assertEquals("1010", result.getOperand2());
        assertEquals("10001", result.getResult());
        assertEquals("add", result.getOperator());
    }

    @Test
    public void addStringWithEmptyOperandsTest() {
        String result = BinaryAPIController.addString("", "");
        assertEquals("0", result);
    }




    @Test
    public void testOrString() {
        BinaryAPIController controller = new BinaryAPIController(); // Create an instance of the controller
        String operand1 = "101";
        String operand2 = "011";

        // Call the actual method
        String result = controller.orString(operand1, operand2);

        // Verify the result
        assertEquals("111", result);
    }


    @Test
    public void testOrJSON() {
        BinaryAPIController controller = new BinaryAPIController(); // Create an instance of the controller
        String operand1 = "101";
        String operand2 = "011";

        // Call the actual method and verify the result
        BinaryAPIResult result = controller.orJSON(operand1, operand2);

        // Create the expected result manually
        BinaryAPIResult expectedResult = new BinaryAPIResult(new Binary(operand1), "or", new Binary(operand2),  Binary.bitwiseOR(new Binary("101"),new Binary("011")));

        // Verify that the actual result matches the expected result
        assertEquals(expectedResult, result);
    }


    @Test
    public void testAndString() {
        BinaryAPIController controller = new BinaryAPIController(); // Create an instance of the controller
        String operand1 = "101";
        String operand2 = "011";

        // Call the actual method and verify the result
        String result = controller.andString(operand1, operand2);
        assertEquals("1", result);
    }

    @Test
    public void testAndJSON() {
        // Given
        String operand1 = "101";
        String operand2 = "011";
        BinaryAPIController controller = new BinaryAPIController();
        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);

        // When
        BinaryAPIResult result = controller.andJSON(operand1, operand2);

        // Then
        BinaryAPIResult expectedResult = new BinaryAPIResult(number1, "and", number2, Binary.bitwiseAND(number1, number2));
        assertEquals("Expected and actual BinaryAPIResult should match", expectedResult, result);
    }


    @Test
    public void testMultiplyString() {
        BinaryAPIController controller = new BinaryAPIController(); // Create an instance of the controller
        String operand1 = "101";
        String operand2 = "011";

        // Call the actual method and verify the result
        String result = controller.multiplyString(operand1, operand2);
        assertEquals("1111", result);
    }



    @Test
    public void testMultiplyJSON() {
        // Given
        String operand1 = "101";
        String operand2 = "011";
        BinaryAPIController controller = new BinaryAPIController();

        // When
        BinaryAPIResult result = controller.multiplyJSON(operand1, operand2);

        // Then
        BinaryAPIResult expectedResult = new BinaryAPIResult(new Binary(operand1), "multiply", new Binary(operand2),  Binary.multiply(new Binary("101"),new Binary("011")));
        //
        assertEquals(expectedResult, result);
    }



}