package de.fhswf.verwaltung;

import java.util.Date;

public class Fahrer {
    
    private Integer Fahrer_ID; 
    private String Name;
    private int Fuehrerscheinklasse;
    private Date FuehrerscheinSeit;
    
    public Fahrer()
    {
        

    }
    
    public Fahrer(Integer fahrer_id, String name, int fuehrerscheinklasse, Date fuehrerscheinseit)
    {
        setFahrer_ID(fahrer_id);
        setName(name);
        setFuehrerscheinklasse(fuehrerscheinklasse);
        setFuehrerscheinSeit(fuehrerscheinseit);
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

    public int getFuehrerscheinklasse() {
        return Fuehrerscheinklasse;
    }

    public void setFuehrerscheinklasse(int fuehrerscheinklasse) {
        Fuehrerscheinklasse = fuehrerscheinklasse;
    }

    public Date getFuehrerscheinSeit() {
        return FuehrerscheinSeit;
    }

    public void setFuehrerscheinSeit(Date fuehrerscheinSeit) {
        FuehrerscheinSeit = fuehrerscheinSeit;
    }
    
}
