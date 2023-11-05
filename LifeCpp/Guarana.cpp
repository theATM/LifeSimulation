#include "Guarana.h"

Guarana::Guarana(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Roslina(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'g';
	this->typ = Typ::guarana;
};

Event* Guarana::kolizja(Organizm * napastnik)
{
	//kto� na mnie wszed�
	// walka :(
	//por�wnuje jego sile z moj�
	if (napastnik->getSila() >= this->sila)
	{
		//daj� napastnikowi super si��:
		napastnik->addSila(3);
		//przegra�em gin� :(
		EventList * listaEventow = new EventList(this->kordy);
		listaEventow->push(new Event(napastnik->getKordy(), Call::morduje, this->kordy));
		listaEventow->push(new Event(napastnik->getKordy(), Call::ruch, this->kordy));
		return listaEventow;
	}
	else
	{
		//wygra�em ginie napastnik :)
		return new Event(this->kordy, Call::morduje, napastnik->getKordy());
	}
};

Organizm * Guarana::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Guarana(turaUrodzenia, this->mojSwiat, kordy);
}