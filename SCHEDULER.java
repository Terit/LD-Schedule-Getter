/*
 * @Author: Andy Theriault
 */
package schedule;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import org.openqa.selenium.example.WebNavigate;



public class SCHEDULER {
   

    public static void main(String[] args) throws FileNotFoundException, IOException, AWTException {

        grabSchedule();
        //tableTester();
    }
    
    public static void tableTester() {
        WebNavigate nav = new WebNavigate();
        nav.tableTest();
    }
    
    public static void grabSchedule() throws AWTException, FileNotFoundException, IOException{
        WebNavigate nav = new WebNavigate();
        nav.ldSchedule();

        robot();
        
        try{
            nav.wait(1000);
        } catch (Exception e){
            
        }
        
        String clipBoard = getClipboardContents();
        nav.exit();

        createTextFile(clipBoard, "C:\\Users\\Andy\\Documents\\Schedule\\ScheduleRaw.txt");
        toCSV("C:\\Users\\Andy\\Documents\\Schedule\\ScheduleRaw.txt","C:\\Users\\Andy\\Documents\\Schedule\\Schedule.txt");
    }
    
  /**
  * Get the String residing on the clipboard.
  *
  * @return any text found on the Clipboard; if none found, return an
  * empty String.
  */
  public static String getClipboardContents() {
    String result = "";
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    //odd: the Object param of getContents is not currently used
    Transferable contents = clipboard.getContents(null);
    boolean hasTransferableText =
      (contents != null) &&
      contents.isDataFlavorSupported(DataFlavor.stringFlavor)
    ;
    if (hasTransferableText) {
      try {
        result = (String)contents.getTransferData(DataFlavor.stringFlavor);
      }
      catch (UnsupportedFlavorException | IOException ex){
        System.out.println(ex);
        ex.printStackTrace();
      }
    }
    return result;
  }
  
  /* 
   * Takes inputFile .txt file and converts 
   * text to CSV format to outputFile .txt file
   */
  public static void toCSV(String inputFile, String outputFile) throws FileNotFoundException, IOException {
 
        String everthing = new String();
        StringBuilder fl = new StringBuilder();
        
        BufferedReader br;
        br = new BufferedReader(new FileReader(inputFile));
        try {
            StringBuilder sb = new StringBuilder();          
            String line = br.readLine();
            

            while (line != null) {
                sb.append(line);
                sb.append('\n');
                
                StringTokenizer st = new StringTokenizer(line);

                while (st.hasMoreTokens()){
                    fl.append(st.nextToken());
                    fl.append(",");
                    //System.out.println(fl);
                }
                fl.append('\n');
                line = br.readLine();
            }
            everthing = sb.toString();
        } finally {
            br.close();
        }
        //System.out.println(everthing);
        //System.out.println(fl);
        createTextFile(fl.toString(),outputFile);
  }
  
  /* 
   * Saves String input to outputFile 
   */
  public static void createTextFile(String input, String outputFile) throws IOException {
        FileWriter fo = new FileWriter(outputFile);

        fo.write(input);
        fo.close();
  }
  
  
  /* 
   * Creates new Robot, navigates to corner of table
   * selects table contents and copies contents
   */
  public static void robot() throws AWTException {
        Robot rob = new Robot();
        
        rob.mouseMove(220, 318);
        rob.mousePress(InputEvent.BUTTON1_MASK);
        rob.mouseMove(700, 600);
        rob.mouseRelease(InputEvent.BUTTON1_MASK);
        rob.keyPress(KeyEvent.VK_CONTROL);
        rob.keyPress(KeyEvent.VK_C);
        rob.delay(1000);
        rob.keyRelease(KeyEvent.VK_CONTROL);
        rob.keyRelease(KeyEvent.VK_C);
        rob.delay(1000);
  }
} 

