package atm.po.org.rosliny;

import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;
import java.awt.Color;

public class Mlecz extends Roslina
{
    private static Color defaultKolor  = Color.YELLOW;
    private final int ILOSCNASIONNATURE = 3;
    public Mlecz(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.iloscNasionNaTure = this.ILOSCNASIONNATURE;
        this.kod = 'm';
        this.typ = Typ.mlecz;
        this.kolor = defaultKolor;
    }
    @Override public  Organizm noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Mlecz(turaUrodzenia, this.mojSwiat,new Pozycja( kordy));
    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }

}
