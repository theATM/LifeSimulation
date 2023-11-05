package atm.po.style;
//import atm.po.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Polygon;
import atm.po.logika.Pozycja;
import java.lang.Math;

public class OknoGry extends JPanel
{
    protected final int graPaddingY = 10;
    protected final int graPaddingX = 10;
    protected Rectangle wymiary;
    protected Color[][] colorMapa;
    protected boolean grid;
    protected int elemX;
    protected int elemY;
    protected int elemSizeX;
    protected int elemSizeY;
    protected int elemSizeHex;
    protected int radian;
    protected int M;
    protected int N;
    public boolean done;
    //private Swiat swiat;
    public OknoGry(Rectangle wymiary,Color[][] colorMapa, int M, int N , boolean grid)//Swiat swiat)
    {
        super.setBounds(wymiary);
        this.wymiary = wymiary;
        this.colorMapa = colorMapa;
        this.done = false;
        /*
        this.colorMapa = new Color[][]
        {
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.RED,Color.RED,Color.RED,Color.RED,Color.RED},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
                {Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE}
        };
         */
        this.grid = grid;
        this.M  =  M;
        this.N  = N;
        this.elemX = this.graPaddingX + wymiary.x;
        this.elemY = this.graPaddingY + wymiary.y;

        this.elemSizeX = (this.wymiary.width - 2* graPaddingX) / N;
        this.elemSizeY = (this.wymiary.height - 2* graPaddingY) / M;

        this.elemSizeHex = Math.min( this.elemSizeX,this.elemSizeY) ;
        this.radian =  this.elemSizeHex/2;

    }

    public void updateColorMapa(Color[][] colorMapa)
    {
        this.colorMapa = colorMapa;
        this.done = false;
    }

    @Override public void paintComponent(Graphics g)
    {
        if(grid)
        {
            for(int m=0;m<M;m++)
            {
                for(int n=0;n<N;n++)
                {
                    g.setColor(this.colorMapa[m][n]);
                    g.fillRect (this.elemX+n * this.elemSizeX, this.elemY+m  *this.elemSizeY, this.elemSizeX, this.elemSizeY);
                    g.setColor(Color.BLACK);
                    g.drawRect (this.elemX+n * this.elemSizeX, this.elemY+m * this.elemSizeY, this.elemSizeX, this.elemSizeY);


                }
            }
        }
        else
        {
            Polygon hex;
            for(int m=0;m<M;m++)
            {
                for (int n = 0; n < N; n++)
                {
                    if(n%2 == 0)
                    {
                       hex = createHex(this.elemX+this.elemSizeHex/2+3*this.elemSizeHex*n/4,this.elemY+(int)(Math.sqrt(3)*this.elemSizeHex/2+Math.sqrt(3)*this.elemSizeHex*m/2),this.radian);
                    }
                    else
                    {
                       hex = createHex(this.elemX+this.elemSizeHex/2+3*this.elemSizeHex*n/4,this.elemY+(int)(Math.sqrt(3)*this.elemSizeHex/4+Math.sqrt(3)*this.elemSizeHex*m/2),this.radian);
                    }
                    g.setColor(this.colorMapa[m][n]);
                    g.fillPolygon(hex);
                    g.setColor(Color.BLACK);
                    g.drawPolygon(hex);

                }
            }
        }
        this.done = true;
    }

    public boolean isInMap(int x,int y)
    {
        if(x > wymiary.x && x < wymiary.x + wymiary.width && y > wymiary.y && y < wymiary.y + wymiary.height)
        {
            return true;
        }
        return false;
    }

    public Pozycja getKordy(int x, int y)
    {
        if (grid)
        {
            int pozX=(x-elemX)/elemSizeX;
            int pozY=(y-elemY)/elemSizeY;
            if(pozX>=0&&pozY>=0&&pozX<N&&pozY<M)
            {
                Pozycja nowapozycja = new Pozycja(pozY,pozX);
                return nowapozycja;
            }
            return null;
        }
        else
        {
            Polygon hex;
            for(int m=0;m<M;m++)
            {
                for (int n = 0; n < N; n++)
                {
                    if(n%2 == 0)
                    {
                        hex = createHex(this.elemX+this.elemSizeHex/2+3*this.elemSizeHex*n/4,this.elemY+(int)(Math.sqrt(3)*this.elemSizeHex/2+Math.sqrt(3)*this.elemSizeHex*m/2),this.radian);
                    }
                    else
                    {
                        hex = createHex(this.elemX+this.elemSizeHex/2+3*this.elemSizeHex*n/4,this.elemY+(int)(Math.sqrt(3)*this.elemSizeHex/4+Math.sqrt(3)*this.elemSizeHex*m/2),this.radian);
                    }
                    if(hex.contains(x,y) )
                    {
                        Pozycja nowapozycja = new Pozycja(m,n);
                        return nowapozycja;
                    }
                }
            }
            return null;
        }
    }

    private Polygon createHex(int centerX,int centerY,int radius)
    {
        Polygon polygon = new Polygon();

        for (int i = 0; i < 6; i++)
        {
            int xval = (int) (centerX + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yval = (int) (centerY + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            polygon.addPoint(xval, yval);
        }

        return polygon;
    }




}
