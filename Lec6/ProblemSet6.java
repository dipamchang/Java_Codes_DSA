import java.util.*;
public class ProblemSet6 {
	public static void main(String []args)
	{
		ProblemSet6 obj = new ProblemSet6();
		NODE one = new NODE(1);//root
		NODE two = new NODE(2);
		NODE three = new NODE(3);
		NODE four = new NODE(4);
		NODE five = new NODE(5);
		NODE six = new NODE(6);
		NODE seven = new NODE(7);
		NODE eight = new NODE(8);
		
		one.left = two;
		one.right = three;
		two.left = four;
		two.right = five;
		three.left = six;
		three.right = seven;
		four.left = eight;
		
		//obj.reverseLevelOrder(one);
		//System.out.println("Height:" + obj.getHeight(one));
		//System.out.println("Height:" + obj.getHeight2(one));
		//obj.typesOfNodes(one);
		//obj.typesOfNodes2(one);
		//System.out.println(obj.isIdentical(one,one));
		//obj.maxLevelSum(one);
		
		//int []path = new int[obj.getHeight(one)];
		//int currentIndex = 0;
		//obj.printAllPaths(one,path,currentIndex);
		
		//System.out.println(obj.pathExists(one,15));//1->2->4->8
	}
	public void reverseLevelOrder(NODE root)
	{
		if(root == null)
			return;//validation
		Stack<NODE> stack = new Stack<NODE>();
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		while(!q.isEmpty())
		{
			NODE temp = q.removeFirst();
			stack.push(temp);
			if(temp.left != null)
				q.addLast(temp.left);
			if(temp.right != null)
				q.addLast(temp.right);
		}
		while(!stack.isEmpty())
			System.out.print(stack.pop().data + " ");
	}
	public int getHeight(NODE root)
	{
		if(root == null)
			return 0;//base case when we've hit bottom and height is zero
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		return ((leftHeight > rightHeight ? leftHeight : rightHeight) + 1);//add 1 to accnt for current node!
	}
	public int getHeight2(NODE root)
	{
		if(root == null)
			return 0;//validation
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		q.addLast(null);//to signify end of level
		int height = 0;
		while(!q.isEmpty())
		{
			root = q.removeFirst();
			if(root == null)//end of level
			{
				height++;
				if(!q.isEmpty())//if its empty , all elements have been scanned , there are no more levels!
					q.addLast(null);//add null to mark end of current level
			}
			else
			{
				if(root.left != null)
					q.addLast(root.left);
				if(root.right != null)
					q.addLast(root.right);
			}
		}
		return height;
	}
	public void deepestNode(NODE root)//deepest node is last node in level order traversal
	{
		if(root == null)
			return;//validation
		
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		while(!q.isEmpty())
		{
			root = q.removeFirst();
			if(root.left != null)
				q.addLast(root.left);
			if(root.right != null)
				q.addLast(root.right);
		}
		System.out.println("Deepest Node :" + root.data);
	}
	public void typesOfNodes(NODE root)//recursive
	{
		if(root == null)
			return;
		
		typesOfNodes(root.left);
		typesOfNodes(root.right);
		if(root.left == null && root.right == null)
			System.out.println("Leaf:" + root.data);
		else if(root.left == null || root.right == null)
			System.out.println("Half Node:" + root.data);
		else
			System.out.println("Full Node:" + root.data);
		
		//fyi - u can print anywhere in the function depending on which order u want !(post pre or in)
	}
	public void typesOfNodes2(NODE root)//non recursive
	{
		if(root == null)
			return;
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		int leaves = 0,full = 0,half = 0;
		while(!q.isEmpty())
		{
			root = q.removeFirst();
			if(root.left == null && root.right == null)
				leaves++;
			else if(root.left == null || root.right == null)
				half++;
			else
				full++;
			if(root.left != null)
				q.addLast(root.left);
			if(root.right != null)
				q.addLast(root.right);
		}
		System.out.print("Full Nodes:" + full + "\n" + "Half Nodes:" + half + "\n" + "Leaves:" + leaves + "\n");
	}
	public boolean isIdentical(NODE root1,NODE root2)
	{
		if(root1 == null && root2 == null)//both have hit bottom simultaneously
			return true;
		else if(root1 == null && root2 != null || root1 != null && root2 == null)//exactly one has hit bottom
			return false;
		
		return (root1.data == root2.data) && isIdentical(root1.left,root2.left) && isIdentical(root1.right,root2.right);
		
	}
	public void maxLevelSum(NODE root)
	{
		if(root == null)
			return;
		int maxSum = 0;
		int levelSum = 0;
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		q.addLast(null);
		int level = 0;
		while(!q.isEmpty())
		{
			root = q.removeFirst();
			
			if(root == null)//end of Level
			{
				level++;
				System.out.println("Level " +level+ " Sum :" + levelSum);
				if(levelSum > maxSum)//update max sum
					maxSum = levelSum;
				levelSum = 0;//reset levelSum
				if(!q.isEmpty())
					q.addLast(null);//mark end of current level
			}
			else
			{
				levelSum += root.data;
				if(root.left != null)
					q.addLast(root.left);
				if(root.right != null)
					q.addLast(root.right);
			}
		}
		System.out.println("Max Level Sum:" + maxSum);
	}
	
	
	
	public void printAllPaths(NODE root,int []path,int currentIndex)
	{
		
		if(root == null) 
			return;
	
		path[currentIndex++] = root.data; //append data to path array
		
		if(root.left == null && root.right == null)//leaf node reached!
			displayArray(path,currentIndex);
		else
		{
			if(root.left != null)
				printAllPaths(root.left,path,currentIndex);
			if(root.right != null)
				printAllPaths(root.right,path,currentIndex);
		}
		
	}
	public void displayArray(int []a,int length)
	{
		for(int i = 0;i < length;i++)
			System.out.print(a[i] + " ");
		System.out.print("\n");
	}
	
	public boolean pathExists(NODE root,int sum)
	{
		if(root == null) // we've hit bottom , now check if sum is zero
			return sum == 0;
		else
			return pathExists(root.left,sum - root.data)||pathExists(root.right,sum - root.data);
		
	}
	
}
