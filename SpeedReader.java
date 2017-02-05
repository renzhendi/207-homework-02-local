import java.awt.*;
import java.io.IOException;

public class SpeedReader {
    public WordGenerator words;
    public int width;
    public int height;
    public int fontSize;
    public int wpm;
	
    public SpeedReader(String fileName, int w, int h, int fs, int wpm) throws IOException{
        words = new WordGenerator(fileName);
        width = w;
        height = h;
        fontSize = fs;
        this.wpm = wpm;
    }
	

    public void demonstratePanel() throws InterruptedException {
        //sets up drawing panel and font
        DrawingPanel panel = new DrawingPanel(width, height);
        Graphics g = panel.getGraphics();
        Font f = new Font("Courier", Font.BOLD, fontSize);
        g.setFont(f);
        FontMetrics center = g.getFontMetrics(f);
        //runs the text file
        while(words.hasNext()){
            g.setColor(Color.black);
            int x = (width - center.stringWidth(text)) / 2;
            int y = ((height - center.getHeight()) / 2) + center.getAscent();
            g.drawString(words.next(), (width / 2), (height / 2));
            Thread.sleep(60000/wpm);
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
        }
        // prints words and sentence counts 
        System.out.println("The number of words is " + words.getWordCount() + ".");
        System.out.println("The number of sentences is " + words.getSentenceCount() + ".");
    }

	
    /**
     * @param args
     * @throws InterruptedException 
     * @throws IOException 
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length < 5){
            System.out.println("Not enough arguments! Please input <filename> <width> <height> <font size> <wpm>");
            return;
        }
        SpeedReader reader = new SpeedReader(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
        reader.demonstratePanel();
        //printStaggered();
        // TODO Auto-generated method stub

    }

}
