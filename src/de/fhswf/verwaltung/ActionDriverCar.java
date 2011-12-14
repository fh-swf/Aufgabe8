package de.fhswf.verwaltung;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class ActionDriverCar implements Action {

	DialogDriverCar source;
	MainWindow parent;
	TableModelDriverCar model;
	String methode;

	public ActionDriverCar(String string, DialogDriverCar dialogDriverCar,
			MainWindow parent, TableModelDriverCar model) {
		this.methode = string;
		this.source = dialogDriverCar;
		this.parent = parent;
		this.model = model;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (methode.contentEquals("add")) {
			DriverCar driverCar = new DriverCar(Integer.valueOf(String
					.valueOf(source.driver.getSelectedItem()).substring(0, 2)
					.trim()), Integer.valueOf(String
					.valueOf(source.car.getSelectedItem()).substring(0, 2)
					.trim()));
			driverCar.setNeu(true);
			model.addRow(driverCar, parent); // Tabelle
			parent.addDriverCar(driverCar); // fachMap

		} else if (methode.contentEquals("del")) {
			model.removeRowAt(source.getRow()); // Tabelle
			parent.delDriverCar(source.getRow()); // fachMap
			
		}
		source.dispose();
	}
}
