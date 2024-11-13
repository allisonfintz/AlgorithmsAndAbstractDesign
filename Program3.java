import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Program3 {
    public record Result(int numPlatforms, int totalHeight, int[] numPaintings) {}

    /**
     * Solution to program 3
     *
     * @param n       number of paintings
     * @param w       width of the platform
     * @param heights array of heights of the paintings
     * @param widths  array of widths of the paintings
     * @return Result object containing the number of platforms, total height of the paintings,
     *         and the number of paintings on each platform
     */
    private static Result program3(int n, int w, int[] heights, int[] widths) {
         int minHeight = Integer.MAX_VALUE;
        int[] bestNumPaintings = null; 
    
        // There are 2^(n-1) possible ways to partition the paintings
        int totalPartitions = (int) Math.pow(2, n - 1);
    
        for (int partition = 0; partition < totalPartitions; partition++) {
            int totalHeight = 0;
            int start = 0;
            List<Integer> numPaintingsList = new ArrayList<>();
    
            int totalWidth = 0;
            int maxHeight = 0;
            int currentPartition = partition;
    
            for (int i = 0; i < n; i++) {
                // Add the current painting to the current platform
                totalWidth += widths[i];
                maxHeight = Math.max(maxHeight, heights[i]);
    
                // If total width exceeds platform width, this partition is invalid
                if (totalWidth > w) {
                    totalHeight = Integer.MAX_VALUE;
                    break;
                }
    
                // If there is a break after the current painting or it's the last painting
                if ((currentPartition % 2 != 0) || i == n - 1) {
                    totalHeight += maxHeight;
                    numPaintingsList.add(i - start + 1);
                    // Reset for the next platform
                    start = i + 1;
                    totalWidth = 0;
                    maxHeight = 0;
                }
    
                // Update partition by dividing it by 2 to check the next bit in the next iteration
                currentPartition /= 2;
            }
    
            // If this partition has a lower total height, update the best partition
            if (totalHeight < minHeight) {
                minHeight = totalHeight;
                bestNumPaintings = new int[numPaintingsList.size()];
                for (int i = 0; i < numPaintingsList.size(); i++) {
                    bestNumPaintings[i] = numPaintingsList.get(i);
                }
            }
        }

    int numPlatforms;
    if (bestNumPaintings != null) {
        numPlatforms = bestNumPaintings.length;
    } else {
        numPlatforms = 0;
    }
    return new Result(numPlatforms, minHeight, bestNumPaintings);
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
        Result result = program3(n, W, heights, widths);
        System.out.println(result.numPlatforms());
        System.out.println(result.totalHeight());
        for (int i = 0; i < result.numPaintings().length; i++) {
            System.out.println(result.numPaintings()[i]);
        }
    }
}
