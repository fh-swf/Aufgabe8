package de.fhswf.verwaltung;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;

import javax.swing.Action;
import javax.swing.JCheckBox;

public class ActionDriver implements Action {

	DialogFahrer source;
	MainWindow parent;
	TableModelFahrer model;
	String methode;

	public ActionDriver(String string, DialogFahrer dialogFahrer,
			MainWindow parent, TableModelFahrer model) {
		this.methode = string;
		this.source = dialogFahrer;
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

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (methode.contentEquals("add")) {
			StringBuilder stringBuilder = new StringBuilder();

			for (JCheckBox checkBox : source.getCheckBoxMap()) {
				if (checkBox.isSelected()) {
					if (stringBuilder.length() == 0)
						stringBuilder.append(checkBox.getLabel().trim());
					else {
						stringBuilder.append(", ");
						stringBuilder.append(checkBox.getLabel().trim());
					}
				}
			}

			Fahrer fahrer = new Fahrer(source.nameFahrer.getText(),
					stringBuilder.toString(), new Date(source.fDatum
							.getSelectedDate().getYear() + 1900, source.fDatum
							.getSelectedDate().getMonth(), source.fDatum
							.getSelectedDate().getDate()));

			fahrer.setEdited(2);

			if (source.getRow() != -1) {
				if (parent.getFahrer(source.getRow()).getEdited() == 3)
					fahrer.setEdited(3);
				fahrer.setFahrer_ID(parent.getFahrer(source.getRow())
						.getFahrer_ID());
				model.editRowAt(fahrer, parent, source.getRow()); // Tabelle
				parent.editFahrer(fahrer, source.getRow()); // fachMap
			} else {
				fahrer.setEdited(3);
				model.addRow(fahrer, parent); // Tabelle
				parent.addFahrer(fahrer); // fachMap
			}

		} else if (methode.contentEquals("del")) {
			model.removeRowAt(source.getRow()); // Tabelle
			parent.delFahrer(source.getRow()); // fachMap
			
		} else if (methode.contentEquals("rel")) {
			StringBuilder stringBuilder = new StringBuilder();

			for (JCheckBox checkBox : source.getCheckBoxMap()) {
				if (checkBox.isSelected()) {
					if (stringBuilder.length() == 0)
						stringBuilder.append(checkBox.getLabel().trim());
					else {
						stringBuilder.append(", ");
						stringBuilder.append(checkBox.getLabel().trim());
					}
				}
			}

			Fahrer fahrer = new Fahrer(source.nameFahrer.getText(),
					stringBuilder.toString(), new Date(source.fDatum
							.getSelectedDate().getYear() + 1900, source.fDatum
							.getSelectedDate().getMonth(), source.fDatum
							.getSelectedDate().getDate()));

			fahrer.setEdited(2);

			if (source.getRow() != -1) {
				if (parent.getFahrer(source.getRow()).getEdited() == 3)
					fahrer.setEdited(3);
				fahrer.setFahrer_ID(parent.getFahrer(source.getRow())
						.getFahrer_ID());
				model.editRowAt(fahrer, parent, source.getRow()); // Tabelle
				parent.editFahrer(fahrer, source.getRow()); // fachMap
			} else {
				fahrer.setEdited(3);
				model.addRow(fahrer, parent); // Tabelle
				parent.addFahrer(fahrer); // fachMap
			}

			parent.database.write(parent);
			parent.fahrerMap.clear();
			parent.fahrerMapDel.clear();
			parent.fahrzeugMap.clear();
			parent.fahrzeugMapDel.clear();
			parent.driverCarMap.clear();
			parent.driverCarMapDel.clear();
			parent.tableDataFahrer.clear();
			parent.tableDataFahrzeug.clear();
			parent.tableDataDriverCar.clear();
			parent.database.load(parent);

			DialogDriverCar dialog = new DialogDriverCar("Fahrzeug hinzufuegen", parent, parent.tableDataDriverCar);
			dialog.setRow(-1);
			dialog.driver.setSelectedIndex(dialog.driver.getItemCount()-1);

		}
		source.dispose();
	}
}
