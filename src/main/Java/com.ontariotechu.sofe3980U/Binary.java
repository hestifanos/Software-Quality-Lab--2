package com.ontariotechu.sofe3980U;

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

        if(this.number=="") { // replace empty strings with a single zero
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
        int maxLength = Math.max(number1.number.length(), number2.number.length());
        StringBuilder result = new StringBuilder(maxLength);

        for (int i = 0; i < maxLength; i++) {
            char bit1 = (i < number1.number.length()) ? number1.number.charAt(i) : '0';
            char bit2 = (i < number2.number.length()) ? number2.number.charAt(i) : '0';
            result.append((bit1 == '1' && bit2 == '1') ? '1' : '0');
        }

        // Trim leading zeros if present
        int length = result.length();
        while (length > 1 && result.charAt(length - 1) == '0') {
            result.deleteCharAt(length - 1);
            length--;
        }

        return new Binary(result.toString());
    }



    // Method to perform bitwise OR operation
    public static Binary bitwiseOR(Binary number1, Binary number2) {
        StringBuilder result = new StringBuilder();
        int minLength = Math.min(number1.number.length(), number2.number.length()); // Find the minimum length

        // Perform bitwise OR operation on each pair of bits up to the minimum length
        for (int i = 0; i < minLength; i++) {
            if (number1.number.charAt(i) == '1' || number2.number.charAt(i) == '1') {
                result.append('1');
            } else {
                result.append('0');
            }
        }

        // If one binary number is longer, append the remaining bits to the result
        if (number1.number.length() > minLength) {
            result.append(number1.number.substring(minLength));
        } else if (number2.number.length() > minLength) {
            result.append(number2.number.substring(minLength));
        }

        return new Binary(result.toString());
    }


    // Method to perform binary multiplication
    public static Binary multiply(Binary number1, Binary number2) {
        // Convert binary numbers to integers for multiplication
        int value1 = Integer.parseInt(number1.getValue(), 2);
        int value2 = Integer.parseInt(number2.getValue(), 2);

        // Perform integer multiplication
        int result = value1 * value2;

        // Convert the result back to binary string
        String binaryResult = Integer.toBinaryString(result);

        return new Binary(binaryResult);
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