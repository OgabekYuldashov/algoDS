package com.ogabek.algorithms.arrays;

public class BestTimeToBuyAndSellStock {

    /**
     * time: O(n)
     * space: O(1)
     */
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;

        for(int i = 1; i < prices.length; i++) {
            int curr = prices[i];
            if(curr - min > maxProfit) {
                maxProfit = curr - min;
            }
            if(curr < min) min = curr;
        }

        return maxProfit;
    }
}
