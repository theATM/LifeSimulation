#pragma once
#include "Roslina.h"

class Mlecz : public Roslina
{
private:
	const int ILOSCNASIONNATURE = 3;
public:
	Mlecz(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};
