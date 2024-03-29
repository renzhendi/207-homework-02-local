import java.awt.*;
import java.io.IOException;

public class SpeedReader {
	
	public WordGenerator word;
	public int width;
	public int height;
	public int fontSize;
	public int wpm;
	
	public SpeedReader(String fileName, int w, int h, int fs, int wpm) throws IOException{
		word = new WordGenerator(fileName);
		width = w;
		height = h;
		fontSize = fs;
		this.wpm = wpm;
	}
	
	/**
	 * creates the panel to display animated words.
	 * 
	 * @throws InterruptedException
	 * 
	 * We learned examples of getFontMetrics() from Oracle:
	 * https://docs.oracle.com/javase/tutorial/2d/text/measuringtext.html
	 */
	public void demonstratePanel() throws InterruptedException {
		DrawingPanel panel = new DrawingPanel(width, height);
		Font f = new Font("Courier", Font.BOLD, fontSize);
	    while(word.hasNext()){
	    	Graphics g = panel.getGraphics();
	    	g.setColor(Color.black);
		    g.setFont(f);
	    	focusDisplay(word.next(), g, g.getFontMetrics(f));
	    	Thread.sleep(60000/wpm);
	    	panel.clear();
	    }
	    System.out.println("There are " + word.getWordCount() + " word(s) and " + word.getSentenceCount() + " sentence(s).");
	}
	
	/**
	 * decides on which letter of the word to focus, and pass on to display
	 * 
	 * @param str: the word to be displayed
	 * @param grf: the graphics on which the word will be displayed
	 * @param met: font metrics of the current graphics
	 */
	public void focusDisplay(String str, Graphics grf, FontMetrics met){
		int wordLength = str.length();
		if (wordLength < 2){focusHelper(str, grf, met, 0, wordLength);}
		else if (wordLength <6){focusHelper(str, grf, met, 1, wordLength);}
		else if (wordLength <10){focusHelper(str, grf, met, 2, wordLength);}
		else if (wordLength <14){focusHelper(str, grf, met, 3, wordLength);}
		else {focusHelper(str, grf, met, 4, wordLength);}
	}
	
	/**
	 * centers the word around the red focus letter
	 * 
	 * @param str: the word to be displayed
	 * @param grf: the graphics on which the word will be displayed
	 * @param met: font metrics of the current graphics
	 * @param focusIndex: the index of the focus letter
	 * @param wordLength: the length of the word
	 * 
	 * We learned examples of substring() from the following web site:
	 * http://beginnersbook.com/2013/12/java-string-substring-method-example/
	 */
	public void focusHelper(String str, Graphics grf, FontMetrics met, int focusIndex, int wordLength){
		int y = ((height - met.getHeight()) / 2) + met.getAscent();
		String op = str.substring(0, focusIndex);
		String ed = str.substring(focusIndex+1, wordLength);
		String focus = str.substring(focusIndex,focusIndex+1);
		int opL = met.stringWidth(op);
		int focL = met.stringWidth(focus);
		grf.drawString(op, (width-focL)/2-opL, y);
		grf.drawString(ed, (width+focL)/2, y);
		grf.setColor(Color.red);
		grf.drawString(focus, (width-focL)/2, y);
	}

	/**
	 * enables command-line implementation of the SpeedReader program.
	 * 
	 * @param args: <filename> <width> <height> <font size> <wpm>
	 * @throws InterruptedException, IOException
	 * 
	 * We learned Integer.parseInt() function from stackoverflow:
	 * http://stackoverflow.com/questions/5585779/converting-string-to-int-in-java
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		if (args.length < 5){
			System.out.println("Not enough arguments! Please input <filename> <width> <height> <font size> <wpm>");
			return;
		}
		SpeedReader reader = new SpeedReader(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
		reader.demonstratePanel();
	}
}