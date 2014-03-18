import java.util.LinkedList;
import java.util.Stack;




public class Trees {
	public static void main(String []args)
	{
		TreeNode one = new TreeNode(1); // root
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		TreeNode five = new TreeNode(5);
		TreeNode six = new TreeNode(6);
		TreeNode seven = new TreeNode(7);
		
		one.left = two;
		one.right = three;
		two.left = four;
		two.right = five;
		three.left = six;
		three.right = seven;
		new Trees().displayPostOrderNonRecursive(one);
		/*System.out.println("Recursive implementation PreORder :-");
		new Trees().displayPreOrderRecursive(one);
		System.out.print("\n");
		System.out.println("Non Recursive implementation PreOrder:-");
		new Trees().displayPreOrderNonRecursive(one);
		System.out.println("\nRecursive implementation InOrder :-");
		new Trees().displayInOrderRecursive(one);
		System.out.println("\nNon-Recursive implementation InOrder :-");
		new Trees().displayInOrderNonRecursive(one);
		System.out.println("\nRecursive implementation PostOrder :-");
		new Trees().displayPostOrderRecursive(one);
		//System.out.println("\nNon-Recursive implementation LevelOrder :-");*/
		//new Trees().displayInOrderRecursive(one);
		//new Trees().convertToMirror(one);
		System.out.print("\n");
		//new Trees().insert(one,8);
		//new Trees().displayInOrderRecursive(one);
		//new Trees().displayMax(one);
		//int []path = new int[10];
		//System.out.println(new Trees().LCA(one,four,five).data);
	}
	
	public void convertToMirror(TreeNode root)
	{
		if(root == null)
			return;//base case
	
		convertToMirror(root.left);
		convertToMirror(root.right);
		
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
	}
	
	public TreeNode LCA(TreeNode root,TreeNode alpha,TreeNode beta)
	{
		if(root == null)
			return root; // empty tree case
		if(root == alpha || root == beta)
			return root; // trivial case where one of the nodes is the root and is obviusly the LCA
		TreeNode left,right;
		left = LCA(root.left,alpha,beta);
		right = LCA(root.right,alpha,beta);
		if(left != null && right != null) // simultaneously both not equal to null
			return root;
		else
			return (left!= null?left:right);
		 
			
	}
	public void displayAllPaths(TreeNode root,int []path,int currentIndex)//path array to store the current path
	{
		if(root == null) 
			return;
	
		path[currentIndex++] = root.data; 
		
		if(root.left == null && root.right == null)
			printArray(path);
		else
		{
			displayAllPaths(root.left,path,currentIndex);
			displayAllPaths(root.right,path,currentIndex);
		}
		
	}
	public void printArray(int []a)
	{
		for(int i : a)
		{
			if(i != 0)
			System.out.print(i + " ");
		}
		System.out.print("\n");
	}
	public int deepestNode(TreeNode root) // last element in level order traversal is deepest node !
	{
		if(root == null)
			return 0;
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		q.addLast(root);
		TreeNode node = new TreeNode(0);
		while(!q.isEmpty())
		{
			node = q.pop();
			if(node.left != null)
				q.addLast(node.left);
			if(node.right != null)
				q.addLast(node.right);
		}
		return node.data;
	}
	public int heightIterative(TreeNode root)
	{
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		int level = 1;
		if(root == null) 
			return 0;
		q.addLast(root);
		q.addLast(null); // null marks end of each level !
		while(!q.isEmpty())
		{
			TreeNode temp = q.pop();

			if(temp == null) // current level is completed
			{
				if(!q.isEmpty())
					q.addLast(null);// add another marker for next level
				else
					break;
				
				level++; 
			}
			else // same level => enqueue children
			{
				if(temp.left != null)
					q.addLast(temp.left);
				if(temp.right != null)
					q.addLast(temp.right);
			}
		}
		return level;
	}
	public int height(TreeNode root)
	{
		if(root == null)
			return 0; // base case we hit bootom and return 0 height
		else
		{
			int leftHeight = height(root.left);
			int rightHeight = height(root.right);
			if(leftHeight > rightHeight)
				return leftHeight + 1;
			else
				return rightHeight + 1;
		}
	}
	public void insert(TreeNode root,int data)
	{
		TreeNode node = new TreeNode(data);
		LinkedList<TreeNode> q = new LinkedList<TreeNode>(); // BFS
		q.addLast(root);
		while(!q.isEmpty())
		{
			TreeNode temp = q.pop();
			if(temp.left == null)
			{
				temp.left = node;
				return;
			}
			else
				q.addLast(temp.left);
			if(temp.right == null)
			{
				temp.right = node;
				return;
			}
			else
				q.addLast(temp.right);
			
		}
	}
	public int max(TreeNode root)
	{
		if(root == null)
			return -1;
		int left = max(root.left);
		int right = max(root.right);
		return maximum(root.data,left,right);
	}
	public int maximum(int a,int b,int c)
	{
		if(a > b && a > c)
			return a;
		else if(b > a && b > c)
			return b;
		else
			return c;
	}
	public int sizeOfTree(TreeNode root) // can be done iteratively using level order
	{
		if(root == null)
			return 0;//base case when we hit bottom , then recursively add left and right subtrees
		else
			return sizeOfTree(root.left) + 1 + sizeOfTree(root.right);
	}
	public boolean searchElement(TreeNode root,int data)
	{
		if(root == null)
			return false; // base case , leaf reached data not found for this branch , return false !
		else
		{
			if(data == root.data)
				return true;
			else // if not current , recurse down left see if its true and if not recurse down right
			{
				if(searchElement(root.right,data)) // First see right/left , if right s true , return true else see left
					return true;
				else
					return searchElement(root.left,data);

			}
		}
	}
	
