package Programs;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Program2 {
    public record Result(int numPlatforms, int totalHeight, int[] numPaintings) {}

    /**
     * Solution to program 2
     *
     * @param n       number of paintings
     * @param W       width of the platform
     * @param heights array of heights of the paintings
     * @param widths  array of widths of the paintings
     * @return Result object containing the number of platforms, total height of the
     *         paintings, and the number of paintings on each platform
     */
    static Result program2(int n, int W, int[] heights, int[] widths) {
        int totalHeight = 0;
        int numPlatforms = 0;
        List<Integer> paintingsPerPlat = new ArrayList<>();

        // Find the index k where heights start increasing
        int k = n; // Default k is n, meaning all heights are non-increasing
        for (int i = 1; i < n; i++) {
            if (heights[i] > heights[i - 1]) {
                k = i;
                break;
            }
        }

        // Process descending part (from index 0 to k-1)
        int currWidth = 0;
        int startIndex = 0;
        int counter = 0;

        for (int i = 0; i < k; i++) {
            if (currWidth + widths[i] <= W) {
                // Add painting to current platform
                currWidth += widths[i];
                counter++;
            } else {
                // Finish current platform
                numPlatforms++;
                totalHeight += heights[startIndex]; // Tallest painting is the first one
                paintingsPerPlat.add(counter);

                // Start new platform
                currWidth = widths[i];
                startIndex = i;
                counter = 1;
            }
        }
        // Handle the last platform in descending part
        if (counter > 0) {
            numPlatforms++;
            totalHeight += heights[startIndex];
            paintingsPerPlat.add(counter);
        }

        // Process ascending part (from index k to n-1)
        currWidth = 0;
        counter = 0;
        int tallestHeight = 0;

        for (int i = k; i < n; i++) {
            if (currWidth + widths[i] <= W) {
                // Add painting to current platform
                currWidth += widths[i];
                counter++;
                tallestHeight = heights[i]; // Heights are increasing
            } else {
                // Finish current platform
                numPlatforms++;
                totalHeight += tallestHeight; // Tallest painting is the last one
                paintingsPerPlat.add(counter);

                // Start new platform
                currWidth = widths[i];
                tallestHeight = heights[i];
                counter = 1;
            }
        }
        // Handle the last platform in ascending part
        if (counter > 0) {
            numPlatforms++;
            totalHeight += tallestHeight;
            paintingsPerPlat.add(counter);
        }

        // Convert paintingsPerPlat to int array
        int[] numPaintings = new int[paintingsPerPlat.size()];
        for (int i = 0; i < numPaintings.length; i++) {
            numPaintings[i] = paintingsPerPlat.get(i);
        }

        return new Result(numPlatforms, totalHeight, numPaintings);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int[] heights = new int[n];
        int[] widths = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            widths[i] = sc.nextInt();
        }
        sc.close();
        Result result = program2(n, W, heights, widths);
        System.out.println(result.numPlatforms);
        System.out.println(result.totalHeight);
        for (int i = 0; i < result.numPaintings.length; i++) {
            System.out.println(result.numPaintings[i]);
        }
    }
}
