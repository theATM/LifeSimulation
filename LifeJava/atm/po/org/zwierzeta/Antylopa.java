package atm.po.org.zwierzeta;
import java.awt.Color;
import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;
import java.util.Random;

public class Antylopa extends Zwierze
{
    private static Color defaultKolor  = new Color(80,0,0); // BROWN
    private final int STARTSILA = 4;
    private final int STARTINICJATYWA = 4;
    private final double SZANSAUCIECZKI = 0.5;
    private int wyborPolaAntylopy()
    {
        //pola :  0   0     0    0
        //        UP DOWN LEFT RIGHT
        int maxpola = 4;
        char pola = 0x0F; // 1 1 1 1

        if (this.kordy.n <= 1)
        {
            pola = (char) (pola & 0xFD); //NO LEFT
            maxpola--;
        }
        if (this.kordy.n >= this.mojSwiat.getN() - 2)
        {
            pola = (char) (pola & 0xFE); //NO RIGHT
            maxpola--;
        }
        if (this.kordy.m <= 1)
        {
            pola = (char) (pola & 0xF7); //NO UP
            maxpola--;
        }
        if (this.kordy.m >= this.mojSwiat.getM() - 2)
        {
            pola = (char) (pola & 0xFB); //NO DOWN
            maxpola--;
        }

        if (maxpola == 0) //gdy zero to nie może sie ruszyć!
            return -1;

        int wybrany = 0;
        int numerPola = this.rand.nextInt(maxpola);
        while (numerPola >= 0)
        {
            int bit = pola & 0x01; // najmłodszy bit
            if (bit != 0)
                numerPola--;
            pola = (char) (pola >> 1);
            wybrany++;
        }
        return wybrany - 1;
    }

    public Antylopa(int turaUrodzenia, Swiat  nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'A';
        this.inicjatywa = this.STARTINICJATYWA;
        this.sila = this.STARTSILA;
        this.typ = Typ.antylopa;
        this.kolor = defaultKolor;
    }

    @Override public Event akcja()
    {
        //ruch
        int nowePole = this.wyborPolaAntylopy();
        Pozycja nowekordy = new Pozycja(this.kordy);
        switch (nowePole)
        {
            case -1: //can't move
                return null;
            //break;
            case 0: // RIGHT
                nowekordy.n += 2; // gdy supermocy nie ma to +1 gdy jest to +2
                break;
            case 1: // LEFT
                nowekordy.n -= 2;
                break;
            case 2: // DOWN
                nowekordy.m += 2;
                break;
            case 3: // UP
                nowekordy.m -= 2;
                break;
        }
        return new Event(this.kordy, Call.ruch, nowekordy);
    }
    @Override public Event kolizja(Organizm napastnik)
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
            if (!this.decyzja(SZANSAUCIECZKI))
            {

                // walka :(
                //porównuje jego sile z moją
                if (napastnik.getSila() >= this.sila)
                {
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
		else
            {
                //ucieczka:
                EventList listaEventow = new EventList(this.kordy);
                Event ucieczka = this.akcja();
                if (ucieczka == null)
                {
                    //nie można ruszyć :
                    //ale przez moją nieuwagę:
                    //ginę:

                    listaEventow.push(new Event(napastnik.getKordy(), Call.morduje, this.kordy));
                    listaEventow.push(new Event(napastnik.getKordy(), Call.ruch, this.kordy));
                    return listaEventow;
                }
                listaEventow.push(ucieczka);
                listaEventow.push(new Event(napastnik.getKordy(), Call.ruch, this.kordy));
                return listaEventow;
            }
        }
        //return null;
    }
    @Override public Organizm  noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Antylopa(turaUrodzenia, this.mojSwiat,new Pozycja( kordy));
    }

    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }

}
