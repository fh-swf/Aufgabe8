package de.fhswf.verwaltung;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * Hauptfenster der Fuhrparkverwaltung
 * 
 *
 * @author Michael Rockstein & Philipp Schaefer
 * @version 1.0
 */
public class MainWindow extends JFrame
{
   /** Version. */
   private static final long serialVersionUID = 1L;
   
   public static  FahrerTableModel tableDataFahrer = new FahrerTableModel();
   public static  FahrzeugTableModel tableDataFahrzeug = new FahrzeugTableModel();

   MainWindow frame;
   /**
    * Bastelt die GUI fuers Hauptfenster.
    */
   public MainWindow()
   {
      super("MainWindow");

      final int width = 1024;
      final int height = 700;
      frame = this;
      JFrame.setDefaultLookAndFeelDecorated(true);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent we){
//            frame.saveAllDatabases();
            System.exit(0);
         }
       });
      
      
      JMenuBar menuBar;
      JMenu menuFile;
      JMenu menuHelp;
      JMenuItem actionExit;
      JMenuItem actionInfo;
      JMenuItem actionNewFahrer;
      JMenuItem actionKwList;
      
      menuBar = new JMenuBar();
      menuFile = new JMenu("Datei");
      menuFile.setMnemonic(KeyEvent.VK_D);
      menuFile.getAccessibleContext().setAccessibleDescription("Datei-Menue");
      menuBar.add(menuFile);

      menuHelp = new JMenu("Hilfe");
      menuHelp.setMnemonic(KeyEvent.VK_H);
      menuHelp.getAccessibleContext().setAccessibleDescription("Hilfe-Menue");
      menuBar.add(menuHelp);

      actionNewFahrer = new JMenuItem("Neuen Fahrer...", KeyEvent.VK_N);
      actionNewFahrer.getAccessibleContext().setAccessibleDescription(
            "Neuen Fahrer anlegen.");
      actionNewFahrer.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
              new DialogFahrer("Fahrer hinzufuegen", frame, tableDataFahrer).setRow(-1);
         }
      });
      menuFile.add(actionNewFahrer);

      
      actionKwList = new JMenuItem("Neues Fahrzeug...", KeyEvent.VK_L);
      actionKwList.getAccessibleContext().setAccessibleDescription(
            "Neues Fahrzeug anlegen.");
      actionKwList.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent e)
          {
               new DialogFahrzeug("Fahrzeug hinzufuegen", frame, tableDataFahrzeug).setRow(-1);
          }
       });
      menuFile.add(actionKwList);
      
      actionExit = new JMenuItem("Beenden", KeyEvent.VK_E);
      actionExit.getAccessibleContext().setAccessibleDescription(
            "Beendet die Anwendung");
      actionExit.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
//            frame.saveAllDatabases();
            System.exit(0);
         }
      });
      menuFile.add(actionExit);

      actionInfo = new JMenuItem("Info", KeyEvent.VK_I);
      actionInfo.getAccessibleContext().setAccessibleDescription(
            "About Dialog");
      actionInfo.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            JDialog aboutDialog = new JDialog();
            JLabel aboutDialogLabel = new JLabel(
                  "Fahrzeugverwaltung - Version 0.8b", JLabel.CENTER);
            aboutDialog.add(aboutDialogLabel);
            aboutDialog.setSize(320, 200);
            aboutDialog.setVisible(true);
         }
      });
      menuHelp.add(actionInfo);

      setJMenuBar(menuBar);
      
      Container c = getContentPane();

      JTable tableDriver = new JTable();
//      table.setModel(tableData);
//      table.addMouseListener(new TableClickListener(table, frame, tableData));
      JScrollPane tableScrollPaneDriver = new JScrollPane(tableDriver);
      tableScrollPaneDriver
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      tableScrollPaneDriver.setPreferredSize(new Dimension(320, 400));
      tableScrollPaneDriver.setBorder(BorderFactory
            .createCompoundBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Fahreruebersicht"), BorderFactory
                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneDriver
                  .getBorder()));
      
      JTable tableCar = new JTable();
//    table.setModel(tableData);
//    table.addMouseListener(new TableClickListener(table, frame, tableData));
    JScrollPane tableScrollPaneCar = new JScrollPane(tableCar);
    tableScrollPaneCar
          .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    tableScrollPaneCar.setPreferredSize(new Dimension(320, 400));
    tableScrollPaneCar.setBorder(BorderFactory
          .createCompoundBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Fahrzeuguebersicht"), BorderFactory
                      .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneCar
                .getBorder()));
    
          
      JPanel hBox = new JPanel();
      hBox.setLayout(new BoxLayout(hBox, BoxLayout.LINE_AXIS));
      
      hBox.add(tableScrollPaneDriver);
      hBox.add(tableScrollPaneCar);
      
      c.add(hBox);
            
      setSize(width, height);
      setVisible(true);
   }

   /**
    * Hauptfunktion des Programms.
    * 
    * @param args
    *           Argumente - derzeit ungenutzt.
    */
   public static void main(String[] args)
   {
//       Display the window in a thread safe way.
	   MainWindow frame = new MainWindow();
       new JFrameShower(frame);
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
