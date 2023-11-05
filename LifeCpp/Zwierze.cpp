#include "Zwierze.h"

Zwierze::Zwierze(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
		:Organizm( turaUrodzenia, nowySwiat, kordy){};

Event* Zwierze::akcja()
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
	return new Event(this->kordy,Call::ruch,nowekordy);
}
Event* Zwierze::kolizja(Organizm * napastnik)
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
	}
	return 0;
};

bool Zwierze::reakcja(Event * evencik)
{
	switch (evencik->getCo())
	{
	case Call::ruch:
		//mo�na sie bezpiecznie ruszy�!
		if (evencik->getKto() == this->kordy)
		{
			this->kordy = evencik->getGdzie();
			return true;
		}
		break;
	case Call::truje:
		{
			return true; // jest podatny;
		}
	}
	return false;
}
void Zwierze::save(std::ofstream &plik)
{
	this->Organizm::save(plik);
}
void Zwierze::load(std::ifstream &plik)
{
	this->Organizm::load(plik);
}
