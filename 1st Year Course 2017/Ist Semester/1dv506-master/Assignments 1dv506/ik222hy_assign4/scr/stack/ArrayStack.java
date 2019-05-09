package stack;
import java.util.Iterator;

public class ArrayStack implements Stack{
	
	private int size=0;
	private int capacity=8;
	private Object[] elements;
	
	// Initialize the stack
	public ArrayStack(){
		elements=new Object[capacity];
	}
	//Return the current stack
	public int size(){
		return size;
	}
	
	private void resize(){
		capacity=2*size;
		Object[] temp=new Object[capacity];
		for(int i=0; i<size; i++){
			temp[i]=elements[i];
		}
		elements=temp;
	}
	
	// Return true if
	public boolean isEmpty(){
		if(size==0)
			return true;
		else
			return false;
	}
	
	public void push(Object element){
		if(size==capacity)
			resize();
		if(size!=0){
			for(int i=size+1; i>0; i--){
				elements[i]=elements[i-1];
				}
		}
		size++;		
		elements[0]=element;
	}
	
	public Object pop(){
		if(size==0)
			throw new IllegalStateException("Cannot pop with an empty stack.");
		else{
		Object top=elements[0];
		for(int i=0; i<size-1; i++){
			elements[i]=elements[i+1];
		}
		size--;
		return top;
		}
	}
	
	public Object peek(){	
		if(size==0)
			throw new IllegalStateException("Cannot peek with an empty stack.");
		else
		    return elements[0];
	}

	public Iterator<Object> iterator(){
		return new StackArrayIterator(elements,size );}}
