#pragma once
#include "Zwierze.h"
#include <iostream> // cin cout
#include <Windows.h> //sleep
#include <conio.h> // kbhit getch
class Czlowiek : public Zwierze
{
private:
	const int STARTSILA = 5;
	const int STARTINICJATYWA = 4;
	const int MAXTURYSUPERMOCY = 5;
	const int MAXTURYPELNEJSUPERMOCY = 3;
	const int TURYLADOWANIASUPERMOCY = 5 + MAXTURYSUPERMOCY;
	int supermoc;
	int turySupermocy;
	int ladowanieSupermocy;
	int getKeyBoard();
public:
	Czlowiek(int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
	Event* akcja() override;
	Organizm * noweDziecko( int turaUrodzenia, Pozycja kordy) override;
	void save(std::ofstream &plik) override;
	void load(std::ifstream &plik) override;
};