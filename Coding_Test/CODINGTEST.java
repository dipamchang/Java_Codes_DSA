
/*
 * Solutions to Coding Test
 */
public class CODINGTEST {
	public static void main(String []args)
	{
		CODINGTEST obj = new CODINGTEST();
		int [][]input = {{1,2,0},{4,5,6},{7,8,9}};
		
		
		//obj.display2DArray(input,3,3);
		//obj.solution1c(input,3,3); // try 1b and 1c also
		//obj.display2DArray(input,3,3);
		
		
		//int []input2 = {1,1,2,2,3,3,3,4,5,5};
		//obj.displayArray(input2);
		//obj.solution2(input2);
		
		//String a = "dragon", b = "gondra";
		//System.out.println(obj.isRotation(a,b));
		
		//String c = "mary",d = "army";
		//System.out.println(obj.isAnagram(c,d));
	}
	

	
	
	
	public boolean isAnagram(String a,String b)// Linear time , extra space for boolean arrays , better than any sorting algorithm !
	{
		if(a.length() != b.length())
			return false;
		
		boolean []ascii1 = new boolean[256];
		boolean []ascii2 = new boolean[256];
		
		char []a1 = a.toCharArray();
		char []b1 = b.toCharArray();
		
		for(char i : a1)
			ascii1[i] = true;
		for(char i : b1)
			ascii2[i] = true;
		
		for(int i = 0;i < 256;i++)
			if(ascii1[i] != ascii2[i])
				return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	public boolean isRotation(String a,String b)//O(n) time to check for substring , constant space
	{
		if(a.length() != b.length())
			return false;
		
		String combination = a.concat(a);
		if(combination.contains(b)) 
			return true;
		else
			return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void solution2(int []array) // remove consecutive duplicates O(n) time , constant space
	{
		int i;
		for(i = 0;i < array.length - 1;i++)
		{
			if(array[i] != array[i+1])
				System.out.print(array[i] + " ");
		}
		System.out.println(array[i]); // Printing the last element
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void displayArray(int []a) // fyi :- System.out.print works on arrays!
	{
		for(int i : a)
			System.out.print(i + " ");
		System.out.print("\n");
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void solution1a(int [][]array,int rows,int cols) // two traversals and -1 could be an element! 
	{
		
		
		for(int i = 0;i < rows;i++)
		{
			for(int j = 0;j < cols;j++)
			{
				if(array[i][j] == 0)// checking if element is zero
				{
					for(int k = 0;k < cols;k++)
						array[i][k] = -1; // set all elements in the row to be -1
					// why -1 and not zero ??
					for(int k = 0;k < rows;k++)
						array[k][j] = -1; // set all elements in column to be -1
				}
			}
		}
		
		for(int i = 0;i < rows;i++)
			for(int j = 0;j < cols;j++)
				if(array[i][j] == -1) // set all elements == -1 as zero
					array[i][j] = 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void solution1b(int [][]array,int rows,int cols)//one traversal + required rows and cols
	{
		boolean []rowFlag = new boolean[rows]; // by default all values are false
		boolean []colFlag = new boolean[cols];
		
		for(int i = 0;i < rows;i++)
			for(int j = 0;j < cols;j++)
				if(array[i][j] == 0)
				{
					rowFlag[i] = true;// row 'i' has a zero ! So mark it by setting it to be true!
					colFlag[j] = true;
				}
		
		for(int i = 0;i < rows;i++)
			if(rowFlag[i])
				for(int j = 0;j < cols;j++)
					array[i][j] = 0;
		
		for(int i = 0;i < cols;i++)
			if(colFlag[i])
				for(int j = 0;j < rows;j++)
					array[j][i] = 0;
		
	}
	
	
	
}
