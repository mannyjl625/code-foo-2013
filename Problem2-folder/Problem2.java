import java.util.*;
import java.lang.*;
import java.io.*;

class HighScore{
	float points;
	String name;

	public HighScore(){
		this.points = 0;
		this.name = null;

	}

	public HighScore(float points, String name){
		this.points = points;
		this.name = name;
	}

}
public class Problem2{
	public static void main(String[] args)
	throws FileNotFoundException{
		//mergesort
		String fileName = "Scores.txt";
		ArrayList<HighScore> scoreList = new ArrayList<HighScore>();
		Scanner sc;
		String word;
		try{
			sc = new Scanner(new File(fileName));
		}catch(FileNotFoundException e){
			System.out.println("file not found");
			return;
		}

		while(sc.hasNext()){
			word = sc.nextLine();
			word = word.trim();
			float points = Integer.parseInt(word.substring(0, word.indexOf(' ')));
			String name = word.substring(word.indexOf(' '), word.length());
			HighScore score = new HighScore(points, name);
			scoreList.add(score);
		}
		HighScore[] scoreList2 = new HighScore[1]; // convert ArrayList to array;
		scoreList2 = scoreList.toArray(scoreList2);
		/*
		for(int i = 0; i<scoreList2.length; i++){
			System.out.println(scoreList2[i].points + " " + scoreList2[i].name);
		}*/
		merge_sort(scoreList2);


	}
	public static HighScore[] merge_sort(HighScore[] scores){
		if(scores.length<=1){
			return scores;
		}
		int midIndex = scores.length/2;
		int leftLength = midIndex;
		int rightLength = scores.length-leftLength;
		HighScore[] left = new HighScore[leftLength];
		HighScore[] right = new HighScore[rightLength];
		for(int i = 0; i<scores.length; i++){
			if(i<leftLength){
				left[i] = scores[i];
			}else{
				right[i-leftLength] = scores[i];
			}
		}
		
		for(int i = 0; i<left.length; i++){
			//System.out.println(left[i].points + " " + left[i].name);
		}
		///System.out.println("*****************");
		for(int i = 0; i<right.length; i++){
			//System.out.println(right[i].points + " " + right[i].name);
		}
		left  = merge_sort(left);
		right = merge_sort(right);
		return merge(left, right);
	}

	public static HighScore[] merge(HighScore[] left, HighScore[] right){
		
	}
}


