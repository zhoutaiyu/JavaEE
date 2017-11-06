package com.rollBook.utils;

/** @author  周太宇 
	@date 2017年9月4日 下午8:50:55 
**/
/**
 * 冒泡法排序
 * @author 周太宇
 * @time 2017年9月4日
 */
public class BubbleSort {
	public static int[] bubbleSort(int[] numbers)
    {
        int temp = 0;
        int size = numbers.length;
        for(int i = 0 ; i < size-1; i ++)
        {
        for(int j = 0 ;j < size-1-i ; j++)
        {
            if(numbers[j] > numbers[j+1])  //交换两数位置
            {
            temp = numbers[j];
            numbers[j] = numbers[j+1];
            numbers[j+1] = temp;
            }
        }
        }
		return numbers;
    }
}
