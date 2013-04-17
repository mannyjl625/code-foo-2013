package friends;

import java.util.*;
import java.lang.*;
import java.io.*;

// Manuel Lopez, YunAh A. Baek
class Person{
	
	String name;
	String school;
	Person next;
	int vertex;
	public Person(){
		this.name = null;
		this.school = null;
		this.next = null;
		this.vertex = 0;
	}
	public Person(String name, String school, int vertex){
		this.name = name;
		this.school = school;
		this.next = null;
		this.vertex = vertex;
	}
}
class Queue<T> {
	
	/**
	 * Linked list node class.
	 *
	 */
	class CLLNode<E> {
		E data; CLLNode<E> next;
		CLLNode(E data) { 
		this.data = data; next = null;
		}
	}
	
	/**
	 * Rear of the queue.
	 */
	CLLNode<T> rear; 
	
	/**
	 * Number of entries in the queue.
	 */
	int size;
	
	/**
	 * Initializes a new queue instance to empty.  
	 */
	public Queue() {
		rear = null; size=0;
	}
	
	/**
	 * Enqueues a given item into the queue.
	 * 
	 * @param item Item to be enqueued.
	 */
	public void enqueue(T item) {
		CLLNode<T> temp = new CLLNode<T>(item);
		if (size == 0) {
			temp.next = temp;
		} else {
			temp.next = rear.next;
			rear.next = temp;
		}
		rear = temp;
		size++;
	}
	
	/**
	 * Dequeues the front item of queue and returns it.
	 * 
	 * @return Item that is dequeued.
	 * @throws NoSuchElementException if item is not in queue.
	 */
	public T dequeue() 
	throws NoSuchElementException { 
		if (size == 0) throw new NoSuchElementException();
		T o = rear.next.data;
		if (size == 1) {
			rear = null; 
		} else {
			rear.next = rear.next.next;
		}
		size--;
		return o;
	}
	
	/**
	 * Returns the first item in the queue without deleting it.
	 * 
	 * @return First item in the queue.
	 * @throws NoSuchElementException if queue is empty.
	 */
	public T peek() 
	throws NoSuchElementException { 
		if (size == 0) throw new NoSuchElementException();
		return rear.next.data;
	}
	
