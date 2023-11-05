#pragma once
#include <fstream>
#include <iostream>
#include <time.h>
#include "Organizm.h"
#include "Event.h"

#include "Czlowiek.h"
#include "Owca.h"
#include "Wilk.h"
#include "Lis.h"
#include "Antylopa.h"
#include "Zolw.h"
#include "Trawa.h"
#include "Mlecz.h"
#include "Guarana.h"
#include "WilczeJagody.h"
#include "BarszczSosnowskiego.h"
class Organizm;
#define path "../Saves/save.txt"

class Swiat
{
private:
	int N;
	int M;
	Organizm **organizmy;
	int ** mapa;
	int iloscOrganizmow;
	int tura;
public:
	Swiat(int N, int M);
	~Swiat();
	void zycie();
	void iniMapa();
	void wykonajTure();
	void eventManager(Event * evencik);
	void rysujSwiat();
	void sortOrganizmy();
	int getN();
	int getM();
	int getMapaIndex(Pozycja kordy);
	Organizm * getOrganizm(int index);
	void save();
	void load(std::ifstream &plik);

};