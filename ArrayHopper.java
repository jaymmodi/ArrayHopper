package ArrayHopper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jmmodi on 12/14/2015.
 */
public class ArrayHopper {

    public static void main(String[] args) {

        ArrayList<Integer> numbers = readFromConsole();

        if (numbers.size() > 0) {
            int[] minHopArray = new int[numbers.size()];

            initializeMinHopArray(numbers, minHopArray);

            calculateMinHop(numbers, minHopArray);

            printSolution(numbers, minHopArray);
        } else {
            System.out.println("failure");
        }
    }

    private static ArrayList<Integer> readFromConsole() {
        ArrayList<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            while ((line = br.readLine()) != null && line.length() != 0) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numbers;
    }

    private static void initializeMinHopArray(ArrayList<Integer> numbers, int[] minHopArray) {
        for (int i = 0; i < numbers.size(); i++) {
            minHopArray[i] = Integer.MAX_VALUE;
        }

        if (numbers.get(numbers.size() - 1) != 0) {
            minHopArray[numbers.size() - 1] = 1;
        }
    }

    private static void printSolution(ArrayList<Integer> numbers, int[] minHopArray) {
        if (minHopArray[0] == Integer.MAX_VALUE) {
            System.out.println("failure");
        } else {

            ArrayList<Integer> minIndicesList = findMinIndicesList(minHopArray, 0, numbers.size() - 1);

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

    private static void calculateMinHop(ArrayList<Integer> numbers, int[] minHopArray) {

        for (int i = numbers.size() - 2; i >= 0; i--) {
            int valueAtIndex = numbers.get(i);

            if (valueAtIndex == 0) {
                minHopArray[i] = Integer.MAX_VALUE;
            } else if (i + valueAtIndex >= numbers.size()) {
                minHopArray[i] = 1;
            } else if (valueAtIndex < 0) {
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
