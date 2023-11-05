#pragma once
#include "Zwierze.h"

class Wilk : public Zwierze
{
private:
	const int STARTSILA = 9;
	const int STARTINICJATYWA = 5;
public:
	Wilk( int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};