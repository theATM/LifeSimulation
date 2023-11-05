package atm.po.org.zwierzeta;
import java.awt.Color;
import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;

public class Wilk extends Zwierze
{
    private static Color defaultKolor  = Color.DARK_GRAY;
    private final int STARTSILA = 9;
    private final int STARTINICJATYWA = 5;

    public Wilk( int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'W';
        this.inicjatywa = this.STARTINICJATYWA;
        this.sila = this.STARTSILA;
        this.typ = Typ.wilk;
        this.kolor = defaultKolor;
    }
    @Override public Organizm noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Wilk(turaUrodzenia, this.mojSwiat, new Pozycja(kordy));
    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }

}
