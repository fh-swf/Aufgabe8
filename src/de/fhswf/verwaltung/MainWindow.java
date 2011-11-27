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
      JMenuItem actionNewNote;
      JMenuItem actionKwList;
      
      menuBar = new JMenuBar();
      menuFile = new JMenu("Datei");
      menuFile.setMnemonic(KeyEvent.VK_D);
      menuFile.getAccessibleContext().setAccessibleDescription("Datei-Menü");
      menuBar.add(menuFile);

      menuHelp = new JMenu("Hilfe");
      menuHelp.setMnemonic(KeyEvent.VK_H);
      menuHelp.getAccessibleContext().setAccessibleDescription("Hilfe-Menü");
      menuBar.add(menuHelp);

      actionNewNote = new JMenuItem("Neuen Fahrer...", KeyEvent.VK_N);
      actionNewNote.getAccessibleContext().setAccessibleDescription(
            "Neuen Fahrer anlegen.");
//      actionNewNote.addActionListener(new ActionListener()
//      {
//         @Override
//         public void actionPerformed(ActionEvent e)
//         {
//              new NoteDialog("Fach hinzufügen", frame, tableData).setRow(-1);
//         }
//      });
      menuFile.add(actionNewNote);

      
      actionKwList = new JMenuItem("Neues Fahrzeug...", KeyEvent.VK_L);
      actionKwList.getAccessibleContext().setAccessibleDescription(
            "Neues Fahrzeug anlegen.");
//      actionKwList.addActionListener(new Aktualisieren(frame, tableData));
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

      JTable table = new JTable();
//      table.setModel(tableData);
//      table.addMouseListener(new TableClickListener(table, frame, tableData));
      JScrollPane tableScrollPane = new JScrollPane(table);
      tableScrollPane
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      tableScrollPane.setPreferredSize(new Dimension(320, 400));
      tableScrollPane.setBorder(BorderFactory
            .createCompoundBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Notenübersicht"), BorderFactory
                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPane
                  .getBorder()));
      
      
      JPanel hBox = new JPanel();
      hBox.setLayout(new BoxLayout(hBox, BoxLayout.PAGE_AXIS));
      
      hBox.add(tableScrollPane);
            
//      JPanel vBox = new JPanel();
//      
//      vBox = new JPanel();
//      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
//      JLabel bachelorLabel = new JLabel(" Bachelor Arbeit:  ");
//      bachelorLabel.setPreferredSize(new Dimension(100, 30));
//      vBox.add(bachelorLabel);
//	  bachelor = new JComboBox(noteStrings);
//	  bachelor.setEditable(false);
//	  bachelor.setSelectedIndex(0);
//	  bachelor.setPreferredSize(new Dimension(10, 10));
//	  bachelor.setMaximumSize(new Dimension(50, 30));
////	  bachelor.addActionListener(this);
//      vBox.add(bachelor);
//      
//      hBox.add(vBox);
//      
//      vBox = new JPanel();
//      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
//      JLabel cololabel = new JLabel(" Kolloquium:          ");
//      cololabel.setPreferredSize(new Dimension(100, 30));
//      vBox.add(cololabel);
//	  colo = new JComboBox(noteStrings);
//	  colo.setEditable(false);
//	  colo.setSelectedIndex(0);
//	  colo.setPreferredSize(new Dimension(10, 10));
//	  colo.setMaximumSize(new Dimension(50, 30));
////	  colo.addActionListener(this);
//      vBox.add(colo);
//      
//      hBox.add(vBox);
//      
//      JPanel hBox1 = new JPanel();
//      hBox1.setLayout(new BoxLayout(hBox1, BoxLayout.PAGE_AXIS));
//      
//      JLabel qLabel = new JLabel(" Notendurchschnitt: ");
//      qLabel.setPreferredSize(new Dimension(100, 10));
//      hBox1.add(qLabel);
//      
//      noteText = new JPopupTextField();
//      noteText.setPreferredSize(new Dimension(100, 30));
//      noteText.setMaximumSize(new Dimension(100, 30));
//      noteText.setEditable(false);
//      hBox1.add(noteText);
//      
//      JLabel eLabel = new JLabel(" ECTS: ");
//      eLabel.setPreferredSize(new Dimension(100, 10));
//      hBox1.add(eLabel);
//      
//      ectsText = new JPopupTextField();
//      ectsText.setPreferredSize(new Dimension(100, 30));
//      ectsText.setMaximumSize(new Dimension(100, 30));
//      ectsText.setEditable(false);
//      hBox1.add(ectsText);
//      
//      hBox1.setSize(new Dimension(250, 100));
//      hBox1.setMaximumSize(new Dimension(250, 250));
//      
//      vBox = new JPanel();
//      vBox.setLayout(new BoxLayout(vBox, BoxLayout.LINE_AXIS));
//      
//      vBox.add(hBox);
//      vBox.add(hBox1, BorderLayout.EAST);
//      
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
