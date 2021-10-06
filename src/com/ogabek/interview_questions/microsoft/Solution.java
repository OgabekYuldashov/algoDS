package com.ogabek.interview_questions.microsoft;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public String calculate(String exp) {
        // TODO: change to a custom InvalidExpression exception later, throw RuntimeException for now
        if(!isValidExpression(exp)) throw new RuntimeException("Invalid Expression");

        ExpressionElement[] elements = bringToCommonDenominator(getExpressionElements(exp));

        ExpressionElement elm = new ExpressionElement(0,elements[0].denominator);
        for(ExpressionElement curElement: elements){
            elm.numerator += curElement.numerator;
        }



        return elm.toExpressionString();
    }

    private boolean isValidExpression(String expression) {
        return true;
    }

    /**
     * @param elements [{numerator: 8; denominator: 3}, {numerator: -2, denominator:1}]
     * @return [{numerator: 8; denominator: 3}, {numerator: -6, denominator:3}]
     */
    private ExpressionElement[] bringToCommonDenominator(ExpressionElement[] elements) {
        // TODO: bring to common denominator
        return null;
    }

    /**
     * @param expression "2/3*4 - 2"
     * @return the expression elements "2/3*4 - 2" => [{numerator: 8; denominator: 3}, {numerator: -2, denominator:1}]
     */
    private ExpressionElement[] getExpressionElements(String expression) {
        return null;
    }


    static class ExpressionElement{
        private int numerator;
        private int denominator;
        public ExpressionElement(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        /**
         * 7/3 =>
         * 7/3 = 2
         * 7%3 = 1
         * 2_1/3
         * @return 2_1/3
         */
        public String toExpressionString(){
            //TODO: do the actual implementation
            return null;
        }
        // getters and setters
    }

    public int maxNonOverlapping(int[] nums, int start, int target) {
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        int count = 0;
        set.add(0);
        for(int i = start; i < nums.length; i++) {
            sum += nums[i];
            if(set.contains(sum - target)) {
                count++;
                sum = 0;
                set = new HashSet<>();
                set.add(0);
            }else {
                set.add(sum);
            }
        }
        return count;
    }

}
