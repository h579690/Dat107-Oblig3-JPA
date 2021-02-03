package no.hvl.dat107.entity;

@SuppressWarnings("unused")
public class ProsjektdeltagelsePK {
    
    private int ansatt;    //NB! M� hete det samme som i Prosjektdeltagelse
    private int prosjekt;  //NB! M� hete det samme som i Prosjektdeltagelse
    
    public ProsjektdeltagelsePK() {}
    
    public ProsjektdeltagelsePK(int ansattId, int prosjektId) {
        this.ansatt = ansattId;
        this.prosjekt = prosjektId;
    }
}
