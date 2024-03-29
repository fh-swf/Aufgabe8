package de.fhswf.verwaltung;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.standbysoft.component.date.swing.JDatePicker;

public class DialogFahrzeug extends JDialog
        implements ActionListener {
    /** Version. */
    private static final long serialVersionUID = 1L;

    protected JTextField      kennzeichen;

    // protected JComboBox key3;

    protected JDatePicker     erstzulassung;

    private JButton           deleteButton;
    private JButton           saveButton;
    private JButton           exitButton;

    private int               row              = -1;

    private JDialog           dialog;

    /**
     * Bastelt die GUI fuers Hauptfenster.
     */
    public DialogFahrzeug(String title, MainWindow parent, FahrzeugTableModel model)
    {
        super();
        if (title == null)
            throw new IllegalArgumentException("Title must not be null!");

        setTitle(title);
        final int width = 480;
        final int height = 280;
        dialog = this;

        JFrame.setDefaultLookAndFeelDecorated(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = getContentPane();

        JPanel vertBox = new JPanel();
        vertBox.setLayout(new BoxLayout(vertBox, BoxLayout.PAGE_AXIS));

        JPanel horiBox = new JPanel();

        horiBox = new JPanel();
        horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
        JLabel kennzLabel = new JLabel(" Kennzeichen: ");
        kennzLabel.setPreferredSize(new Dimension(100, 10));
        horiBox.add(kennzLabel);
        kennzeichen = new JTextField();
        horiBox.add(kennzeichen);
        vertBox.add(horiBox);

        horiBox = new JPanel();
        horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
        JLabel erstzLabel = new JLabel(" Erstzulassung: ");
        erstzLabel.setPreferredSize(new Dimension(100, 10));
        horiBox.add(erstzLabel);
        erstzulassung = new JDatePicker();
        horiBox.add(erstzulassung);
        vertBox.add(horiBox);
        
        deleteButton = new JButton("Loeschen");
//        deleteButton.addActionListener(new DelNoteAction(this, parent, model));
        deleteButton.setEnabled(false);

        saveButton = new JButton("Speichern");
//        saveButton.addActionListener(new AddNoteAction(this, parent, model));
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

    public void loadFahrzeug(Fahrzeug fahrzeug)
    {
        kennzeichen.setText(fahrzeug.getKennzeichen());
//        key1.setSelectedIndex(fach.getNote1());
        erstzulassung.setSelectedDate(fahrzeug.getErstzulassung());
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(int row) {
        // System.out.println(row.toString());
        this.row = row;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}
