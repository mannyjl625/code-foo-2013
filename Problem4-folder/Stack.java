
public class Stack<T> {

	
	private ArrayList<T> items;

	
	public Stack() {
		items = new ArrayList<T>();
	}

	public void push(T item) {
		items.add(item);
	}

	
	public T pop() 
	throws NoSuchElementException {
		if (items.isEmpty()) {
			throw new NoSuchElementException("can't pop from an empty stack");
		}
		return items.remove(items.size()-1);
	}

	public T peek() 
	throws NoSuchElementException {
		if (items.size() == 0) {
			throw new NoSuchElementException("can't peek into an empty stack");
		}
		return items.get(items.size()-1);
	}

	
	public boolean isEmpty() {
		return items.isEmpty();
	}

	
	public int size() {
		return items.size();
	}

	
	public void clear() {
		items.clear();
	}
}
