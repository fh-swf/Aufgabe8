package de.fhswf.verwaltung;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;

import javax.swing.Action;
import javax.swing.JCheckBox;

public class ActionDriver implements Action
{

   DialogFahrer source;
   MainWindow parent;
   TableModelFahrer model;
   String methode;
   
   private StringBuilder stringBuilder;
   
   public ActionDriver(String string, DialogFahrer dialogFahrer, MainWindow parent, TableModelFahrer model)
   {
	  this.methode = string;
      this.source = dialogFahrer;
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

   @SuppressWarnings("deprecation")
@Override
   public void actionPerformed(ActionEvent e)
   {
	   if ( methode.contentEquals("add") )
	   {
		   stringBuilder = new StringBuilder();
		   for( JCheckBox checkBox : source.getCheckBoxMap() )
		      {
		         if (checkBox.isSelected())
		         {
		        	 if(stringBuilder.length() == 0)
		        		 stringBuilder.append(checkBox.getLabel().trim());
		        	 else
		        	 {
		        		 stringBuilder.append(", ");
		        		 stringBuilder.append(checkBox.getLabel().trim());
		        	 }
		         }
		      }
		   
		   Fahrer fahrer = new Fahrer(source.nameFahrer.getText(),
										stringBuilder.toString(),
										new Date (source.fDatum.getSelectedDate().getYear(), source.fDatum.getSelectedDate().getMonth(), source.fDatum.getSelectedDate().getDate())
										);
		   if (source.getRow() != -1)
		   {
			   model.editRowAt(fahrer, parent, source.getRow());	// Tabelle
			   parent.editFahrer(fahrer, source.getRow());     // fachMap
		   }
		   else
		   {
			   if (parent.getFahrerMap() == null)
				   fahrer.setFahrer_ID(0);
			   else
				   fahrer.setFahrer_ID(parent.getFahrerMap().size());
			   model.addRow(fahrer, parent);          // Tabelle
			   parent.addFahrer(fahrer);                // fachMap
		   }



	   }
	   else if ( methode.contentEquals("del") )
	   {
		   model.removeRowAt(source.getRow());	// Tabelle
		   parent.delFahrer(source.getRow());     // fachMap
	   }
            source.dispose();
   }
}
