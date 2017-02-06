import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
	
	public String thisWord;
	public Scanner text;
	public int wordLength;
	private int wordCount;
	private int sentCount;

	/**
	 * constructs a new generator that processes text from the given file.
	 * 
	 * @param filename: a string of the name of the file to be read
	 * @throws IOException 
	 */
	public WordGenerator(String filename) throws IOException {
		text = new Scanner(new File(filename));
	    if (!text.hasNext()) {
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
		wordCount++;
		if (checkSent(newWord)) {
    		sentCount++;
		}
		return newWord;
	}
	
	/**
	 * 
	 * @param word: a string
	 * @return whether or not the string is the end of a sentence
	 */
	public boolean checkSent(String word){
		int i = word.length() - 1;
		return (word.charAt(i) == '.' || word.charAt(i) == '!' || word.charAt(i) == '?' ||
				word.endsWith(".\"") || word.endsWith("!\"") || word.endsWith("?\""));
	}
	
	/**
	 * 
	 * @return the number of words produced by the WordGenerator so far.
	 */
	public int getWordCount(){
		return wordCount;
	}
	
	/**
	 * 
	 * @return the number of sentences produced by the WordGenerator so far.
	 */
	public int getSentenceCount(){
		return sentCount;
	}
}
