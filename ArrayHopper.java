package DynamicProgramming;

/**
 * Created by jmmodi on 12/14/2015.
 */
public class ArrayHopper {

    public static void main(String[] args) {

        int array[] = {5,6,8,7,4,1,3,9,6,8,4,2,1,5};
    
        int minHopArray[] = new int[array.length];

        for (int i = 0; i < minHopArray.length; i++) {
            minHopArray[i] = Integer.MAX_VALUE;
        }

        if (array[array.length - 1] != 0) {
            minHopArray[array.length - 1] = 1;
        }
        calculateMinHop(array, minHopArray);

        System.out.println(printMinHopString(minHopArray, 0, array.length - 1)+"out");
    }

    private static void printArray(int[] minHopArray) {
        for (int aMinHopArray : minHopArray) {
            System.out.print(aMinHopArray + " ");
        }
    }

    private static void calculateMinHop(int[] array, int[] minHopArray) {

        for (int i = array.length - 2; i >= 0; i--) {
            int valueAtIndex = array[i];

            if (valueAtIndex == 0) {
                minHopArray[i] = Integer.MAX_VALUE;
            } else if (i + valueAtIndex >= array.length) {
                minHopArray[i] = 1;
            } else {
                int integerMax = minHopArray[i + valueAtIndex];
                if (integerMax == Integer.MAX_VALUE) {
                    minHopArray[i] = Integer.MAX_VALUE;
                } else {
                    minHopArray[i] = 1 + integerMax;
                }
            }
        }
    }

    private static String printMinHopString(int[] minHopArray,int start,int end) {
        StringBuilder stringBuilder = new StringBuilder();

        int min;

        while (start<=end) {
            min = findMinIndex(minHopArray, start, end);
            stringBuilder.append(String.valueOf(min));
            end = min-1;
        }

        return stringBuilder.reverse().toString();
    }

    private static int findMinIndex(int[] minHopArray, int start, int end) {
        int min = Integer.MAX_VALUE;
        int minIndex = Integer.MAX_VALUE;

        for (int i = start; i <= end; i++) {
            if (minHopArray[i] < min) {
                min = minHopArray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

}
