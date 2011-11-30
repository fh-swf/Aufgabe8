package de.fhswf.verwaltung;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

/*
 * $RCSFile$
 *
 * Created on 01.11.2011
 * for Project: Notenverwaltung
 * by Michael Rockstein & Philipp Schäfer
 *
 * (C) 2005-2006 by 
 */

public class TableClickListenerDriver implements MouseListener
{
   JTable table;
   MainWindow parent;
   TableModelFahrer dataModel;
   
   public TableClickListenerDriver(JTable table, MainWindow parent, TableModelFahrer dataModel)
   {
      this.table = table;
      this.parent = parent;
      this.dataModel = dataModel;
   }
   
   @Override
   public void mouseClicked(MouseEvent e)
   {
      if(e.getClickCount()==2)
      {
         int row = table.rowAtPoint(e.getPoint());
         DialogFahrer dialog = new DialogFahrer("Note bearbeiten - Zeile " + row, parent, dataModel);
         dialog.setRow(row);
         dialog.loadFahrer(parent.getFahrer(row));
         dialog.setDeleteEnabled(true);
         dialog.setSaveEnabled(true);
         dialog.setExitEnabled(true);
         System.out.println(row);
         System.out.println(dialog.getRow());
      }
   }

   @Override
   public void mouseEntered(MouseEvent e)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mousePressed(MouseEvent e)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseReleased(MouseEvent e)
   {
      // TODO Auto-generated method stub
      
   }
}
