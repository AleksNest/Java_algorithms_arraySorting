
import java.util.Arrays;
import java.util.Random;

public class Program {


    public static void main(String[] args) {

        int[] arr = ArrayUtils.prepareArray();  // создание рандомного массива

        // данные для сортировки вставками
        System.out.printf("сортировка вставками \n");
        ArrayUtils.printArray(arr);             // печать  массива
        SortUtils.directSort(arr);              // отсортировать методом вставки
        ArrayUtils.printArray(arr);             // печать массива

        // данные для быстрой сортировки
        System.out.printf("быстрая сортировка \n");
        arr = ArrayUtils.prepareArray();
        ArrayUtils.printArray(arr);
        SortUtils.quickSort(arr);               // метод с перегрузкой с одним параметром
        ArrayUtils.printArray(arr);

        // данные для пирамидальной сортировки
        System.out.printf("пирамидальная сортировка \n");
        arr = ArrayUtils.prepareArray();
        ArrayUtils.printArray(arr);
        SortUtils.piramidSort(arr);
        ArrayUtils.printArray(arr);

        System.out.printf("\nрасчеты производительности выполнения сортировок \n");
        // скорость сортировки вставками
        int[] testArray = ArrayUtils.prepareArray(100000);
        long startTime = System.currentTimeMillis();
        SortUtils.directSort(testArray.clone());
        long endTime = System.currentTimeMillis();
        long processingTimeDirectSort = endTime - startTime;
        System.out.printf("Время работы сортировки выбором: %d мс.\n", processingTimeDirectSort);

        // скорость быстрой сортировки
        startTime = System.currentTimeMillis();
        SortUtils.quickSort(testArray.clone());
        endTime = System.currentTimeMillis();
        long processingTimeQuickSort = endTime - startTime;
        System.out.printf("Время работы быстрой сортировки: %d мс.\n", processingTimeQuickSort);

        // скорость пирамидальной сортировки
        startTime = System.currentTimeMillis();
        SortUtils.piramidSort(testArray.clone());
        endTime = System.currentTimeMillis();
        long processingTimePiramidSort = endTime - startTime;
        System.out.printf("Время работы пирамидальной  сортировки: %d мс.\n", processingTimePiramidSort);

        // выявление самой производительной сортировки
        System.out.printf("\nСАМАЯ БЫСТТАРЯ СОРИТРОВКА: \n");
        if (processingTimeQuickSort < processingTimeDirectSort) {
            if (processingTimePiramidSort < processingTimeQuickSort) {
                System.out.printf("пирамидальная сортировка: %d мс.\n", processingTimePiramidSort);
            } else {
                System.out.printf("быстрая сортировка: %d мс.\n", processingTimeQuickSort);
            }
        } else {
            System.out.printf("сортировка вставками: %d мс.\n", processingTimeDirectSort);
        }


        System.out.printf("\nпоиск элемента массива \n");
        // данные для поиска элемента в массиве
        testArray = new int[]{9, -10, 100, 22, -5, -5, 0, 9, 22, 13};
        ArrayUtils.printArray(testArray);
        SortUtils.quickSort(testArray);
        ArrayUtils.printArray(testArray);
        int res01 = SearchUtils.binarySearch(testArray, 13);
        System.out.printf("Элемент %d %s\n",
                13, res01 >= 0 ? String.format("найден в массиве по индексу %d", res01) :
                        "не найден в массиве");

        // стандартные методы java JDK  сортировки и поиска
        testArray = new int[]{9, -10, 100, 22, -5, -5, 0, 9, 22, 13};
        Arrays.sort(testArray); // стандартный метод сортировки массива
        ArrayUtils.printArray(testArray);
        int res02 = Arrays.binarySearch(testArray, 13); // стандартный метод поиска отсортированного массива
        System.out.printf("Элемент %d %s\n",
                13, res02 >= 0 ? String.format("найден в массиве по индексу %d", res02) :
                        "не найден в массиве");

    }

    static class ArrayUtils {                                   // CLASS печать и заполнение массива

