package de.fhswf.verwaltung;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class ActionDriverCar implements Action
{

   DialogDriverCar source;
   MainWindow parent;
   TableModelDriverCar model;
   String methode;
   
   public ActionDriverCar(String string, DialogDriverCar dialogDriverCar, MainWindow parent, TableModelDriverCar model)
   {
	  this.methode = string;
      this.source = dialogDriverCar;
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
		   DriverCar driverCar = new DriverCar(source.driver.getSelectedIndex(),
				   							   source.car.getSelectedIndex()
				   									   );
		   if (parent.getDriverCarMap() == null)
				   driverCar.setBez_ID(0);
			   else
				   driverCar.setBez_ID(parent.getDriverCarMap().size());
			   model.addRow(driverCar, parent);          // Tabelle
			   parent.addDriverCar(driverCar);                // fachMap


	   }
	   else if ( methode.contentEquals("del") )
	   {
		   model.removeRowAt(source.getRow());	// Tabelle
		   parent.delFahrzeug(source.getRow());     // fachMap
	   }
            source.dispose();
   }
}
