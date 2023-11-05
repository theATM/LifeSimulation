package atm.po.logika;

public class Pozycja
{
    public int m;
    public int n;
    public Pozycja(int m, int n)
    {
        this.m = m;
        this.n = n;
    }
    public Pozycja(Pozycja nowaPozycja) //copy constructor
    {
        m = nowaPozycja.m;
        n = nowaPozycja.n;
    }
    public Pozycja() {};
    public boolean compere(Pozycja p2)
    {
        //if the same return true else false
        return (this.m == p2.m && this.n == p2.n) ? true : false;
    }

    public String save()
    {
        return "< " + String.valueOf(m) + " , " + String.valueOf(n) + " > ";
    }
    public void load(String zczyt1,String zczyt2)
    {
        this.m = Integer.parseInt(zczyt1);
        this.n = Integer.parseInt(zczyt2);
    }
    /*
    friend std::ostream& operator<<(std::ostream& out, const Pozycja & p)
    {
        out << "< " << p.m << " , " << p.n << " > ";
        return out;
    }
    friend std::istream& operator >> (std::istream& in, Pozycja &p)
    {
        char c;
        in >> c;
        in >>p.m;
        in >> c;
        in >> p.n;
        in >> c;
        return in;
    }
    */

}