        private static final Random random = new Random();

        static int[] prepareArray() {                           // заполнение рандомно массива с рандомной длиной
            int[] array = new int[random.nextInt(16) + 5];
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(100);
            }
            return array;
        }


        static int[] prepareArray(int length) {               // заполнение рандомно массива с фиксированной длиной
            int[] array = new int[length];
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(100);
            }
            return array;
        }

        static void printArray(int[] array) {               // вывод на экран массива
            for (int element : array) {
                System.out.printf("%d\t", element);
            }
            System.out.println();
        }

    }

    static class SortUtils {                                // CLASS  сортировка массивов

        static void directSort(int[] array) {                       // метод сортировки массива методом вставок
            for (int i = 0; i < array.length; i++) {
                int index = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[index]) {
                        index = j;
                    }
                }
                if (index != i) {
                    int buf = array[i];
                    array[i] = array[index];
                    array[index] = buf;
                    /* другой способ поменять местами значения без использования третьей переменной
                    array[i] = array[i] + array[index];
                    array[index] = array[i] - array[index];
                    array[i] = array[i] - array[index];
                    */
                }


            }
        }

        static void quickSort(int[] array) {                    //  метод перегруженный быстрая сортировка массива
            quickSort(array, 0, array.length - 1);
        }

        static void quickSort(int[] array, int startPosition, int endPosition) {  // метод быстрая сортировка
            int left = startPosition;
            int right = endPosition;
            int middle = array[(left + right) / 2];     // значение опорного элемента

            do {
                while (array[left] < middle) {
                    left++;
                }
                while (array[right] > middle) {
                    right--;
                }
                if (left <= right) {
                    if (left < right) {
                        int buf = array[left];
                        array[left] = array[right];
                        array[right] = buf;
                    }
                    left++;
                    right--;
                }
            }
            while (left <= right);
            if (left < endPosition) {
                quickSort(array, left, endPosition);
            }
            if (startPosition < right) {
                quickSort(array, startPosition, right);
            }
        }


        // метод просеивания  кучи для пирамидальной сортировки массива
        static void heapify (int[] array, int heapSize, int rootIndex){
            int largest = rootIndex; // индекс корня (родителя)
            int leftChild = 2 * rootIndex +1; // индекс левого дочернего элемента массива
            int rightChild = 2 * rootIndex +2; // индекс правого дочернего элемента массива

            if (leftChild < heapSize && array[leftChild] > array [largest])
                largest = leftChild;

            if (rightChild < heapSize && array[rightChild] > array [largest])
                largest = rightChild;

            if (largest != rootIndex) {
                int temp = array[rootIndex];
                array[rootIndex] = array[largest];
                array[largest] = temp;
                heapify(array, heapSize, largest);  // рекурсивно преобразуем в двоичную кучу
            }
        }
        // метод пирамидальной сортировки массива
        static void piramidSort (int []array){
            for (int i = array.length / 2 -1; i >= 0; i--)  // построение кучи (перегруппировка массива)
                heapify(array, array.length, i);            //самый тяжелый элемент массива окажется в индексе 0

            for (int i = array.length - 1 ; i >= 0; i--) {  // извлечение элементов из кучи
                int temp = array [0];                       // перемещение самого тяжелого элемента в конец массива
                array [0] = array [i];
                array [i] = temp;
                heapify(array, i, 0);   // вызов метода просеивания на уменьшенной куче
            }
        }
    }

    static class SearchUtils {                                      // CLASS бинарный поиск

        static int binarySearch(int[] array, int value) {
            return binarySearch(array, value, 0, array.length - 1);
        }

        static int binarySearch(int[] array, int value, int min, int max) { // метод поиска в сортированном массиве элемента
            if (max < min) return -1;
            int middle = (min + max) / 2;
            if (array[middle] == value) {
                return middle;
            } else if (array[middle] > value) {
                return binarySearch(array, value, min, middle - 1);
            } else {
                return binarySearch(array, value, middle + 1, max);
            }
        }
    }


}
