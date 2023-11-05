package atm.po;
import atm.po.org.*; // wszystkie organizmy:
import atm.po.org.zwierzeta.*;
import atm.po.org.rosliny.*;
import atm.po.logika.*; // wszystkie narzędzia
import atm.po.style.OknoApki;

import java.awt.Color;
import java.util.ArrayList; // do listy organizmów
import java.util.Random; // liczby pseudolosowe

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Swiat
{
    private int N;
    private int M;
    private ArrayList <Organizm> organizmy;
    private int[][] mapa;
    private int iloscOrganizmow;
    private int tura;
    private Random rand;
    private OknoApki okienko;
    public boolean grid;
    public boolean done;
    public Swiat(int M, int N, boolean grid)
    {
        rand = new Random(); // generator liczb pseudolosowych
        this.tura = 0;
        this.iloscOrganizmow = 0;
        this.M = M;
        this.N = N;
        this.grid = grid;
        mapa = new int[M][N];
        organizmy = new ArrayList<Organizm>();
        for(int i = 0 ; i < N*M + 10;i++)
        {
            //inicjalizacja listy pełnej pustych miejsc  na organizmy
            organizmy.add(null);
        }
        //wypełnienie mapy "-1":
        for (int n = 0; n < N; n++)
        {
            for (int m = 0; m < M; m++)
            {
                mapa[m][n] = -1;
            }
        }
        this.okienko = new OknoApki(grid,this);
        done = true;

    }

    public void iniMapa()
    {
        done = false;
        //Dodanie troche organizmow w pierwszej turze:
        this.tura = 1;
        this.iloscOrganizmow = 0;
        if(this.M == 0 && this.N == 0) return;
        //INIT WORLD CONSTS:
        final int maxOrg = N * M / 3;
        final int maxSameOrg = maxOrg / 5;
        final int maxSamePla = maxOrg / 8;

        //dodanie czlowieka:
        if (this.iloscOrganizmow < maxOrg && 1 <= maxSameOrg)
        {
            int m, n;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Czlowiek(0, this, new Pozycja(m, n)));
            mapa[m][n] = 0;
            this.iloscOrganizmow++;
        }

        //dodanie cyber owcy:
        if (this.iloscOrganizmow < maxOrg && 1 <= maxSameOrg)
        {
            int m, n;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new CyberOwca(0, this, new Pozycja(m, n)));
            mapa[m][n] = 1;
            this.iloscOrganizmow++;
        }


        //dodanie wilka
        int liczbaWilkow = rand.nextInt(maxSameOrg);
        int ityWilk = 0;
        while (this.iloscOrganizmow < maxOrg && ityWilk < liczbaWilkow)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Wilk(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            ityWilk++;
            this.iloscOrganizmow++;
        }

        //dodanie owcy
        int liczbaOwiec = rand.nextInt(maxSameOrg);
        int itaOwca = 0;
        while (this.iloscOrganizmow < maxOrg && itaOwca < liczbaOwiec)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Owca(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            itaOwca++;
            this.iloscOrganizmow++;
        }

        //dodanie lisa
        int liczbaLisow = rand.nextInt(maxSameOrg);
        int ityLis = 0;
        while (this.iloscOrganizmow < maxOrg && ityLis < liczbaLisow)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Lis(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            ityLis++;
            this.iloscOrganizmow++;
        }

        //dodanie Żółwia
        int liczbaZolwi = rand.nextInt(maxSameOrg);
        int ityZolw = 0;
        while (this.iloscOrganizmow < maxOrg && ityZolw < liczbaZolwi)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Zolw(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            ityZolw++;
            this.iloscOrganizmow++;
        }

        //dodanie Antylopy
        int liczbaAntylop = rand.nextInt(maxSameOrg);
        int itaAntylopa = 0;
        while (this.iloscOrganizmow < maxOrg && itaAntylopa < liczbaAntylop)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Antylopa(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            itaAntylopa++;
            this.iloscOrganizmow++;
        }


        //dodanie trawy
        int liczbaTrawy = rand.nextInt(maxSamePla);
        int itaTrawa = 0;
        while (this.iloscOrganizmow < maxOrg && itaTrawa < liczbaTrawy)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Trawa(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            itaTrawa++;
            this.iloscOrganizmow++;
        }

        //dodanie mleczy
        int liczbaMleczy = rand.nextInt(maxSamePla);
        int itaMlecz = 0;
        while (this.iloscOrganizmow < maxOrg && itaMlecz < liczbaMleczy)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Mlecz(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            itaMlecz++;
            this.iloscOrganizmow++;
        }

        //dodanie guarany
        int liczbaGuaran = rand.nextInt(maxSamePla);
        int itaGuarana = 0;
        while (this.iloscOrganizmow < maxOrg && itaGuarana < liczbaGuaran)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Guarana(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            itaGuarana++;
            this.iloscOrganizmow++;
        }

        //dodanie jagód
        int liczbaJagod = rand.nextInt(maxSamePla);
        int itaJagoda = 0;
        while (this.iloscOrganizmow < maxOrg && itaJagoda < liczbaJagod)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Jagoda(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            itaJagoda++;
            this.iloscOrganizmow++;
        }

        //dodanie barszczu
        int liczbaBarszczu = rand.nextInt(maxSamePla);
        int ityBarszcz = 0;
        while (this.iloscOrganizmow < maxOrg && ityBarszcz < liczbaBarszczu)
        {
            int n, m;
            do
            {
                n = rand.nextInt(this.N);
                m = rand.nextInt(this.M);
            } while (mapa[m][n] != -1);
            organizmy.set(iloscOrganizmow, new Barszcz(0, this, new Pozycja(m, n)));
            mapa[m][n] = this.iloscOrganizmow;
            ityBarszcz++;
            this.iloscOrganizmow++;
        }
        done = true;
    }


    public void zycie()
    {

        done = false;
        System.out.println("Tura: " + this.tura );
        rysujSwiat();
        System.out.println(" Wydarzenia Tury: " );
        wykonajTure();
        done = true;

    }

    void wykonajTure()
    {
        sortOrganizmy();
        this.tura++;
        Event eventZwrotny = null;;
        int iloscPierwotnychOrganizmow = iloscOrganizmow;
        for (int organizm = 0; organizm < iloscPierwotnychOrganizmow; organizm++)
        {
            if (organizmy.get(organizm) != null)
            {
                eventZwrotny = organizmy.get(organizm).akcja();
                if(eventZwrotny != null)
                    eventManager(eventZwrotny);
            }
        }
        sortOrganizmy();

    }


    void eventManager(Event evencik)
    {
        if (evencik == null) return;
        switch (evencik.getCo())
        {
            case ruch:
                if(mapa[evencik.getKto().m][evencik.getKto().n] != mapa[evencik.getGdzie().m][evencik.getGdzie().n])
                {
                    //czy ktoś tam już jest?

                    if (mapa[evencik.getKto().m][evencik.getKto().n] != -1)
                    {
                        //jest ktoś kto chce się ruszyć
                        if (mapa[evencik.getGdzie().m][evencik.getGdzie().n] == -1)
                        {
                            //jeżeli nie to można tu wejść
                            Organizm org = organizmy.get(mapa[evencik.getKto().m][evencik.getKto().n]);
                            if (org != null && org.reakcja(evencik))
                            {
                                int buf = mapa[evencik.getKto().m][evencik.getKto().n];
                                mapa[evencik.getKto().m][evencik.getKto().n] = -1;
                                mapa[evencik.getGdzie().m][evencik.getGdzie().n] = buf;
                            }
                        }
                        else
                        {
                            Organizm napastnik = organizmy.get(mapa[evencik.getKto().m][evencik.getKto().n]);
                            Organizm ofiara = organizmy.get(mapa[evencik.getGdzie().m][evencik.getGdzie().n]);
                            if (ofiara != null)
                            {

                                Event odpowiedz = ofiara.kolizja(napastnik);
                                if (odpowiedz != null)
                                {
                                    eventManager(odpowiedz); //wysyłam listę eventów do rozpatrzenia
                                }
                                odpowiedz = null;
                            }
                            else
                            {
                                //mapa źle pokazuje - nikogo tam nie ma
                                mapa[evencik.getGdzie().m][evencik.getGdzie().n] = -1;
                                Organizm org = organizmy.get(mapa[evencik.getKto().m][evencik.getKto().n]);
                                if (org != null && org.reakcja(evencik))
                                {
                                    int buf = mapa[evencik.getKto().m][evencik.getKto().n];
                                    mapa[evencik.getKto().m][evencik.getKto().n] = -1;
                                    mapa[evencik.getGdzie().m][evencik.getGdzie().n] = buf;
                                }
                            }
                        }
                    }
                }
                evencik = null;
                break;
            case morduje:
            {

                Organizm ofiara = null;
                Organizm napastnik = null;
                if(mapa[evencik.getGdzie().m][evencik.getGdzie().n] != -1)
                    ofiara = organizmy.get(mapa[evencik.getGdzie().m][evencik.getGdzie().n]);
                else
                {
                    evencik = null;
                    break;
                }
                if (mapa[evencik.getKto().m][evencik.getKto().n] != -1)
                {
                    napastnik = organizmy.get(mapa[evencik.getKto().m][evencik.getKto().n]);
                    System.out.println(napastnik.getTyp() + " zabija " + ofiara.getTyp() +" ("+ofiara.getKordy().m + "," + ofiara.getKordy().n+")" );
                }
                else
                {
                    //apastnik zabił pośmiertnie
                    System.out.println("ginie "+ ofiara.getTyp() );
                }
                //usuwanie:
                organizmy.set(mapa[evencik.getGdzie().m][evencik.getGdzie().n], null);
                mapa[evencik.getGdzie().m][evencik.getGdzie().n] = -1;
                ofiara = null;
                evencik = null;
                break;
            }
            case truje:
            {

                Organizm ofiara = null;
                Organizm napastnik = null;
                if (mapa[evencik.getGdzie().m][evencik.getGdzie().n] != -1)
                    ofiara = organizmy.get(mapa[evencik.getGdzie().m][evencik.getGdzie().n]);
                else
                {
                    evencik = null; //nikogo nie ma do otrucia
                    break;
                }
                if (mapa[evencik.getKto().m][evencik.getKto().n] != -1)
                {
                    napastnik = organizmy.get(mapa[evencik.getKto().m][evencik.getKto().n]);
                    if (napastnik != null && napastnik.getTyp() == ofiara.getTyp())
                    {
                        evencik = null; // ten sam gatunek jest na siebie odporny
                        break;
                    }
                }
                //gdy napastnik jest nulem to jest to zatrucie pośmiertne

                if (ofiara.reakcja(evencik))
                {
                    //jest podaty na zatrucia
                    eventManager(new Event(evencik.getKto(), Call.morduje, evencik.getGdzie()));
                }
                evencik = null;
                break;
            }
            case tworzy:
                if (mapa[evencik.getKto().m][evencik.getKto().n] != -1 && mapa[evencik.getGdzie().m][evencik.getGdzie().n] == -1 && this.iloscOrganizmow <  N*M + 10)
            {
                Organizm rodzic = organizmy.get(mapa[evencik.getKto().m][evencik.getKto().n]);

                Organizm dziecko = rodzic.noweDziecko(this.tura, new Pozycja(evencik.getGdzie()));
                if (dziecko != null)
                {
                    this.organizmy.set(iloscOrganizmow, dziecko);
                    mapa[evencik.getGdzie().m][evencik.getGdzie().n] = this.iloscOrganizmow;
                    this.iloscOrganizmow++;
                    System.out.println( "rodzi sie " + dziecko.getTyp() + " ("+dziecko.getKordy().m + "," + dziecko.getKordy().n+")" );
                }
            }
            evencik = null;
            break;
            case eventlist:
            {
                EventList lista = (EventList) evencik;
                if (lista.czyNiePusta())
                {
                    for (Event wewevent = lista.pop(); wewevent != null; wewevent = lista.pop())
                    {
                        eventManager(wewevent);
                    }
                }
                evencik = null;
                lista = null;
                break;
            }
            default:
                evencik = null;
                break;
        }
    }


    private void sortOrganizmy()
    {
        //po turze należy przesortować organizmy;
        int iloscPustych = 0;
        for (int j = 0; j < this.iloscOrganizmow; j++)
        {
            Organizm org = null;
            int maxindex = -1;
            for (int index = j; index < this.iloscOrganizmow; index++)
            {
                if (org != null && this.organizmy.get(index) != null)
                {
                    //porównanie organizmów
                    //ten z większą inicjatywą idzie na początek
                    //gdy mają tą samą inicjatywe na początek idzie
                    //starszy
                    if (this.organizmy.get(index).getInicjatywa() > org.getInicjatywa()
                        || (org.getInicjatywa() == this.organizmy.get(index).getInicjatywa()
                        && this.organizmy.get(index).getWiek() < org.getWiek()))
                    {
                        org = this.organizmy.get(index);
                        maxindex = index;
                    }
                }
                else if (this.organizmy.get(index) != null)
                {
                    org = this.organizmy.get(index);
                    maxindex = index;
                }

            }
            //wybrano najlepszego:
            //i wstawienie go na początek
            if (org != null && maxindex != -1)
            {
                if (organizmy.get(j) != null)
                {
                    this.mapa[organizmy.get(j).getKordy().m][organizmy.get(j).getKordy().n] = maxindex;
                }
                this.mapa[org.getKordy().m][org.getKordy().n] = j;
                this.organizmy.set(maxindex, organizmy.get(j));
                organizmy.set(j, org);
            }
            else if (org == null)
            {
                iloscPustych++;
                if (organizmy.get(j) != null)
                {
                    this.mapa[organizmy.get(j).getKordy().m][organizmy.get(j).getKordy().n] = -1;
                }
                organizmy.set(j, null);

            }
        }
        this.iloscOrganizmow -= iloscPustych;
    }

    public int getN()
    {
        return this.N;
    }

    public int getM()
    {
        return this.M;
    }

    public int getMapaIndex(Pozycja kordy)
    {
        return mapa[kordy.m][kordy.n];
    }

    public Organizm getOrganizm(int index)
    {
        if (index != -1)
        {
            return this.organizmy.get(index);
        }
        return null;
    }

    public int getTura()
    {
        return this.tura;
    }

    public String [] getNamesForSelect()
    {
        String[] odp = { "Nie Wybrano" , "Antylopa" ,  "Cyber Owca" , "Czlowiek" , "Lis" , "Owca" , "Żółw" , "Wilk",
                "Barszcz Sosaboskiego" , "Guarana" , "Trawa" , "Wilcze Jagody"  , "Mlecz"};
        return odp;
    }

    public Color getColorsFromSelect(int selectedIndex)
    {
        switch(selectedIndex)
        {
            case 0: //Brak
                return null;
            case 1: //"Antylopa"
                return Antylopa.getDefaultKolor();
            case 2: //"Cyber Owca"
                return null;
            case 3: //"Czlowiek"
                return Czlowiek.getDefaultKolor();
            case 4: // "Lis"
                return Lis.getDefaultKolor();
            case 5: //"Owca"
                return Owca.getDefaultKolor();
            case 6: //"Żółw"
                return Zolw.getDefaultKolor();
            case 7: //"Wilk"
                return Wilk.getDefaultKolor();
            case 8: //"Barszcz Sosaboskiego"
                return Barszcz.getDefaultKolor();
            case 9: //"Guarana"
                return Guarana.getDefaultKolor();
            case 10: //"Trawa"
                return Trawa.getDefaultKolor();
            case 11: //"Wilcze Jagody"
                return Jagoda.getDefaultKolor();
            case 12: //"Mlecz"
                return Mlecz.getDefaultKolor();
            default:
                return null;
        }
    }

    public void rysujSwiat()
    {
        System.out.print(" __");
        for (int n = 0; n < this.N - 2; n++)
        {
            System.out.print("____");
        }
        if (N == 1)
            System.out.print("_");
        else
            System.out.print("_____");
        System.out.println("");


        for (int m = 0; m < this.M; m++)
        {
            for (int n = 0; n < this.N; n++)
            {
                System.out.print("| ");
                if (mapa[m][n] == -1)
                {
                    System.out.print(" ");
                }
                else
                {
                    if (organizmy.get(mapa[m][n]) != null)
                    {
                        organizmy.get(mapa[m][n]).rysuj();;
                    }
                    else
                    {
                        mapa[m][n] = -1;
                        System.out.print(" ");
                    }
                }
                System.out.print(" ");
            }
            System.out.println("|");
            if (m != this.M - 1)
            {
                if (N == 1 && M == 1)
                    System.out.print("|");
                else
                    System.out.print("|---");
                for (int n = 0; n < this.N - 2; n++)
                {
                    System.out.print("----");
                }
                if (N == 1)
                    System.out.print("|");
                else
                    System.out.print("----|");
                System.out.println("");
            }

        }
        if (N == 1)
            System.out.print("|___");
        else
            System.out.print("|___");
        for (int n = 0; n < this.N - 2; n++)
        {
            System.out.print("____");
        }
        if (N == 1)
            System.out.print("|");
        else
            System.out.print("____|");
        System.out.println("");
    }

    public Color[][] sendMapaToRys()
    {
        done = false;
        Color [][] colorMapa = new Color[M][N];
        for (int n = 0; n < N; n++)
        {
            for (int m = 0; m < M; m++)
            {
                int id = mapa[m][n];
                if(id != -1)
                {
                    if(organizmy.get(id)!= null)
                    {

                        if(organizmy.get(id).getKolor() != null)
                        {
                            colorMapa[m][n] = organizmy.get(id).getKolor();

                        }
                        else
                        {
                            colorMapa[m][n] = Color.WHITE;
                        }
                    }
                    else
                    {
                        //repere mapa
                        mapa[m][n] = -1;
                        colorMapa[m][n] = Color.WHITE;
                    }
                }
                else
                {
                    colorMapa[m][n] = Color.WHITE;
                }
            }
        }
        done = true;
        return colorMapa;
    }

    public boolean isOkienkoActive()
    {
        return this.okienko.isActive();
    }
    public boolean isOkienkoBizzy()
    {
        return this.okienko.isOknoApkiBizzy();
    }



    public void ruchCzlowieka(int m, int n,boolean supermoc)
    {
        for(int i = 0 ; i < this.iloscOrganizmow;i++)
        {
            if(this.organizmy.get(i) instanceof Czlowiek)
            {
                ((Czlowiek)this.organizmy.get(i)).getKeyBoard(m,n,supermoc);
                break;
            }
        }
    }

    public int getIloscOrganizmow()
    {
        return this.iloscOrganizmow;
    }


    public void save()
    {
        FileWriter myWriter;
        try
        {
            myWriter = Main.save();
            //grid
            myWriter.write(String.valueOf( this.grid));
            myWriter.write(System.getProperty( "line.separator" ));
            //M N
            myWriter.write(String.valueOf(this.M));
            myWriter.write(" ");
            myWriter.write(String.valueOf(this.N));
            myWriter.write(System.getProperty( "line.separator" ));
            //tura & ilosc organizmow
            myWriter.write(String.valueOf(this.tura));
            myWriter.write(" ");
            myWriter.write(String.valueOf(this.iloscOrganizmow));
            myWriter.write(System.getProperty( "line.separator" ));
            //reszta:
            for (int index = 0; index < iloscOrganizmow; index++)
            {
                String odp = organizmy.get(index).save();
                myWriter.write(odp);
                myWriter.write(System.getProperty( "line.separator" ));
            }
            myWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Nie udało się zapisać gry");
            return;
        }


    }

    public void load(Scanner czytnik) throws InputMismatchException
    {
        this.tura = czytnik.nextInt();
        czytnik.hasNextByte();
        this.iloscOrganizmow = czytnik.nextInt();
        czytnik.nextLine();
        for (int index = 0; index < iloscOrganizmow; index++)
        {
            String zczyt = czytnik.nextLine();
            switch (zczyt.charAt(2))
            {
                case 'A':
                    organizmy.set(index, new Antylopa(this.tura, this,new Pozycja(0, 0)));
                    break;
                case 'C':
                    organizmy.set(index, new Czlowiek(this.tura, this,new  Pozycja(0, 0)));
                    break;
                case 'W':
                    organizmy.set(index, new Wilk(this.tura, this,new  Pozycja(0, 0)));
                    break;
                case 'O':
                    organizmy.set(index, new Owca(this.tura, this,new  Pozycja(0, 0)));
                    break;
                case 'Q':
                    organizmy.set(index, new CyberOwca(this.tura, this, new Pozycja(0, 0)));
                    break;
                case 'L':
                    organizmy.set(index, new Lis(this.tura, this, new Pozycja(0, 0)));
                    break;
                case 'Z':
                    organizmy.set(index, new Zolw(this.tura, this, new Pozycja(0, 0)));
                    break;
                case 't':
                    organizmy.set(index, new Trawa(this.tura, this, new Pozycja(0, 0)));
                    break;
                case 'm':
                    organizmy.set(index, new Mlecz(this.tura, this, new Pozycja(0, 0)));
                    break;
                case 'g':
                    organizmy.set(index, new Guarana(this.tura, this, new Pozycja(0, 0)));
                    break;
                case 'j':
                    organizmy.set(index, new Jagoda(this.tura, this, new Pozycja(0, 0)));
                    break;
                case 'b':
                    organizmy.set(index, new Barszcz(this.tura, this, new Pozycja(0, 0)));
                    break;
            }
            organizmy.get(index).load(zczyt);
            mapa[organizmy.get(index).getKordy().m][organizmy.get(index).getKordy().n] = index;

        }
        this.okienko.loadPaint();
    }


    public boolean dodajOrganizm(int wybrany,Pozycja nowaPozycja)
    {
        // ograniczenie ilość organizmów nie może
        // przekraczać rozmiaru tablicy organizmów
        // usunięte organizmy są usuwane w turze następnej
        if(this.iloscOrganizmow < N * M + 10)
        {

            switch(wybrany)
            {
                default:
                    return false;
                case 0: //Nie Wybrano
                    return false;
                case 1: // Antylopa
                    organizmy.set(this.iloscOrganizmow, new Antylopa(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 2:// Cyber Owca
                    organizmy.set(this.iloscOrganizmow, new CyberOwca(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 3:// Czlowiek
                    if(jestCzlowiek()) return false;
                    organizmy.set(this.iloscOrganizmow, new Czlowiek(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 4: // Lis
                    organizmy.set(this.iloscOrganizmow, new Lis(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 5: // Owca
                    organizmy.set(this.iloscOrganizmow, new Owca(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 6: // Żółw"
                    organizmy.set(this.iloscOrganizmow, new Zolw(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 7: //Wilk
                    organizmy.set(this.iloscOrganizmow, new Wilk(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 8 : //Barszcz Sosaboskiego
                    organizmy.set(this.iloscOrganizmow, new Barszcz(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 9:// Guarana
                    organizmy.set(this.iloscOrganizmow, new Guarana(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 10:// Trawa
                    organizmy.set(this.iloscOrganizmow, new Trawa(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 11://Wilcze Jagody
                    organizmy.set(this.iloscOrganizmow, new Jagoda(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;
                case 12://Mlecz
                    organizmy.set(this.iloscOrganizmow, new Mlecz(this.tura, this,new Pozycja(nowaPozycja)));
                    this.iloscOrganizmow++;
                    break;

            }
            //jezeli ktoś tam jest to usuwa:
            int index = this.mapa[nowaPozycja.m][nowaPozycja.n];
            this.mapa[nowaPozycja.m][nowaPozycja.n] = this.iloscOrganizmow-1;
            if(index != -1)
            {
                System.out.println("Użytkownik Morduje");
                organizmy.set(index,null);
                //this.iloscOrganizmow--;
            }
            return true;
        }
        return false;
    }

    private boolean jestCzlowiek()
    {
        //czlowiek może być tylko jeden
        for(int i = 0 ; i  <this.iloscOrganizmow;i++)
        {
            if(this.organizmy.get(i) instanceof Czlowiek)
            {
                return true;
            }
        }
        return false;
    }

    public void test()
    {
        for(int m = 0 ; m  <M ; m++)
        {
            for(int n = 0 ; n  <N ; n++)
            {
                int index = mapa[m][n];
                if(index != -1)
                {

                    //dla każdego:
                    for (int y = 0; y < M; y++)
                    {

                        for (int x = 0; x < N; x++)
                        {
                            if (x != n && y != m)
                            {

                                int index2 = mapa[y][x];
                                if (index == index2)
                                {
                                    Organizm org = organizmy.get(index);
                                    System.out.println("Problem");
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
