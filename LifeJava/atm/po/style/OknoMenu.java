package atm.po.style;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoMenu extends JFrame
{
    //deklaracja zmiennych:
    protected int M;
    protected int N;
    protected boolean grid = true;
    protected boolean play = true;
    protected boolean myactive = true;
    protected JButton startButton;
    protected JButton wczytajButton;
    protected JButton mapaButton;
    protected JLabel Ml;
    protected JLabel Nl;
    protected JSlider Ms;
    protected JSlider Ns;

    public OknoMenu(int M , int N)
    {
        super("Menu");
        this.M = M;
        this.N = N;

        //komponenty:
        this.startButton = new JButton("Start");
        this.wczytajButton = new JButton("Wczytaj");
        this.mapaButton = new JButton("Grid");
        this.Ml = new JLabel("M:");
        this.Nl = new JLabel("N:");
        this.Ms = new JSlider(1,31,this.M);
        this.Ns = new JSlider(1,31,this.N);

        super.setLayout(null);
        super.setSize(220,300);

        //rozmieszczenie:
        this.startButton.setBounds(50,25,100,25);
        this.wczytajButton.setBounds(50,60,100,25);
        this.mapaButton.setBounds(50,90,100,25);
        this.Ml.setBounds(75,125,20,20);
        this.Ms.setBounds(50,145,100,40);
        this.Nl.setBounds(75,185,20,20);
        this.Ns.setBounds(50,205,100,40);

        //opcje:
        this.Ms.setPaintTrack(true);
        this.Ms.setPaintTicks(true);
        this.Ms.setPaintLabels(true);
        this.Ms.setMajorTickSpacing(10);
        this.Ms.setMinorTickSpacing(5);

        this.Ns.setPaintTrack(true);
        this.Ns.setPaintTicks(true);
        this.Ns.setPaintLabels(true);
        this.Ns.setMajorTickSpacing(10);
        this.Ns.setMinorTickSpacing(5);

        //gdy coś kliknę:
        this.startButton.addActionListener(startListen);
        this.wczytajButton.addActionListener(wczytajListen);
        this.mapaButton.addActionListener(mapaListen);

        //dodanie elementów do okna:
        super.getContentPane().add(this.startButton);
        super.getContentPane().add(this.wczytajButton);
        super.getContentPane().add(this.mapaButton);
        super.getContentPane().add(this.Ml);
        super.getContentPane().add(this.Ms);
        super.getContentPane().add(this.Nl);
        super.getContentPane().add(this.Ns);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);

    }

    private ActionListener startListen = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            //gdy kliknięty start:
            OknoMenu.this.play = true;
            //zapisanie wybranych ustawień:
            OknoMenu.this.M = OknoMenu.this.Ms.getValue();
            OknoMenu.this.N = OknoMenu.this.Ns.getValue();
            //start gry
            OknoMenu.this.myactive = false;
            OknoMenu.this.dispose();
        };
    };

    private ActionListener wczytajListen = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            //gdy kliknięty wczytaj
            //olanie wszelkich wybranych ustawień
            //wczytanie gry
            OknoMenu.this.play = false;
            OknoMenu.this.myactive = false;
            OknoMenu.this.dispose();
        };
    };

    private ActionListener mapaListen = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            //gdy kliknięty przycisk zmiany mapy
            if(grid == true)
            {
                OknoMenu.this.grid = false;
                OknoMenu.this.mapaButton.setText("Hex");
            }
            else
            {
                OknoMenu.this.grid = true;
                OknoMenu.this.mapaButton.setText("Grid");
            }


        }
    };

    public int getM() {return this.M;}
    public int getN() {return this.N;}
    public boolean getGrid() {return this.grid;}
    public boolean getPlay() {return this.play;}
    public boolean getMyActive(){return this.myactive;}
}
