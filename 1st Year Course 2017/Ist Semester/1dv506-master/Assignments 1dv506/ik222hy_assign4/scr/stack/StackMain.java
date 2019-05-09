package stack;
import java.util.Iterator;

public class StackMain {

	public static void main(String[] args) {
		Stack stack = new ArrayStack();
		stack.push(1);
        stack.push(2);
        stack.push(3);
		System.out.println("Elements of the stack:");
		Iterator<Object> it = stack.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("\nPop operation: " + stack.pop());
		System.out.println("Peek operation: " + stack.peek());

		System.out.println("\nElements of the stack after pop and peek operation:");
		Iterator<Object> itr = stack.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	}

