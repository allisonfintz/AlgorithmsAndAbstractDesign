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
        List<Integer> paintingsPerPlatform = new ArrayList<>();
        int currWidth = 0;
        int startIndex = 0;
        int counter = 0;

        for (int i = 0; i < n; i++) {
            if (currWidth + widths[i] <= W) {
                // Add painting to current platform
                currWidth += widths[i];
                counter++;
            } else {
                // Finish current platform
                numPlatforms++;
                totalHeight += heights[startIndex]; // Tallest painting is the first one
                paintingsPerPlatform.add(counter);

                // Start new platform
                currWidth = widths[i];
                startIndex = i;
                counter = 1;
            }
        }
        // Handle the last platform
        numPlatforms++;
        totalHeight += heights[startIndex];
        paintingsPerPlatform.add(counter);

        // Convert paintingsPerPlatform to int array
        int[] numPaintings = new int[paintingsPerPlatform.size()];
        for (int i = 0; i < numPaintings.length; i++) {
            numPaintings[i] = paintingsPerPlatform.get(i);
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
