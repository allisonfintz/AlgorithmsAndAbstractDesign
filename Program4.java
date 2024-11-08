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
        int[] minHeight = new int[n + 1];
        int[] split = new int[n + 1];

        minHeight[0] = 0;

        for (int i = 1; i <= n; i++) {
            minHeight[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                int totalWidth = 0;
                int maxHeight = 0;
                for (int k = j; k < i; k++) {
                    totalWidth += widths[k];
                    maxHeight = Math.max(maxHeight, heights[k]);
                }
                if (totalWidth <= w) {
                    int totalHeight = minHeight[j] + maxHeight;
                    if (totalHeight < minHeight[i]) {
                        minHeight[i] = totalHeight;
                        split[i] = j;
                    }
                }
            }
        }

        List<Integer> paintings = new ArrayList<>();
        int index = n;
        while (index > 0) {
            int start = split[index];
            paintings.add(0, index - start);
            index = start;
        }

        int numPlatforms = paintings.size();
        return new Result(numPlatforms, minHeight[n], paintings.stream().mapToInt(i -> i).toArray());
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
