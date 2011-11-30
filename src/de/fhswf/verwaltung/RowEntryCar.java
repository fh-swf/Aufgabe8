package de.fhswf.verwaltung;

import java.sql.Date;


class RowEntryCar
{
   String kennzeichen;
   Date erstzulassung;

   public RowEntryCar(String kennzeichen, Date erstzulassung )
   {
      this.kennzeichen = kennzeichen;
      this.erstzulassung = erstzulassung;
   }

   public String getKennzeichen()
   {
      return kennzeichen;
   }

   public Date getErstzulassung()
   {
      return erstzulassung;
   }
   
}
