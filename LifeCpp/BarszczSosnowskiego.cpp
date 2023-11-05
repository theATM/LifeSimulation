#include "BarszczSosnowskiego.h"
#include "Swiat.h"
BarszczSosnowskiego::BarszczSosnowskiego(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Roslina(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'b';
	this->sila = this->STARTSILA;
	this->typ = Typ::barszSosnowskiego;
	this->szansaSiania = 0.0001;
};

Event* BarszczSosnowskiego::akcja()
{
	//rozprzestrzenianie siê
	EventList * listaEventow = new EventList(this->kordy);
	//otrucie wiszystkich dooko³a
	if (this->kordy.n > 0 && this->mojSwiat->getMapaIndex(Pozycja(this->kordy.m, this->kordy.n - 1)) != -1)
	{
		Pozycja nowekordy = this->kordy;
		nowekordy.n--;
		listaEventow->push(new Event(this->kordy, Call::truje, nowekordy));
	}
	if (this->kordy.n < this->mojSwiat->getN() - 1 && this->mojSwiat->getMapaIndex(Pozycja(this->kordy.m, this->kordy.n + 1)) != -1)
	{
		Pozycja nowekordy = this->kordy;
		nowekordy.n++;
		listaEventow->push(new Event(this->kordy, Call::truje, nowekordy));
	}
	if (this->kordy.m > 0 && this->mojSwiat->getMapaIndex(Pozycja(this->kordy.m - 1, this->kordy.n)) != -1)
	{
		Pozycja nowekordy = this->kordy;
		nowekordy.m--;
		listaEventow->push(new Event(this->kordy, Call::truje, nowekordy));
	}
	if (this->kordy.m < this->mojSwiat->getM() - 1 && this->mojSwiat->getMapaIndex(Pozycja(this->kordy.m + 1, this->kordy.n)) != -1)
	{
		Pozycja nowekordy = this->kordy;
		nowekordy.m++;
		listaEventow->push(new Event(this->kordy, Call::truje, nowekordy));
	}

	int pozostalaIloscProb = this->iloscNasionNaTure;
	while (pozostalaIloscProb)
	{
		if (decyzja(this->szansaSiania))
		{
			//bêdê sia³:
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
	
	//zwaracnie listy eventow:
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


Event* BarszczSosnowskiego::kolizja(Organizm * napastnik)
{
	//ktoœ na mnie wszed³
	// walka :(
	if (napastnik->getTyp() == Typ::cyberOwca)
	{
		//przegra³em ginê :(
		EventList * listaEventow = new EventList(this->kordy);
		listaEventow->push(new Event(napastnik->getKordy(), Call::morduje, this->kordy));
		listaEventow->push(new Event(napastnik->getKordy(), Call::ruch, this->kordy));
		return listaEventow;
	}
	else
	{
		//ginê ale ginie napastnik  te¿ :)
		EventList * listaEventow = new EventList(this->kordy);
		listaEventow->push(new Event(napastnik->getKordy(), Call::morduje, this->kordy));
		listaEventow->push(new Event(this->kordy, Call::truje, napastnik->getKordy()));
		listaEventow->push(new Event(napastnik->getKordy(), Call::ruch, this->kordy));
		return listaEventow;
	}
};
Organizm * BarszczSosnowskiego::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new BarszczSosnowskiego(turaUrodzenia, this->mojSwiat, kordy);
}