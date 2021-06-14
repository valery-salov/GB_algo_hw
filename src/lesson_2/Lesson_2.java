/*
Домашнее задание к 2-ому уроку

Валерий 'Veter' Салов
valery.salov75@gmail.com

1. Создать массив большого размера (миллион элементов).
2. Написать методы удаления, добавления, поиска элемента массива.
3. Заполнить массив случайными числами.
4. Написать методы, реализующие рассмотренные виды сортировок, и проверить скорость выполнения каждой.
 */


package lesson_2;

public class Lesson_2 {
    public static int ARRAY_SIZE = 100_000;// 1_000_000 на моём слабом ноутбуке слишком долго было, уменьшил до 100к

    public static void main(String[] args) {
        //1. Создать массив большого размера (миллион элементов) - три одинаковых массива, чтоб сравнение было чеснее...
        int[] arr1 = new int[ARRAY_SIZE];
        int[] arr2 = new int[ARRAY_SIZE];
        int[] arr3 = new int[ARRAY_SIZE];
        //3. Заполнить массив случайными числами.
        for (int i = 0; i < arr1.length; i++) arr1[i] = (int) (Math.random() * ARRAY_SIZE);
        System.arraycopy(arr1, 0, arr2, 0, ARRAY_SIZE);
        System.arraycopy(arr1, 0, arr3, 0, ARRAY_SIZE);

        /*4. Написать методы, реализующие рассмотренные виды сортировок, и проверить скорость выполнения каждой.
        только время мерил не в нано-секундах, а в мили-секундах, на массиве в 100к - так получается наглядней
        на моём ноутбуке: 4ядра/8потоков 16RAM OpenJDK 11 такие результаты:
        Bubble: 16414
        Select: 5521
        Insert: 1036
        */
        long startTime;

        startTime = System.currentTimeMillis();;
        sortByBubble(arr1);
        System.out.println("Bubble: "+(System.currentTimeMillis()-startTime));

        startTime = System.currentTimeMillis();
        sortBySelect(arr2);
        System.out.println("Select: "+(System.currentTimeMillis()-startTime));

        startTime = System.currentTimeMillis();
        sortByInsert(arr3);
        System.out.println("Insert: "+(System.currentTimeMillis()-startTime));


    }
    //2. Написать методы удаления, добавления, поиска элемента массива.
    public static int indexOf(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) return i;
        }
        return -1;
    }

    public static void addToArray(int[] arr, int index, int value) {
        if (index >= 0 && index < ARRAY_SIZE) {
            arr[index] = value;
        } else System.out.println("Index incorrect!");
    }

    public static void removeFromArrayByIndex(int[] arr, int index) {
        if (index >= 0 && index < arr.length) {
            arr[index] = 0;
        } else System.out.println("Index incorrect!");
    }

    public static void removeFromArrayByValue(int[] arr, int value) {
        int index = indexOf(arr, value);
        if (index > -1) {
            removeFromArrayByIndex(arr, index);
        } else System.out.println("No value!");
    }

    public static void sortByBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
            }
        }

    }


    public static void sortBySelect(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[minIndex]) minIndex = j;
            }
            swap(arr,i,minIndex);
        }

    }

    public static void sortByInsert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int in = i;
            while (in > 0 && arr[in-1] >= temp){
                arr[in] = arr[in-1];
                in--;
            }
            arr[in] = temp;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
