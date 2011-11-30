package de.fhswf.verwaltung;

import java.sql.Date;

public class Fahrer {
    
    private Integer Fahrer_ID; 
    private String Name;
    private String fKlasse;
    private Date fSeit;
    
    public Fahrer()
    {
    }
    
    public Fahrer(String name, String fuehrerscheinklasse, Date date)
    {
        setName(name);
        setFueKlasse(fuehrerscheinklasse);
        setFueSeit(date);
    }

    public Integer getFahrer_ID() {
        return Fahrer_ID;
    }

    public void setFahrer_ID(Integer fahrer_ID) {
        Fahrer_ID = fahrer_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFueKlasse() {
        return fKlasse;
    }

    public void setFueKlasse(String fuehrerscheinklasse) {
        fKlasse = fuehrerscheinklasse;
    }

    public Date getFueSeit() {
        return fSeit;
    }

    public void setFueSeit(Date fuehrerscheinSeit) {
        fSeit = fuehrerscheinSeit;
    }
    
}
