#include "Trawa.h"

Trawa::Trawa(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Roslina(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 't';
	this->typ = Typ::trawa;
	this->szansaSiania = SZANSASIANIA;
};

Organizm * Trawa::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Trawa(turaUrodzenia, this->mojSwiat, kordy);
}