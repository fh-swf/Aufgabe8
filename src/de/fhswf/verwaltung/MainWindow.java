package de.fhswf.verwaltung;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;


/**
 * Hauptfenster der Notenverwaltung
 * 
 *
 * @author Michael Rockstein & Philipp Schäfer
 * @version 1.0
 */
public class MainWindow extends JFrame
{
   /** Version. */
   private static final long serialVersionUID = 1L;

   MainWindow frame;
   /**
    * Bastelt die GUI fürs Hauptfenster.
    */
   public MainWindow()
   {
      super("MainWindow");

//      final int width = 1024;
//      final int height = 700;
      frame = this;
      JFrame.setDefaultLookAndFeelDecorated(true);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent we){
//            frame.saveAllDatabases();
            System.exit(0);
         }
       });
      
   }

   /**
    * Hauptfunktion des Programms.
    * 
    * @param args
    *           Argumente - derzeit ungenutzt.
    */
   public static void main(String[] args)
   {
      // Display the window in a thread safe way.
//	   MainWindow frame = new MainWindow();
//      new JFrameShower(frame);
//      try
//      {  
//      }
//      catch (FileNotFoundException e)
//      {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      catch (IOException e)
//      {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      catch (NumberFormatException nfe)
//      {
//         // ignore
//      }
   }

}
