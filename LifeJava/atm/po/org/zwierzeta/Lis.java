package atm.po.org.zwierzeta;
import java.awt.Color;
import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;

public class Lis extends Zwierze
{
    private static Color defaultKolor  = Color.MAGENTA;
    private final int STARTSILA = 3;
    private final int STARTINICJATYWA = 7;
    public Lis(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'L';
        this.inicjatywa = this.STARTINICJATYWA;
        this.sila = this.STARTSILA;
        this.typ = Typ.lis;
        this.kolor = defaultKolor;
    }
    @Override public Event akcja()
    {
        //ruch
        int nowePole = this.wyborPola(false);
        Pozycja nowekordy = new Pozycja(this.kordy);
        switch (nowePole)
        {
            case -1: //can't move
                return null;
                //break;
            case 0: // RIGHT
                nowekordy.n++;
                break;
            case 1: // LEFT
                nowekordy.n--;
                break;
            case 2: // DOWN
                nowekordy.m++;
                break;
            case 3: // UP
                nowekordy.m--;
                break;
        }
        int index = this.mojSwiat.getMapaIndex(nowekordy);
        if (index != -1)
        {
            Organizm zagrorzenie = this.mojSwiat.getOrganizm(index);
            if (zagrorzenie.getSila() <= this.getSila())
            {
                return null; //wyczuwa zagrorzenie nie rusza się
            }
        }
        return new Event(this.kordy, Call.ruch, nowekordy);
    }
    @Override public Organizm noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Lis(turaUrodzenia, this.mojSwiat, new Pozycja(kordy));

    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }
}
