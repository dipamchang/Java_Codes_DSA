import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Stack;

public class ProblemSet3 {
	public static void main(String []args)
	{
		ProblemSet3 obj = new ProblemSet3();
		node head = new node(2); // creating the input LL
		obj.appendToTail(head,3);
		obj.appendToTail(head,5);
		obj.appendToTail(head,6);
		obj.appendToTail(head,7);
		obj.appendToTail(head,9);
		//obj.displayList(head);
		
		//obj.solution1c(head,2); // 1d -> find length and compute n-k+1 th node;
		
		node head2 = new node(0);
		node one = new node(1);
		node two = new node(2);
		node three = new node(3);
		node four = new node(4);
		node five = new node(5);
		node six = new node(6);
		
		
		head2.next = one;
		one.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		five.next = six;
		six.next = null;
		//obj.displayList(head2);
		
		
		node head3 = new node(3);
		node a = new node(4);
		node b = new node(5);
		node c = new node(6);
		
		head3.next = a;
		a.next = b;
		b.next = c;
		c.next = three;
		//obj.displayList(head3);
		
		System.out.println("Cycle starts at :" + obj.solution2b(head2).data);
		
		//obj.removeloop(head2);
		
		//node tail = obj.reverseList(head);
		
		//System.out.println("They intersect at :" + obj.getIntersection4(head2,head3).data);
		
		//obj.getMiddle2(head3);
		
		//obj.reverseList(head2);
		
		//obj.displayList(obj.merge2(head,head2));
		
		//obj.displayList(obj.reverseInPairs2(head)); // try reverse in pairs1 also
		
		node head4 = new node(1);
		obj.appendToTail(head4,2);
		obj.appendToTail(head4,3);
		obj.appendToTail(head4,2);
		obj.appendToTail(head4,1);
		//obj.appendToTail(head4,2);
		//obj.displayList(head4);
		
		//System.out.println(obj.isPalindrome(head4));
		
		
		
		
	}
	
	
	
