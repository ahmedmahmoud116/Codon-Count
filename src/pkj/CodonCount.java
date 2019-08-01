package pkj;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CodonCount {
	
	private HashMap<String,Integer> map;
	
	public CodonCount() {
		map = new HashMap<String,Integer>();
	}
	
	private void buildCodonMap(String dna,int start) {
		map.clear();
		StringBuilder sb = new StringBuilder(dna);
		for(int i = start ;i+3<=sb.length();i=i+3) {
			String s = sb.substring(i,i+3);
			if(!map.containsKey(s)) {
				map.put(s, 1);
			}
			else {
				map.put(s,map.get(s)+1);
			}
		}
	}
	
	private String getMostCommonCodon() {//assume already we have in our map the codons and their counts
		int max = 0;
		String dna = "";
		for(String s : map.keySet()) {
			if(max<map.get(s)) {
				dna = s;
				max = map.get(s);
			}
		}
		return dna;
	}
	
	private void printCodonCounts(int start,int end) {
		
		System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
		for(String s : map.keySet()) {
			if(start<=map.get(s) && map.get(s)<=end) {
				System.out.println(s + " " +map.get(s));
			}
		}
	}
	
	public void tester() throws FileNotFoundException {
		DirectoryResource dr = new DirectoryResource();
		Scanner sc = null;
		for(File f : dr.selectedFiles()) {
			sc = new Scanner(f);
			while(sc.hasNext()) {
				String s = sc.next();
				s = s.toUpperCase();
				for(int i = 0; i<3 ;i++) {
					System.out.println();
					buildCodonMap(s, i);
					System.out.println("Reading frame starting with " + i + " results in " + map.size() + " unique codons");
					String max = getMostCommonCodon();
					System.out.println("and most common codon is " + max + " with count " + map.get(max));
					printCodonCounts(7, 7);
			}
		}
		
	   }
	}
}
