package de.fhswf.verwaltung;

import java.sql.Date;


class RowEntryDriver
{
   String name;
   String fKlasse;
   Date fDatum;

   public RowEntryDriver(String name, String fKlasse, Date fDatum )
   {
      this.name = name;
      this.fKlasse = fKlasse;
      this.fDatum = fDatum;
   }

   public String getName()
   {
      return name;
   }

   public String getKlasse()
   {
      return fKlasse;
   }

   public Date getDatum()
   {
      return fDatum;
   }
   
}
