package atm.po.org.rosliny;

import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;
import java.awt.Color;

public class Trawa extends Roslina
{
    private static Color defaultKolor  = Color.GREEN;
    private final double SZANSASIANIA = 0.1;

    public Trawa( int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 't';
        this.typ = Typ.trawa;
        this.szansaSiania = SZANSASIANIA;
        this.kolor = defaultKolor;
    }

    @Override public Organizm noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Trawa(turaUrodzenia, this.mojSwiat, kordy);

    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }
}
