package de.fhswf.verwaltung;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class TableModelFahrzeug extends AbstractTableModel {
	   private static final long serialVersionUID = 1L;
	   private String[] headers = {"Kennzeichen", "Erstzulassung" };

	   @SuppressWarnings({ "rawtypes" })
	   Class[] columnClasses = { String.class, String.class };

	   Vector<RowEntryCar> data = new Vector<RowEntryCar>();

	   @Override
	   public int getColumnCount() {
	       return headers.length;
	   }

	   @Override
	   public int getRowCount() {
	       return data.size();
	   }

	   @SuppressWarnings({ "unchecked", "rawtypes" })
	   public Class getColumnClass(int c) {
	       return columnClasses[c];
	   }

	   public String getColumnName(int c) {
	       return headers[c];
	   }

	   public boolean isCellEditable(int r, int c) {
	       return false;
	   }

	   @Override
	   public Object getValueAt(int r, int c) {
	       RowEntryCar e = data.get(r);
	       switch (c) {
	       case 0:
	           return e.getKennzeichen();
	       case 1:
	           return e.getErstzulassung();
	       default:
	           return null;
	       }
	   }

	   public void setValueAt(Object value, int r, int c) {
	   }

	   public void addRow(Fahrzeug fahrzeug, MainWindow frame) {
	       int ID = getRowCount();
	    
	       data.add(ID, new RowEntryCar(fahrzeug.getKennzeichen(), fahrzeug.getErstzulassung()));
	       fireTableRowsInserted(ID, ID); 
	   }

	   public void removeRowAt(int r) {
	       data.removeElementAt(r);
	       fireTableRowsDeleted(r, r);
	   }

	   public void editRowAt(Fahrzeug fahrzeug, MainWindow frame, int r) {
		   data.removeElementAt(r);
	       fireTableRowsDeleted(r, r);
		   data.insertElementAt(new RowEntryCar(fahrzeug.getKennzeichen(), fahrzeug.getErstzulassung() ), r);
	       fireTableRowsInserted(r, r);
	   }
	   
	   
	   public void clear() {
	      int rowCount = data.size();
	      data.clear();
	      fireTableRowsDeleted(0, rowCount);
	   }
	}