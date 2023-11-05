package atm.po.org.rosliny;

import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;
import java.awt.Color;

public class Barszcz extends Roslina
{
    private static Color defaultKolor  =new Color(78, 78, 5);  //ciemny brudny żółty
    private final int STARTSILA = 10;

    public Barszcz(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'b';
        this.sila = this.STARTSILA;
        this.typ = Typ.barszSosnowskiego;
        this.szansaSiania = 0.05;
        this.kolor =  defaultKolor;
    }

    @Override public Event akcja()
    {
        //rozprzestrzenianie się
        EventList listaEventow = new EventList(this.kordy);
        //otrucie wiszystkich dookoła
        if (this.kordy.n > 0 && this.mojSwiat.getMapaIndex(new Pozycja(this.kordy.m, this.kordy.n - 1)) != -1)
        {
            Pozycja nowekordy = new Pozycja(this.kordy);
            nowekordy.n--;
            listaEventow.push(new Event(this.kordy, Call.truje, nowekordy));
        }
        if (this.kordy.n < this.mojSwiat.getN() - 1 && this.mojSwiat.getMapaIndex(new Pozycja(this.kordy.m, this.kordy.n + 1)) != -1)
        {
            Pozycja nowekordy = new  Pozycja(this.kordy);
            nowekordy.n++;
            listaEventow.push(new Event(this.kordy, Call.truje, nowekordy));
        }
        if (this.kordy.m > 0 && this.mojSwiat.getMapaIndex(new Pozycja(this.kordy.m - 1, this.kordy.n)) != -1)
        {
            Pozycja nowekordy = new  Pozycja(this.kordy);
            nowekordy.m--;
            listaEventow.push(new Event(this.kordy, Call.truje, nowekordy));
        }
        if (this.kordy.m < this.mojSwiat.getM() - 1 && this.mojSwiat.getMapaIndex(new Pozycja(this.kordy.m + 1, this.kordy.n)) != -1)
        {
            Pozycja nowekordy = new  Pozycja(this.kordy);
            nowekordy.m++;
            listaEventow.push(new Event(this.kordy, Call.truje, nowekordy));
        }

        int pozostalaIloscProb = this.iloscNasionNaTure;
        while (pozostalaIloscProb > 0)
        {
            if (decyzja(this.szansaSiania))
            {
                //będę siał:
                int nowePole = this.wyborPola(true); //pole gdzie nikogo nie ma
                Pozycja nowekordy = new Pozycja(this.kordy);
                switch (nowePole)
                {
                    case -1: //can't move
                        continue;
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
                listaEventow.push(new Event(this.kordy, Call.tworzy, nowekordy));
            }
            pozostalaIloscProb--;
        }

        //zwaracnie listy eventow:
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
    @Override public Event kolizja(Organizm  napastnik)
    {
        //ktoś na mnie wszedł
        // walka :(
        if (napastnik.getTyp() == Typ.cyberOwca)
        {
            //przegrałem ginę :(
            EventList listaEventow = new EventList(this.kordy);
            listaEventow.push(new Event(napastnik.getKordy(), Call.morduje, this.kordy));
            listaEventow.push(new Event(napastnik.getKordy(), Call.ruch, this.kordy));
            return listaEventow;
        }
        else
        {
            //ginę ale ginie napastnik  też :)
            EventList listaEventow = new EventList(this.kordy);
            listaEventow.push(new Event(napastnik.getKordy(), Call.morduje, this.kordy));
            listaEventow.push(new Event(this.kordy, Call.truje, napastnik.getKordy()));
            listaEventow.push(new Event(napastnik.getKordy(), Call.ruch, this.kordy));
            return listaEventow;
        }
    }
    @Override public Organizm  noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new Barszcz(turaUrodzenia, this.mojSwiat, new Pozycja(kordy));
    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }

}
