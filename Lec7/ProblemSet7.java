
package Test2;
import java.util.LinkedList;
import java.util.Stack;

public class ProblemSet7 {
	public static void main(String []args)
	{
		ProblemSet7 obj = new ProblemSet7();
		
		
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
		
		//System.out.println("Sum of all elements : " + obj.sum(one));
		//System.out.println("Sum of all elements : " + obj.sum2(one));
		//obj.printAncestors(one,eight);
		
		//obj.levelOrder(one);
		//System.out.print("\n");
		//obj.convertToMirrorImage(one);
		//obj.levelOrder(one);
		
		//int []inOrder = {4,2,5,1,6,3,7};
		//int []preOrder = {1,2,4,5,3,6,7};
		//NODE root = obj.constructTree(inOrder,preOrder,0,inOrder.length-1);
		//obj.levelOrder(root);
		
		//System.out.println("Least Common Ancestor :" + obj.leastCommonAncestor(one,five,eight).data);
		//obj.printZigZag(one);
		//System.out.println(obj.areIsomorphic(one,one));
		
	}
	public int sum(NODE root)//using level order traversal
	{
		if(root == null)
			return 0;//validation
		LinkedList<NODE> q = new LinkedList<NODE>();
		q.addLast(root);
		int sum = 0;
		while(!q.isEmpty())
		{
			root = q.removeFirst();
			sum += root.data;//update sum
			if(root.left != null)
				q.addLast(root.left);
			if(root.right != null)
				q.addLast(root.right);
		}
		return sum;
	}
	public int sum2(NODE root)
	{
		if(root == null)
			return 0;//base case when we hit bottom
		else
			return (root.data + sum2(root.left) + sum2(root.right));//sum = sum of current element + left subtree + right subtree 
	}
	public boolean printAncestors(NODE root,NODE x)
	{
		if(root == null)
			return false;//base case when we hit bottom
		else if(root.data == x.data || printAncestors(root.left,x) || printAncestors(root.right,x))
		{
			//if u dont want node x to be printed , then say root.left.data == x.data || root.right.data == x.data instead of root.data == x.data in the if statement
			System.out.print(root.data + " ");
			return true;
		}
		else
			return false;
	}
	public void convertToMirrorImage(NODE root)
	{
		if(root != null)
		{	
			if(root.left != null) // optional ( to avoid one more recursive call)
				convertToMirrorImage(root.left);
			if(root.right != null) // optional ( to avoid one more recursive call)
				convertToMirrorImage(root.right);
			
			if(root.left != null || root.right != null) // optional (there s at least one child) and we avoid swapping two nulls
			{	
				NODE temp = root.left;
				root.left = root.right;
				root.right = temp;
			}
		}
	}
	public void convertToMirror(NODE root)
	{
		if(root == null)
			return;
		
		
		
		convertToMirror(root.left);//recurse down left subtree
		
		NODE temp = root.left;
		root.left = root.right;
		root.right = temp;		
		
		convertToMirror(root.right);//recurse down right subtree
		
		 // swap left and right child starting from last level to get mirror image
		
	}
	public boolean isMirrorImage(NODE root1,NODE root2)
	{
		if(root1 == null && root2 == null)//they hit bottom simultaneously
			return true;
		else if(root1 == null && root2 != null || root1 != null && root2 == null)//they dint hit bottom simultaneously
			return false;
		else // both are not null
			return root1.data == root2.data && isMirrorImage(root1.left,root2.right) && isMirrorImage(root1.right,root2.left);
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
	
	int preIndex = 0;//pre oRder index that we dont want changing for every recursive call
	//u can declare this inside as static too !
	public NODE constructTree(int []inOrder,int []preOrder,int left,int right)
	{
		if(left > right)
			return null;
		NODE curr = new NODE(preOrder[preIndex++]);//pick the current element from pre order and increment index
		if(left == right)
			return curr;//this node has no children
		else // it has children as sub array has at least two elements
		{
			int pivot = 0;
			for(int i = left;i <= right;i++)
				if(inOrder[i] == curr.data) // find the element in In Order!
					pivot = i;
			
			curr.left = constructTree(inOrder,preOrder,left,pivot-1);
			curr.right = constructTree(inOrder,preOrder,pivot+1,right);
			return curr;
		}
	}
	public NODE leastCommonAncestor(NODE root,NODE node1,NODE node2)
	{
		if(root == null)
			return null;
		if(root == node1 || root == node2)
			return root;
		NODE left = leastCommonAncestor(root.left,node1,node2);
		NODE right = leastCommonAncestor(root.right,node1,node2);
		
		if(left != null && right != null)
			return root; // validation here ??
		else
			return left != null ?left : right;
	}
	public void printZigZag(NODE root)//slight modification to BFS using additional stack
	{
		if(root == null)
			return;
		LinkedList<NODE> q = new LinkedList<NODE>();
		Stack<NODE> stack = new Stack<NODE>();
		q.addLast(root);
		q.addLast(null);
		int level = 0;
		while(!q.isEmpty())
		{
			root = q.removeFirst();
			
			if(root == null)
			{
				if(level % 2 != 0)//reverse printing
				{
					while(!stack.isEmpty())
						System.out.print(stack.pop().data + " ");
				}
				
				level++;
				System.out.print("\t");//optional
				if(!q.isEmpty())// or else infinite loop cos after last level u dont add anything to queue
					q.addLast(null);
			}
			else
			{
				if(level % 2 == 0)//print left to right
					System.out.print(root.data + " ");
				else
				{
					stack.push(root);
				}
				
				if(root.left != null)
					q.addLast(root.left);
				if(root.right != null)
					q.addLast(root.right);
			}
		}
	}
	public boolean areIsomorphic(NODE root1,NODE root2)//have same structure not necessarily data
	{
		if(root1 == null && root2 == null)
			return true;//both hit bottom simultaneously => so far isomorphic
		if(root1 == null && root2 != null || root1 != null && root2 == null)// # root1 == null || root2 == null would ve sufficed as both together null has been ruled out in the previous if condition !
			return false;//one hit bottom but other didn't!
		else
			return areIsomorphic(root1.left,root2.left) && areIsomorphic(root1.right,root2.right);//both subtrees must be isomorphic ! # if u check for equality of data as well , they will be identical trees
	}
	public void fillNextSibling2(SpecialNode root)
	{
		if(root == null)
			return;//base case when we hit bottom
		if(root.left != null)
			root.left.nextSibling = root.right;
		if(root.right != null)
			root.right.nextSibling = root.nextSibling.left;
		else
			root.right.nextSibling = null;
		fillNextSibling2(root.left);
		fillNextSibling2(root.right);
	}
	public void fillNextSiblings(SpecialNode root)//refer SpecialNode class for its structure
	{
		if(root == null)
			return;
		LinkedList<SpecialNode> q = new LinkedList<SpecialNode>();
		q.add(root);
		q.add(null);//mark end of level
		while(!q.isEmpty())
		{
			root = q.removeFirst();
			if(root == null)
			{
				if(!q.isEmpty())
					q.addLast(null);//mark end of current level
			}
			else
			{
				root.nextSibling = q.peekFirst();//next element to be removed is nextSibling in Level Order Traversal
				if(root.left != null)
					q.addLast(root.left);
				if(root.right != null)
					q.addLast(root.right);
			}
		}
		
		
	}
	
	
}
class SpecialNode
{
	int data;
	SpecialNode left = null;
	SpecialNode right = null;
	SpecialNode nextSibling = null;
	
	public SpecialNode(int x)
	{
		data = x;
	}
}
