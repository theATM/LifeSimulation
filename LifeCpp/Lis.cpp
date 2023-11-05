#include "Lis.h"
#include "Swiat.h"

Lis::Lis(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Zwierze(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'L';
	this->inicjatywa = this->STARTINICJATYWA;
	this->sila = this->STARTSILA;
	this->typ = Typ::lis;
};


Event* Lis::akcja()
{
	//ruch
	int nowePole = this->wyborPola(false);
	Pozycja nowekordy = this->kordy;
	switch (nowePole)
	{
	case -1: //can't move
		return NULL;
		break;
	case 0: // RIGHT
		nowekordy.n++;
		break;
	case 1: // LEFT
		nowekordy.n--;
		break;
	case 2: // DOWN
		nowekordy.m++;
		break;
	case 3: // UP
		nowekordy.m--;
		break;
	}
	int index = this->mojSwiat->getMapaIndex(nowekordy);
	if (index != -1)
	{
		Organizm * zagrorzenie = this->mojSwiat->getOrganizm(index);
		if (zagrorzenie->getSila() <= this->getSila())
		{
			return NULL; //wyczuwa zagrorzenie nie rusza siê
		}
	}
	return new Event(this->kordy, Call::ruch, nowekordy);
}

Organizm * Lis::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Lis(turaUrodzenia, this->mojSwiat, kordy);
}
