package pkj;

import java.io.IOException;

public class Tester {

	public Tester() {
		
	}
	
	public static void main(String[] args) throws IOException{
		CodonCount cc = new CodonCount();
//		cc.tester();
		WordsInFiles wf = new WordsInFiles();
		wf.tester();
	}

}
