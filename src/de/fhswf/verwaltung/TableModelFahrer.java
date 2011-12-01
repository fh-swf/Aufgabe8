package de.fhswf.verwaltung;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class TableModelFahrer extends AbstractTableModel {
	   private static final long serialVersionUID = 1L;
	   private String[] headers = {"Fahrer", "Fuehrerscheinklassen", "Fuehrerscheindatum" };

	   @SuppressWarnings({ "rawtypes" })
	   Class[] columnClasses = { String.class, String.class, String.class };

	   Vector<RowEntryDriver> data = new Vector<RowEntryDriver>();

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
	       RowEntryDriver e = data.get(r);
	       switch (c) {
	       case 0:
	           return e.getName();
	       case 1:
	           return e.getKlasse();
	       case 2:
	           return e.getDatum();
	       default:
	           return null;
	       }
	   }

	   public void setValueAt(Object value, int r, int c) {
	   }

	   public void addRow(Fahrer fahrer, MainWindow frame) {
	       int ID = getRowCount();
	    
	       data.add(ID, new RowEntryDriver(fahrer.getName(), fahrer.getFueKlasse(), fahrer.getFueSeit()));
	       fireTableRowsInserted(ID, ID); 
	   }

	   public void removeRowAt(int r) {
	       data.removeElementAt(r);
	       fireTableRowsDeleted(r, r);
	   }

	   public void editRowAt(Fahrer fahrer, MainWindow frame, int r) {
		   data.removeElementAt(r);
	       fireTableRowsDeleted(r, r);
		   data.insertElementAt(new RowEntryDriver(fahrer.getName(), fahrer.getFueKlasse(), fahrer.getFueSeit() ), r);
	       fireTableRowsInserted(r, r);
	   }
	   
	   
	   public void clear() {
	      int rowCount = data.size();
	      data.clear();
	      fireTableRowsDeleted(0, rowCount);
	   }
	}