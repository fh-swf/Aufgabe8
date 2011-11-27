package de.fhswf.verwaltung;

import java.util.Date;

public class Fahrzeuge {
    
    private Integer Fahrzeug_ID; 
    private String Kennzeichen;
    private Date Erstzulassung;
    
    public Fahrzeuge()
    {
        

    }
    
    public Fahrzeuge(Integer fahrzeug_id, String kennzeichen, Date erstzulassung)
    {
        setFahrzeug_ID(fahrzeug_id);
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
}
