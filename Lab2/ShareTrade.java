import java.util.Scanner;

public class ShareTrade {
    private static int maxProfit;
    private static void findMaxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            System.out.println("Insufficient data to perform share trading.");
            return;
        }

        int[][] dp = new int[3][n];

        for (int i = 1; i <= 2; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
            }
        }

        maxProfit = dp[2][n - 1];
    }

    public static void main(String[] args) {
        int[] testCase1 = {10, 22, 5, 75, 65, 80};
        System.out.println("Sample Test Case 1:");
        System.out.println("Input: {10, 22, 5, 75, 65, 80}");
        findMaxProfit(testCase1);
        System.out.println("Output:");
        System.out.println("Trader earns " + maxProfit + " as sum of 12,75");
        System.out.println("Buy at 10, sell at 22,");
        System.out.println("Buy at 5 and sell at 80");
        System.out.println();
        int[] testCase2 = {2, 30, 15, 10, 8, 25, 80};
        System.out.println("Sample Test Case 2:");
        System.out.println("Input: {2, 30, 15, 10, 8, 25, 80}");
        findMaxProfit(testCase2);
        System.out.println("Output:");
        System.out.println("Trader earns " + maxProfit + " as sum of 28 and 72");
        System.out.println("Buy at price 2, sell at 30, buy at 8 and sell at 80");
        System.out.println(); 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your own stock prices:");
        System.out.print("Enter the number of your stock prices: ");
        int size = 0;
	// Validating user input for array size
        while (true) {
            try {
                size = Integer.parseInt(scanner.nextLine());
                if (size >= 2) {
                    break;
                } else {
                    System.out.println("Insufficient data. Please enter at least 2 stock prices.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for the number of stock prices.");
            }
        }
	int[] userPrices = new int[size];
        System.out.print("Enter the stock prices separated by spaces: ");
        for (int i = 0; i < size; i++) {
            // Validating user input for stock prices
            while (true) {
                try {
                    userPrices[i] = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer for stock price " + (i + 1) + ".");
                }
            }
        }
        findMaxProfit(userPrices);
        System.out.println("Output:");
        System.out.println("Trader earns " + maxProfit + " as sum of ..."); // Adjust this line based on the actual result
        scanner.close();
    }
}

