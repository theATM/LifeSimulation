package atm.po.style;
import atm.po.Swiat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import atm.po.logika.Pozycja;

public class OknoApki extends JFrame
{
    //stałe:
    protected final int oknoSizeX = 1000;
    protected final int oknoSizeY = 600;
    //menu panel:
    protected final int menuPanelXMargin = 0;
    protected final int menuPanelYMargin = 0;
    protected final int menuPanelXSize = 120;
    protected final int menuPanelYSize = oknoSizeY - 2 * menuPanelYMargin - 50;
    protected final int menuPanelXPadding = 10;
    protected final int menuPanelYPadding = 10;
    protected final int menuPanelElementXSize = menuPanelXSize - 2 * menuPanelXPadding;
    protected final int menuPanelElementYSize = 25;
    //pole gry:
    protected final int graMarginX =  menuPanelXMargin + menuPanelXSize;
    protected final int graSizeX  = oknoSizeX - menuPanelXSize - menuPanelXMargin -10;
    protected final int graSizeY  = oknoSizeY - 50;
    protected final int graMarginY =  menuPanelYMargin;

    //Komponenty:
    protected JPanel menuPanel;
    protected JButton startButton;
    protected JButton zapiszButton;
    protected JComboBox wyborNowego;
    protected JLabel wyborNowegoLabel;
    protected JLabel turaLabel;
    protected OknoGry oknoGry;

    //zmienne:
    private boolean grid;
    protected int wybrany = -1;
    //element:
    protected int gridElenentXSize;
    protected int gridElenentYSize;
    protected Swiat mojSwiat;

    public OknoApki(boolean grid,Swiat mojSwiat)
    {
        super("Symulator Życia");
        //super.setLayout(null);
        super.setSize(1000,600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] odp = { "Nie Wybrano" , "Antylopa" ,  "Cyber Owca" , "Czlowiek" , "Lis" , "Owca" , "Żółw" , "Wilk",
                "Barszcz Sosaboskiego" , "Guarana" , "Trawa" , "Wilcze Jagody"  , "Mlecz"};
        //init:
        this.grid = grid;
        this.mojSwiat = mojSwiat;
        this.menuPanel = new JPanel();
        this.turaLabel = new JLabel("Witaj w Grze!");
        this.startButton = new JButton("Start");
        this.zapiszButton = new JButton("Zapisz");
        this.wyborNowegoLabel = new JLabel("Dodaj Organizm:");
        this.wyborNowego = new JComboBox(odp);
        this.oknoGry = new OknoGry(new Rectangle(this.graMarginX,this.graMarginY,this.graSizeX,this.graSizeY),this.mojSwiat.sendMapaToRys(),this.mojSwiat.getM(),this.mojSwiat.getN(),this.grid);
        //Opcje:
        this.wyborNowego.setSelectedIndex(0);
        this.menuPanel.setBackground(Color.gray);
        this.menuPanel.setLayout(null);
        this.zapiszButton.setVisible(false);
        this.wyborNowegoLabel.setVisible(false);
        this.wyborNowego.setVisible(false);
        //Rozmiary:
        this.menuPanel.setBounds(this.menuPanelXMargin,this.menuPanelYMargin,this.menuPanelXSize,this.menuPanelYSize);
        this.turaLabel.setBounds(this.menuPanelXPadding + this.menuPanelXMargin,  this.menuPanelYPadding + this.menuPanelYMargin ,this.menuPanelElementXSize,this.menuPanelElementYSize);
        this.startButton.setBounds(this.menuPanelXPadding + this.menuPanelXMargin,2 *  this.menuPanelYPadding + this.menuPanelYMargin +  this.menuPanelElementYSize ,this.menuPanelElementXSize,this.menuPanelElementYSize);
        this.zapiszButton.setBounds(this.menuPanelXPadding + this.menuPanelXMargin,3 * this.menuPanelYPadding + this.menuPanelYMargin + 2 * this.menuPanelElementYSize ,this.menuPanelElementXSize,this.menuPanelElementYSize);
        this.wyborNowegoLabel.setBounds(this.menuPanelXPadding + this.menuPanelXMargin,4 * this.menuPanelYPadding + this.menuPanelYMargin + 3 * this.menuPanelElementYSize ,this.menuPanelElementXSize,this.menuPanelElementYSize);
        this.wyborNowego.setBounds(this.menuPanelXPadding + this.menuPanelXMargin,5 * this.menuPanelYPadding + this.menuPanelYMargin + 4 * this.menuPanelElementYSize ,this.menuPanelElementXSize,this.menuPanelElementYSize);
        //Listenery:
        this.startButton.addActionListener(startListen);
        this.zapiszButton.addActionListener(zapiszListen);
        this.wyborNowego.addActionListener(wyborNowegoListen);
        //this.oknoGry.addMouseListener(dodajNowegoListener);
        super.getContentPane().addKeyListener(czlowiekRuchListener);
        super.getContentPane().addMouseListener(dodajNowegoListener);
        //Dodanie:
        this.menuPanel.add(this.turaLabel);
        this.menuPanel.add(this.startButton);
        this.menuPanel.add(this.zapiszButton);
        this.menuPanel.add(this.wyborNowegoLabel);
        this.menuPanel.add(this.wyborNowego);
        super.getContentPane().add(this.menuPanel);
        super.getContentPane().add(oknoGry);
        //rysowanie:
        super.repaint();
        super.getContentPane().setFocusable(true);
        super.getContentPane().requestFocusInWindow();
        super.setVisible(true);

    }

