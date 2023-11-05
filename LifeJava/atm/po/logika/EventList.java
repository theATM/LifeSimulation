package atm.po.logika;

public class EventList extends Event
{
    //Lista FIFO:
    private class Lista
    {
        Lista next = null;
        Event evencik;
        Lista(Event  evencik)
        {
            this.evencik = evencik;
        }
    };
    private Lista lista;


    public EventList(Pozycja kto)
    {
        this.lista = null;
        this.co = Call.eventlist;
        this.kto = new Pozycja(kto);
        this.gdzie = new Pozycja(kto);
    }
    public Event pop()
    {
        if (this.lista == null)
        return null; //nie ma elementów

        Event wynik;
        wynik = this.lista.evencik;
        if (this.lista.next == null)
        {
            //jest jeden element:
            this.lista = null;
        }
	    else
        {
            // jest wiele elementów
            Lista buf = this.lista;
            this.lista = this.lista.next;
            buf = null;
        }
        return wynik;
    }
    public void push(Event evencik)
    {
        //jeżeli nie ma elementów listy:
        if (this.lista == null)
        {
            this.lista = new Lista(evencik);
            return;
        }
        //szukamy ostatniego elementu listy:
        Lista l;
        for (l = this.lista; l.next != null; l = l.next);
        l.next = new Lista(evencik);
        return;
    }
    public boolean czyNiePusta()
    {
        return (this.lista != null) ? (true) : (false);
    }
}

