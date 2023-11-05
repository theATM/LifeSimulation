#pragma once
#include "Zwierze.h"

class Antylopa : public Zwierze
{
private:
	const int STARTSILA = 4;
	const int STARTINICJATYWA = 4;
	const float SZANSAUCIECZKI = 0.5;
	int wyborPolaAntylopy();
public:
	Antylopa(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Event* akcja() override;
	Event* kolizja(Organizm * napastnik) override;
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};