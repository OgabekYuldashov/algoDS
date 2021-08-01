package com.ogabek.algorithms.dynamic_programming;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        return coinChangeMemoized(coins, amount, new int[amount + 1]);
    }

    /**
     * time: O(S*n) where S = amount, n = coins.length
     * space: O(n)
     */
    public int coinChangeMemoized(int[] coins, int amount, int[] memo) {
        if(amount < 0) return -1;
        if(amount == 0) return 0;
        if(memo[amount] > 0) return memo[amount];

        int minCoins = Integer.MAX_VALUE;
        for(int coin : coins) {
            int res = coinChangeMemoized(coins, amount - coin, memo);
            if(res > -1) minCoins = Math.min(minCoins, res + 1);
        }

        minCoins = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        memo[amount] = minCoins;
        return minCoins;
    }

    /**
     * time: O(S*n)
     * space: O(S)
     */
    public int coinChangeTabulation(int[] coins, int amount) {
        int[] minCoins = new int[amount + 1];

        for(int i = 1; i <= amount; i++) {
            minCoins[i] = amount + 1;
        }

        for(int currAmt = 1; currAmt <= amount; currAmt++) {
            for(int coin : coins) {
                int remaining = currAmt - coin;
                if(remaining >= 0) minCoins[currAmt] = Math.min(minCoins[currAmt], minCoins[remaining] + 1);
            }
        }

        return (minCoins[amount] == amount + 1) ? -1 : minCoins[amount];
    }
}
