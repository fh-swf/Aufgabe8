package de.fhswf.verwaltung;

public class Fahrer {
    
    private Integer Fahrer_ID; 
    private String Name;
    private String Fuehrerscheinklasse;
    private String FuehrerscheinSeit;
    
    public Fahrer()
    {
        

    }
    
    public Fahrer(Integer fahrer_id, String name, String fuehrerscheinklasse, String fuehrerscheinseit)
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

    public String getFuehrerscheinklasse() {
        return Fuehrerscheinklasse;
    }

    public void setFuehrerscheinklasse(String fuehrerscheinklasse) {
        Fuehrerscheinklasse = fuehrerscheinklasse;
    }

    public String getFuehrerscheinSeit() {
        return FuehrerscheinSeit;
    }

    public void setFuehrerscheinSeit(String fuehrerscheinSeit) {
        FuehrerscheinSeit = fuehrerscheinSeit;
    }
    
}
