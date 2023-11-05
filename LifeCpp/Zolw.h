#pragma once
#include "Zwierze.h"

class Zolw : public Zwierze
{
private:
	const int STARTSILA = 2;
	const int STARTINICJATYWA = 1;
	const float SZANSARUCHU = 0.25;
	const int MAXBLOCKSILA = 5;
public:
	Zolw(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Event* akcja() override;
	Event* kolizja(Organizm * napastnik) override;
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};
