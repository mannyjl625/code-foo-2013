import java.util.*;
import java.lang.*;
import java.io.*;

class HighScore{
	float score;
	String name;

	public HighScore(){
		this.score = 0;
		this.name = null;

	}

	public HighScore(float score, String name){
		this.score = score;
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
		//reads all scores from Score.txt into ArrayList
		while(sc.hasNext()){
			word = sc.nextLine();
			word = word.trim();
			float score = Integer.parseInt(word.substring(0, word.indexOf(' ')));
			String name = word.substring(word.indexOf(' '), word.length());
			HighScore userScore = new HighScore(score, name);
			scoreList.add(userScore);
		}
		scoreList.trimToSize();
		HighScore[] scoreList2 = new HighScore[1]; // convert ArrayList to array;
		scoreList2 = scoreList.toArray(scoreList2);
		
		/*
		System.out.println("Unordered List");
		for(int i = 0; i<scoreList2.length; i++){
			System.out.println("Score: " + scoreList2[i].score + "Name: " + scoreList2[i].name);
		}
		*/
		
		HighScore[] orderedList = merge_sort(scoreList2);
		System.out.println("High Scores");
		for(int i = 0; i<orderedList.length; i++){
			System.out.println((i+1) + ". " + orderedList[i].score + " " + orderedList[i].name);
		}
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
		
		left  = merge_sort(left);
		right = merge_sort(right);
		return merge(left, right);
	}

	public static HighScore[] merge(HighScore[] left, HighScore[] right){
		HighScore[] combo = new HighScore[left.length + right.length];
		int indexL = 0;
		int indexR = 0;
		while(indexL < left.length || indexR < right.length){
			if(indexL<left.length && indexR<right.length){
				if(left[indexL].score > right[indexR].score){
					combo[indexL+indexR] = left[indexL];
					indexL++;
				}else if(left[indexL].score < right[indexR].score){
					combo[indexL+indexR] = right[indexR];
					indexR++;
				}else{
					if(left[indexL].name.compareToIgnoreCase(right[indexR].name) <= 0){
						combo[indexL+indexR] = left[indexL];
						indexL++;
					}else{
						combo[indexL+indexR] = right[indexR];
						indexR++;
					}
				}
			}else if(indexL<left.length){
				combo[indexL+indexR] = left[indexL];
				indexL++;
			}else if(indexR<right.length){
				combo[indexL+indexR] = right[indexR];
				indexR++;
			}	
		}	
		
		return combo;
	}
}


