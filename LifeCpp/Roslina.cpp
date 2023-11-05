#include "Roslina.h"
Roslina::Roslina( int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Organizm( turaUrodzenia, nowySwiat, kordy)
{
	this->inicjatywa = this->DEFAULTSTARTINICJATYWA;
	this->sila = this->DEFAULTSTARTSILA;
	this->szansaSiania = this->DEFAULTSZANSASIANIA;
	this->iloscNasionNaTure = this->DEFAULTILOSCNASIONNATURE;
};



Event* Roslina::akcja() 
{
	//rozprzestrzenianie si�
	EventList * listaEventow = new EventList(this->kordy);
	int pozostalaIloscProb = this->iloscNasionNaTure;
	while (pozostalaIloscProb)
	{
		if (decyzja(this->szansaSiania))
		{
			//b�d� sia�:
			int nowePole = this->wyborPola(true); //pole gdzie nikogo nie ma
			Pozycja nowekordy = this->kordy;
			switch (nowePole)
			{
			case -1: //can't move
				continue;
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
			listaEventow->push(new Event(this->kordy, Call::tworzy, nowekordy));
		}
		pozostalaIloscProb--;
	}
	if (listaEventow->czyNiePusta())
	{
		return listaEventow;
	}
	else
	{
		delete listaEventow;
		return NULL;
	}

}
Event* Roslina::kolizja(Organizm * napastnik) 
{ 
	//kto� na mnie wszed�
	// walka :(
	//por�wnuje jego sile z moj�
	if (napastnik->getSila() >= this->sila)
	{
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
bool Roslina::reakcja(Event * ewencik) 
{
	switch (ewencik->getCo())
	{
	case Call::ruch:
		return false; //ro�liny si� nie ruszaj�
		break;
	case Call::truje:
		return false; //ro�liny s� odporne na zatrucia
		break;
	}
	return false;
};