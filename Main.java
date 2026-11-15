import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ask the user to select a sorting routine
        System.out.println("Make your choice of a sorting routine first:");
        System.out.println("1. Insertion Sort");
        System.out.println("2. Shell Sort");
        System.out.println("3. MergeSort");
        System.out.println("4. QuickSort");
        System.out.print("Enter your choice (1-4): ");
        int choice = scanner.nextInt();

        // 2. Ask the user for the number of integers
        System.out.print("Enter the number of integers: ");
        int n = scanner.nextInt();

        // 3. Store integers into a primitive int array
        int[] arr = new int[n];
        System.out.print("Enter the " + n + " integers: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Handle edge case for empty array
        if (n <= 0) {
            System.out.println("The resulting array is: ");
            scanner.close();
            return;
        }

        // 4. Sort the array using the user-specified sorting routine
        switch (choice) {
            case 1:
                insertionSort(arr);
                break;
            case 2:
                shellSort(arr);
                break;
            case 3:
                mergeSort(arr);
                break;
            case 4:
                quickSort(arr);
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Insertion Sort.");
                insertionSort(arr);
                break;
        }

        // 5. Remove duplicates in place
        int uniqueSize = removeDuplicatesInPlace(arr);

        // 6. Print the resulting array with unique numbers
        System.out.print("The resulting array is:");
        for (int i = 0; i < uniqueSize; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();

        scanner.close();
    }

    // ==================== SORTING ROUTINES ====================

    // 1. Insertion Sort
    public static void insertionSort(int[] a) {
        for (int p = 1; p < a.length; p++) {
            int tmp = a[p];
            int j = p;
            for (; j > 0 && tmp < a[j - 1]; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    // 2. Shell Sort
    public static void shellSort(int[] a) {
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                int tmp = a[i];
                int j = i;
                for (; j >= gap && tmp < a[j - gap]; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    // 3. MergeSort
    public static void mergeSort(int[] a) {
        int[] tmpArray = new int[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    private static void mergeSort(int[] a, int[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    private static void merge(int[] a, int[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos] <= a[rightPos]) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = a[leftPos++];
        }

        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = a[rightPos++];
        }

        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    // 4. QuickSort
    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(a, low, high);
            quickSort(a, low, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
    }

    // ==================== IN-PLACE DUPLICATE REMOVAL ====================

    /**
     * Removes duplicates from a sorted array in place.
     * Returns the new effective size of the unique elements.
     */
    public static int removeDuplicatesInPlace(int[] a) {
        if (a.length == 0) return 0;

        int j = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[j]) {
                j++;
                a[j] = a[i];
            }
        }
        return j + 1;
    }
}
