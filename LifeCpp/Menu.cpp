#include "Menu.h"

using namespace std;

void Menu::iniMenu()
{
	cout << endl << endl;
	cout << "        _______________________________ " << endl
		 << "       |         Symulator Zycia       |" << endl
		 << "       |-------------------------------|" << endl
		 << "       |  Aleksander Madajczak 175867  |" << endl
         << "       |_______________________________|" << endl << endl;
	cout << "          <Enter>: Zacznij juz teraz!       " << endl;
	getchar();
	system("cls");
}

int Menu::getMenu(int &N, int &M)
{
	while (true)
	{
		cout << endl;
		cout << "        _________________________________ " << endl
	 		 << "       |         <1> Symuluj             |" << endl
	 		 << "       | ------------------------------- |" << endl
			 << "       |         <2> Zaladuj             |" << endl
			 << "       | ------------------------------- |" << endl
			 << "       |         <3> N:"<<showNM(N,18)<<"|" << endl
			 << "       | ------------------------------- |" << endl
			 << "       |         <4> M:"<<showNM(M,18)<<"|" << endl
			 << "       | ------------------------------- |" << endl
			 << "       |         <5> Koniec              |" << endl
			 << "       |_________________________________|" << endl;
		
		cout << " >> ";
		int wybor;
		cin >> wybor;
		system("cls");
		switch (wybor)
		{
		case 1:
			//Start
			return 1;
			break;
		case 2:
			//load from file
			return 2;
			break;
		case 3:
			N = readNM();
			break;
		case 4:
			M = readNM();
			break;
		case 54:
			//wyjscie
			return 0;
			break;
		default:
			//nic nie rób
			break;
		}
	}
	return 0;
}

string Menu::showNM(int NM, int space)
{
	string wynik = to_string(NM);
	int cyfry = space - (int)wynik.length();
	if (cyfry < 0)
	{
		string nowywynik = "";
		int len = (int)wynik.length();
		for (int i = 0; i < cyfry*-1; i++)
		{
			nowywynik += wynik[len - i];
		}
		wynik = nowywynik;
	}
	else
	{
		for (int i = 0; i < cyfry; i++)
		{
			wynik += " ";
		}
	}
	return wynik;
}

int Menu::readNM()
{
	int NM;
	while (true)
	{
		system("cls");
		cout << endl;
		cout << " Podaj dany wymiar: >> ";
		cin >> NM;
		cout << endl;
		if (NM > 0)
			break;
		cout << " Podaj poprawny wymiar!!!";
		Sleep(2000);
	}
	return NM;
}