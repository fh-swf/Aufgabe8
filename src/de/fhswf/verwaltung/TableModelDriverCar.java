package de.fhswf.verwaltung;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class TableModelDriverCar extends AbstractTableModel {
	   private static final long serialVersionUID = 1L;
	   private String[] headers = {"Fahrer", "Fahrzeug" };

	   @SuppressWarnings({ "rawtypes" })
	   Class[] columnClasses = { String.class, String.class };

	   Vector<RowEntryDriverCar> data = new Vector<RowEntryDriverCar>();

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
	       RowEntryDriverCar e = data.get(r);
	       switch (c) {
	       case 0:
	           return e.getFahrer();
	       case 1:
	           return e.getFahrzeug();
	       default:
	           return null;
	       }
	   }

	   public void setValueAt(Object value, int r, int c) {
	   }

	   public void addRow(DriverCar driverCar, MainWindow frame) {
	       int ID = getRowCount();
	    
	       data.add(ID, new RowEntryDriverCar(driverCar.getDriverId().toString() + " " + frame.getFahrer(driverCar.getDriverId()).getName(), driverCar.getCarId().toString() + " " + frame.getFahrzeug(driverCar.getCarId()).getKennzeichen()));
	       fireTableRowsInserted(ID, ID); 
	   }

	   public void removeRowAt(int r) {
	       data.removeElementAt(r);
	       fireTableRowsDeleted(r, r);
	   }

	   public void clear() {
	      int rowCount = data.size();
	      data.clear();
	      fireTableRowsDeleted(0, rowCount);
	   }
	}