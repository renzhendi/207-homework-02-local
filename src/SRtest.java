import java.awt.*;
import java.io.IOException;


public class SRtest {
	
	public WordGenerator word;
	public int width;
	public int height;
	public int fontSize;
	public int wpm;
	
	public SRtest(String fileName, int w, int h, int fs, int wpm) throws IOException{
		word = new WordGenerator(fileName);
		width = w;
		height = h;
		fontSize = fs;
		this.wpm = wpm;
	}
	
	/**
	 * 
	 * @throws InterruptedException
	 * creates the panel to display animated words.
	 * 
	 * We learned examples of getFontMetrics() from Oracle:
	 * https://docs.oracle.com/javase/tutorial/2d/text/measuringtext.html
	 */
	public void demonstratePanel() throws InterruptedException {
		// create a panel
		DrawingPanel panel = new DrawingPanel(width, height);
	    Graphics g = panel.getGraphics();
	    Font f = new Font("Courier", Font.BOLD, fontSize);
	    FontMetrics metrics = g.getFontMetrics(f);
	    g.setFont(f);
	    // print a word to the center of the panel
	    while(word.hasNext()){
	    	String text = word.next();
	    	g.setColor(Color.black);
	    	g.drawString(text, (width - metrics.stringWidth(text)) / 2, ((height - metrics.getHeight()) / 2) + metrics.getAscent());
	    	Thread.sleep(60000/wpm);
	    	g.setColor(Color.white);
	    	g.fillRect(0, 0, width, height);
	    }
	    System.out.println("The number of words is " + word.getWordCount() + ".");
	    System.out.println("The number of sentences is " + word.getSentenceCount() + ".");
	}
	
	/**
	 * 
	 * @param args: <filename> <width> <height> <font size> <wpm>
	 * @throws InterruptedException, IOException
	 * implements command-line usage of the SpeedReader program.
	 * 
	 * We learned Integer.parseInt() function from stackoverflow:
	 * http://stackoverflow.com/questions/5585779/converting-string-to-int-in-java
	 * 
	 * We retrieved the sample text from Wikipedia:
	 * https://en.wikipedia.org/wiki/Giant_panda
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		String arg1 = "panda.txt";
		int arg2 = 640;
		int arg3 = 480;
		int arg4 = 32;
		int arg5 = 360;
		SRtest reader = new SRtest(arg1, arg2, arg3, arg4, arg5);
		reader.demonstratePanel();
		// TODO Auto-generated method stub
	}
}