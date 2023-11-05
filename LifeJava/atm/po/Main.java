package atm.po;
import atm.po.style.OknoMenu;
import atm.po.style.OknoApki;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class Main
{
    public static final String path = "Saves\\save.txt";
    private static int M = 10;
    private static int N = 10;
    private static boolean grid = true;
    public static void main(String[] args)
    {
        OknoMenu oknoMenu = new OknoMenu(Main.M, Main.N);
        while(oknoMenu.isActive() || oknoMenu.getMyActive());
        if(oknoMenu.getPlay())
        {
            // nowa gra
            Main.M = oknoMenu.getM();
            Main.N = oknoMenu.getN();
            Main.grid = oknoMenu.getGrid();
            Swiat swiatZabawy = new Swiat(Main.M, Main.N, Main.grid);
        }
        else
        {
            Scanner czytnik;
            try
            {
                czytnik =  Main.load();
            }
            catch(FileNotFoundException e)
            {
                System.out.println("No such file.");
                return;
            }
            //odczytanie danych:
            boolean NewGrid;
            int NewM;
            int NewN;
            try
            {
                NewGrid = czytnik.hasNextBoolean();
                czytnik.nextLine();
                NewM = czytnik.nextInt();
                czytnik.hasNextByte();
                NewN = czytnik.nextInt();
                czytnik.nextLine();

            }
            catch ( InputMismatchException e)
            {
                System.out.println("Mallitious Save File.");
                return;
            }
            Swiat swiatZabawy = new Swiat(NewM, NewN, NewGrid);
            try
            {
                swiatZabawy.load(czytnik);
            }
            catch ( InputMismatchException e)
            {
                System.out.println("Mallitious Save File.");
                return;
            }
            czytnik.close();

        }

    }

    private static Scanner load() throws FileNotFoundException
    {
        //wczytanie starej gry:
        File savePlik = new File(path);
        System.out.println(savePlik.exists());
        Scanner czytnik = new Scanner(savePlik);
        return czytnik;
    }
    public static FileWriter save() throws IOException
    {
        //zapisanie obecnej gry
        File savePlik = new File(path);
        FileWriter myWriter = new FileWriter(savePlik);
        return myWriter;

    }
}
