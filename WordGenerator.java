import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
	public String thisWord;
	public Scanner text;
	public int wordIndex;
	public int wordLength;
	public int sentCount;

	/**
	 * 
	 * @param filename: a string of the name of the file to be read
	 * @throws IOException 
	 * constructs a new generator that processes text from the given file.
	 */
	public WordGenerator(String filename) throws IOException {
		text = new Scanner(new File(filename));
	    if (text.hasNext()) {
	    } else {
	    	System.out.println(filename + " is an empty document!");
	    }
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return true iff the underlying Scanner of this WordGenerator has text left to process.
	 */
	public boolean hasNext(){
		return text.hasNext();
	}
	
	/**
	 * 
	 * @return the next word of the underlying Scanner.
	 */
	public String next(){
		String newWord = text.next();
		wordLength = newWord.length();
		wordIndex++;
		if (checkSent(newWord)) {
    		sentCount++;
		}
		return newWord;
	}
	
	public boolean checkSent(String word){
		int i = word.length() - 1;
		return (word.charAt(i) == '.' || word.charAt(i) == '!' || word.charAt(i) == '?' );
	}
	
	/**
	 * 
	 * @return the number of words produced by the WordGenerator so far.
	 */
	public int getWordCount(){
		return wordIndex;
	}
	
	/**
	 * 
	 * @return the number of sentences produced by the WordGenerator so far.
	 */
	public int getSentenceCount(){
		return sentCount;
	}
}
