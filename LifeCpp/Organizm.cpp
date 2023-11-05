#include "Organizm.h"
#include "Swiat.h"
using namespace std;



Organizm::Organizm(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
{
	this->wiek = turaUrodzenia;
	this->mojSwiat = nowySwiat;
	this->kordy = kordy;
	this->sila = 0;
	this->inicjatywa = 0;
	this->niegotowosc = 0; 
	this->maxniegotowosc = 0;
}

bool Organizm::decyzja(float szansa)
{
	double mysli = (double)rand() / RAND_MAX;
	return (mysli <= (double)szansa) ? (true) : (false);
}

int Organizm::wyborPola(bool bezpieczne = false) 
{
	//pola :  0   0     0    0
	//        UP DOWN LEFT RIGHT
	int maxpola = 4;
	uint8_t pola = 0x0F; // 1 1 1 1

	if (this->kordy.n == 0 || (bezpieczne && this->mojSwiat->getMapaIndex(Pozycja(this->kordy.m, this->kordy.n-1)) != -1))
	{
		pola = pola & 0xFD; //NO LEFT
		maxpola--;
	}
	if (this->kordy.n == this->mojSwiat->getN() - 1 || (bezpieczne && this->mojSwiat->getMapaIndex(Pozycja(this->kordy.m, this->kordy.n+1)) != -1))
	{
		pola = pola & 0xFE; //NO RIGHT
		maxpola--;
	}
	if (this->kordy.m == 0 || (bezpieczne && this->mojSwiat->getMapaIndex(Pozycja(this->kordy.m-1, this->kordy.n )) != -1))
	{
		pola = pola & 0xF7; //NO UP
		maxpola--;
	}
	if (this->kordy.m == this->mojSwiat->getM() - 1 || (bezpieczne && this->mojSwiat->getMapaIndex(Pozycja(this->kordy.m+1, this->kordy.n )) != -1))
	{
		pola = pola & 0xFB; //NO DOWN
		maxpola--;
	}

	if (maxpola == 0) //gdy zero to nie mo¿e sie ruszyæ!
		return -1;

	int wybrany = 0;
	int numerPola = rand() % maxpola;
	while (numerPola >= 0) 
	{
		int bit = pola & 0x01; // najm³odszy bit
		if (bit)
			numerPola--;
		pola = pola >> 1;
		wybrany++;
	}
	return wybrany - 1;
}

std::ostream& operator<<(std::ostream& out, Typ typ)
{
	switch (typ)
	{
	case Typ::czlowiek:
		out <<"czlowiek";
		break;
	case Typ::owca:
		out <<"owca";
		break;
	case Typ::wilk:
		out << "wilk";
		break;
	case Typ::lis:
		out << "lis";
		break;
	case Typ::zolw:
		out << "zolw";
		break;
	case Typ::antylopa:
		out << "antylopa";
		break;
	case Typ::cyberOwca:
		out << "cyber owca";
		break;
	case Typ::trawa:
		out <<"trawa";
		break;
	case Typ::mlecz:
		out << "mlecz";
		break;
	case Typ::guarana:
		out << "guarana";
		break;
	case Typ::wilczeJagody:
		out << "wilcze jagody";
		break;
	case Typ::barszSosnowskiego:
		out << "barsz sosnowskiego";
		break;
	default:
		out <<"nieznany";
		break;
	}
	return out;
}

void Organizm::rysuj()
{
	cout << kod;
}
void Organizm::addSila(int ile)
{
	if (this->sila + ile > 0)
	{
		this->sila += ile;
	}
	else
	{
		this->sila = 0;
	}
}

bool Organizm::czyGotowy() // potencjalny sposób na zbalansowanie iloœci narodzin
{
	if(!this->niegotowosc)
	{
		this->niegotowosc = this->maxniegotowosc;
		return true;
	}
	return false;
}
Pozycja Organizm::getKordy()
{
	return this->kordy;
}

int Organizm::getSila()
{
	return this->sila;
}
int Organizm::getInicjatywa()
{
	return this->inicjatywa;
}
int Organizm::getWiek()
{
	return wiek;
}
Typ Organizm::getTyp()
{
	return this->typ;
}
void Organizm::save(ofstream &plik)
{
	plik << "[ " << kod << " " << wiek << " " << sila << " " << inicjatywa;
	plik << " " << this->kordy << "] " << std::endl;
}
void Organizm::load(ifstream &plik)
{
	plik >> wiek;
	plik >> sila;
	plik >> inicjatywa;
	plik >> kordy;
	char c;
	plik >> c;
}