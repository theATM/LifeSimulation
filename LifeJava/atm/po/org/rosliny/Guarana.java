package atm.po.org.rosliny;

import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;
import java.awt.Color;

public class Guarana extends Roslina
{
    private static Color defaultKolor = Color.RED;
    public Guarana(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'g';
        this.typ = Typ.guarana;
        this.kolor = defaultKolor;
    }

    @Override public Event kolizja(Organizm  napastnik)
    {
        //ktoś na mnie wszedł
        // walka :(
        //porównuje jego sile z moją
        if (napastnik.getSila() >= this.sila)
        {
            //daję napastnikowi super siłę:
            napastnik.addSila(3);
            //przegrałem ginę :(
            EventList listaEventow = new EventList(this.kordy);
            listaEventow.push(new Event(napastnik.getKordy(), Call.morduje, this.kordy));
            listaEventow.push(new Event(napastnik.getKordy(), Call.ruch, this.kordy));
            return listaEventow;
        }
	    else
        {
            //wygrałem ginie napastnik :)
            return new Event(this.kordy, Call.morduje, napastnik.getKordy());
        }
    }

    @Override public Organizm  noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Guarana(turaUrodzenia, this.mojSwiat,new Pozycja( kordy));
    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }

}
