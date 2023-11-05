package atm.po.org.zwierzeta;

import atm.po.Swiat;
import atm.po.logika.Call;
import atm.po.logika.Event;
import atm.po.logika.Pozycja;
import atm.po.org.Organizm;
import atm.po.org.Typ;
import atm.po.org.rosliny.Barszcz;

import java.awt.*;
import java.lang.Math;

public class CyberOwca extends Zwierze
{
    private static Color defaultKolor  = Color.CYAN;
    private final int STARTSILA = 11;
    private final int INKOGNITOSILA = 4;
    private final int STARTINICJATYWA = 4;
    private boolean inCognito;
    private Pozycja cel;
    private double celDist;
    private double caclDist(Pozycja pcelu)
    {
        double wynik = 0;
        wynik = Math.sqrt(Math.pow((pcelu.m - this.kordy.m),2) + Math.pow((pcelu.n - this.kordy.n),2)  );
        return wynik;
    }
    public CyberOwca(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'Q';
        this.inicjatywa = this.STARTINICJATYWA;
        this.sila = this.INKOGNITOSILA;
        this.typ = Typ.cyberOwca;
        this.kolor = Color.LIGHT_GRAY;
        this.inCognito = true;
        cel = new Pozycja(this.kordy);
        celDist = 0;
    }
    @Override  public Event akcja()
    {
        if(!this.inCognito)
        {
            int idex = this.mojSwiat.getMapaIndex(cel);
            if(idex != -1 && !(this.mojSwiat.getOrganizm(idex) instanceof Barszcz))
            {
                this.inCognito = true;
                this.sila = INKOGNITOSILA;
                cel = new Pozycja(this.kordy);
                celDist = 0;
                this.kolor = Color.LIGHT_GRAY;
            }
        }

        if(!this.inCognito && (cel.compere(this.kordy)|| this.mojSwiat.getMapaIndex(cel) == -1 ))
        {
            this.inCognito = true;
            this.sila = INKOGNITOSILA;
            cel = new Pozycja(this.kordy);
            celDist = 0;
            this.kolor = Color.LIGHT_GRAY;
        }

        if(this.inCognito)
        {
            cel = new Pozycja(this.kordy);
            celDist = 0;
            //czy jest gdzieś barszcz?
            for (int i = 0; i < this.mojSwiat.getIloscOrganizmow(); i++)
            {
                if (this.mojSwiat.getOrganizm(i) instanceof Barszcz)
                {
                    double newDist = caclDist(this.mojSwiat.getOrganizm(i).getKordy());
                    if(newDist < celDist || celDist == 0)
                    {
                        celDist = newDist;
                        cel = this.mojSwiat.getOrganizm(i).getKordy();
                    }
                    this.inCognito = false;
                }
            }
            if(this.inCognito)
            {
                this.sila = INKOGNITOSILA;
                return super.akcja();

            }
        }
        else if(!inCognito)
        {
            //czy jest gdzieś barszcz bliżej?

            for (int i = 0; i < this.mojSwiat.getIloscOrganizmow(); i++)
            {
                if (this.mojSwiat.getOrganizm(i) instanceof Barszcz)
                {
                    double newDist = caclDist(this.mojSwiat.getOrganizm(i).getKordy());
                    if(newDist < celDist)
                    {
                        celDist = newDist;
                        cel = this.mojSwiat.getOrganizm(i).getKordy();
                    }
                }
            }
        }

        if(!this.inCognito)
        {
            this.sila = STARTSILA;
            this.kolor = defaultKolor;
            Pozycja nowekordy = new Pozycja(this.kordy);
            //ruch w strone celu:
            if(this.kordy.m > this.cel.m)
            {
                nowekordy.m--;
            }
            else if(this.kordy.m < this.cel.m)
            {
                nowekordy.m++;
            }
            else if(this.kordy.n > this.cel.n)
            {
                nowekordy.n--;
            }
            else if(this.kordy.n < this.cel.n)
            {
                nowekordy.n++;
            }
            else
            {
                return null;
            }
            return new Event(this.kordy, Call.ruch,nowekordy);
        }
        else
        {
            System.out.println("Error, z cyber Owcą");
            return null;
        }

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
                return false; // jest odporna :D;
            }
        }
        return false;
    }

    @Override public String save()
    {
        String odp = new String(
                "[ " + String.valueOf(kod) + " " + String.valueOf(inCognito) + " "
                        + String.valueOf(wiek) + " " + String.valueOf(sila) + " "
                        + String.valueOf(inicjatywa) + " " + this.kordy.save() + "] ");
        return odp;
    }

    @Override public void load(String zczyt)
    {
        zczyt = zczyt.substring(2,zczyt.length()-2);
        String buf[] = zczyt.split(" ");
        this.kod = buf[0].charAt(0);
        this.inCognito = Boolean.parseBoolean(buf[1]);
        this.wiek = Integer.parseInt(buf[2]);
        this.sila = Integer.parseInt(buf[3]);
        this.inicjatywa = Integer.parseInt(buf[4]);
        this.kordy.load(buf[6],buf[8]);
        if(this.inCognito)
        {
            this.sila = INKOGNITOSILA;
            cel = new Pozycja(this.kordy);
            celDist = 0;
            this.kolor = Color.LIGHT_GRAY;
        }
        else
        {
            this.sila = STARTSILA;
            this.kolor = defaultKolor;
        }

    }

    @Override public Organizm noweDziecko(int turaUrodzenia, Pozycja kordy)
    {
        return new CyberOwca(turaUrodzenia,this.mojSwiat,new Pozycja( kordy));
    }
    public static Color getDefaultKolor()
    {
        return defaultKolor;
    }
}
