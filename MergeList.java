package Test2;
public class MergeList {
	public static void main(String []args)
	{
		Listnode head1 = new Listnode(1);
		Listnode three = new Listnode(3);
		Listnode five = new Listnode(5);
		Listnode seven2 = new Listnode(7);
		Listnode five2 = new Listnode(5);
		Listnode three2 = new Listnode(3);
		Listnode one = new Listnode(1);
		
		
		
		Listnode head2 = new Listnode(2);
		Listnode four = new Listnode(4);
		Listnode six = new Listnode(6);
		Listnode seven = new Listnode(7);
		Listnode eight = new Listnode(8);
		
		head1.next = three;
		three.next = five;
		five.next = seven2;
		seven2.next = five2;
		five2.next = three2;
		three2.next = one;
		//one.next = new Listnode(13);
		
		head2.next = four;
		four.next = six;
		six.next = seven;
		seven.next = eight;
		
		//Listnode result = new MergeList().mergeListsInPlace(head1,head2);
		
	
		
		for(Listnode temp = head1;temp != null;temp = temp.next)
		{
			if(temp.next != null)
				System.out.print(temp.data + " -> ");
			else
				System.out.println(temp.data);
		}
		
		//System.out.println(new MergeList().isPalindrome(head1,7).result);
		System.out.println(new MergeList().isPalindrome2(head1,7));
		
	}
	public Listnode mergeListsInPlace(Listnode head1,Listnode head2)
	{
		Listnode curr2 = head2; // temp node to traverse throuh list2
		Listnode curr1 = head1; // temp node to traverse throuh list1
		while(true)
		{
			if(curr2 == null)
				return head1;//merge is done
			if(curr1.next == null)
			{
				curr1.next = curr2;
				return head1;
			}
			if(curr2.data >= curr1.data && curr2.data < curr1.next.data)
			{
				Listnode temp = curr2.next;
				curr2.next = curr1.next;
				curr1.next = curr2;
				curr1 = curr1.next;
				curr2 = temp;
			}
			else
				curr1 = curr1.next;
			
		}

	}
	public Result isPalindrome(Listnode head,int length)
	{
		if(head == null || length == 0)//validation against empty linked list
			return new Result(null,true);
		else if(length == 1)//base case when only middle element left
			return new Result(head.next,true);
		else if(length == 2)//base case when only two elements are left
			return new Result(head.next.next,head.data == head.next.data);
		
		Result obj = isPalindrome(head.next,length-2);
		if(obj.result == false || obj.node == null)
			return obj;
		else
		{
			obj.result = (head.data == obj.node.data ? true : false);//just head.data == obj.node.data would suffice
			obj.node = obj.node.next;
			return obj;
		}
			
	}
	public Listnode isPalindrome2(Listnode head,int length)
	{
		if(head == null || length == 0)
			return null;
		else if(length == 1)
			return head.next;
		else if(length == 2)
		{
			if(head.data != head.next.data)
			{
				System.out.println("NOT A PALINDROME");
				System.exit(0);
			}
			return head.next.next;
		}
		Listnode temp = isPalindrome2(head.next,length-2);

		if(temp.data != head.data)
		{
			System.out.println("NOT A PALINDROME");
			System.exit(0);
		}
		if(temp.next == null)
		{
			System.out.println("IT IS A PALINDROME");
			System.exit(0);
		}
		return temp.next;
	}
}
class Result
{
	public Listnode node;
	public boolean result;
	
	public Result(Listnode x,boolean y)
	{
		node = x;
		result = y;
	}
}
class Listnode
{
	int data;
	Listnode next = null;
	
	public Listnode(int x)
	{
		data = x;
	}
}
