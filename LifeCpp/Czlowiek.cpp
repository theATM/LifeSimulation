#include "Czlowiek.h"
#include "Swiat.h"
using namespace std;

Czlowiek::Czlowiek( int turaUrodzenia, Swiat * nowySwiat, Pozycja kordy)
	:Zwierze(turaUrodzenia, nowySwiat, kordy)
{
	this->kod = 'C';
	this->turySupermocy = 0;
	this->supermoc = 1;
	this->ladowanieSupermocy = 0;
	this->inicjatywa = this->STARTINICJATYWA;
	this->sila = this->STARTSILA;
	this->typ = Typ::czlowiek;
};

int Czlowiek::getKeyBoard()
{
	std::cout << "Podaj Ruch Czlowieka! ";
	
	//super moc 2 ruchu przez 3 tury i 0.5 szansy na 2 ruchu przez 2 kolejne
	if (this->turySupermocy > MAXTURYSUPERMOCY - MAXTURYPELNEJSUPERMOCY )
	{
		this->supermoc = 2;
		this->turySupermocy--;
	}
	else if (this->turySupermocy < MAXTURYPELNEJSUPERMOCY  && this->turySupermocy > 0)
	{
		this->supermoc = rand() % 2;
		this->supermoc++;
		this->turySupermocy--;
	}
	else if (this->turySupermocy <= 0)
	{
		this->supermoc = 1;
		this->turySupermocy = 0;
	}

	if (ladowanieSupermocy > 0) ladowanieSupermocy--;
	else std::cout << "S - Super Moc";


	std::cout << std::endl;

	while (true)
	{
		if (_kbhit())
		{
			unsigned char key = _getch();
			if (key == 224)
			{
				switch (_getch())
				{

				case 72: //UP
					if (this->kordy.m > 0)
					{
						if (this->kordy.m == 1)
							this->supermoc = 1;
						return 3;
					}
					break;
				case 80: //DOWN
					if (this->kordy.m < this->mojSwiat->getM() - 1)
					{
						if (this->kordy.m == this->mojSwiat->getM() - 2)
							this->supermoc = 1;
						return 2;
					}
					break;
				case  75: //LEFT
					if (this->kordy.n > 0)
					{
						if(this->kordy.n == 1)
							this->supermoc = 1;
						return 1;
					}
					break;
				case 77: //RIGHT
					if (this->kordy.n < this->mojSwiat->getN() - 1)
					{
						if (this->kordy.n == this->mojSwiat->getN() - 2)
							this->supermoc = 1;
						return 0;
					}
					break;
				}
			}
			else if (key == 's')
			{
				if (!ladowanieSupermocy)
				{
					//supermoc aktywowana!
					cout << "Aktywowano Supermoc!" << endl;
					this->turySupermocy = MAXTURYSUPERMOCY;
					this->supermoc = 2;
					this->ladowanieSupermocy = TURYLADOWANIASUPERMOCY;
					//ruch wyczerpany
					return -1; 
				}
			}
		}
		Sleep(100);
	}
	return -1;
}
Event* Czlowiek::akcja() 
{
	//ruch
	int nowePole = this->getKeyBoard();
	Pozycja nowekordy = this->kordy;
	switch (nowePole)
	{
	case -1: //can't move
		return NULL;
		break;
	case 0: // RIGHT
		nowekordy.n += this->supermoc; // gdy supermocy nie ma to +1 gdy jest to +2
		break;
	case 1: // LEFT
		nowekordy.n -= this->supermoc;
		break;
	case 2: // DOWN
		nowekordy.m += this->supermoc;
		break;
	case 3: // UP
		nowekordy.m -= this->supermoc;
		break;
	}
	return new Event(this->kordy, Call::ruch, nowekordy);
};

Organizm * Czlowiek::noweDziecko(int turaUrodzenia,  Pozycja kordy)
{
	return NULL; //cz³owiek siê nie rozmnarza
}
void Czlowiek::save(std::ofstream &plik)
{
	plik << "[ " << kod << " " << this->turySupermocy;
	plik << " " << this->ladowanieSupermocy << " " << wiek;
	plik << " " << sila << " " << inicjatywa;
	plik << " " << this->kordy << "] " << std::endl;
}
void Czlowiek::load(std::ifstream &plik)
{
	plik >> this->turySupermocy;
	plik >> this->ladowanieSupermocy;
	this->Zwierze::load(plik);
}
