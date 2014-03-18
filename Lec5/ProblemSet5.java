import java.util.LinkedList;
import java.util.Stack;

public class ProblemSet5 {
	public static void main(String []args)
	{
		ProblemSet5 obj = new ProblemSet5();
		
		NODE one = new NODE(1);//root
		NODE two = new NODE(2);
		NODE three = new NODE(3);
		NODE four = new NODE(4);
		NODE five = new NODE(5);
		NODE six = new NODE(6);
		NODE seven = new NODE(7);
		
		one.left = two;
		one.right = three;
		two.left = four;
		two.right = five;
		three.left = six;
		three.right = seven;
		
		//obj.inOrder(one);
		//obj.inOrder2(one);
		//obj.preOrder(one);
		//obj.preOrder2(one);
		//obj.postOrder(one);
		//obj.levelOrder(one);
		//System.out.println("Max :" + obj.maxElement(one));
		//System.out.println("Max :" + obj.maxElement2(one));
		//System.out.println(obj.elementExists(one,5));
		//System.out.println(obj.elementExists2(one,7));
		
		//obj.insertIntoBinaryTree(one,8);
		//obj.inOrder(one);
		//System.out.print("\n");
		//obj.levelOrder(one);
		
		//System.out.println("No. Of Nodes:" + obj.sizeOfTree(one));
		//System.out.println("No. Of Nodes:" + obj.sizeOfTree2(one));
		
		//obj.deleteBinaryTree(one);
		//obj.levelOrder(one);
	}
	public void inOrder(NODE root)
	{
		if(root == null)
			return;//base case when we hit bottom
		inOrder(root.left); // left subtree first
		System.out.print(root.data + " ");//then current node
		inOrder2(root.right);//followed by right subtree
		
	}
	public void inOrder2(NODE root)
	{
		if(root == null)
			return; // validation
		Stack<NODE> stack = new Stack<NODE>(); // to store current node
		while(true)
		{
			while(root != null)
			{
				stack.push(root);
				root = root.left;
			}
			
			if(stack.isEmpty()) // terminal condition
				break;
			
			root = stack.pop();
			System.out.print(root.data + " ");
			root = root.right;
		}
	}
	public void preOrder(NODE root)
	{
		if(root == null)
			return;//base case when we hit bottom
		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	public void preOrder2(NODE root)
	{
		if(root == null)
			return;//validation
		Stack<NODE> stack = new Stack<NODE>(); // to store current node
		while(true)
		{
			while(root != null)
			{
				System.out.print(root.data + " ");
				stack.push(root);
				root = root.left;
			}
			if(stack.isEmpty())
				break;
			
			root = stack.pop();
			root = root.right;
		}
		
	}
	public void postOrder(NODE root)
	{
		if(root == null)
			return; // base case when we hit bottom
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}
	public void postOrder2(NODE root)
	{
		
	}
	public void levelOrder(NODE root)//similar to BFS
	{
		if(root == null)
			return;//validation
		LinkedList<NODE> q = new LinkedList<NODE>();//to be treated as a queue
		q.addLast(root);//enqueue
		while(!q.isEmpty())
		{
			NODE temp = q.removeFirst();//dequeue
			System.out.print(temp.data + " ");
			if(temp.left != null)
				q.addLast(temp.left);//enqueue left child
			if(temp.right != null)
				q.addLast(temp.right);//enqueue right child
		}
	}
	public int maxElement(NODE root)
	{
		if(root == null)
			return 0; // base case when we hit bottom
		int leftMax = maxElement(root.left);//max element in left subtree
		int rightMax = maxElement(root.right);//max element in right subtree
		return max(root.data,leftMax,rightMax);
	}
	
	public int max(int a,int b,int c) // returns max of three integers
	{
		if(a > b && a > c)
			return a;
		else if(b > a && b > c)
			return b;
		else
			return c;
	}
	
	
	public int maxElement2(NODE root)//Use level order traversal
	{
		if(root == null)
			return 0;//validation
		LinkedList<NODE> q = new LinkedList<NODE>();
		int max = 0;
		q.addLast(root);
		while(!q.isEmpty())
		{
			NODE temp = q.removeFirst();
			if(temp.data > max)
				max = temp.data;//set max
			if(temp.left != null)
				q.addLast(temp.left);//enqueue left child
			if(temp.right != null)
				q.addLast(temp.right);//enqueue right child
				
		}
		return max;
		
	}
	public boolean elementExists(NODE root,int n)
	{
		if(root == null)
			return false;//validation and base case when we hit bottom
		if(root.data == n || elementExists(root.left,n) ||elementExists(root.right,n))
			return true;
		else
			return false;
	}
	public boolean elementExists2(NODE root,int n)//using level order traversal
	{
		if(root == null)
			return false;
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		while(!q.isEmpty())
		{
			NODE temp = q.removeFirst();
			if(temp.data == n)
				return true;
			if(temp.left != null)
				q.addLast(temp.left);
			if(temp.right != null)
				q.addLast(temp.right);
		}
		return false;
	}
	
	public void insertIntoBinaryTree(NODE root,int n)//returns root of tree
	{
		if(root == null)
			return; 
		
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		NODE temp;
		while(!q.isEmpty())
		{
			temp = q.removeFirst();
			
			if(temp.left != null)
				q.addLast(temp.left);
			else
			{
				temp.left = new NODE(n);
				break;
			}
			if(temp.right != null)
				q.addLast(temp.right);
			else
			{
				temp.right = new NODE(n);
				break;
			}
		}
		
		
		
	}
	public int sizeOfTree(NODE root)
	{
		if(root == null)
			return 0;//base case when we hit bottom
		else
			return sizeOfTree(root.left) + 1 + sizeOfTree(root.right);
	}
	public int sizeOfTree2(NODE root)
	{
		if(root == null)
			return 0;//validation
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		int size = 0;
		while(!q.isEmpty())
		{
			NODE temp = q.removeFirst();
			size++;
			if(temp.left != null)
				q.addLast(temp.left);
			if(temp.right != null)
				q.addLast(temp.right);
		}
		return size;
	}
	public void deleteBinaryTree(NODE root)
	{
		if(root == null)
			return;//base case when we hit bottom
		deleteBinaryTree(root.left);
		deleteBinaryTree(root.right);
		root.data = -1;
		root.left = null;
		root.right = null;
		//in c use free()!
	}
	public void printTreeTopDown(NODE root)
	{
		
	}
	public void printTreeLeftRight(NODE root)
	{
		//rotate the 2- d array and print
	}
}
class NODE // Node of a binary tree
{
	int data;
	NODE left = null;
	NODE right = null;
	
	public NODE(int x)
	{
		data = x;
	}
}
