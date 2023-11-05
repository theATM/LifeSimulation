package atm.po.org.zwierzeta;
import java.awt.Color;
import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;

public class Zolw extends Zwierze
{
    private static Color defaultKolor  = new Color(0,95,0); //DARK_GREEN;
    private final int STARTSILA = 2;
    private final int STARTINICJATYWA = 1;
    private final double SZANSARUCHU = 0.25;
    private final int MAXBLOCKSILA = 5;
    public Zolw(int turaUrodzenia, Swiat  nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'Z';
        this.inicjatywa = this.STARTINICJATYWA;
        this.sila = this.STARTSILA;
        this.typ = Typ.zolw;
        this.kolor = defaultKolor;
    }
    @Override public Event akcja()
    {
        //ruch
        if (this.decyzja(SZANSARUCHU))
        {
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
            return new Event(this.kordy, Call.ruch, nowekordy);
        }
        return null;
    }
    @Override public Event kolizja(Organizm  napastnik)
    {
        //ktoś na mnie wszedł :(
        //czy był to przedstawiciel tego samego gatunku?
        if (this.typ == (Typ)napastnik.getTyp())
        {
            //czy możemy?
            if (this.czyGotowy() && napastnik.czyGotowy())
            {
                // zabieram się do pracy :)
                int nowePole = this.wyborPola(true);
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
                return new Event(this.kordy, Call.tworzy, nowekordy);
            }
		else
            {
                //nie dziś
                return null;
            }
        }
	else
        {
            // walka :(
            //porównuje jego sile z moją
            if (napastnik.getSila() >= MAXBLOCKSILA)
            {
                //przegrałem ginę :(

                EventList listaEventow = new EventList(this.kordy);
                listaEventow.push(new Event(napastnik.getKordy(), Call.morduje, this.kordy));
                listaEventow.push(new Event(napastnik.getKordy(), Call.ruch, this.kordy));
                return listaEventow;
            }
            else
            {
                //wygrałem napastnik się cofa :)
                return null;
            }
        }
        //return null;
    }
    @Override public Organizm  noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Zolw(turaUrodzenia, this.mojSwiat, new Pozycja(kordy));
    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }
}
