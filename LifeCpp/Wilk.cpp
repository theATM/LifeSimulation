#include "Wilk.h"

Wilk::Wilk(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Zwierze( turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'W';
	this->inicjatywa = this->STARTINICJATYWA;
	this->sila = this->STARTSILA;
	this->typ = Typ::wilk;
};

Organizm * Wilk::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Wilk(turaUrodzenia, this->mojSwiat, kordy);
}