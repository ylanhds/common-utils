package utils;

import java.util.Arrays;

/**
 * 数组工具测试
 */
public class ArraysTest {

    public static void main(String[] args) {
        int [] num ={1,2,3,4,5,6,7,8,9,55,75,344,6653,34256};
        //二分查找(前提是有序的) 查找 53
        int i = Arrays.binarySearch(num, 55);
        System.out.println("找到的下标"+i);
        int a = num[i];
        System.out.println(a);


         int [] num2 ={45,62,64,65,754,2345,687,73,53,66,2345,6,567};
        //数组的排序
        Arrays.sort(num2);
        System.out.println(Arrays.toString(num2));

        //数组复制
        int[] ints = Arrays.copyOf(num2, 7);
        System.out.println(ints.length);
        System.out.println(Arrays.toString(ints));
    }
}
