package com.Collection;
import java.util.*;

public class compare
{
	public static void main(String[] args)
	{
		int a[] =
		{ 1, 6, 2, 8, 5, 8, 6, 9, 0 };
		int b[] =
		{ 4, 5, 4, 8, 7, 6, 2, 0 };

		new compare().getResult1(a, b);

	}

	// 字符串查找,推给sun来做
	public void getResult1(int[] array1, int[] array2)
	{
		String ar1Str = "";
		String elem = "";
		for (int ind1 = 0; ind1 < array1.length; ind1++)
		{
			elem = new Integer(array1[ind1]).toString();
			// 不相同的值加入;
			if (ar1Str.indexOf(elem) == -1)
				ar1Str += elem + ",";

		}
		for (int ind2 = 0; ind2 < array2.length; ind2++)
		{
			elem = new Integer(array2[ind2]).toString();
			if (ar1Str.indexOf(elem) != -1)
			{
				System.out.println("相同元素" + elem);
			}
		}
	}

}