#include "Guarana.h"

Guarana::Guarana(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Roslina(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'g';
	this->typ = Typ::guarana;
};

Event* Guarana::kolizja(Organizm * napastnik)
{
	//ktoœ na mnie wszed³
	// walka :(
	//porównuje jego sile z moj¹
	if (napastnik->getSila() >= this->sila)
	{
		//dajê napastnikowi super si³ê:
		napastnik->addSila(3);
		//przegra³em ginê :(
		EventList * listaEventow = new EventList(this->kordy);
		listaEventow->push(new Event(napastnik->getKordy(), Call::morduje, this->kordy));
		listaEventow->push(new Event(napastnik->getKordy(), Call::ruch, this->kordy));
		return listaEventow;
	}
	else
	{
		//wygra³em ginie napastnik :)
		return new Event(this->kordy, Call::morduje, napastnik->getKordy());
	}
};

Organizm * Guarana::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Guarana(turaUrodzenia, this->mojSwiat, kordy);
}