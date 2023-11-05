package atm.po.org.rosliny;
import atm.po.org.Organizm;
import atm.po.Swiat;
import atm.po.logika.*;

public abstract class Roslina extends Organizm
{
    protected final int DEFAULTSTARTINICJATYWA = 0;
    protected final int DEFAULTSTARTSILA = 0;
    protected final int DEFAULTILOSCNASIONNATURE = 1;
    protected final double DEFAULTSZANSASIANIA = 0.1;
    protected double szansaSiania;
    protected int iloscNasionNaTure;

    public Roslina( int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super( turaUrodzenia, nowySwiat, kordy);
        this.inicjatywa = this.DEFAULTSTARTINICJATYWA;
        this.sila = this.DEFAULTSTARTSILA;
        this.szansaSiania = this.DEFAULTSZANSASIANIA;
        this.iloscNasionNaTure = this.DEFAULTILOSCNASIONNATURE;
    }
    @Override public Event akcja()
    {
        //rozprzestrzenianie się
        EventList listaEventow = new EventList(this.kordy);
        int pozostalaIloscProb = this.iloscNasionNaTure;
        char byl = 0;
        while (pozostalaIloscProb > 0)
        {
            if (decyzja(this.szansaSiania))
            {
                //będę siał:
                int nowePole = this.wyborPola(true); //pole gdzie nikogo nie ma
                Pozycja nowekordy = new Pozycja(this.kordy);
                if (byl == 15) break;
                switch (nowePole)
                {
                    case -1: //can't move
                        continue;
                        //break;
                    case 0: // RIGHT
                        if((byl & 1) == 0)
                        {
                            nowekordy.n++;
                            byl |= 1;
                            break;
                        }
                        else
                        {
                            continue;
                        }
                    case 1: // LEFT
                        if((byl & 2) == 0)
                        {
                            nowekordy.n--;
                            byl |= 2;
                            break;
                        }
                        else
                        {
                            continue;
                        }
                    case 2: // DOWN
                        if((byl & 4) == 0)
                        {
                            nowekordy.m++;
                            byl |= 4;
                            break;
                        }
                        else
                        {
                            continue;
                        }
                    case 3: // UP
                        if((byl & 8) == 0)
                        {
                            nowekordy.m--;
                            byl |= 8;
                            break;
                        }
                        else
                        {
                            continue;
                        }
                }
                listaEventow.push(new Event(this.kordy, Call.tworzy, nowekordy));
            }
            pozostalaIloscProb--;
        }
        if (listaEventow.czyNiePusta())
        {
            return listaEventow;
        }
        else
        {
            listaEventow = null;
            return null;
        }
    }
    @Override public Event kolizja(Organizm napastnik)
    {
        //ktoś na mnie wszedł
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
    @Override public boolean reakcja(Event ewencik)
    {
        switch (ewencik.getCo())
        {
            case ruch:
                return false; //rośliny się nie ruszają
            //break;
            case truje:
                return false; //rośliny są odporne na zatrucia
            //break;
        }
        return false;
    }


    @Override public abstract  Organizm noweDziecko(int turaUrodzenia, Pozycja kordy);
}
