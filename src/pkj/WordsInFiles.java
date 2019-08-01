package pkj;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordsInFiles {
	
	private HashMap<String,ArrayList<String>> map;

	public WordsInFiles() {
		map = new HashMap<String,ArrayList<String>>();
	}
	
	private void addWordsFromFile(File f) throws FileNotFoundException{
		Scanner sc = new Scanner(f);
		ArrayList<String> al = null;
		while(sc.hasNext()) {
			String w = sc.next();
			if(!map.containsKey(w)) {
				al = new ArrayList<String>();
				al.add(f.getName());
				map.put(w, al);
			}
			else {
				al = map.get(w);
				if(!al.contains(f.getName())) {
					al.add(f.getName());
				}
			}
		}
		sc.close();
	}
	private void buildWordFileMap() throws FileNotFoundException {
		map.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f:dr.selectedFiles()) {
			addWordsFromFile(f);
		}
	}
	
	private int maxNumber() {//assume hashmap already been constructed
		int max = 0;
		ArrayList<String> al = null;
		for(String s:map.keySet()) {
			al = map.get(s);
			if(max < al.size()) {
				max = al.size();
			}
		}
		return max;
	}
	
	private ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> al = null;
		ArrayList<String> words = new ArrayList<String>();
		for(String s:map.keySet()) {
			al = map.get(s);
			if(al.size() == number)
			{
				words.add(s);
			}
		}
		return words;
	}
	
	private void printFilesIn(String word) {
		ArrayList<String> al= map.get(word);
		System.out.print("\"" + word + "\"" + " appears in the files: ");
		for(String s:al) {
			System.out.print(s + ", ");
		}
	}
	
	public void tester() throws FileNotFoundException {
		buildWordFileMap();
		
		int max = maxNumber();
		ArrayList<String> words = wordsInNumFiles(4);
		
		System.out.print("The greatest number of files a word appears in is "+ max + ", and there are " + words.size() + " such words: ");
		boolean first = true;
		for(String s:words) {
			if(first)
			{
				first =false;
				System.out.print("\"" + s +"\"");
			}
			else
			System.out.print(" and " + "\"" + s +"\"");
		}
		
		System.out.println();
		
		for(String s:words) {
			printFilesIn(s);
			System.out.println();
		}
		
	}
}
