package atm.po.org.rosliny;

import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;
import java.awt.Color;

public class Jagoda extends Roslina
{
    private static Color defaultKolor  = new Color(89, 18, 145); //ciemny fiolet
    private final int ILOSCNASIONNATURE = 1;
    private final int STARTSILA = 99;
    public Jagoda(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.iloscNasionNaTure = this.ILOSCNASIONNATURE;
        this.kod = 'j';
        this.sila = this.STARTSILA;
        this.typ = Typ.wilczeJagody;
        this.kolor = defaultKolor;
    }
    @Override public Event kolizja(Organizm napastnik)
    {
        //ktoś na mnie wszedł
        // walka :(
        //ginę ale ginie napastnik  też :)
        if(napastnik.getTyp() != this.getTyp())
        {

            EventList listaEventow = new EventList(this.kordy);
            listaEventow.push(new Event(napastnik.getKordy(), Call.morduje, this.kordy));
            listaEventow.push(new Event(this.kordy, Call.truje, napastnik.getKordy()));
            listaEventow.push(new Event(napastnik.getKordy(), Call.ruch, this.kordy));
            return listaEventow;
        }
        return null;
    }
    @Override public Organizm noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Jagoda(turaUrodzenia, this.mojSwiat,new Pozycja( kordy));
    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }
}
