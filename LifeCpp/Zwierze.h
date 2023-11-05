#pragma once
#include "Organizm.h"

class Swiat;
class Zwierze : public Organizm
{
protected:
	Zwierze(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
public:
	Event* akcja() override;
	Event* kolizja(Organizm * napastnik) override;
	bool reakcja(Event * ewencik) override;
	virtual Organizm * noweDziecko( int turaUrodzenia, Pozycja kordy) override = 0;
	void save(std::ofstream &plik) override;
	void load(std::ifstream &plik) override;

};