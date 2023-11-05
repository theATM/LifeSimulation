package atm.po.logika;

public class Event
{
    protected Pozycja kto;
    protected Pozycja gdzie;
    protected Call co;
    public Event(){};
    public Event(Pozycja kto, Call co, Pozycja gdzie)
    {
        this.kto = new Pozycja(kto);
        this.co = co;
        this.gdzie = new Pozycja(gdzie);
    }
    public Call getCo()
    {
        return this.co;
    }
    public Pozycja getKto()
    {
        return this.kto;
    }
    public Pozycja getGdzie()
    {
        return this.gdzie;
    }
}
