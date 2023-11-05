#pragma once
#include <iostream>
class Pozycja
{
public:
	int m;
	int n;	
	Pozycja(int m, int n)
	{
		this->m = m;
		this->n = n;
	}
	Pozycja(const Pozycja &nowaPozycja) //copy constructor
	{
		m = nowaPozycja.m;
		n = nowaPozycja.n;
	}
	Pozycja() {};
	bool friend operator ==(const Pozycja &p1,const Pozycja &p2)
	{
		return (p1.m == p2.m && p1.n == p2.n) ? true : false;
	}
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
};