import java.util.*;
public class ProblemSet2 {
	public static void main(String []args)
	{
		ProblemSet2 obj = new ProblemSet2();
		//String input = "abcdefA";
		//System.out.println(obj.solution1d(input)); // try 1b also
		
		//int []array = {4,1,6,8,2,5};
		//System.out.println(obj.solution2(array,0,array.length-1,4)); // we want 3 rd smallest element
		//Solution 2 - nlog(n) sort!
		
		//int [][]input = {{1,2,3},{4,5,6},{7,8,9}};
		//obj.solution3(input,3,3);
		
		//int []a = {5,3,8,2,-11,-7};
		//obj.solution4(a);
		
		//int []sortedArray = {1,2,3,4,5,6,7,8,9};
		//int sum = 9;
		//obj.solution5(sortedArray,sum);
	}
	
	
	
	
	
	
	
	public boolean solution1a(String unique)
	{
		char []array = unique.toLowerCase().toCharArray(); // convert all characters to lowercase
		boolean []ascii = new boolean[256];
		
		for(char c : array)
		{
			if(ascii[c])
				return false;
			ascii[c] = true; // next time same character comes , it ll already be true and function will return false !
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean solution1b(String unique)
	{
		Hashtable<Character,Integer> table = new Hashtable<Character,Integer>();// maps character to integer
		char []array = unique.toLowerCase().toCharArray();
		for(char c : array)
		{
			if(table.containsKey(c))
				return false;
			table.put(c,0);
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	public boolean solution1c(String unique)
	{
		char []array = unique.toLowerCase().toCharArray();
		for(int i = 0;i < array.length;i++)
		{
				for(int j = i + 1;j < array.length;j++)
					if(array[i] == array[j])
						return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean solution1d(String unique)
	{
		char []array = unique.toLowerCase().toCharArray();
		sort(array);//O(nlog(n)) for quicksort
		
		for(int i = 0;i < array.length - 1;i++) // O(n)
			if(array[i] == array[i+1]) // check for consecutive characters
				return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void sort(char []a) //Use quickSort instead for better efficiency!
	{
		for(int i = 0;i < a.length;i++)
			for(int j = 0;j < a.length - i - 1;j++)
				if(a[j] > a[j+1])
				{
					char temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
	}
	
	
	
	
	
	
	
	
	
	
	
	public int solution2(int []array,int left,int right,int k)
	{
		if(left == right)
			return left;//base case for recursion
		
		int r = partition(array,left,right); // r has position of pivot
		
		int rank = r - left + 1;
		
		if(rank == k)
			return array[r];
		else if(k < rank)
			return solution2(array,left,r-1,k);
		else
			return solution2(array,r+1,right,k - rank);
	}
	public int partition(int []array,int left,int right)
	{
		int pivot = array[left]; // you can choose right most element or even a random element !
		//int pivot = array[(int)(Math.random() * (array.length-1))]; random partition
		int i = left;
		for(int j = i + 1;j <= right;j++)
			if(array[j] < pivot)
			{
				i++;
				swap(array,i,j);
			}
		swap(array,left,i);
		return i; // final position of pivot
	}
	public void swap(int []a,int x,int y)
	{
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void solution3(int [][]array,int rows,int cols) // for iterative check spiral.java
	{
		printTopRight(array,0,0,rows-1,cols-1);
	}
	public void printTopRight(int [][]array,int x1,int y1,int x2,int y2) // x1,y1 is index of top left element and x2 y2 - bottom right
	{
		for(int i = x1;i <= x2;i++)
			System.out.print(array[y1][i] + " ");
		for(int j = y1 + 1;j <= y2;j++)
			System.out.print(array[j][x2] + " ");
		if(x2 > x1)
			printBottomLeft(array,x1+1,y1,x2,y2-1);
	}
	public void printBottomLeft(int [][]array,int x1,int y1,int x2,int y2)
	{
		for(int i = y2;i >= y1;i--)
			System.out.print(array[x2][i] + " ");
		for(int j = x2-1;j >= x1;j--)
			System.out.print(array[j][y1] + " ");
		if(x2 > x1)
			printTopRight(array,x1,y1+1,x2-1,y2);
	}
	
	
	
	
	
	
	
	
	
	
	public void sort(int []array)
	{
		for(int i = 0;i < array.length;i++)
			for(int j = 0;j < array.length - i - 1;j++)
				if(array[j] > array[j+1])
					swap(array,j,j+1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void solution4(int []array)
	{
		sort(array); // use quickSOrt/mergresort for nlog(n) time
		int minSum = 1000; // some large value
		
		int i = 0 , j = 0;
		for(int left = 0,right = array.length-1;left<right;)
		{
			int sum = array[left] + array[right];
			if(sum == 0)
			{
				minSum = 0;
				i = left;
				j = right;
				break;
			}
			else if(sum < 0)
				left++;
			else
			{
				if(sum < minSum)
				{
					minSum = sum;
					i = left;
					j = right;
				}
				right--;
			}
		}
		System.out.println(array[i] + " + " + array[j]);
		System.out.println("MinSum : " + minSum);
	}
	
	
	
	
	
	
	
	
	
	
	public void solution5(int []array,int sum)
	{
		for(int left = 0,right = array.length - 1;left < right;)
		{
			int total = array[left] + array[right];
			if(total == sum)
			{
				System.out.print("(" + array[left] + "," + array[right] + ")");
				left++;
				right--;
			}
			if(total < sum)
				left++;
			if(total > sum)
				right--;
			
		}
			
	}
}
