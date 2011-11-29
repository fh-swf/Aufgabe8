package de.fhswf.verwaltung;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.standbysoft.component.date.swing.JDatePicker;

/**
 * Hauptfenster der Fahrerverwaltung
 * 
 *
 * @author Michael Rockstein & Philipp Schaefer
 * @version 1.0
 */

public class DialogFahrer extends JDialog
							implements ActionListener{
	
	/** Version. */
	   private static final long serialVersionUID = 1L;

	   protected JTextField nameFahrer;
	   protected JCheckBox A1, A, B, C1, C, D1, D, BE, C1E, CE, D1E, DE, M, L, TS;
	   protected JDatePicker fDatum;
	   
	   private JButton deleteButton;
	   private JButton saveButton;
	   private JButton exitButton;
	   
	   private  FahrzeugTableModel tableDataFahrzeug = new FahrzeugTableModel();
	   
	   private String[] fKlasseStrings= { "A1", "A", "B", "C1", "C", "D1", "D", "BE", "C1E", "CE", "D1E", "DE", "M", "L", "T/S" };
	   
	   private int row = -1;
	   
	   private JDialog dialog;
	   
	   
	   /**
	    * Bastelt die GUI f�rs Hauptfenster.
	    */
	   public DialogFahrer (String title, MainWindow parent, FahrerTableModel model)
	   {
	      super();
	      if (title == null)
	         throw new IllegalArgumentException("Title must not be null!");
	      
//	      fKlasseStrings = parent.noteStrings;

	      setTitle(title);
	      final int width = 480;
	      final int height = 420;
	      dialog = this;
	      
	      JFrame.setDefaultLookAndFeelDecorated(true);

	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      Container c = getContentPane();

	      JPanel vertBox = new JPanel();
	      vertBox.setLayout(new BoxLayout(vertBox, BoxLayout.PAGE_AXIS));

	      JPanel vertBox1 = new JPanel();
	      vertBox1.setLayout(new BorderLayout());
	      JPanel vertBox2 = new JPanel();
	      vertBox2.setLayout(new BorderLayout());
	      
	      JPanel horiBox = new JPanel();
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel nameLabel = new JLabel(" Fahrer Name: ");
	      nameLabel.setPreferredSize(new Dimension(140, 30));
	      nameLabel.setMaximumSize(new Dimension(140, 30));
	      horiBox.add(nameLabel);
	      nameFahrer = new JTextField();
	      horiBox.add(nameFahrer);
	      vertBox.add(horiBox);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      A1 = new JCheckBox(fKlasseStrings[0] + "  ");
	      A1.setMnemonic(KeyEvent.VK_A);
	      A1.setSelected(false);
	      horiBox.add(A1);
	      
	      A = new JCheckBox(fKlasseStrings[1] + "   ");
	      A.setMnemonic(KeyEvent.VK_A);
	      A.setSelected(false);
	      horiBox.add(A);
	      vertBox1.add(horiBox, BorderLayout.NORTH);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      B = new JCheckBox(fKlasseStrings[2] + "    ");
	      B.setMnemonic(KeyEvent.VK_B);
	      B.setSelected(false);
	      horiBox.add(B);
	      
	      BE = new JCheckBox(fKlasseStrings[7] + "  ");
	      BE.setMnemonic(KeyEvent.VK_B);
	      BE.setSelected(false);
	      horiBox.add(BE);
	      vertBox1.add(horiBox, BorderLayout.WEST);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      C1 = new JCheckBox(fKlasseStrings[3] + "  ");
	      C1.setMnemonic(KeyEvent.VK_C);
	      C1.setSelected(false);
	      horiBox.add(C1);

	      C1E = new JCheckBox(fKlasseStrings[8]);
	      C1E.setMnemonic(KeyEvent.VK_C);
	      C1E.setSelected(false);
	      horiBox.add(C1E);

	      C = new JCheckBox(fKlasseStrings[4] + "    ");
	      C.setMnemonic(KeyEvent.VK_C);
	      C.setSelected(false);
	      horiBox.add(C);

	      CE = new JCheckBox(fKlasseStrings[9] + "  ");
	      CE.setMnemonic(KeyEvent.VK_C);
	      CE.setSelected(false);
	      horiBox.add(CE);
	      vertBox1.add(horiBox, BorderLayout.SOUTH);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      D1 = new JCheckBox(fKlasseStrings[5] + "  ");
	      D1.setMnemonic(KeyEvent.VK_D);
	      D1.setSelected(false);
	      horiBox.add(D1);

	      D1E = new JCheckBox(fKlasseStrings[10]);
	      D1E.setMnemonic(KeyEvent.VK_D);
	      D1E.setSelected(false);
	      horiBox.add(D1E);

	      D = new JCheckBox(fKlasseStrings[6] + "    ");
	      D.setMnemonic(KeyEvent.VK_D);
	      D.setSelected(false);
	      horiBox.add(D);

	      DE = new JCheckBox(fKlasseStrings[11] + "  ");
	      DE.setMnemonic(KeyEvent.VK_D);
	      DE.setSelected(false);
	      horiBox.add(DE);
	      vertBox2.add(vertBox1, BorderLayout.NORTH);
	      vertBox2.add(horiBox, BorderLayout.WEST);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      M = new JCheckBox(fKlasseStrings[12] + "    ");
	      M.setMnemonic(KeyEvent.VK_M);
	      M.setSelected(false);
	      horiBox.add(M);

	      L = new JCheckBox(fKlasseStrings[13] + "    ");
	      L.setMnemonic(KeyEvent.VK_L);
	      L.setSelected(false);
	      horiBox.add(L);

	      TS = new JCheckBox(fKlasseStrings[14]);
	      TS.setMnemonic(KeyEvent.VK_T);
	      TS.setSelected(false);
	      horiBox.add(TS);
	      vertBox2.add(horiBox, BorderLayout.SOUTH);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BorderLayout());
	      JLabel a1Label = new JLabel(" Fuehrerscheinklassen: ");
	      a1Label.setPreferredSize(new Dimension(140, 30));
	      a1Label.setMaximumSize(new Dimension(140, 30));
	      horiBox.add(a1Label, BorderLayout.WEST);
//	      horiBox.add(vertBox1, BorderLayout.WEST);
	      horiBox.add(vertBox2, BorderLayout.CENTER);
	      vertBox.add(horiBox);

	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel fSeitLabel = new JLabel(" Fuehrerschein Seit: ");
	      fSeitLabel.setPreferredSize(new Dimension(140, 30));
	      fSeitLabel.setMaximumSize(new Dimension(140, 30));
	      horiBox.add(fSeitLabel);
	      fDatum = new JDatePicker();
	      horiBox.add(fDatum);
	      vertBox.add(horiBox);
	      
	      JTable tableCar = new JTable();
//	      tableCar.setModel(tableData);
//	      tableCar.addMouseListener(new TableClickListener(tableCar, frame, tableDataFahrzeug));
	      JScrollPane tableScrollPaneCar = new JScrollPane(tableCar);
	      tableScrollPaneCar
	            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      tableScrollPaneCar.setPreferredSize(new Dimension(400, 150));
	      tableScrollPaneCar.setMaximumSize(new Dimension(440, 150));
	      tableScrollPaneCar.setBorder(BorderFactory
	            .createCompoundBorder(BorderFactory.createCompoundBorder(
	                  BorderFactory.createTitledBorder("Fahrzeuguebersicht"), BorderFactory
	                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneCar
	                  .getBorder()));
	      
	      vertBox.add(tableScrollPaneCar);
	       
	      deleteButton = new JButton("L�schen");
//	      deleteButton.addActionListener(new DelNoteAction(this, parent, model));
	      deleteButton.setEnabled(false);

	      saveButton = new JButton("Speichern");
//	      saveButton.addActionListener(new AddNoteAction(this, parent, model));
	      saveButton.setEnabled(true);

	      exitButton = new JButton("Abbrechen");
	      exitButton.addActionListener(new ActionListener()
	      {
	         @Override
	         public void actionPerformed(ActionEvent e)
	         {
	            dialog.dispose();
	         }
	      });
	      exitButton.setEnabled(true);

	      JPanel buttonBox = new JPanel();
	      buttonBox.setLayout(new BoxLayout(buttonBox, BoxLayout.LINE_AXIS));
	      buttonBox.add(deleteButton);
	      buttonBox.add(saveButton);
	      buttonBox.add(exitButton);

	      vertBox.add(buttonBox, BorderLayout.SOUTH);
	      
	      // Fahrer, Tabelle und Buttons
	      c.add(vertBox, BorderLayout.NORTH);
	      
	      setSize(width, height);
	      setVisible(true);
	   }

	   public void setDeleteEnabled(boolean state)
	   {
	      deleteButton.setEnabled(state);
	   }
	   
	   public void setSaveEnabled(boolean state)
	   {
	      saveButton.setEnabled(state);
	   }
	   
	   public void setExitEnabled(boolean state)
	   {
	      exitButton.setEnabled(state);
	   }  
	 
	   public void loadFahrer(Fahrer fahrer)
	   {
	      nameFahrer.setText(fahrer.getName());
//	      fKlasse.setSelectedIndex(fahrer.getFueKlasse());
	      fDatum.setSelectedDate(fahrer.getFueSeit());
	   }

	public Integer getRow() {
		return row;
	}

	public void setRow(int row) {
//		System.out.println(row.toString());
		this.row = row;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
