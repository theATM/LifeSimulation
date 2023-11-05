#pragma once
#include "Roslina.h"

class WilczeJagody : public Roslina
{
private:
	const int ILOSCNASIONNATURE = 3;
	const int STARTSILA = 99;
public:
	WilczeJagody(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Event* kolizja(Organizm * napastnik) override;
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};
