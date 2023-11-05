package atm.po.org.zwierzeta;

import java.awt.Color;
import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;

public class Owca extends Zwierze
{
    private static Color defaultKolor  = Color.LIGHT_GRAY;
    private final int STARTSILA = 4;
    private final int STARTINICJATYWA = 4;

    public Owca(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'O';
        this.inicjatywa = this.STARTINICJATYWA;
        this.sila = this.STARTSILA;
        this.typ = Typ.owca;
        this.kolor = defaultKolor;
    }
    @Override public Organizm noweDziecko( int turaUrodzenia, Pozycja kordy)
    {
        return new Owca(turaUrodzenia,this.mojSwiat,new Pozycja(kordy));
    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }
}
