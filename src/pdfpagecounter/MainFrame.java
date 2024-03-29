package pdfpagecounter;

import com.itextpdf.text.pdf.PdfReader;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author master
 */
public class MainFrame extends javax.swing.JFrame {

    private static final String OS = System.getProperty("os.name");
    private boolean b = true;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        UIManager.put("FileChooser.filesOfTypeLabelText", "Save File As");
        UIManager.put("FileChooser.cancelButtonText", "Quit");
        UIManager.put("FileChooser.fileNameLabelText", "Choosen Directory");
        initComponents();
        this.fileChooser.setApproveButtonText("process");
        this.fileChooser.setSelectedFile(new File(this.fileChooser.getCurrentDirectory().getAbsolutePath()));
        FileFilter filter = new FileNameExtensionFilter("XML File", "xml");
        FileFilter HtmlFilter = new FileNameExtensionFilter("HTML File", "html");
        this.fileChooser.addChoosableFileFilter(HtmlFilter);
        this.fileChooser.addChoosableFileFilter(filter);
        this.fileChooser.setFileFilter(filter);
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.exitMenuItem.addActionListener((e) -> System.exit(0));
        this.aboutMenuItem.addActionListener((e) -> {
            about about = new about();
            about.setVisible(true);

        });
        //System.out.println(this.fileChooser.getFileFilter().getDescription());
       
            
        
    }

    public static void exit() {
        System.exit(0);
    }

    public static int efficientPDFPageCount(String filePath) throws IOException {
        try {
            PdfReader reader = new PdfReader(filePath, new byte[0], true);
            int pages = reader.getNumberOfPages();
            reader.close();
            return pages;
        } catch (NullPointerException  e) {
            // e.getCause().printStackTrace();
            System.out.println("Possible corrupted file:" + filePath);
        }
        return 0;
    }

    public static void openInBrowser(File f) {
        try {
            Desktop.getDesktop().browse(f.toURI());
        } catch (IOException ex) {
            System.out.println("Error while opening file");
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    public static void printToXml(List<String> list, Boolean b) throws IOException, ParserConfigurationException {
        try {

            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();
            Document d = db.newDocument();
            Element r = d.createElement("files");
            d.appendChild(r);
            for (String f : list) {
                Element dir = d.createElement("file");
                r.appendChild(dir);
                Element pdf = d.createElement("name");
                pdf.appendChild(d.createTextNode(f.substring(f.lastIndexOf(System.getProperty("file.separator")) + 1)));
                dir.appendChild(pdf);
                Element name = d.createElement("path");
                if (OS.matches("^Win.*")) {
                    name.appendChild(d.createTextNode("file:\\" + f.substring(0, f.lastIndexOf(System.getProperty("file.separator")) + 1)));
                } else {
                    name.appendChild(d.createTextNode(f.substring(f.indexOf(System.getProperty("file.separator")), f.lastIndexOf(System.getProperty("file.separator")) + 1)));
                }
                dir.appendChild(name);
                /*   Attr ar = d.createAttribute("path");
                ar.setValue(f.substring(f.indexOf("/"), f.lastIndexOf("/")+1));
                dir.setAttributeNode(ar);*/
                Element p = d.createElement("pages");
                p.appendChild(d.createTextNode(Integer.toString(efficientPDFPageCount(f))));
                dir.appendChild(p);
                /*Attr attr = d.createAttribute("pages");
                attr.setValue(Integer.toString(efficientPDFPageCount(f)));
                p.setAttributeNode(attr);
          /*  Element pages = d.createElement(Integer.toString(efficientPDFPageCount(f)));
           d.appendChild(pages);*/
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("src/xsl/main.xsl"));
            //Transformer transformer = transformerFactory.newTransformer(xslt);
            Transformer transformer = (b ? transformerFactory.newTransformer() : transformerFactory.newTransformer(xslt));
            DOMSource domSource = new DOMSource(d);
            // Source text = new StreamSource(new File("/home/master/test.xml"));
            // transformer.transform(text, new StreamResult(new File("/home/master/output.xml")));
            //StreamResult streamResult = new StreamResult(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "pdfCounter.html"));
            StreamResult streamResult = new StreamResult(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "pdfCounter" + (b ? ".xml" : ".html")));
            transformer.transform(domSource, streamResult);
            if (OS.matches("^Win.*") && !b) {
                openInBrowser(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "pdfCounter.html"));
            }
        } catch (IOException | TransformerException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        fileChooser = new javax.swing.JFileChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Page Counter");
        setBackground(new java.awt.Color(38, 38, 38));

        fileChooser.setBackground(new java.awt.Color(38, 38, 38));
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_DOWN_MASK));
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void printPages() throws ParserConfigurationException {

        try {
            Stream<Path> paths = Files.walk(Paths.get(fileChooser.getCurrentDirectory().getCanonicalPath()));
            {

                List<String> pathList = paths
                        .parallel()
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".pdf"))
                        .filter(path -> !path.getFileName().toString().startsWith("."))
                        //.peek(System.out::println)
                        .map(p -> {
                            if (Files.isDirectory(p)) {
                                return p.toString() + "/";
                            }
                            return p.toString();
                        })
                        .collect(Collectors.toList());
                printToXml(pathList, b);
                /*for (String f:pathList){
                        System.out.printf("%s"+")"+"%s" +"%d"+"\n",i++, f, efficientPDFPageCount(f));
               }*/

            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UncheckedIOException ex) {
            System.out.println("Acces Denied");
        }

    }

    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        //fileChooser.setApproveButtonText("test");
        String action = evt.getActionCommand();
        if (action.equals(JFileChooser.CANCEL_SELECTION)) {
            System.exit(0);
        } //
        else if (action.equals(JFileChooser.APPROVE_SELECTION)) {
            fileChooser.setCurrentDirectory(fileChooser.getSelectedFile().getAbsoluteFile());
             if (fileChooser.getFileFilter().getDescription().equals("HTML File")){
            b = false;
            System.out.println(fileChooser.getFileFilter().getDescription());
        }
        }
        Runnable runnable = ()
                -> {
            try {
                printPages();
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        //  openInBrowser(new File(System.getProperty("user.home")+ System.getProperty("file.separator")+"test1.html"));
        /* { UIDefaults defaults = UIManager.getDefaults();
        System.out.println(defaults.size()+ " properties");
        for (Enumeration e = defaults.keys();
        e.hasMoreElements();) {
        Object key = e.nextElement();
        System.out.println(key + " = " + defaults.get(key));
        }}*/

    }//GEN-LAST:event_fileChooserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

}
