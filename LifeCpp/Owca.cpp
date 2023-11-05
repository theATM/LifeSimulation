#include "Owca.h"

Owca::Owca(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Zwierze(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'O';
	this->inicjatywa = this->STARTINICJATYWA;
	this->sila = this->STARTSILA;
	this->typ = Typ::owca;
};

Organizm * Owca::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Owca(turaUrodzenia,this->mojSwiat, kordy);
}


