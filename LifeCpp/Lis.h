#pragma once
#include "Zwierze.h"

class Lis : public Zwierze
{
private:
	const int STARTSILA = 3;
	const int STARTINICJATYWA = 7;
public:
	Lis(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Event* akcja() override;
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};