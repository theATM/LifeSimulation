#include "WilczeJagody.h"

WilczeJagody::WilczeJagody(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Roslina(turaUrodzenia, nowySwiat, kordy)
{
	this->iloscNasionNaTure = this->ILOSCNASIONNATURE;
	this->kod = 'j';
	this->sila = this->STARTSILA;
	this->typ = Typ::wilczeJagody;
};

Event* WilczeJagody::kolizja(Organizm * napastnik)
{
	//ktoœ na mnie wszed³
	// walka :(
	//ginê ale ginie napastnik  te¿ :)
	EventList * listaEventow = new EventList(this->kordy);
	listaEventow->push(new Event(napastnik->getKordy(), Call::morduje, this->kordy));
	listaEventow->push(new Event(this->kordy, Call::truje, napastnik->getKordy()));
	listaEventow->push(new Event(napastnik->getKordy(), Call::ruch, this->kordy));
	return listaEventow;
};

Organizm * WilczeJagody::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new WilczeJagody(turaUrodzenia, this->mojSwiat, kordy);
}