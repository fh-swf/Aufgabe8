package de.fhswf.verwaltung;

import java.sql.Date;

public class Fahrzeug {
    
    private Integer Fahrzeug_ID; 
    private String Kennzeichen;
    private Date Erstzulassung;
    
    public Fahrzeug()
    {
        

    }
    
    public Fahrzeug(String kennzeichen, Date erstzulassung)
    {
        setKennzeichen(kennzeichen);
        setErstzulassung(erstzulassung);
    }

    public Integer getFahrzeug_ID() {
        return Fahrzeug_ID;
    }

    public void setFahrzeug_ID(Integer fahrzeug_ID) {
        Fahrzeug_ID = fahrzeug_ID;
    }

    public String getKennzeichen() {
        return Kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        Kennzeichen = kennzeichen;
    }

    public Date getErstzulassung() {
        return Erstzulassung;
    }

    public void setErstzulassung(Date erstzulassung) {
        Erstzulassung = erstzulassung;
    }
    
    public String toString()
    {
       return "Kennzeichen: " + Kennzeichen + " Erstzulassung: " + Erstzulassung.toString();
    }
}
