package de.fhswf.verwaltung;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;

import javax.swing.Action;

public class ActionCar implements Action
{

   DialogFahrzeug source;
   MainWindow parent;
   TableModelFahrzeug model;
   String methode;
   
   public ActionCar(String string, DialogFahrzeug dialogFahrzeug, MainWindow parent, TableModelFahrzeug model)
   {
	  this.methode = string;
      this.source = dialogFahrzeug;
      this.parent = parent;
      this.model = model;
   }

   @Override
   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public Object getValue(String key)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean isEnabled()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void putValue(String key, Object value)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void removePropertyChangeListener(PropertyChangeListener listener)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void setEnabled(boolean b)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
	   if ( methode.contentEquals("add") )
	   {
		   @SuppressWarnings("deprecation")
		   Fahrzeug fahrzeug = new Fahrzeug(source.kennzeichen.getText(),
				   							new Date (source.erstzulassung.getSelectedDate().getYear(), source.erstzulassung.getSelectedDate().getMonth(), source.erstzulassung.getSelectedDate().getDate())
						);
		   if (source.getRow() != -1)
		   {
			   model.editRowAt(fahrzeug, parent, source.getRow());	// Tabelle
			   parent.editFahrzeug(fahrzeug, source.getRow());     // fachMap
		   }
		   else
		   {
			   if (parent.getFahrzeugMap() == null)
				   fahrzeug.setFahrzeug_ID(0);
			   else
				   fahrzeug.setFahrzeug_ID(parent.getFahrzeugMap().size());
			   model.addRow(fahrzeug, parent);          // Tabelle
			   parent.addFahrzeug(fahrzeug);                // fachMap
		   }



	   }
	   else if ( methode.contentEquals("del") )
	   {
		   model.removeRowAt(source.getRow());	// Tabelle
		   parent.delFahrzeug(source.getRow());     // fachMap
	   }
            source.dispose();
   }
}
