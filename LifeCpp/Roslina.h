#pragma once
#include "Organizm.h"

class Swiat;
class Roslina : public Organizm
{
protected:
	const int DEFAULTSTARTINICJATYWA = 0;
	const int DEFAULTSTARTSILA = 0;
	const int DEFAULTILOSCNASIONNATURE = 1;
	const float DEFAULTSZANSASIANIA = 0.1;
	float szansaSiania;
	int iloscNasionNaTure;
public:
	Roslina( int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Event* akcja() override;
	Event* kolizja(Organizm * napastnik) override;
	bool reakcja(Event * ewencik) override;
	virtual  Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) override = 0;


};