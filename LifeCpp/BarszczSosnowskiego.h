#pragma once
#include "Roslina.h"
class BarszczSosnowskiego : public Roslina
{
private:
	const int STARTSILA = 10;
public:
	BarszczSosnowskiego(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Event* akcja() override;
	Event* kolizja(Organizm * napastnik) override;
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};