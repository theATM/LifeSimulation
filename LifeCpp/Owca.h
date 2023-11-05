#pragma once
#include "Zwierze.h"

class Owca : public Zwierze
{
private:
	const int STARTSILA = 4;
	const int STARTINICJATYWA = 4;
public:
	Owca(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Organizm * noweDziecko( int turaUrodzenia, Pozycja kordy) override;
};