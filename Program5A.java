import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Program5A {
    public record Result(int numPlatforms, int totalHeight, int[] numPaintings) {}

    /**
     * Solution to program 5A
     *
     * @param n       number of paintings
     * @param w       width of the platform
     * @param heights array of heights of the paintings
     * @param widths  array of widths of the paintings
     * @return Result object containing the number of platforms, total height of the paintings,
     *         and the number of paintings on each platform
     */
    private static Result program5A(int n, int w, int[] heights, int[] widths) {
        int[] memo = new int[n + 1];
        int[] split = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;

        computeMinTotalHeight(n, w, heights, widths, memo, split);

        // Reconstruct the number of paintings on each platform
        List<Integer> paintings = new ArrayList<>();
        int index = n;
        while (index > 0) {
            int start = split[index];
            paintings.add(0, index - start);
            index = start;
        }

        int numPlatforms = paintings.size();
        return new Result(numPlatforms, memo[n], paintings.stream().mapToInt(i -> i).toArray());
    }

    private static int computeMinTotalHeight(int i, int w, int[] heights, int[] widths, int[] memo, int[] split) {
        if (memo[i] != -1) {
            return memo[i];
        }
        int minTotalHeight = Integer.MAX_VALUE;
        int bestSplit = -1;
        int totalWidth = 0;
        int maxHeight = 0;

        for (int j = i - 1; j >= 0; j--) {
            totalWidth += widths[j];
            maxHeight = Math.max(maxHeight, heights[j]);
            if (totalWidth <= w) {
                int totalHeight = computeMinTotalHeight(j, w, heights, widths, memo, split) + maxHeight;
                if (totalHeight < minTotalHeight) {
                    minTotalHeight = totalHeight;
                    bestSplit = j;
                }
            } else {
                break;
            }
        }
        memo[i] = minTotalHeight;
        split[i] = bestSplit;
        return minTotalHeight;
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
        Result result = program5A(n, W, heights, widths);
        System.out.println(result.numPlatforms());
        System.out.println(result.totalHeight());
        for (int i = 0; i < result.numPaintings().length; i++) {
            System.out.println(result.numPaintings()[i]);
        }
    }
}
