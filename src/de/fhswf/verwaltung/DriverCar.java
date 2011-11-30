package de.fhswf.verwaltung;


public class DriverCar {
    
    private Integer Bez_ID; 
    private Integer Driver_ID;
    private Integer Car_ID;
    
    public DriverCar()
    {
        

    }
    
    public DriverCar(Integer driverId, Integer carId)
    {
        setDriverId(driverId);
        setCarId(carId);
    }

    public Integer getBez_ID() {
        return Bez_ID;
    }

    public void setBez_ID(Integer Bez_Id) {
        Bez_ID = Bez_Id;
    }

    public Integer getDriverId() {
        return Driver_ID;
    }

    public void setDriverId(Integer driverId) {
        Driver_ID = driverId;
    }

    public Integer getCarId() {
        return Car_ID;
    }

    public void setCarId(Integer carId) {
        Car_ID = carId;
    }
    
    public String toString()
    {
       return "Fahrer_ID: " + Driver_ID.toString() + " Fahrzeug_ID: " + Car_ID.toString();
    }
}
