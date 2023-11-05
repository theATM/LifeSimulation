package atm.po.org.zwierzeta;
import atm.po.logika.Event;
import atm.po.org.Organizm;
import atm.po.logika.*;
import atm.po.Swiat;
import atm.po.org.Typ;

import java.awt.*;

public abstract class Zwierze extends Organizm
{
    protected Zwierze(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super( turaUrodzenia, nowySwiat, kordy);
    }
    @Override  public Event akcja()
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
        return new Event(this.kordy,Call.ruch,nowekordy);
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
            if (napastnik.getSila() >= this.sila)
            {
                //przegrałem ginę :(

                EventList  listaEventow = new EventList(this.kordy);
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
        //return null;
    }
    @Override public boolean reakcja(Event ewencik)
    {
        switch (ewencik.getCo())
        {
            case ruch:
                //można sie bezpiecznie ruszyć!
                if (ewencik.getKto().compere(this.kordy))
            {
                this.kordy = new Pozycja(ewencik.getGdzie());
                return true;
            }
            break;
            case truje:
            {
                return true; // jest podatny;
            }
        }
        return false;
    }
    @Override public abstract Organizm  noweDziecko( int turaUrodzenia, Pozycja kordy);

    @Override public String save()
    {
        return super.save();
    }
    @Override public void load(String zczyt) { super.load(zczyt);}



}