	public void displayPreOrderRecursive(TreeNode node)
	{
		if(node != null)
		{
			System.out.print(node.data + " ");
			displayPreOrderRecursive(node.left);
			displayPreOrderRecursive(node.right);
		}
	}
	public void displayPreOrderNonRecursive(TreeNode node)
	{
		Stack<TreeNode> temp = new Stack<TreeNode>();
		
		for(;true;node = node.right)
		{
			for(;node != null;node = node.left)
			{
				System.out.print(node.data + " ");
				temp.push(node);
			}
			if(temp.isEmpty())
				break;
			node = temp.pop();
		}
	}
	public void displayInOrderRecursive(TreeNode node)
	{
		if(node != null)
		{
			displayInOrderRecursive(node.left);
			System.out.print(node.data + " ");
			displayInOrderRecursive(node.right);
		}
	}
	public void displayInOrderNonRecursive(TreeNode node)
	{
		Stack<TreeNode> temp = new Stack<TreeNode>();
		
		for(;true;node = node.right)
		{
			for(;node != null;node = node.left)
			{
				
				temp.push(node);
			}
			if(temp.isEmpty())
				break;

			node = temp.pop();
			System.out.print(node.data + " ");
		}
	}
	public void displayPostOrderRecursive(TreeNode node)
	{
		if(node != null)
		{
			displayPostOrderRecursive(node.left);
			displayPostOrderRecursive(node.right);
			System.out.print(node.data + " ");
		}
	}
	public void displayPostOrderNonRecursive(TreeNode node)
	{
		TreeNode root = node;
		Stack<TreeNode> temp = new Stack<TreeNode>();
		while(true)
		{
			if(node != null)
			{
				temp.push(node);
				node = node.left;
			}
			else
			{
				if(temp.peek().right == null) // stack top is a leaf node
				{
					node = temp.pop();
					System.out.print(node.data + " ");
					if(node == temp.peek().right)// if the node is the right child of stack top => pop and print top
					{
						System.out.print(temp.peek().data +" ");
						node = temp.pop();	
					}
					if(temp.isEmpty())
						break;
					if(temp.peek() == root && root.right == node)//terminal condition , only root left and you just popped root's right child
					{
						System.out.println(temp.pop().data);
						break;
					}
				}
				if(!temp.isEmpty())
					node = temp.peek().right;//do the same for right child
				else
					node = null;
			}
		}
	}
	public void displayLevelOrder(TreeNode node)
	{
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		if(node == null)
			return;
		q.addLast(node);
		TreeNode temp;
		while(!q.isEmpty())
		{
			temp = q.pop();
			System.out.print(temp.data +" ");
			if(temp.left != null)
				q.addLast(temp.left);
			if(temp.right != null)
				q.addLast(temp.right);
		}
		
		
	}
	public boolean displayAncestors(TreeNode root,TreeNode node)
	{
		if(root == null)
			return false;
		if(root.left == node || root.right == node || displayAncestors(root.left,node) || displayAncestors(root.right,node))
			{
				System.out.print(root.data + " ");
				return true;
			}
		return false;
	}
	public void displayMax(TreeNode node)
	{
		int max = 0;
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		if(node == null)
			return;
		q.addLast(node);
		TreeNode temp;
		while(!q.isEmpty())
		{
			temp = q.pop();
			if(temp.data > max)
				max = temp.data;
			if(temp.left != null)
				q.addLast(temp.left);
			if(temp.right != null)
				q.addLast(temp.right);
		}
		
		System.out.println("Max element : " + max);

	}
	}
class TreeNode
{
	int data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int x)
	{
		data = x;
	}
}
