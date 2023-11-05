#pragma once
#include "Roslina.h"

class Guarana : public Roslina
{
public:
	Guarana(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Event* kolizja(Organizm * napastnik) override;
	Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override;
};