package atm.po.org;
import atm.po.logika.*;
import atm.po.Swiat;
import java.util.Random; // liczby pseudolosowe
import java.awt.Color;

public abstract class Organizm
{
    protected Color kolor;
    protected char kod;
    protected int wiek;
    protected int sila;
    protected int inicjatywa;
    protected int niegotowosc;
    protected int maxniegotowosc;
    protected Swiat mojSwiat;
    protected Pozycja kordy;
    protected Typ typ;
    protected Random rand;
    protected Organizm( int turaUrodzenia, Swiat nowySwiat, Pozycja kordy)
    {
        this.wiek = turaUrodzenia;
        this.mojSwiat = nowySwiat;
        this.kordy = kordy;
        this.sila = 0;
        this.inicjatywa = 0;
        this.niegotowosc = 0;
        this.maxniegotowosc = 0;
        this.rand = new Random();
    }

    protected boolean decyzja(double szansa)
    {
        double mysli = this.rand.nextDouble();
        return (mysli <= szansa) ? (true) : (false);
    }

    protected int wyborPola(boolean bezpieczne)
    {
        //pola :  0   0     0    0
        //        UP DOWN LEFT RIGHT
        int maxpola = 4;
        char pola = 0x0F; // 1 1 1 1

        if (this.kordy.n == 0 || (bezpieczne && this.mojSwiat.getMapaIndex(new Pozycja(this.kordy.m, this.kordy.n-1)) != -1))
        {
            pola = (char) (pola & 0xFD); //NO LEFT
            maxpola--;
        }
        if (this.kordy.n == this.mojSwiat.getN() - 1 || (bezpieczne && this.mojSwiat.getMapaIndex(new Pozycja(this.kordy.m, this.kordy.n+1)) != -1))
        {
            pola = (char) (pola & (char)0xFE); //NO RIGHT
            maxpola--;
        }
        if (this.kordy.m == 0 || (bezpieczne && this.mojSwiat.getMapaIndex(new Pozycja(this.kordy.m-1, this.kordy.n )) != -1))
        {
            pola = (char) (pola & 0xF7); //NO UP
            maxpola--;
        }
        if (this.kordy.m == this.mojSwiat.getM() - 1 || (bezpieczne && this.mojSwiat.getMapaIndex(new Pozycja(this.kordy.m+1, this.kordy.n )) != -1))
        {
            pola = (char) (pola & 0xFB); //NO DOWN
            maxpola--;
        }

        if (maxpola <= 0) //gdy zero to nie może sie ruszyć!
            return -1;

        int wybrany = 0;
        int numerPola = rand.nextInt(maxpola);
        while (numerPola >= 0)
        {
            int bit = pola & 0x01; // najmłodszy bit
            if (bit != 0 )
                numerPola--;
            pola = (char) (pola >> 1);
            wybrany++;
        }
        return wybrany - 1;
    }

    public abstract Event akcja();
    public abstract Event kolizja(Organizm napastnik);
    public abstract boolean reakcja(Event ewencik);
    public abstract  Organizm noweDziecko(int turaUrodzenia, Pozycja kordy);
    public void rysuj()
    {
        //rysowanie w konsoli:
        System.out.print(kod);
    }

    public String save()
    {
        String odp = new String(
          "[ " + String.valueOf(kod) + " " + String.valueOf(wiek) + " " + String.valueOf(sila) + " "
                  + String.valueOf(inicjatywa) + " " + this.kordy.save() + "] ");
        return odp;
    }

    public void load(String zczyt)
    {
        zczyt = zczyt.substring(2,zczyt.length()-2);
        String buf[] = zczyt.split(" ");
        this.kod =  buf[0].charAt(0);
        this.wiek = Integer.parseInt(buf[1]);
        this.sila = Integer.parseInt(buf[2]);
        this.inicjatywa = Integer.parseInt(buf[3]);
        this.kordy.load(buf[5],buf[7]);

    }


    public void addSila(int ile)
    {
        if (this.sila + ile > 0)
        {
            this.sila += ile;
        }
	    else
        {
            this.sila = 0;
        }
    }

    public boolean czyGotowy()
    {
        if(this.niegotowosc == 0)
        {
            this.niegotowosc = this.maxniegotowosc;
            return true;
        }
        return false;
    }

    //getery:
    public Pozycja getKordy()
    {
        return this.kordy;
    }

    public int getSila()
    {
        return this.sila;
    }

    public int getInicjatywa()
    {
        return this.inicjatywa;
    }

    public int getWiek()
    {
        return this.wiek;
    }

    public Typ getTyp()
    {
        return this.typ;
    }

    public Color getKolor()
    {
        return this.kolor;
    }

}
