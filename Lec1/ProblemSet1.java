/*
 * Solutions to problem set 1
 */
public class ProblemSet1 {
	public static void main(String []args)
	{
		ProblemSet1 obj = new ProblemSet1();
		
		//int []array = {1,2,3,4,5,6};
		//int pivot = 2; // i want to rotate around index 2 !
		//obj.displayArray(array);
		//obj.solution1a(array,pivot);//try 1b
		//obj.displayArray(array);
	
		//String input = "aaabbccccddeef";
		//System.out.println(input);
		//obj.solution2(input);
		
		//int [][]array = {{1,2,3},{4,5,6},{7,8,9}};
		//obj.display2DArray(array,3,3);
		//obj.solution3(array,3,3,9);
		
		//int []a = {1,-2,3,-4,5,-9};
		//obj.solution4(a);
		
		//int n = 27;
		//obj.solution5(n);
	}
	public void display2DArray(int [][]array,int rows,int cols)
	{
		for(int i = 0;i < rows;i++)
		{
			for(int j = 0;j < cols;j++)
				System.out.print(array[i][j] + " ");
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public void displayArray(int []array)
	{
		for(int i : array)
			System.out.print(i + " ");
		System.out.print("\n");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void reverseArray(int []array,int left,int right)// reverses from left index to right index including both !
	{
		for(;left < right;left++,right--)
			swap(array,left,right);
	}
	public void swap(int []array,int x,int y)
	{
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	public void solution1b(int []array,int pivot) // In place without using extra data structure
	{
		reverseArray(array,0,pivot);
		reverseArray(array,pivot+1,array.length-1);
		reverseArray(array,0,array.length-1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void solution1a(int []array,int pivot)
	{
		int []result = new int[array.length];
		
		int resultIndex = 0;
		for(int i = pivot + 1;i < array.length;i++,resultIndex++)
			result[resultIndex] = array[i];
		
		for(int i = 0;i <= pivot;i++,resultIndex++)
			result[resultIndex] = array[i];
	
		displayArray(result);
		
	}
	
	
	
	
	
	
	
	
	
	public void solution2(String str)
	{
		char []input = str.toCharArray();
		for(int i = 0;i < input.length;)
		{
			char current = input[i];
			int count = 0;
			while(input[i] == current)
			{
				count++;
				i++;
				if(i == input.length)
					break;
			}
			System.out.print(current);
			System.out.print(count);
		}
	}
	
	
	
	
	
	
	
	
	
	public void solution3(int [][]array,int rows,int cols,int element) // array is sorted row and column wise
	{
		int row = rows-1,col = 0; // start at bottom left
		while(array[row][col] != element)
		{
			if(array[row][col] > element)
				row--; // move upwards
			
			if(row == -1 || col == cols)
			{
				System.out.println("element not in array");
				return;
			}
			
			if(array[row][col] < element)
				col++; // move towards right
			
			if(row == -1 || col == cols)
			{
				System.out.println("element not in array");
				return;
			}
		}
		System.out.println("Row :" + (row+1) + " Col :" + (col+1)); // +1 to change from index system to actual row
		
	}
	
	
	
	
	
	
	
	
	public void solution4(int []array)
	{
		int currentSum = 0,maxSum = 0;
		for(int i : array)
		{
			currentSum += i;
			if(currentSum > maxSum)
				maxSum = currentSum;
			if(currentSum < 0)
				currentSum = 0;
		}
		System.out.println("Max Sum :" + maxSum);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void solution5(int n)
	{
		int noOfTrailingZeros = 0;
		for(int i = 5;i <= n;i += 5)
		{
			noOfTrailingZeros += computeFactorsOfFive(i);
		}
		System.out.println("No. of trailing zeros :" + noOfTrailingZeros);
	}
	public int computeFactorsOfFive(int x)
	{
		int count = 0;
		while(x % 5 == 0)
		{
			count++;
			x /= 5;
		}
		return count; // count is no. of 5(s) in any no. eg. 25 = 5 * 5 , 125 = 5 * 5 * 5
	}
	
	
	
	
	
	
	
	
	
}
