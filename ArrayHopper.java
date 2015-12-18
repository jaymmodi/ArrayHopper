package ArrayHopper;

import java.util.ArrayList;

/**
 * Created by jmmodi on 12/14/2015.
 */
public class ArrayHopper {

    public static void main(String[] args) {

        int array[] = {8,0,0,0,0,0,0,0,0};

        int minHopArray[] = new int[array.length];

        for (int i = 0; i < minHopArray.length; i++) {
            minHopArray[i] = Integer.MAX_VALUE;
        }

        if (array[array.length - 1] != 0) {
            minHopArray[array.length - 1] = 1;
        }
        calculateMinHop(array, minHopArray);

        if (minHopArray[0] == Integer.MAX_VALUE) {
            System.out.println("failure");
        } else {

            ArrayList<Integer> minIndicesList = findMinIndicesList(minHopArray, 0, array.length - 1);

            for (int i = minIndicesList.size() - 1; i >= 0; i--) {
                System.out.print(minIndicesList.get(i) + " ");
            }
            System.out.print("out");

        }
    }

    private static void printArray(int[] minHopArray) {
        for (int aMinHopArray : minHopArray) {
            System.out.print(aMinHopArray + " ");
        }
        System.out.println("");
    }

    private static void calculateMinHop(int[] array, int[] minHopArray) {

        for (int i = array.length - 2; i >= 0; i--) {
            int valueAtIndex = array[i];

            if (valueAtIndex == 0) {
                minHopArray[i] = Integer.MAX_VALUE;
            } else if (i + valueAtIndex >= array.length) {
                minHopArray[i] = 1;
            }else if(valueAtIndex < 0) {
                minHopArray[i] = Integer.MAX_VALUE;
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

    private static ArrayList<Integer> findMinIndicesList(int[] minHopArray, int start, int end) {
        ArrayList<Integer> reverseList = new ArrayList<>();

        int min;

        while (start <= end) {
            min = findMinIndex(minHopArray, start, end);
            reverseList.add(min);
            end = min - 1;
        }

        return reverseList;
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
