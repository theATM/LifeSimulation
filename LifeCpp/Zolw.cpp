#include "Zolw.h"

Zolw::Zolw(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Zwierze(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'Z';
	this->inicjatywa = this->STARTINICJATYWA;
	this->sila = this->STARTSILA;
	this->typ = Typ::zolw;
};

Event* Zolw::akcja()
{
	//ruch
	if (this->decyzja(SZANSARUCHU))
	{

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
		return new Event(this->kordy, Call::ruch, nowekordy);
	}
	return NULL;

}

Event* Zolw::kolizja(Organizm * napastnik)
{
	//kto� na mnie wszed� :(
	//czy by� to przedstawiciel tego samego gatunku?
	if (this->typ == (Typ)napastnik->getTyp())
	{
		//czy mo�emy?
		if (this->czyGotowy() && napastnik->czyGotowy())
		{
			// zabieram si� do pracy :)
			int nowePole = this->wyborPola(true);
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
			return new Event(this->kordy, Call::tworzy, nowekordy);
		}
		else
		{
			//nie dzi�
			return NULL;
		}
	}
	else
	{
		// walka :(
		//por�wnuje jego sile z moj�
		if (napastnik->getSila() >= MAXBLOCKSILA)
		{
			//przegra�em gin� :(

			EventList * listaEventow = new EventList(this->kordy);
			listaEventow->push(new Event(napastnik->getKordy(), Call::morduje, this->kordy));
			listaEventow->push(new Event(napastnik->getKordy(), Call::ruch, this->kordy));
			return listaEventow;
		}
		else
		{
			//wygra�em napastnik si� cofa :)
			return NULL;
		}
	}
	return 0;
}

Organizm * Zolw::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Zolw(turaUrodzenia, this->mojSwiat, kordy);
}