    public void loadPaint()
    {
        OknoApki.this.zapiszButton.setVisible(true);
        OknoApki.this.wyborNowegoLabel.setVisible(true);
        OknoApki.this.wyborNowego.setVisible(true);
        OknoApki.this.startButton.setText("Tura");
        OknoApki.super.getContentPane().remove(oknoGry);
        oknoGry = new OknoGry(new Rectangle(OknoApki.this.graMarginX,OknoApki.this.graMarginY,OknoApki.this.graSizeX,OknoApki.this.graSizeY),OknoApki.this.mojSwiat.sendMapaToRys(),OknoApki.this.mojSwiat.getM(),OknoApki.this.mojSwiat.getN(), OknoApki.this.grid);
        OknoApki.super.getContentPane().add(oknoGry);
        OknoApki.super.repaint();
        OknoApki.super.getContentPane().setFocusable(true);
        OknoApki.super.getContentPane().requestFocusInWindow();
        OknoApki.super.setVisible(true);
    }

    private ActionListener startListen = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //if(OknoApki.this.oknoGry.done == true)
           // {

                //TODO:
                //next tura
                if(OknoApki.this.mojSwiat.getTura() == 0)
                {
                    OknoApki.this.startButton.setText("Tura");
                    OknoApki.this.mojSwiat.done = false;
                    OknoApki.this.mojSwiat.iniMapa();
                    while(OknoApki.this.mojSwiat.done == false);
                    OknoApki.this.zapiszButton.setVisible(true);
                    OknoApki.this.wyborNowegoLabel.setVisible(true);
                    OknoApki.this.wyborNowego.setVisible(true);

                }
                else
                {
                    OknoApki.this.mojSwiat.done = false;
                    OknoApki.this.mojSwiat.zycie();
                    while(OknoApki.this.mojSwiat.done == false);
                }
                //OknoApki.this.mojSwiat.done = false;
                OknoApki.super.getContentPane().remove(oknoGry);
                oknoGry = new OknoGry(new Rectangle(OknoApki.this.graMarginX,OknoApki.this.graMarginY,OknoApki.this.graSizeX,OknoApki.this.graSizeY),OknoApki.this.mojSwiat.sendMapaToRys(),OknoApki.this.mojSwiat.getM(),OknoApki.this.mojSwiat.getN(), OknoApki.this.grid);
                //OknoApki.this.oknoGry.updateColorMapa(OknoApki.this.mojSwiat.sendMapaToRys());
                //while(OknoApki.this.mojSwiat.done == false);
                OknoApki.super.getContentPane().add(oknoGry);
                OknoApki.super.repaint();
                OknoApki.super.getContentPane().setFocusable(true);
                OknoApki.super.getContentPane().requestFocusInWindow();
                OknoApki.super.setVisible(true);
           // }
        }
    };
    private ActionListener zapiszListen = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //TODO :
            //zapis swiata
            OknoApki.this.mojSwiat.save();
            OknoApki.super.getContentPane().setFocusable(true);
            OknoApki.super.getContentPane().requestFocusInWindow();
        }
    };
    private ActionListener wyborNowegoListen = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            wybrany = wyborNowego.getSelectedIndex();
            OknoApki.super.setFocusable(true);
            OknoApki.super.requestFocusInWindow();
            //mojSwiat.getColorsFromSelect(index);

        }
    };

    private KeyListener  czlowiekRuchListener = new KeyListener()
    {

        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            boolean wysylac = false;
            boolean supermoc = false;
            int newm = 0;
            int newn = 0;
            if(e.getKeyChar()=='s')
            {
                supermoc = true;
                wysylac = true;
            }
            else
            {
                int keyCode = e.getKeyCode();
                switch( keyCode )
                {
                    case KeyEvent.VK_UP:
                        // handle up
                        newm-=1;
                        wysylac = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        // handle down
                        newm+=1;
                        wysylac = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        // handle left
                        newn-=1;
                        wysylac = true;
                        break;
                    case KeyEvent.VK_RIGHT :
                        // handle right
                        newn+=1;
                        wysylac = true;
                        break;
                }
            }
            if(wysylac)
            {
                OknoApki.this.mojSwiat.ruchCzlowieka(newm,newn,supermoc);
            }
            OknoApki.super.getContentPane().setFocusable(true);
            OknoApki.super.getContentPane().requestFocusInWindow();
        }

        @Override
        public void keyReleased(KeyEvent e)
        {

        }
    };

    private MouseListener dodajNowegoListener = new MouseListener()
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            int x=e.getX();
            int y=e.getY();
            System.out.println(x+","+y);
            if(OknoApki.this.oknoGry.isInMap(x,y))
            {
                if(OknoApki.this.wybrany!=-1)
                {
                    //kliknąłem w pole gry:
                    Pozycja nowaPozycja = OknoApki.this.oknoGry.getKordy(x,y);
                    if(nowaPozycja!= null)
                    {
                        boolean result = OknoApki.this.mojSwiat.dodajOrganizm(wybrany,nowaPozycja);
                        if(result == true)
                        {
                            OknoApki.super.getContentPane().remove(oknoGry);
                            OknoApki.this.oknoGry = new OknoGry(new Rectangle(OknoApki.this.graMarginX,OknoApki.this.graMarginY,OknoApki.this.graSizeX,OknoApki.this.graSizeY),OknoApki.this.mojSwiat.sendMapaToRys(),OknoApki.this.mojSwiat.getM(),OknoApki.this.mojSwiat.getN(), OknoApki.this.grid);
                            OknoApki.super.getContentPane().add(oknoGry);
                            OknoApki.super.repaint();
                        }
                    }
                }
            }

            OknoApki.super.getContentPane().setFocusable(true);
            OknoApki.super.getContentPane().requestFocusInWindow();
            OknoApki.super.setVisible(true);

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    };

    public boolean isOknoApkiBizzy()
    {
        return !(OknoApki.this.oknoGry.done);
    }
    //TODO:
    //dodać listenera myszki
    //dodać listenera klawiatury

}
