package de.fhswf.verwaltung;



class RowEntryDriverCar
{
   String driver;
   String car;

   public RowEntryDriverCar(String Driver, String Car )
   {
      this.driver = Driver;
      this.car = Car;
   }

   public String getFahrer()
   {
      return driver;
   }

   public String getFahrzeug()
   {
      return car;
   }
   
}
