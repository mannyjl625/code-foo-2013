import java.util.*;
import java.lang.*;
import java.io.*;

/* For the family tree problem, I used a pre-built barebones family tree graph using Person objects
 * modeled after my own family tree for testing purposes of the search by name and generation algorithm.
 * I implemented a dept first search on the graph to find all family members that matched the name and/or
 * generation specified by the user
 */



/*Person object class used to represent family members in the family tree
 *Only implemented fields that  were neccesary for the depth-first search
 *For the purposes of this algorithm, son and daughter-in-laws are considered the same as natural children
 */


class Person{
	String name;
	boolean visited;
	int generation;
	ArrayList<Person> children;

	public Person(){
		this.name = "";
		this.visited = false;
		this.generation = 0;
		this.children = new  ArrayList<Person>();
	}

	public Person(String name){
		this.name = name.toLowerCase();
		this.visited = false;
		this.generation = 0;
		this.children = new  ArrayList<Person>();
	}

}

/*Generic Stack class used for depth first search
 */
class Stack<T> {

	
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
public class Problem4{
	public static void main(String[] args){
		
		/*A pre-built family tree modeled after my own
		 * For the purposes of the breadth-first search, I made the oldest generation
		 *children of the root of the tree in order to have a constant starting point 
		 *search
		*/
		
		//generation 0
		Person root = new Person("root");
		
		//generation 1
		Person blanca = new Person("blanca");
		Person eguardo = new Person("eguardo");
		
		Person may = new Person("may");
		Person jake = new Person("jake");
		
		//generation 2
		Person wilfredo = new Person("wilfredo");
		Person mimi = new Person("mimi");
		
		Person elsi = new Person("elsi");
		Person eddy = new Person("eddy");

		Person tony = new Person("tony");
		Person denise = new Person("denise");

		Person lyn = new Person("lyn");
		Person piggie = new Person("piggie");  //yes my uncle's name is piggie
		
		//generation 3
		Person manny = new Person("manny");
		Person may2 = new Person("may");

		Person tito = new Person("tito");
		Person diego = new Person("diego");
		Person mary = new Person("mary");

		Person aja = new Person("aja");
		Person desiree = new Person("desiree");
		
		//generation 4
		Person titoJR = new Person("tito");
		Person maryJR = new Person("mary");

		//Assigning the parent/children relationships

		//generations 0 relations
		root.children.add(blanca);
		root.children.add(eguardo);
		root.children.add(may);
		root.children.add(jake);

		//generation  1 parents
		blanca.children.add(wilfredo);
		blanca.children.add(mimi);
		blanca.children.add(elsi);
		blanca.children.add(eddy);
		blanca.children.add(tony);
		blanca.children.add(denise);
		eguardo.children = blanca.children;

		may.children.add(tony);
		may.children.add(denise);
		may.children.add(lyn);
		may.children.add(piggie);
		jake.children = may.children;

		//generation 2 parents
		elsi.children.add(desiree);
		eddy.children  = elsi.children;

		mimi.children.add(diego);
		mimi.children.add(tito);
		mimi.children.add(mary);
		wilfredo.children = mimi.children;
		
		lyn.children.add(aja);
		piggie.children = lyn.children;

		denise.children.add(manny);
		denise.children.add(may2);
		tony.children = denise.children;
		
		//generation 3 parents
		mary.children.add(maryJR);
		mary.children.add(titoJR);
		tito.children = mary.children;

		//Start of menu/user input

		Scanner sc;
		String option = "";  
		String name = "";
		int genNum = 0;
		while(true){
			System.out.println("Enter number for search option");
			System.out.println("(1): search by name");
			System.out.println("(2): search by generation");
			System.out.println("(3): search by name and generation");
			sc = new Scanner(System.in);
			option = sc.nextLine();
			if(option.equals("1")){
				System.out.println("enter name");
				sc =  new Scanner(System.in);
				name = sc.nextLine().toLowerCase();
				break;
			}else if(option.equals("2")){
				System.out.println("enter generation");
				sc = new Scanner(System.in);
				genNum = sc.nextInt();
				break;
			}else if(option.equals("3")){
				System.out.println("enter name");
				sc = new Scanner(System.in);
				name = sc.nextLine().toLowerCase();
				
				System.out.println("enter generation");
				sc = new Scanner(System.in);
				genNum = sc.nextInt();
				break;

			}else{
				System.out.println("Not a valid option. Pick option 1, 2, or 3");
				System.out.println("");
			}
		}

		/*begining of depth first search with stack
		if family member matches name and or generation depending on the menue option,
		the person object will be added to the results ArrayList
		*/
		Stack<Person> s = new Stack<Person>();
		ArrayList<Person> results = new ArrayList<Person>();
		s.push(root);
		root.visited = true;
		while(!s.isEmpty()){
			Person ancestor = s.peek();
			Person relative = getUnvisitedChild(ancestor);
			if(relative!=null){
				relative.generation = ancestor.generation+1;
				relative.visited =true;
				if(option.equals("1") && relative.name.equals(name)){
					results.add(relative);
				}else if(option.equals("2") && relative.generation == genNum){
					results.add(relative);
				}else if(option.equals("3") && relative.name.equals(name) && relative.generation == genNum){
					results.add(relative);
				}
				s.push(relative);
			}else{
				s.pop();
			}
		}
		
		//no familiy members found 
		if(results.isEmpty()){
			System.out.println("No results found in the family tree");
		}else{   //custom print-out based on option chosed at start
			if(option.equals("1")){
				System.out.println("Family members with the name "+name);
			}else if (option.equals("2")){
				System.out.println("Family members of the " + genNum + " generation");
			}else if(option.equals("3")){
				System.out.println("Family members with the name  " +  name + " and of the " + genNum + " generation");  
			}

			//prints all family members that match the search preferences
			for(int i = 0; i<results.size(); i++){
				System.out.println("name: " + results.get(i).name  + " / genertaion: " +results.get(i).generation);
			}
		}
	}
	
	/*Helper method that takes in a Person object and returns an unvisited child 
	 *returns null if person has no childrend or if all children are already visited
	 */
	public static Person getUnvisitedChild(Person person){
		for(int i = 0; i<person.children.size(); i++){
			if(!person.children.get(i).visited){
				return person.children.get(i);
			}
		}
		return null;
	}
}
