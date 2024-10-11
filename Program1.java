import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Program1 {
    public record Result(int numPlatforms, int totalHeight, int[] numPaintings) {}

    /**
     * Solution to program 1
     *
     * @param n       number of paintings
     * @param W       width of the platform
     * @param heights array of heights of the paintings
     * @param widths  array of widths of the paintings
     * @return Result object containing the number of platforms, total height of the
     *         paintings, and the number of paintings on each platform
     */
    private static Result program1(int n, int W, int[] heights, int[] widths) {
        int totalHeight = 0;
        int numPlatforms = 0;
        List<Integer> numPaintingsPerPlatform = new ArrayList<>();
        int currentPlatformWidth = 0;
        int platformStartIndex = 0;
        int countPaintingsInCurrentPlatform = 0;

        for (int i = 0; i < n; i++) {
            if (currentPlatformWidth + widths[i] <= W) {
                // Add painting to current platform
                currentPlatformWidth += widths[i];
                countPaintingsInCurrentPlatform++;
            } else {
                // Finish current platform
                numPlatforms++;
                totalHeight += heights[platformStartIndex]; // Tallest painting is the first one
                numPaintingsPerPlatform.add(countPaintingsInCurrentPlatform);

                // Start new platform
                currentPlatformWidth = widths[i];
                platformStartIndex = i;
                countPaintingsInCurrentPlatform = 1;
            }
        }
        // Handle the last platform
        numPlatforms++;
        totalHeight += heights[platformStartIndex];
        numPaintingsPerPlatform.add(countPaintingsInCurrentPlatform);

        // Convert numPaintingsPerPlatform to int array
        int[] numPaintings = new int[numPaintingsPerPlatform.size()];
        for (int i = 0; i < numPaintings.length; i++) {
            numPaintings[i] = numPaintingsPerPlatform.get(i);
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
        Result result = program1(n, W, heights, widths);
        System.out.println(result.numPlatforms);
        System.out.println(result.totalHeight);
        for (int i = 0; i < result.numPaintings.length; i++) {
            System.out.println(result.numPaintings[i]);
        }
    }
}
