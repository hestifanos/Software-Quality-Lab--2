package com.ontariotechu.sofe3980U;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
    private String number="0";  // string containing the binary value '0' or '1'

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0";
            return;
        }
        for (int i = 0; i < number.length(); i++) {
            // check each character if it's not 0 or 1
            char ch=number.charAt(i);
            if(ch!='0' && ch!='1') {
                number="0"; // if not store "0" and end the function
                return;
            }
        }
        // remove any trailing zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg)!='0')
                break;
        }
        //beg has the index of the first non zero digit in the number
        this.number=number.substring(beg); // exclude the trailing zeros if any
        // uncomment the following code

        if(this.number.isEmpty()) {
            this.number="0";
        }

    }
    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue()
    {
        return this.number;
    }
    /**
     * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
     *
     * @param num1 The first addend object
     * @param num2 The second addend object
     * @return A binary variable with a value of <i>num1+num2</i>.
     */
    public static Binary add(Binary num1,Binary num2)
    {
        // the index of the first digit of each number
        int ind1=num1.number.length()-1;
        int ind2=num2.number.length()-1;
        //initial variable
        int carry=0;
        String num3="";  // the binary value of the sum
        while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
        {
            int sum=carry; // previous carry
            if(ind1>=0){ // if num1 has a digit to add
                sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
                ind1--; // update ind1
            }
            if(ind2>=0){ // if num2 has a digit to add
                sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
                ind2--; //update ind2
            }
            carry=sum/2; // the new carry
            sum=sum%2;  // the resultant digit
            num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
        }
        Binary result=new Binary(num3);  // create a binary object with the calculated value.
        return result;

    }


    // Method to perform bitwise AND operation
    public static Binary bitwiseAND(Binary number1, Binary number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        BigInteger result = new BigInteger(number1.number, 2).and(new BigInteger(number2.number, 2));
        return new Binary(result.toString(2));
    }



    // Method to perform bitwise OR operation
    public static Binary bitwiseOR(Binary number1, Binary number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        BigInteger result = new BigInteger(number1.number, 2).or(new BigInteger(number2.number, 2));
        return new Binary(result.toString(2));
    }


    // Method to perform binary multiplication
    public static Binary multiply(Binary number1, Binary number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        BigInteger result = new BigInteger(number1.number, 2).multiply(new BigInteger(number2.number, 2));
        return new Binary(result.toString(2));
    }


    // Helper method to add two binary strings
    protected static String addBinary(String binary1, String binary2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = binary1.length() - 1;
        int j = binary2.length() - 1;
        // Iterate from right to left, adding corresponding bits and considering carry
        while (i >= 0 || j >= 0 || carry == 1) {
            int sum = carry;
            if (i >= 0) {
                sum += binary1.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += binary2.charAt(j--) - '0';
            }
            result.insert(0, sum % 2);
            carry = sum / 2;
        }
        return result.toString();
    }

    // Helper method to shift a binary string left by one position
    protected static Binary shiftLeft(Binary binary) {
        return new Binary(binary.number + "0");
    }



}