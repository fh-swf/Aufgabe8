package de.fhswf.verwaltung;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	   protected JComboBox fKlasse;
	   protected JDatePicker fDatum;
	   
	   private JButton deleteButton;
	   private JButton saveButton;
	   private JButton exitButton;
	   
	   private String[] fKlasseStrings= { "A1", "A", "B", "BE", "C1", "C1E", "CE", "D", "M" };
	   
	   private int row = -1;
	   
	   private JDialog dialog;
	   /**
	    * Bastelt die GUI fürs Hauptfenster.
	    */
	   public DialogFahrer (String title, MainWindow parent, FahrerTableModel model)
	   {
	      super();
	      if (title == null)
	         throw new IllegalArgumentException("Title must not be null!");
	      
//	      fKlasseStrings = parent.noteStrings;

	      setTitle(title);
	      final int width = 480;
	      final int height = 280;
	      dialog = this;
	      
	      JFrame.setDefaultLookAndFeelDecorated(true);

	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      Container c = getContentPane();

	      JPanel vertBox = new JPanel();
	      vertBox.setLayout(new BoxLayout(vertBox, BoxLayout.PAGE_AXIS));
	            
	      JLabel label = new JLabel();
	      label.setPreferredSize(new Dimension(100, 10));

	      JPanel horiBox = new JPanel();
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel nameLabel = new JLabel(" Fahrer Name: ");
	      nameLabel.setPreferredSize(new Dimension(100, 10));
	      horiBox.add(nameLabel);
	      nameFahrer = new JTextField();
	      horiBox.add(nameFahrer);
	      vertBox.add(horiBox);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel ectslabel = new JLabel(" ECTS Punkte: ");
	      ectslabel.setPreferredSize(new Dimension(100, 10));
	      horiBox.add(ectslabel);
	      fKlasse = new JComboBox(fKlasseStrings);
	      fKlasse.setEditable(false);
	      fKlasse.setSelectedIndex(0);
	      fKlasse.addActionListener(this);
	      horiBox.add(fKlasse);
	      vertBox.add(horiBox);

	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel fSeitLabel = new JLabel(" Fuehrerschein Seit: ");
	      fSeitLabel.setPreferredSize(new Dimension(100, 10));
	      horiBox.add(fSeitLabel);
	      fDatum = new JDatePicker();
	      horiBox.add(fDatum);
	      vertBox.add(horiBox);
	       
	      deleteButton = new JButton("Löschen");
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
	      
	      // Noten und Buttons
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
	      fKlasse.setSelectedIndex(fahrer.getFuehrerscheinklasse());
	      fDatum.setSelectedDate(fahrer.getFuehrerscheinSeit());
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
