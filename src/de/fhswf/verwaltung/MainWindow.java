package de.fhswf.verwaltung;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

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
   
   public Vector<Fahrer> fahrerMap = new Vector<Fahrer>();
   public Vector<Fahrer> fahrerMapDel = new Vector<Fahrer>();
   public Vector<Fahrzeug> fahrzeugMap = new Vector<Fahrzeug>();
   public Vector<Fahrzeug> fahrzeugMapDel = new Vector<Fahrzeug>();
   public Vector<DriverCar> driverCarMap = new Vector<DriverCar>();
   public Vector<DriverCar> driverCarMapDel = new Vector<DriverCar>();
   public TableModelFahrer tableDataFahrer = new TableModelFahrer();
   public TableModelFahrzeug tableDataFahrzeug = new TableModelFahrzeug();
   public TableModelDriverCar tableDataDriverCar = new TableModelDriverCar();

   MainWindow frame;
   Database database = new Database();
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
            database.write(frame);
            database.shutdown();
            System.exit(0);
         }
       });
      
      
      JMenuBar menuBar;
      JMenu menuFile;
      JMenu menuHelp;
      JMenuItem actionExit;
      JMenuItem actionInfo;
      JMenuItem actionNewDriver;
      JMenuItem actionNewCar;
      JMenuItem actionNewDriverCar;
      JMenuItem actionLoadDb;
      JMenuItem actionWriteDb;
      
      menuBar = new JMenuBar();
      menuFile = new JMenu("Datei");
      menuFile.setMnemonic(KeyEvent.VK_D);
      menuFile.getAccessibleContext().setAccessibleDescription("Datei-Menue");
      menuBar.add(menuFile);

      menuHelp = new JMenu("Hilfe");
      menuHelp.setMnemonic(KeyEvent.VK_H);
      menuHelp.getAccessibleContext().setAccessibleDescription("Hilfe-Menue");
      menuBar.add(menuHelp);

      actionNewDriver = new JMenuItem("Neuen Fahrer...", KeyEvent.VK_N);
      actionNewDriver.getAccessibleContext().setAccessibleDescription(
            "Neuen Fahrer anlegen.");
      actionNewDriver.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
              new DialogFahrer("Fahrer hinzufuegen", frame, tableDataFahrer).setRow(-1);
         }
      });
      menuFile.add(actionNewDriver);

      
      actionNewCar = new JMenuItem("Neues Fahrzeug...", KeyEvent.VK_L);
      actionNewCar.getAccessibleContext().setAccessibleDescription(
            "Neues Fahrzeug anlegen.");
      actionNewCar.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent e)
          {
               new DialogFahrzeug("Fahrzeug hinzufuegen", frame, tableDataFahrzeug).setRow(-1);
          }
       });
      menuFile.add(actionNewCar);
      
      
      actionNewDriverCar = new JMenuItem("Neue Relation...", KeyEvent.VK_L);
      actionNewDriverCar.getAccessibleContext().setAccessibleDescription(
            "Neues Relation anlegen.");
      actionNewDriverCar.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent e)
          {
               new DialogDriverCar("Relation hinzufuegen", frame, tableDataDriverCar).setRow(-1);
          }
       });
      menuFile.add(actionNewDriverCar);
      
      
      actionLoadDb = new JMenuItem("Laden...", KeyEvent.VK_L);
      actionLoadDb.getAccessibleContext().setAccessibleDescription(
            "Aus Datenbank Laden.");
      actionLoadDb.addActionListener(new ActionListener()
      {
    	  @Override
          public void actionPerformed(ActionEvent e)
          {
    		   fahrerMap.clear();
    		   fahrerMapDel.clear();
    		   fahrzeugMap.clear();
    		   fahrzeugMapDel.clear();
    		   driverCarMap.clear();
    		   driverCarMapDel.clear();
    		   tableDataFahrer.clear();
    		   tableDataFahrzeug.clear();
    		   tableDataDriverCar.clear();
         	database.load(frame);
          }
       });
      menuFile.add(actionLoadDb);
      
      
      actionWriteDb = new JMenuItem("Schreiben...", KeyEvent.VK_L);
      actionWriteDb.getAccessibleContext().setAccessibleDescription(
            "In Datenbank schreiben.");
      actionWriteDb.addActionListener(new ActionListener()
      {
    	  @Override
          public void actionPerformed(ActionEvent e)
          {
         	database.write(frame);
          }
       });
      menuFile.add(actionWriteDb);
      
      
      actionExit = new JMenuItem("Beenden", KeyEvent.VK_E);
      actionExit.getAccessibleContext().setAccessibleDescription(
            "Beendet die Anwendung");
      actionExit.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
        	database.write(frame);
            database.shutdown();
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
      tableDriver.setModel(tableDataFahrer);
      tableDriver.addMouseListener(new TableClickListenerDriver(tableDriver, frame, tableDataFahrer));
      JScrollPane tableScrollPaneDriver = new JScrollPane(tableDriver);
      tableScrollPaneDriver
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      tableScrollPaneDriver.setPreferredSize(new Dimension(400, 400));
      tableScrollPaneDriver.setBorder(BorderFactory
            .createCompoundBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Fahreruebersicht"), BorderFactory
                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneDriver
                  .getBorder()));
      
      JTable tableCar = new JTable();
      tableCar.setModel(tableDataFahrzeug);
      tableCar.addMouseListener(new TableClickListenerCar(tableCar, frame, tableDataFahrzeug));
      JScrollPane tableScrollPaneCar = new JScrollPane(tableCar);
      tableScrollPaneCar
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      tableScrollPaneCar.setPreferredSize(new Dimension(240, 400));
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
	   Database database = new Database();
	   database.load(frame);
       new JFrameShower(frame);

   }
   
   public void addFahrer(Fahrer fahrer)
   {
      fahrerMap.add(fahrer);
   }
   
   public void editFahrer(Fahrer fahrer, int row)
   {
 	  fahrerMap.remove(row);
 	  fahrerMap.insertElementAt(fahrer, row);
   }
   public void delFahrer(int row)
   {
	   fahrerMapDel.add(fahrerMap.get(row));
	   fahrerMap.remove(row);
   }
   
   public Fahrer getFahrer(int index)
   {
      return fahrerMap.get(index);
   }
   
   public Vector<Fahrer> getFahrerMap()
   {
 	  return fahrerMap;
   }
   
   
   public void addFahrzeug(Fahrzeug fahrzeug)
   {
	   fahrzeugMap.add(fahrzeug);
   }
   
   public void editFahrzeug(Fahrzeug fahrzeug, int row)
   {
	  fahrzeugMap.remove(row);
	  fahrzeugMap.insertElementAt(fahrzeug, row);
   }
   public void delFahrzeug(int row)
   {
	   fahrzeugMapDel.add(fahrzeugMap.get(row));
	   fahrzeugMap.remove(row);
   }
   
   public Fahrzeug getFahrzeug(int index)
   {
      return fahrzeugMap.get(index);
   }
   
   public Vector<Fahrzeug> getFahrzeugMap()
   {
 	  return fahrzeugMap;
   }
   
   
   public void addDriverCar(DriverCar driverCar)
   {
	   driverCarMap.add(driverCar);
   }
   
   public void delDriverCar(int row)
   {
	   driverCarMapDel.add(driverCarMap.get(row));
	   driverCarMap.remove(row);
   }
   
   public DriverCar getDriverCar(int index)
   {
      return driverCarMap.get(index);
   }
   
   public Vector<DriverCar> getDriverCarMap()
   {
 	  return driverCarMap;
   }

}
