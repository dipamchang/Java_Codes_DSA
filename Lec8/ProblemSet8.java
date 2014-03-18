import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ProblemSet8 extends ProblemSet5{
	public static void main(String []args)
	{
		ProblemSet8 obj = new ProblemSet8();
		
		NODE four = new NODE(4);
		NODE two = new NODE(2);
		NODE six = new NODE(6);
		
		four.left = two;
		four.right = six;
		
		NODE one = new NODE(1);
		NODE three = new NODE(3);
		
		two.left = one;
		two.right = three;
		
		NODE five = new NODE(5);
		NODE seven = new NODE(7);
		
		six.left = five;
		six.right = seven;
		
		//obj.inOrder(four);
		
		//System.out.println(obj.findElement1(four,seven));
		//System.out.println(obj.findElement1(four,new NODE(8)));
		//System.out.println(obj.findElement2(four,two));
		//System.out.println(obj.findElement1(four,new NODE(6)));
		
		//System.out.println(obj.getMin(four).data);
		//System.out.println(obj.getMin2(four).data);
		
		//System.out.println(obj.getMax(four).data);
		//System.out.println(obj.getMax2(four).data);
		
		//obj.inOrder(four);
		//System.out.println();
		//obj.insert1(four,0);
		//obj.insert2(four,8);
		//obj.inOrder(four);
		
		//System.out.println(obj.inOrderSuccessor(four).data);
		//System.out.println(obj.inOrderPredecessor(four).data);
		
		//obj.inOrder(four);
		//System.out.print("\n");
		//obj.delete1(four,4);
		//obj.inOrder(four);
		
		//System.out.println(obj.LCA(four,one,three).data);
		//System.out.println(obj.LCA2(four,five,seven).data);
		
		//obj.shortestPath(four,one,five);

		NODE x = new NODE(6);
		NODE y = new NODE(2);
		NODE z = new NODE(8);
		
		NODE a = new NODE(1);
		NODE b = new NODE(9);
		
		x.left = y;
		x.right = z;
		
		y.left = a;
		y.right = b;
		
		//System.out.println(obj.isBST(four));
		// System.out.println(obj.isBST2(x,-20,20));
		//System.out.println(obj.isBST3(x));
		
		//obj.convertToDLL(four);
		
		//obj.printInRange(four,3,6);
	}
	public boolean findElement1(NODE root,NODE x)
	{
		if(root == null)
			return false;//if we hit bottom
		
		if(root == x)
			return true;
		else if(x.data > root.data)
			return findElement1(root.right,x);
		else
			return findElement1(root.left,x);
	}
	
	public boolean findElement2(NODE root,NODE x)
	{
		while(root != null)
		{
			if(root == x)
				return true;
			else if(x.data > root.data)
				root = root.right;
			else
				root = root.left;
		}
		return false;//in case we hit bottom and don't find it!
	}
	
	public NODE getMin(NODE root)
	{
		if(root.left == null)
			return root;
		else
			return getMin(root.left);
	}
	
	public NODE getMin2(NODE root)
	{
		while(root.left != null)
			root = root.left;
		return root;
	}
	

	public NODE getMax(NODE root)
	{
		if(root.right == null)
			return root;
		else
			return getMax(root.right);
	}
	
	public NODE getMax2(NODE root)
	{
		while(root.right != null)
			root = root.right;
		return root;
	}
	public void insert1(NODE root,int x)
	{
		if(root.data == x)
			return;//element already exists!
		else if(x > root.data)
		{
			if(root.right == null)
				root.right = new NODE(x);
			else
				insert1(root.right,x);
		}
		else 
		{
			if(root.left == null)
				root.left = new NODE(x);
			else
				insert1(root.left,x);
		}
		
	}
	public void insert2(NODE root,int x)
	{
		while(root != null)
		{
			if(root.data == x)
				return;
			else if(x > root.data)
			{
				if(root.right == null)
					root.right = new NODE(x);
				else
					root = root.right;
			}
			else
			{
				if(root.left == null)
					root.left = new NODE(x);
				else
					root = root.left;
			}
				
		}
	}
	public NODE delete1(NODE root,int x)
	{
		if(root == null)
			System.out.println("Element not present");
		else
		{
			if(root.data == x)
			{
				if(root.left == null && root.right == null)//leaf
				{
					root = null;
					return root;
				}
				else if(root.left != null && root.right == null)
				{					
					root = root.left;
					return root;
				}
				else if(root.left == null && root.right != null)
				{					
					root = root.right;
					return root;
				}
				else
				{
					NODE temp = inOrderSuccessor(root);
					root.data = temp.data;
					root.right = delete1(root.right,temp.data);
				}
			}
			else if(x > root.data)
				root.right = delete1(root.right,x);
			else
				root.left = delete1(root.left,x);
		}
		return root;
	}
	
	public NODE inOrderSuccessor(NODE root)
	{//not applicable for leaves
		if(root.right != null)
			root = root.right;
		else
			return null;
		while(root.left != null)
			root = root.left;
		
		return root;
	}
	public NODE inOrderPredecessor(NODE root)
	{//not applicable for leaves
		if(root.left != null)
			root = root.left;
		else
			return null;
		while(root.right != null)
			root = root.right;
		return root;
	}
	
	public void shortestPath(NODE root,NODE a,NODE b)
	{
		NODE lca = LCA(root,a,b);
		NODE copy = lca;
		Stack<NODE> temp = new Stack<NODE>();
		while(lca != null)
		{		
			temp.push(lca);
			if(a.data > lca.data)
				lca = lca.right;
			else if(a.data < lca.data)
				lca = lca.left;
			else
				break;
		}
		while(!temp.isEmpty())
			System.out.print(temp.pop().data + " ");
		while(copy != null)
		{
			if(b.data > copy.data)
				copy = copy.right;
			else if(b.data < copy.data)
				copy = copy.left;
			else
				break;
			System.out.print(copy.data + " ");
		}
	}
	public NODE LCA(NODE root,NODE a,NODE b)
	{//first check if both a and b are present in BST!(O(log(n)) time
		if(root == null)
			return null;
		if(root.data < a.data && root.data < b.data)
			return LCA(root.right,a,b);
		else if(root.data > a.data && root.data > b.data)
			return LCA(root.left,a,b);
		else
			return root;
	}
	public NODE LCA2(NODE root,NODE a,NODE b)
	{//check if both a and b are in BST!
		if(root == null)
			return null;
		while(root != null)
		{
			if(root.data < a.data && root.data < b.data)
				root = root.right;
			else if(root.data > a.data && root.data > b.data)
				root = root.left;
			else
				return root;
		}
		return root;// null
	}
	public boolean isBST(NODE root)
	{
		if(root == null)
			return true;
		if(root.left != null && getMax(root.left).data > root.data)
			return false;
		if(root.right != null && getMin(root.right).data < root.data)
			return false;
		if(!isBST(root.left) || !isBST(root.right))
			return false;
		return true;
	}
	public boolean isBST2(NODE root,int min,int max)
	{
		if(root == null)
			return true;
		else
			return root.data < max && root.data > min && isBST2(root.left,min,root.data) && isBST2(root.right,root.data,max);
	}
	public boolean isBST3(NODE root)
	{
		if(root == null)
			return true; // validation
		Stack<NODE> stack = new Stack<NODE>(); // to store current node
		int prev = -1000;//USE INT_MIN
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
			
			System.out.println(root.data + " " + prev);
			
			if(root.data < prev)
				return false;
			prev = root.data;
			
			root = root.right;
		}
		return true;
	}
	public LNODE convertToDLL(NODE root)
	{
		int []inOrder = inOrder3(root);
		LNODE head = new LNODE(inOrder[0]);
		LNODE curr = head;
		for(int i = 1;i < inOrder.length;i++)
		{
			curr.next = new LNODE(inOrder[i]);
			curr.next.previous = curr;
			curr = curr.next;
		}
		return head;
		
	}

	public int[] inOrder3(NODE root)
	{
		int []inOrder = new int[sizeOfTree(root)];
		if(root == null)
			return null; // validation
		Stack<NODE> stack = new Stack<NODE>(); // to store current node
		int i = 0;
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
			inOrder[i++] = root.data;
			root = root.right;
		}
		return inOrder;
	}
	
	public LNODE getBST(LNODE head)
	{
		if(head == null || head.next == null)
			return head;//base case
		LNODE mid = getMiddle(head);
		
		mid.previous.next = null;
		mid.next.previous = null;
		
		mid.previous = getBST(head);
		mid.next = getBST(mid.next);
		return mid;
		
	}
	public LNODE getMiddle(LNODE head)
	{
		int length = 0;
		for(LNODE curr = head;curr != null;curr = curr.next)
			length++;
		LNODE mid;
		length /= 2;
		for(mid = head;length > 0;length--)
			mid = mid.next;
		return mid;
	}
	public NODE buildBST(int []inOrder,int left,int right)
	{
		NODE temp;
		if(left > right)
			return null;
		else if(left == right)//leaf node
			temp = new NODE(inOrder[left]);
		else
		{
			int mid = (left + right)/2;
			temp = new NODE(inOrder[mid]);
			temp.left = buildBST(inOrder,left,mid-1);
			temp.right = buildBST(inOrder,mid+1,right);
		}
		return temp;
		
	}
	public NODE kthSmallest(NODE root,int k)
	{
		if(root == null)
			return null; // validation
		int count = 0;
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
			count++;
			if(count == k)
				return root;
			root = root.right;
		}
		return null;
	}
	public void printInRange(NODE root,int a,int b)
	{
		if(root == null)
			return;
		if(root.data > b)
			printInRange(root.left,a,b);
		else if(root.data < a)
			printInRange(root.right,a,b);
		else
		{
			System.out.print(root.data + " ");
			printInRange(root.left,a,b);
			printInRange(root.right,a,b);
		}
	}
	
}

 class LNODE
 {
	 int data;
	 LNODE previous = null;
	 LNODE next = null;
	 
	 public LNODE(int x)
	 {
		 data = x;
	 }
 }
