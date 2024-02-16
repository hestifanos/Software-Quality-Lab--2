package com.ontariotechu.sofe3980U;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
public class BinaryAPIController {

    @PostMapping("/")
    public ResponseEntity<String> performOperation(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operator") String operator,
            @RequestParam(name = "operand2") String operand2) {

        if (!isValidBinary(operand1) || !isValidBinary(operand2)) {
            return ResponseEntity.badRequest().body("Invalid binary number provided");
        }

        if (!isValidOperator(operator)) {
            return ResponseEntity.badRequest().body("Invalid operator provided");
        }

        // Perform the operation based on the operator

        return ResponseEntity.ok("Result of the operation");
    }

    private boolean isValidBinary(String binary) {
        return binary.matches("[01]+");
    }

    private boolean isValidOperator(String operator) {
        // Add logic to validate the operator
        return operator.equals("+") || operator.equals("*") || operator.equals("|") || operator.equals("&");
    }


    @GetMapping("/add")
    public static String addString(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                   @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2){



        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.add(number1, number2).getValue();
    }



    @GetMapping("/add_json")
    public static BinaryAPIResult addJSON(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                          @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {


        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        Binary sum = Binary.add(number1, number2);
        return new BinaryAPIResult(number1, "add", number2, sum);
    }



    @GetMapping("/or")
    public static String orString(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                  @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {


        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.bitwiseOR(number1, number2).getValue();
        // Example usage: http://localhost:8080/or?operand1=111&operand2=1010
    }

    @GetMapping("/or_json")
    public static BinaryAPIResult orJSON(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                         @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {



        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return new BinaryAPIResult(number1, "or", number2, Binary.bitwiseOR(number1, number2));
        // Example usage: http://localhost:8080/or_json?operand1=111&operand2=1010
    }

    @GetMapping("/and")
    public static String andString(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                   @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {


        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.bitwiseAND(number1, number2).getValue();
        // Example usage: http://localhost:8080/and?operand1=111&operand2=1010
    }

    @GetMapping("/and_json")
    public static BinaryAPIResult andJSON(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                          @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {


        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return new BinaryAPIResult(number1, "and", number2, Binary.bitwiseAND(number1, number2));
        // Example usage: http://localhost:8080/and_json?operand1=111&operand2=1010
    }

    @GetMapping("/multiply")
    public static String multiplyString(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                        @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {


        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.multiply(number1, number2).getValue();
        // Example usage: http://localhost:8080/multiply?operand1=111&operand2=1010
    }

    @GetMapping("/multiply_json")
    public static BinaryAPIResult multiplyJSON(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                               @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {


        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return new BinaryAPIResult(number1, "multiply", number2, Binary.multiply(number1, number2));
        // Example usage: http://localhost:8080/multiply_json?operand1=111&operand2=1010

    }

//


}





