#include "Antylopa.h"
#include "Swiat.h"

Antylopa::Antylopa(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Zwierze(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'A';
	this->inicjatywa = this->STARTINICJATYWA;
	this->sila = this->STARTSILA;
	this->typ = Typ::antylopa;
};

Event* Antylopa::akcja()
{
	//ruch
	int nowePole = this->wyborPolaAntylopy();
	Pozycja nowekordy = this->kordy;
	switch (nowePole)
	{
	case -1: //can't move
		return NULL;
		break;
	case 0: // RIGHT
		nowekordy.n += 2; // gdy supermocy nie ma to +1 gdy jest to +2
		break;
	case 1: // LEFT
		nowekordy.n -= 2;
		break;
	case 2: // DOWN
		nowekordy.m += 2;
		break;
	case 3: // UP
		nowekordy.m -= 2;
		break;
	}
	return new Event(this->kordy, Call::ruch, nowekordy);
};


int Antylopa::wyborPolaAntylopy()
{
	//pola :  0   0     0    0
	//        UP DOWN LEFT RIGHT
	int maxpola = 4;
	uint8_t pola = 0x0F; // 1 1 1 1

	if (this->kordy.n <= 1)
	{
		pola = pola & 0xFD; //NO LEFT
		maxpola--;
	}
	if (this->kordy.n >= this->mojSwiat->getN() - 2)
	{
		pola = pola & 0xFE; //NO RIGHT
		maxpola--;
	}
	if (this->kordy.m <= 1)
	{
		pola = pola & 0xF7; //NO UP
		maxpola--;
	}
	if (this->kordy.m >= this->mojSwiat->getM() - 2)
	{
		pola = pola & 0xFB; //NO DOWN
		maxpola--;
	}

	if (maxpola == 0) //gdy zero to nie mo¿e sie ruszyæ!
		return -1;

	int wybrany = 0;
	int numerPola = rand() % maxpola;
	while (numerPola >= 0)
	{
		int bit = pola & 0x01; // najm³odszy bit
		if (bit)
			numerPola--;
		pola = pola >> 1;
		wybrany++;
	}
	return wybrany - 1;
}

Event* Antylopa::kolizja(Organizm * napastnik)
{
	//ktoœ na mnie wszed³ :(
	//czy by³ to przedstawiciel tego samego gatunku?
	if (this->typ == (Typ)napastnik->getTyp())
	{
		//czy mo¿emy?
		if (this->czyGotowy() && napastnik->czyGotowy())
		{
			// zabieram siê do pracy :)
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
			//nie dziœ
			return NULL;
		}
	}
	else
	{
		if (!this->decyzja(SZANSAUCIECZKI))
		{

			// walka :(
			//porównuje jego sile z moj¹
			if (napastnik->getSila() >= this->sila)
			{
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
		}
		else
		{
			//ucieczka: 
			EventList * listaEventow = new EventList(this->kordy);
			Event * ucieczka = this->akcja();
			if (ucieczka == NULL)
			{
				//nie mo¿na ruszyæ : 
				//ale przez moj¹ nieuwagê:
				//ginê:
				
				listaEventow->push(new Event(napastnik->getKordy(), Call::morduje, this->kordy));
				listaEventow->push(new Event(napastnik->getKordy(), Call::ruch, this->kordy));
				return listaEventow;
			}
			listaEventow->push(ucieczka);
			listaEventow->push(new Event(napastnik->getKordy(), Call::ruch, this->kordy));
			return listaEventow;
		}
	}
	return 0;
};

Organizm * Antylopa::noweDziecko(int turaUrodzenia, Pozycja kordy)
{
	return new Antylopa(turaUrodzenia, this->mojSwiat, kordy);
}