	/**
	 * Tells whether the queue is empty.
	 * 
	 * @return True if empty, false otherwise.
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/**
	 * Returns the number of items in the queue.
	 * 
	 * @return Number of items.
	 */
	public int size(){
		return size;
	}
	
}
public class Friends {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	static HashMap<String, Person> people;
	static Person[] relationships;
	static int[] dfsnum;
	static int[] back;
	static boolean[] visited;
	static ArrayList<String> connectors = new ArrayList<String>();
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("enter text file");
		Scanner sc;
		String fileName;
		while(true){
			try{
				sc = new Scanner(System.in);
				fileName= sc.next();
				sc = new Scanner(new File(fileName));
			}catch(FileNotFoundException e){
				System.out.println("file not found");
				System.out.println("enter valid file name");
				continue;
			}
			break;
		}
		int size = Integer.parseInt(sc.next());
		people = new HashMap<String, Person>(size, 2.0f );		
		relationships = new Person[size];
		relationships = buildGraph(sc);
		//print(relationships);
		while(true){
			System.out.println(" ");
			System.out.println("select choice number");
			System.out.println("(1) Subgraph)");
			System.out.println("(2) Shortest Path ");
			System.out.println("(3) Cliques ");
			System.out.println("(4) Connectors ");
			System.out.println("(5) Quit ");
			//System.out.println("(6) vertex to name");
			//System.out.println("(7) name to vertex");
			//System.out.println("(8) connected?");
			Scanner choice = new Scanner(System.in);
			String str = choice.nextLine();
			if(str.equals("1")){
				System.out.println("enter school name");
				choice = new Scanner(System.in);
				String school = choice.nextLine(); 
				//print(subGraph(school));
				subGraph(school);
			}else if(str.equals("2")){
				System.out.println("enter start name");
				choice  = new Scanner(System.in);
				String start = choice.nextLine();
				System.out.println("enter end name");
				choice = new Scanner(System.in);
				String end = choice.nextLine();
				shortestPath(start, end);
			}else if(str.equals("3")){
				System.out.println("enter school");
				choice = new Scanner(System.in);
				String school = choice.nextLine();
				cliques(school);
			}else if(str.equals("4")){
				connectors();
			}else if(str.equals("5")){
				return;
			}/*else if(str.equals("6")){
				System.out.println("enter vertex");
				choice = new Scanner(System.in);
				int vertex = Integer.parseInt(choice.nextLine());
				System.out.println("name: "+ getName(vertex));
				
			}else if(str.equals("7")){
				System.out.println("enter name");
				choice = new Scanner(System.in);
				String name = choice.nextLine();
				System.out.println("vertex: "+ getVertex(name));
			}else if(str.equals("8")){
				
				System.out.println("");
				System.out.println("enter school");
				System.out.println("");
				choice = new Scanner(System.in);
				String school = choice.nextLine();
				
				System.out.println("");
				System.out.println("enter first name");
				System.out.println("");
				
				choice = new Scanner(System.in);
				String name1 = choice.nextLine();
				
				System.out.println("");
				System.out.println("enter second name");
				System.out.println("");
				
				choice = new Scanner(System.in);
				String name2 = choice.nextLine();
				
				System.out.println("");
				System.out.println(isConnected(name1, name2, makeSubGraph(school)));
				System.out.println("");
				
				}*/else{
				System.out.println("invalid input");
				System.out.println("enter choice number 1-5");
			}
		}
		
	}
	private static Person[] buildGraph(Scanner sc){
		//Person[] relationships = new Person[size];
		int vertex = 1;
		while(sc.hasNext()){
			String str = sc.nextLine();
			//System.out.println(str+"!");
			if(str.length()==0){
				continue;
			}
			if(str.contains("|y|")){
				String name  =  str.substring(0, str.indexOf("|")).toLowerCase();
				String school = str.substring(str.lastIndexOf("|")+1).toLowerCase();
				Person p = new Person(name, school, vertex);
				people.put(name, p);
				relationships[vertex-1] = p;
			}else if(str.substring(str.length()-2).equals("|n")){
				String name  =  str.substring(0, str.indexOf("|")).toLowerCase();
				Person p = new Person(name, "", vertex);
				people.put(name, p);
				relationships[vertex-1] = p;
			}else{
				String name1 = str.substring(0, str.indexOf("|")).toLowerCase();
				String name2 = str.substring(str.indexOf("|")+1).toLowerCase();
				Person p1= new Person(people.get(name1).name, people.get(name1).school, people.get(name1).vertex);
				Person p2= new Person(people.get(name2).name, people.get(name2).school, people.get(name2).vertex);
				
				p2.next = relationships[people.get(p1.name).vertex-1].next;
				relationships[people.get(p1.name).vertex-1].next = p2;
				p1.next = relationships[people.get(p2.name).vertex-1].next;
				relationships[people.get(p2.name).vertex-1].next = p1;
			}
			vertex++;
		}
		return relationships;
	}
	
	private static void subGraph(String school){		//used to be return type Person[]
		school = school.trim().toLowerCase();
		Person[] sub = new Person[relationships.length];
		ArrayList<Integer> markers = new ArrayList<Integer>();
		for(int i = 0; i<sub.length; i++){
			Person curr = relationships[i];
			if(curr.school.equals(school.toLowerCase())){
				markers.add(i);
				Person subP = new Person(curr.name, curr.school, curr.vertex);
				sub[i] = subP;
				curr = curr.next;
				while(curr!=null){
					if(curr.school.equals(school.toLowerCase())){
						subP = new Person(curr.name, curr.school, curr.vertex);
						subP.next = sub[i].next;
						sub[i].next= subP;
					}
					curr = curr.next;
				}
			}
			
		}
		printSubGraph(sub, markers);
	}
	
	private static void shortestPath(String start, String end){
		start = start.trim().toLowerCase();
		end = end.trim().toLowerCase();
		if(!people.containsKey(start) && !people.containsKey(end)){
			System.out.println(start +" and "+ end +" are not in graph");
			return;
		}else if(!people.containsKey(start)){
			System.out.println(start + " is not in graph");
			return;
		}else if(!people.containsKey(end)){
			System.out.println(end + " is not in graph");
			return;
		}
		int count = 1;
		boolean[] visited = new boolean[relationships.length];
		int[] num = new int[relationships.length];
		Queue<Person> t = new Queue<Person>();
		visited[getVertex(start)-1] = true;
		num[getVertex(start)-1] = count;
		t.enqueue(relationships[getVertex(start)-1]);
		//System.out.println("visit "  +count+" " + relationships[getVertex(start)-1].name);
		Person w = null;
		while(!t.isEmpty()){
			count++;
			w = t.dequeue();
			w = w.next;
			while(w!=null){
				if(!visited[w.vertex-1]){
					visited[w.vertex-1] = true;
					num[w.vertex-1] = count;
					t.enqueue(relationships[w.vertex-1]);
					//System.out.println("visit "+ count+ " "+ relationships[w.vertex-1].name);
					if(relationships[w.vertex-1].name.equals(end)){
						//System.out.println("done");
						printPath(visited, num, w);
						return;
						
					}
					//System.out.println();
				}
				w = w.next;
			}
		}
		System.out.println(start+" and " + end + " are not connected");
		return;
	}
	private static void printPath(boolean[] visited, int[] num, Person w){
	
		//System.out.println("************************");
		ArrayList<String> names = new ArrayList<String>();
		Person curr = relationships[w.vertex-1];
		names.add(curr.name);
		while(true){
			if(num[curr.vertex-1]==1){
				break;
			}
			//System.out.println(" count "+ num[curr.vertex-1]);
			//System.out.println("1");
			Person smallest = relationships[curr.vertex-1].next;
			while(smallest!=null){
				if(num[smallest.vertex-1]==0){
					smallest = smallest.next;
				}else{
					break;
				}
			}
			Person ptr = smallest.next;
			while(ptr!=null){
				//System.out.println("2");
				if(num[ptr.vertex-1]<num[smallest.vertex-1]&&visited[ptr.vertex-1]){
					smallest = ptr;
				}
				ptr = ptr.next;
			}
			names.add(smallest.name);
			Person newCurr = new Person(relationships[smallest.vertex-1].name, relationships[smallest.vertex-1].school, relationships[smallest.vertex-1].vertex);
			newCurr.next = curr;
			curr = newCurr;
		}
		//System.out.println("shortest path");
		System.out.println("");
		for(int i = names.size()-1; i>=0; i--){
			if(i == 0){
				System.out.print(names.get(i));
			}else{
				System.out.print(names.get(i)+"--");
			}
		}
		System.out.println("");
		return;
		
	}
	
	private static void cliques(String school){
		school = school.trim().toLowerCase();
		Person[] subGraph = makeSubGraph(school);
		//ArrayList<ArrayList<Integer>> markers = new ArrayList<ArrayList<Integer>>();
		ArrayList<Person[]> subGraphs = new ArrayList<Person[]>();
		subGraphs.add(subGraph);
		boolean allConnected = true;
		int count = 0;
		do{	
			allConnected = true;
			Person[] currGraph = subGraphs.get(count);
			Person[] newGraph = new Person[currGraph.length];
			subGraphs.add(newGraph);
			//find first name within subgraph
			String name1 = "";
			int numPeople = 0;
			int name1Index = 0;
			for(int i = 0; i<currGraph.length; i++){
				if(currGraph[i]!=null){
					numPeople++;
					if(name1.equals("")){
						name1 = currGraph[i].name.toLowerCase();
						name1Index = i;
					}
				}
			}
			if(numPeople==1){
				break;
				//start printing
			}
			for(int i = name1Index+1; i<currGraph.length; i++){
				if(currGraph[i]!=null && !isConnected(name1, currGraph[i].name, currGraph)){
					newGraph[i] = currGraph[i];
					currGraph[i] = null;
					allConnected = false;
				}
			}
			
			count++;
		}while(!allConnected);
		
		subGraphs.remove(subGraphs.size()-1);
		
		//print all subGraphs
		for(int i = 0; i<subGraphs.size(); i++){
			ArrayList<Integer> markers = new ArrayList<Integer>();
			ArrayList<String> lines = new ArrayList<String>();
			
			Person[] graph = subGraphs.get(i);
			int counter = 0;
			for(int j = 0; j<graph.length; j++){
				if(graph[j]!=null){
					markers.add(j);
					counter++;
				}
			}
			if(counter == 0){
				System.out.println("");
				System.out.println(school + " is not in graph");
				return;
			}
			System.out.println("");
			System.out.println("Clique "+(i+1));
			System.out.println("");
			System.out.println(counter);
			
			for(int j = 0; j<markers.size(); j++){
				System.out.println(graph[markers.get(j)].name+"|y|"+graph[markers.get(j)].school);
			}
			for(int j = 0; j<markers.size(); j++){
				if(graph[markers.get(j)]!=null){
					String name1 = graph[markers.get(j)].name;
					Person curr = graph[markers.get(j)].next;
					while(curr!=null){
						String line = (name1+"|"+curr.name);
						String compare = curr.name+"|"+name1;
						if(!lines.contains(compare)){
							System.out.println(line);
						}
						lines.add(line);
						curr=curr.next;
					}
				}
			}
		}
		
	}
	
	private static void connectors(){
		
		dfsnum = new int[relationships.length];
		back = new int[relationships.length];
		visited = new boolean[relationships.length];
		
		for(int i = 0; i<visited.length; i++){
			if(!visited[i]){ 
				//System.out.println("entry "+ relationships[i].name);
				dfs(relationships[i], 1);
			}
		}
		
		System.out.println("");
		for(int i = 0; i<connectors.size(); i++){
			System.out.println(connectors.get(i));
		}
	}
	
	private static int dfs(Person v, int dfsCount){
		boolean flag = false;
		visited[v.vertex-1] = true;
		Person curr = v.next;
		dfsnum[v.vertex-1] = dfsCount;
		back[v.vertex-1] = dfsCount;
		//System.out.println(v.name+ " "+dfsnum[v.vertex-1]+"/"+back[v.vertex-1]);
		while(curr!=null){
			int backNum;
			if(!visited[curr.vertex-1]){
				dfsCount++;
				backNum = dfs(relationships[curr.vertex-1], dfsCount);
				if(dfsnum[v.vertex-1]>back[backNum-1]){
					back[v.vertex-1] = Math.min(back[v.vertex-1], back[backNum-1]);
					//System.out.println(v.name+ " "+dfsnum[v.vertex-1]+"/"+back[v.vertex-1]);
				}else{  
					if(dfsnum[v.vertex-1]==1 && !flag){
						//System.out.println("***check***");
						flag = true;
					}else if(dfsnum[v.vertex-1] == 1 && flag){
						if(!connectors.contains(v.name)){
							connectors.add(v.name);
							//System.out.println("confirm");
						}
					}else if(!connectors.contains(v.name)){
						connectors.add(v.name);
					}
				}
			}else{
				back[v.vertex-1] = Math.min(back[v.vertex-1], dfsnum[curr.vertex-1]);
				//System.out.println(v.name+ " "+dfsnum[v.vertex-1]+"/"+back[v.vertex-1]);
			}
			curr = curr.next;
		}
		return v.vertex;
	}
	
	private static String getName(int vertex){
		
		if(vertex>0 && vertex<=relationships.length){
			return relationships[vertex-1].name;
		}
		return "not a valid vertex";
	}
	
	private static int getVertex(String name){
			
		if(people.containsKey(name)){
			return people.get(name).vertex;
		}
		return -1;
	
	}
	
	
	
	private static void print(Person[] table){
		System.out.println("");
		System.out.println("relationships");
		System.out.println("");
		for(int i = 0; i<table.length; i++){
			Person curr = table[i];
			if(curr==null){
				continue;
			}
			if(!curr.school.equals("")){
				System.out.print("(" +curr.name+","+curr.school+"): ");
			}else{
				System.out.print("(" +curr.name+"): ");
			}
			curr = curr.next;
			while(curr!=null){
				if(curr.school.equals("")){
					System.out.print("/("+curr.name+")/");
				}else{
					System.out.print("/("+curr.name+", "+curr.school+")/");
				}
				
				curr= curr.next;
			}
			System.out.println();
		}
	}
	
	private static void printSubGraph(Person[] subGraph, ArrayList<Integer> markers){
		ArrayList<String> lines = new ArrayList<String>();
		if(markers.size()==0){
			System.out.println("school not in graph");
			return;
		}
		System.out.println(markers.size());
		for(int i = 0; i<markers.size(); i++){
			System.out.println(subGraph[markers.get(i)].name+"|y|"+ subGraph[markers.get(i)].school);
		}
		for(int i = 0; i<markers.size(); i++){
			String name1 = subGraph[markers.get(i)].name;
			Person curr = subGraph[markers.get(i)].next;
			while(curr!= null){
				String line = (name1+"|"+curr.name);
				String compare = curr.name+"|"+name1;
				if(!lines.contains(compare)){
					System.out.println(line);
				}
				lines.add(line);
				curr = curr.next;
			}
		}
	}
	private static boolean isConnected(String start, String end, Person[] sub){
		if(!people.containsKey(start)){
			System.out.println(start + " not in graph");
			return false;
		}
		int count = 1;
		boolean[] visited = new boolean[sub.length];
		int[] num = new int[sub.length];
		Queue<Person> t = new Queue<Person>();
		visited[getVertex(start)-1] = true;
		num[getVertex(start)-1] = count;
		t.enqueue(sub[getVertex(start)-1]);
		//System.out.println("visit "  +count+" " + relationships[getVertex(start)-1].name);
		Person w = null;
		while(!t.isEmpty()){
			count++;
			w = t.dequeue();
			w = w.next;
			while(w!=null){
				if(!visited[w.vertex-1]){
					visited[w.vertex-1] = true;
					num[w.vertex-1] = count;
					t.enqueue(sub[w.vertex-1]);
					//System.out.println("visit "+ count+ " "+ relationships[w.vertex-1].name);
					if(sub[w.vertex-1].name.equals(end)){
						//System.out.println("done");
						return true;
						
					}
					//System.out.println();
				}
				w = w.next;
			}
		}
		//System.out.println(start+" and " + end + " are not connected");
		return false;
	}
	
	private static Person[] makeSubGraph(String school){	
		
		Person[] sub = new Person[relationships.length];
		ArrayList<Integer> markers = new ArrayList<Integer>();
		for(int i = 0; i<sub.length; i++){
			Person curr = relationships[i];
			if(curr.school.equals(school.toLowerCase())){
				markers.add(i);
				Person subP = new Person(curr.name, curr.school, curr.vertex);
				sub[i] = subP;
				curr = curr.next;
				while(curr!=null){
					if(curr.school.equals(school.toLowerCase())){
						subP = new Person(curr.name, curr.school, curr.vertex);
						subP.next = sub[i].next;
						sub[i].next= subP;
					}
					curr = curr.next;
				}
			}
			
		}
		return sub;
	}
}

