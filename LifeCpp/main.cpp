#include "Menu.h"
#include "Swiat.h"
#include "Zwierze.h"
#include <conio.h>
#include "EventList.h"
#define ININ 20
#define INIM 10
int main()
{


	//menu:
	int N = ININ;
	int M = INIM;
	
	Menu::iniMenu();
	switch(Menu::getMenu(N,M))
	{
		default:
			return 0; //Wyjscie
			break;
		case 1:
		{
			//play!
			Swiat swiatZabawy = *new Swiat(N, M);
			//swiatZabawy.rysujSwiat();
			swiatZabawy.iniMapa();
			swiatZabawy.zycie();
			break;
		}
		case 2:
		{
			//load and play
			std::ifstream plik(path, std::ios::out);
			if (!plik.fail())
			{
				plik >> N;
				plik >> M;
				Swiat swiatZabawy = *new Swiat(N, M);
				swiatZabawy.load(plik);
				swiatZabawy.zycie();
			}
			else
			{
				std::cout << "Nie znaleziono pliku" << std::endl;
				return 1;
			}
			break;
		}
	}
		
	return 0;
}