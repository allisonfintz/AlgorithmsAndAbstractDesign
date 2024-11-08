import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Program4 {
    public record Result(int numPlatforms, int totalHeight, int[] numPaintings) {}

    /**
     * Solution to program 4
     *
     * @param n       number of paintings
     * @param w       width of the platform
     * @param heights array of heights of the paintings
     * @param widths  array of widths of the paintings
     * @return Result object containing the number of platforms, total height of the paintings,
     *         and the number of paintings on each platform
     */
    private static Result program4(int n, int w, int[] heights, int[] widths) {
        int[] minTotalHeight = new int[n + 1];
        int[] splitPoint = new int[n + 1];

        minTotalHeight[0] = 0;

        for (int i = 1; i <= n; i++) {
            minTotalHeight[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                int totalWidth = 0;
                int maxHeight = 0;
                for (int k = j; k < i; k++) {
                    totalWidth += widths[k];
                    maxHeight = Math.max(maxHeight, heights[k]);
                }
                if (totalWidth <= w) {
                    int totalHeight = minTotalHeight[j] + maxHeight;
                    if (totalHeight < minTotalHeight[i]) {
                        minTotalHeight[i] = totalHeight;
                        splitPoint[i] = j;
                    }
                }
            }
        }

        // Reconstruct the number of paintings on each platform
        List<Integer> numPaintingsList = new ArrayList<>();
        int index = n;
        while (index > 0) {
            int start = splitPoint[index];
            numPaintingsList.add(0, index - start);
            index = start;
        }

        int numPlatforms = numPaintingsList.size();
        return new Result(numPlatforms, minTotalHeight[n], numPaintingsList.stream().mapToInt(i -> i).toArray());
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
        Result result = program4(n, W, heights, widths);
        System.out.println(result.numPlatforms());
        System.out.println(result.totalHeight());
        for (int i = 0; i < result.numPaintings().length; i++) {
            System.out.println(result.numPaintings()[i]);
        }
    }
}
