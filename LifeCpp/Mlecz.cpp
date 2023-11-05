#include "Mlecz.h"

Mlecz::Mlecz(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Roslina(turaUrodzenia, nowySwiat, kordy)
{
	this->iloscNasionNaTure = this->ILOSCNASIONNATURE;
	this->kod = 'm';
	this->typ = Typ::mlecz;
};

Organizm * Mlecz::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Mlecz(turaUrodzenia, this->mojSwiat, kordy);
}