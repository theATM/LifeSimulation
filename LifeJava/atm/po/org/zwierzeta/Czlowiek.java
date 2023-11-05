package atm.po.org.zwierzeta;

import java.awt.Color;
import atm.po.logika.*;
import atm.po.org.*;
import atm.po.Swiat;

public class Czlowiek extends Zwierze
{
    private static Color defaultKolor  = Color.BLUE;
    private final int STARTSILA = 5;
    private final int STARTINICJATYWA = 4;
    private final int MAXTURYSUPERMOCY = 5;
    private final int MAXTURYPELNEJSUPERMOCY = 3;
    private final int TURYLADOWANIASUPERMOCY = 5 + MAXTURYSUPERMOCY;
    private int supermoc;
    private int turySupermocy;
    private int ladowanieSupermocy;
    private int ruchNastepny;


    public Czlowiek(int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        super(turaUrodzenia, nowySwiat, kordy);
        this.kod = 'C';
        this.turySupermocy = 0;
        this.supermoc = 1;
        this.ladowanieSupermocy = 0;
        this.inicjatywa = this.STARTINICJATYWA;
        this.sila = this.STARTSILA;
        this.typ = Typ.czlowiek;
        this.kolor = defaultKolor;
        this.ruchNastepny = -1;
    }
    @Override public Event akcja()
    {
        //przechodzi tura:

        //super moc 2 ruchu przez 3 tury i 0.5 szansy na 2 ruchu przez 2 kolejne
        if (this.turySupermocy > MAXTURYSUPERMOCY - MAXTURYPELNEJSUPERMOCY )
        {
            this.supermoc = 2;
            this.turySupermocy--;
        }
        else if (this.turySupermocy < MAXTURYPELNEJSUPERMOCY  && this.turySupermocy > 0)
        {
            this.supermoc = this.rand.nextInt(2);
            this.supermoc++;
            this.turySupermocy--;
        }
        else if (this.turySupermocy <= 0)
        {
            this.supermoc = 1;
            this.turySupermocy = 0;
        }
        if (ladowanieSupermocy > 0) ladowanieSupermocy--;
        else System.out.println(  "S - Super Moc");

        //ruch
        int nowePole = this.ruchNastepny;
        this.ruchNastepny = -1;
        Pozycja nowekordy = new Pozycja(this.kordy);
        switch (nowePole)
        {
            case -1: //can't move
                return null;
            //break;
            case 0: // RIGHT
                nowekordy.n += this.supermoc; // gdy supermocy nie ma to +1 gdy jest to +2
                break;
            case 1: // LEFT
                nowekordy.n -= this.supermoc;
                break;
            case 2: // DOWN
                nowekordy.m += this.supermoc;
                break;
            case 3: // UP
                nowekordy.m -= this.supermoc;
                break;
        }
        return new Event(this.kordy, Call.ruch, nowekordy);
    }

    public void getKeyBoard(int newm, int newn,boolean czySupermoc)
    {
        //odebranie wiadomosci:

        if(newm == -1)//UP
        {
            if (this.kordy.m > 0)
            {
                if (this.kordy.m == 1)
                    this.supermoc = 1;
                this.ruchNastepny = 3;
                return;
            }
        }
        else if(newm == 1)//DOWN
        {
            if (this.kordy.m < this.mojSwiat.getM() - 1)
            {
                if (this.kordy.m == this.mojSwiat.getM() - 2)
                    this.supermoc = 1;
                this.ruchNastepny = 2;
                return;
            }
        }
        else if(newn == -1) //LEFT
        {
            if (this.kordy.n > 0)
            {
                if (this.kordy.n == 1)
                    this.supermoc = 1;
                this.ruchNastepny = 1;
                return;
            }
        }
        else if(newn == 1) //RIGHT
        {
            if (this.kordy.n < this.mojSwiat.getN() - 1)
            {
                if (this.kordy.n == this.mojSwiat.getN() - 2)
                    this.supermoc = 1;
                this.ruchNastepny =  0;
                return;
            }
        }
        else if (czySupermoc)
        {
            if (ladowanieSupermocy == 0)
            {
                //supermoc aktywowana!
                System.out.println(  "Aktywowano Supermoc!");
                this.turySupermocy = MAXTURYSUPERMOCY;
                this.supermoc = 2;
                this.ladowanieSupermocy = TURYLADOWANIASUPERMOCY;
                //ruch nie było
                this.ruchNastepny =  -1;
                return;
            }
        }
        this.ruchNastepny =  -1;
    }


    @Override public String save()
    {
        String odp = new String(
                "[ " + String.valueOf(kod) + " " + String.valueOf(turySupermocy) + " "+ String.valueOf(ladowanieSupermocy) + " "
                        + String.valueOf(wiek) + " " + String.valueOf(sila) + " "
                        + String.valueOf(inicjatywa) + " " + this.kordy.save() + "] ");
        return odp;
    }

    @Override public void load(String zczyt)
    {
        zczyt = zczyt.substring(2,zczyt.length()-2);
        String buf[] = zczyt.split(" ");
        this.kod = buf[0].charAt(0);
        this.turySupermocy = Integer.parseInt(buf[1]);
        this.ladowanieSupermocy = Integer.parseInt(buf[2]);
        this.wiek = Integer.parseInt(buf[3]);
        this.sila = Integer.parseInt(buf[4]);
        this.inicjatywa = Integer.parseInt(buf[5]);
        this.kordy.load(buf[7],buf[9]);

    }

    @Override public Organizm  noweDziecko( int turaUrodzenia, Pozycja kordy)
    {
        return null; //człowiek się nie rozmnarza
    }



   // @Override public void save(std::ofstream &plik) ;
    //@Override public void load(std::ifstream &plik);
   public static Color getDefaultKolor()
   {
       return defaultKolor;
   }
}
