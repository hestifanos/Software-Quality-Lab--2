package com.ontariotechu.sofe3980U;

import java.util.Objects;

public class BinaryAPIResult {
    private String operand1;
    private String operator;
    private String operand2;
    private String result;
    public BinaryAPIResult(Binary operand1, String operator, Binary operand2, Binary result)
    {
        this.operand1=operand1.getValue();
        this.operator=new String(operator);
        this.operand2=operand2.getValue();
        this.result=result.getValue();
    }
    public String getOperand1(){
        return operand1;
    }
    public String getOperator(){
        return operator;
    }
    public String getOperand2(){
        return operand2;
    }
    public String getResult(){
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BinaryAPIResult other = (BinaryAPIResult) obj;
        return Objects.equals(this.operand1, other.operand1) &&
                Objects.equals(this.operator, other.operator) &&
                Objects.equals(this.operand2, other.operand2) &&
                Objects.equals(this.result, other.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operator, operand2, result);
    }


}