	public void appendToTail(node head,int data) // insert into linked list
	{
		
		node current = head;
		
		while(current.next != null)
			current = current.next; // set current to last element
		
		node tail = new node(data); // new node to be inserted at the end
		current.next = tail; // by default tail.next = null!
		
	}
	public void displayList(node head)
	{
		for(node current = head;current != null;current = current.next)
		{
			if(current.next != null)
				System.out.print(current.data + " -> ");
			else
				System.out.println(current.data); // for last element i dont want '->' and i use println
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public void solution1a(node head,int k) // iterative O(n)-time , O(1) space
	{
		node first = head;
		node second = head; // set first and second to start of LL
		
		for(int count = 0;count < k;count++) // set second to k elements from head;
			second = second.next;
		
		while(second != null) // by the time loop finishes , first will be the kth to last node
		{
			first = first.next;
			second = second.next;
		}
		System.out.println(k +" from last :" + first.data);
			
	}
	
	
	
	
	
	
	
	
	public int solution1b(node head,int k) // recursive : O(n) time,O(n) space for system stack(recursive implementation)!
	{
		if(head == null)
			return 0;
		int i = 1 + solution1b(head.next,k);
		if(i == k)
		{
			System.out.println(k + " from last :" + head.data);
			System.exit(0);
		}
		return i;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void solution1c(node head,int k) // essentially same as 1b , except instead of system stack we use our own !
	{
		Stack<node> stack = new Stack<node>();
		for(node current = head;current != null;current = current.next)
			stack.push(current);
		for(int count = 0;count < k-1;count++)
			stack.pop();
		node result = stack.pop();
		System.out.println(k + " from last :" + result.data);
	}
	
	
	public boolean solution2BruteForce(node head)
	{
		for(node current = head;current != null;current = current.next)
		{
			for(node temp = current.next;temp != null;temp = temp.next)
			{
				if(temp == current)
					return true;//current node is the start of loop !
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	public node solution2a(node head)// using hash table , O(n) - time , O(n) space
	{
		Hashtable<node,Integer> table = new Hashtable<node,Integer>();
		for(node current = head;current != null;current = current.next)
		{
			if(table.containsKey(current))// current is the start node for the loop
			{
				return current;
			}
			table.put(current,0); // Integer value is of no use , just choosing zero here!
		}
		return null; // in case there is no loop
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public node solution2b(node head)
	{
		boolean loopExists = false;
		node slow,fast;
		for(slow = head,fast = head;slow != null && fast != null;)//slow - 1, fast 2 nodes at a time // fyi - 2 & 3 will work too
		{
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast)
			{
				loopExists = true;
				break;
			}
		}
		if(loopExists)
		{
			slow = head; // reset slow to start
			while(slow != fast) // when they are equal , they meet at the start of loop
			{
				slow = slow.next;
				fast = fast.next;
			}
			return slow;//or fast
		}
			
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void removeloop(node head)
	{
		node startOfLoop = solution2b(head);
		node current;
		int lengthOfLoop = 1;// considering startOfLoop as part of loop
		for(current = startOfLoop;current.next != startOfLoop;current = current.next,lengthOfLoop++);// set current to last element in the loop
		current.next = null; // remove the loop!
		System.out.println("Length of loop :" + lengthOfLoop);
		displayList(head);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public node reverseList(node head)
	{
		node t1 = head.next;
		node t2 = t1.next;
		head.next = null;
		while(t1 != null)
		{
			t1.next = head;
			head = t1;
			t1 = t2;
			if(t2 != null) // check to avoid exception !
				t2 = t2.next;
		}
		displayList(head);
		return head;
	}
	
	
	
	
	
	
	
	
	public node getIntersection(node head1,node head2)// brute force - O(m*n) time, space - O(1)
	{
		for(node t1 = head1;t1 != null;t1 = t1.next)
			for(node t2 = head2;t2 != null;t2 = t2.next)
				if(t1 == t2)
				{
					return t1; // or t2
				}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public node getIntersection2(node head1,node head2)// O(m>n?m:n) - time , same space complexity cos of hashtable
	{
		Hashtable<node,Integer> table = new Hashtable<node,Integer>();
		for(node temp = head1;temp != null;temp = temp.next)
			table.put(temp,0); // again integer part is of no use to us
		for(node temp = head2;temp != null;temp = temp.next)
			if(table.containsKey(temp)) // will happen at intersection node
				return temp;
		return null; // only if no nodes are intersecting
	}
	
	
	
	
	
	
	
	
	
	
	
	public node getIntersection3(node head1,node head2) // time - O(m>n?m:n) , space - same for both the stacks
	{
		Stack<node> stack1 = new Stack<node>();
		Stack<node> stack2 = new Stack<node>();
		
		for(node temp = head1;temp != null;temp = temp.next)
			stack1.push(temp);
		for(node temp = head2;temp != null;temp = temp.next)
			stack2.push(temp);
		
		while(stack1.pop() == stack2.pop());
		return stack1.pop().next.next;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public node getIntersection4(node head1,node head2)
	{
		int length1 = 0,length2 = 0;
		
		for(node temp = head1;temp != null;temp = temp.next)
			length1++; // compute length of first list
		
		for(node temp = head2;temp != null;temp = temp.next)
			length2++; // compute length of second list
		
		if(length1 > length2)
		{
			int diff = length1 - length2;
			node temp1 = head1,temp2 = head2;
	
			for(int i = 0;i < diff;i++)
				temp1 = temp1.next; // set temp1 to diff places from first
			for(;temp1 != null && temp2 != null;temp1 = temp1.next,temp2 = temp2.next)
				if(temp1 == temp2)
					return temp1; // or temp2
			return null;// if they never intersect
		}
		else
		{
			int diff = length2 - length1;
			node temp1 = head1,temp2 = head2;
	
			for(int i = 0;i < diff;i++)
				temp2 = temp2.next; // set temp2 to diff places from first
			for(;temp1 != null && temp2 != null;temp1 = temp1.next,temp2 = temp2.next)
				if(temp1 == temp2)
					return temp1; // or temp2
			return null; // if they never intersect
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void getMiddle(node head)// brute force , two loop traversals
	{
		int length = 0;
		for(node temp = head;temp != null;temp = temp.next)
			length++; // compute length of list
		
		node temp = head;
		for(int i = 0;i < length/2;i++)
		{
			temp = temp.next;
		}
		System.out.println("Midlle element :" + temp.data);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void getMiddle2(node head)// single time traversal
	{
		node slow = head;//moves one at a time
		node fast = head;//moves two at a time
		
		while(fast.next != null && fast != null)
		{
			slow = slow.next;
			fast = fast.next.next;
		}
		
		System.out.println("Middle Element: " + slow.data);	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void printBackwards(node head)
	{
		if(head == null)
			return;
		printBackwards(head.next);
		System.out.print(head.data + " <- ");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public node merge(node head1,node head2) // recursive , time - O(m<n?m:n) , space - same for system stack
	{
		node result = null; // result node
		if(head1 == null)
			return head2; // if either list is empty , the other list is the result!
		if(head2 == null)
			return head1;
		
		if(head1.data < head2.data)
		{
			result = head1;
			result.next = merge(head1.next,head2);
		}
		else
		{
			result = head2;
			result.next = merge(head1,head2.next);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public node merge2(node head1,node head2)//iterative , time complexity - O(m<n?m:n) 
	{
		node result = null;
		if(head1.data < head2.data)
		{
			 result = new node(head1.data); // setting result to first element
			 head1 = head1.next;
		}
		else
		{
			 result = new node(head2.data);
			 head2 = head2.next;
		}
		
		while(head1 != null && head2 != null)
		{
			if(head1.data < head2.data)
			{
				appendToTail(result,head1.data);
				head1 = head1.next;
			}
			else
			{
				appendToTail(result,head2.data);
				head2 = head2.next;
			}
		}
		while(head1 != null)// if head1 is not empty yet
		{
			appendToTail(result,head1.data);
			head1 = head1.next;
		}
		while(head2 != null)// if head2 is not empty yet
		{
			appendToTail(result,head2.data);
			head2 = head2.next;
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public node reverseInPairs(node head) // recursive version , O(n) time , O(n) space(system stack)
	{
		
		if(head == null || head.next == null) // base case for 0 or 1 element list
			return head;
		
		node current = head;
		node temp = current.next;
		node temp2 = temp.next;
		
		temp.next = current;
		current.next = reverseInPairs(temp2);
		
		return temp;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public node reverseInPairs2(node head) // iterative , NOT in place though O(n) time and O(n) space
	{
		node result = new node(head.next.data);
		node current = head;
		boolean flag = false; // first time dont append current.next.data as already we ve done that in init statement
		while(current != null && current.next != null)
		{
			if(flag)
				appendToTail(result,current.next.data);
			flag =true;
			appendToTail(result,current.data);
			current = current.next.next;
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean isPalindrome(node head) // O(n) time , O(n) space for stack
	{
		Stack<Integer> stack = new Stack<Integer>();
		
		for(node a = head;a != null;a = a.next)
			stack.push(a.data);
		
		for(node a = head;a != null && !stack.isEmpty();a = a.next)
			if(a.data != stack.pop())
				return false;
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	public ListNode cloneList(ListNode head)
	{
		Hashtable<ListNode,ListNode> table = new Hashtable<ListNode,ListNode>();
		
		//first make a dummy copy of the list
		for(ListNode current = head;current != null;current = current.next)
		{
			ListNode copy = new ListNode(current.data);
			copy.next = null;
			copy.random = null;
			table.put(current,copy);
		}
		
		//once we have all nodes , set the pointers next and random accordingly
		for(ListNode current = head;current != null;current = current.next)
		{
			ListNode temp = table.get(current);
			temp.next = table.get(current.next);
			temp.random = table.get(current.random);//reason we need hashtable
		}
		
		return table.get(head); // head pointer of cloned list
		
	}

	
}



class node
{
	int data;
	node next = null;
	
	public node(int x)
	{
		data = x;
	}
}
class ListNode // for last question
{
	int data;
	ListNode next = null;
	ListNode random = null;
	
	public ListNode(int x)
	{
		data = x;
	}
}
