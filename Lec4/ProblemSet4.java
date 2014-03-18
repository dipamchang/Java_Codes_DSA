import java.util.Hashtable;
import java.util.Stack;

public class ProblemSet4 {
	public static void main(String []args)
	{
		ProblemSet4 obj = new ProblemSet4();
		
		//System.out.println(obj.solution1("(a+b)+(c+d)"));
		
		System.out.println(obj.solution2("((a+b)*(c+d))/((e+f)*(h+g))"));
		
		//System.out.println("\n" + "Expression Value: "+ obj.solution3("123*+5-"));
		
		/*
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(1);
		stack.add(2);
		stack.add(3);
		stack.add(4);
		stack.add(5);
		System.out.println(stack);
		*/
		
		/*
		obj.reverseStack(stack);
		System.out.println(stack);
		*/
		
		//System.out.println(obj.reverseStack2(stack));
		
		Stack<Integer> s = new Stack<Integer>();
		s.add(5);
		s.add(4);
		s.add(3);
		s.add(2);
		s.add(1);
		
		//System.out.println(s);
		//s = obj.sortStack(s);//returns sorted stack
		//System.out.println(s);
		
	}
	public boolean solution1(String expr)
	{
		Stack<Character> stack = new Stack<Character>();// to store brackets
		
		char []exp = expr.toCharArray();
		for(char c : exp)
		{
			if(c == '(' || c == '[' || c == '{')
				stack.push(c);// if its a opening bracket , push onto stack
			
			if(c == ')' || c == ']' || c == '}')
			{
				
				if(stack.isEmpty()) //no opening brackets in stack
				{
					System.out.println("Error: Opening bracket missing");
					return false;
				}
				char top = stack.pop();
				if(c == ')' && top != '(')// opening bracket - closing bracket mismatch
				{
					System.out.println("Error: Opening bracket-closing bracket mismatch");
					return false;
				}
				if(c == ']' && top != '[')// opening bracket - closing bracket mismatch
				{
					System.out.println("Error: Opening bracket-closing bracket mismatch");
					return false;
				}
				if(c == '}' && top != '{')// opening bracket - closing bracket mismatch
				{
					System.out.println("Error: Opening bracket-closing bracket mismatch");
					return false;
				}
				
			}
		}
		if(!stack.isEmpty()) // at the end of exp , if stack is not empty => some closing brackets are missing !
		{
			System.out.println("Error:Closing Bracket Missing");
			return false;
		}
		
			return true; // if every case above is satisfied , then expression is true !
	}
	public String solution2(String infix)
	{
		Stack<Character> stack = new Stack<Character>();
		
		Hashtable<Character,Integer> operator = new Hashtable<Character,Integer>(); 
		//to store operators and their relative priorities!
		operator.put('/',2);
		operator.put('*',2);
		operator.put('%',2);
		operator.put('+',1);
		operator.put('-',1);
		operator.put('(',0);
		
		StringBuilder postfix = new StringBuilder();
		
		for(char c : infix.toCharArray())
		{
			if(!operator.containsKey(c) && c != '(' && c != ')')// i.e. c is an operand
				postfix.append(c);
			if(c == '(')
			{
				stack.push(c);
				continue;
			}
			if(c == ')')
			{
				for(char top = stack.pop();top != '(';top = stack.pop()) // pop and append until '(' is encountered
					postfix.append(top);
			}
			if(operator.containsKey(c))// c is an operator
			{
				while(!stack.isEmpty())
				{
					char top = stack.peek();
					if(top == '(' || operator.get(top) < operator.get(c)) // if '(' or lower priority operator is encountered
						break;
					else
					{
						postfix.append(top);
						stack.pop();
					}
				}
				stack.push(c);
			}
		}
		while(!stack.isEmpty()) // pop and output tokens till stack is empty
			postfix.append(stack.pop());
		
		return postfix.toString();
		
	}
	public int solution3(String postfix)//evaluate postfix
	{
		//assuming no unary operators!
		Stack<Integer> stack = new Stack<Integer>();
		
		Hashtable<Character,Integer> operator = new Hashtable<Character,Integer>();//for operators with priority values
		operator.put('/',2);
		operator.put('*',2);
		operator.put('%',2);
		operator.put('+',1);
		operator.put('-',1);
		
		for(char c : postfix.toCharArray())
		{
			System.out.println("Current character :" +"'"+ c +"'");
			if(!operator.containsKey(c))// its an operand
				stack.push((int)c - 48);//type cast to int and subtract ascii value of zero
			else//its an operator
			{
				int a = stack.pop();
				int b = stack.pop(); //pop top two elements
				
				if(c == '+')
					stack.push(b+a);
				if(c == '-')
					stack.push(b-a);
				if(c == '*')
					stack.push(b*a);
				if(c == '/')
					stack.push(b/a);
				if(c == '%')
					stack.push(b%a);
				
			}
			System.out.println("Stack :"+stack+"<- top");
		}
		return stack.pop();
	}
	
	public void reverseStack(Stack<Integer> stack)//Quadratic time , linear space for recursive stack
	{
		if(stack.isEmpty())
			return;//base case when we hit bottom
		int temp = stack.pop();
		reverseStack(stack);//reverse the rest of the stack
		insertAtBottom(stack,temp);//add to the bottom of the reversed sub stack
	}
	
	public void insertAtBottom(Stack<Integer> stack,int data)
	{
		if(stack.isEmpty()) // base case when we hit bottom
		{
			stack.push(data); // insert at bottom
			return;
		}
		//if stack isn't empty
		int temp = stack.pop(); // remove and store the top in temp
		insertAtBottom(stack,data);//recursively call function on the rest of the stack
		stack.push(temp);// put the top back after sub stack has been reversed!
	}
	
	
	public Stack<Integer> reverseStack2(Stack<Integer> stack)// O(n) time , O(n) space
	{
		Stack<Integer> result = new Stack<Integer>();
		while(!stack.isEmpty())
			result.push(stack.pop());
		return result;
	}
	
	public Stack<Integer> sortStack(Stack<Integer> stack)
	{
		Stack<Integer> result = new Stack<Integer>();
		
		int temp;
		while(!stack.isEmpty())
		{
			temp = stack.pop();
			while(!result.isEmpty() && result.peek() > temp)
				stack.push(result.pop());
			result.push(temp);
		}
		return result;
	}
	
}
