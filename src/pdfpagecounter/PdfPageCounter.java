
package pdfpagecounter;


public class PdfPageCounter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable(){
          public void run(){
              new MainFrame().setVisible(true);
          }
      });
    }
      
}
