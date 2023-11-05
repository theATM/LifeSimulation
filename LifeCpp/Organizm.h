#pragma once
#include "Event.h"
#include "EventList.h"
#include "Pozycja.h"
#include <iostream>
#include <fstream>
#include <string>
class Swiat;

enum class Typ
{
	//enum class nie 
	//blokuje mi tych nazw jej
	//dodaæ tu gdy dojaje siê nowe organizmy:
	czlowiek,
	owca,
	wilk,
	lis,
	zolw,
	antylopa,
	cyberOwca,
	trawa,
	mlecz,
	guarana,
	wilczeJagody,
	barszSosnowskiego
};


class Organizm
{
protected:
	char kod;
	int wiek;
	int sila;
	int inicjatywa;
	int niegotowosc;
	int maxniegotowosc;
	Swiat *mojSwiat;
	Pozycja kordy;
	Typ typ;
	static bool decyzja(float szansa);
	int wyborPola(bool bezpieczne);
	Organizm( int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy);
public:
	virtual Event* akcja() = 0;
	virtual Event* kolizja(Organizm * napastnik) = 0;
	virtual bool reakcja(Event * ewencik) = 0;
	virtual  Organizm * noweDziecko(int turaUrodzenia, Pozycja kordy) = 0;
	friend std::ostream& operator<<(std::ostream& out, Typ typ);
	void rysuj();
	void addSila(int ile);
	bool czyGotowy();
	//getery:
	Pozycja getKordy();
	int getSila();
	int getInicjatywa();
	int getWiek();
	Typ getTyp();
	virtual void save(std::ofstream &plik);
	virtual void load(std::ifstream &plik);
};