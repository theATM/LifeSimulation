#pragma once
#include "Roslina.h"

class Trawa : public Roslina
{
private:
	const float SZANSASIANIA = 0.1;
public:
	Trawa( int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};