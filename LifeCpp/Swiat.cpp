#include "Swiat.h"


using namespace std;

void Swiat::iniMapa()
{

	//Dodanie troche organizmow w pierwszej turze:
	this->tura = 0;
	this->iloscOrganizmow = 0;

	//INIT WORLD CONSTS:
	int maxOrg = N * M / 3;
	int maxSameOrg = maxOrg / 5;
	int maxSamePla = maxOrg / 8;

	//dodanie czlowieka:
	if (this->iloscOrganizmow < maxOrg && 1 <= maxSameOrg)
	{
		int m, n;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Czlowiek(0, this, Pozycja(m, n));
		mapa[m][n] = 0;
		this->iloscOrganizmow++;
	}

	
	//dodanie wilka
	int liczbaWilkow = rand() % maxSameOrg;
	int ityWilk = 0;
	while (this->iloscOrganizmow < maxOrg && ityWilk < liczbaWilkow)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Wilk(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		ityWilk++;
		this->iloscOrganizmow++;
	}
	
	//dodanie owcy
	int liczbaOwiec = rand() % maxSameOrg;
	int itaOwca = 0;
	while (this->iloscOrganizmow < maxOrg && itaOwca < liczbaOwiec)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Owca( 0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		itaOwca++;
		this->iloscOrganizmow++;
	}
	
	//dodanie lisa
	int liczbaLisow = rand() % maxSameOrg;
	int ityLis = 0;
	while (this->iloscOrganizmow < maxOrg && ityLis < liczbaLisow)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Lis(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		ityLis++;
		this->iloscOrganizmow++;
	}

	//dodanie ¯ó³wia
	int liczbaZolwi = rand() % maxSameOrg;
	int ityZolw = 0;
	while (this->iloscOrganizmow < maxOrg && ityZolw < liczbaZolwi)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Zolw(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		ityZolw++;
		this->iloscOrganizmow++;
	}
	
	//dodanie Antylopy
	int liczbaAntylop = rand() % maxSameOrg;
	int itaAntylopa = 0;
	while (this->iloscOrganizmow < maxOrg && itaAntylopa < liczbaAntylop)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Antylopa(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		itaAntylopa++;
		this->iloscOrganizmow++;
	}



	//dodanie trawy
	int liczbaTrawy = rand() % maxSamePla;
	int itaTrawa = 0;
	while (this->iloscOrganizmow < maxOrg && itaTrawa < liczbaTrawy)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Trawa(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		itaTrawa++;
		this->iloscOrganizmow++;
	}

	//dodanie mleczy
	int liczbaMleczy = rand() % maxSamePla;
	int itaMlecz = 0;
	while (this->iloscOrganizmow < maxOrg && itaMlecz < liczbaMleczy)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Mlecz(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		itaMlecz++;
		this->iloscOrganizmow++;
	}

	//dodanie guarany
	int liczbaGuaran = rand() % maxSamePla;
	int itaGuarana = 0;
	while (this->iloscOrganizmow < maxOrg && itaGuarana < liczbaGuaran)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new Guarana(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		itaGuarana++;
		this->iloscOrganizmow++;
	}

	//dodanie jagód
	int liczbaJagod = rand() % maxSamePla;
	int itaJagoda = 0;
	while (this->iloscOrganizmow < maxOrg && itaJagoda < liczbaJagod)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new WilczeJagody(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		itaJagoda++;
		this->iloscOrganizmow++;
	}
	
	//dodanie barszczu
	int liczbaBarszczu = rand() % maxSamePla;
	int ityBarszcz = 0;
	while (this->iloscOrganizmow < maxOrg && ityBarszcz < liczbaBarszczu)
	{
		int n, m;
		do
		{
			n = rand() % this->N;
			m = rand() % this->M;
		} while (mapa[m][n] != -1);
		organizmy[iloscOrganizmow] = new BarszczSosnowskiego(0, this, Pozycja(m, n));
		mapa[m][n] = this->iloscOrganizmow;
		ityBarszcz++;
		this->iloscOrganizmow++;
	}
	
}

Swiat::Swiat(int N, int M)
{
	//inicjalizacja generatora liczb pseudolosowych
	srand(time(NULL));

	this->tura = 0;
	this->iloscOrganizmow = 0;

	this->N = N;
	this->M = M;
	//stworzenie mapy[m][n]
	// |---------> N
	// |---------> N
	// |---------> N
	// |---------> N
	// v
	// M
	mapa = new int*[M];
	for (int i = 0; i < M; i++)
	{
		mapa[i] = new int[N];
	}
	organizmy = new  Organizm*[N*M + 10] {NULL};

	//wype³nienie mapy "-1":
	for (int n = 0; n < this->getN(); n++)
	{
		for (int m = 0; m < this->getM(); m++)
		{
			mapa[m][n] = -1;
		}
	}



}

Swiat::~Swiat()
{
	for (int i = 0; i < M; i++)
	{
		delete[] mapa[i];
	}
	delete[] mapa;
}

void Swiat::zycie()
{
	char c = 13; //dowolna
	
	while (true)
	{
		system("cls");
		cout << "Tura: " << this->tura << endl;
		rysujSwiat();
		cout << " Wydarzenia Tury: " << endl;
		wykonajTure();
		cout << "Kliknij cos by przejsc do nastepnej tury, q wyjscie, z zapis" << endl;
		c = _getch();
		switch (c)
		{
		case 'q':
			//wyjscie
			return;
			break;
		case 'z':
			this->save();
			cout << "Zapisano gre" << endl;
			Sleep(1500);
			break;
		default:
			//nastêpna tura:
			continue; 
			break;
		}
	}
}

void Swiat::wykonajTure()
{
	this->tura++;
	Event *eventZwrotny = NULL;;
	int iloscPierwotnychOrganizmow = iloscOrganizmow;
	for (int organizm = 0; organizm < iloscPierwotnychOrganizmow; organizm++)
	{
		if (organizmy[organizm] != NULL)
		{
			eventZwrotny = organizmy[organizm]->akcja();
			if(eventZwrotny != NULL)
				eventManager(eventZwrotny);
		}
	}
	sortOrganizmy();

}



void Swiat::eventManager(Event * evencik)
{
	if (evencik == NULL) return;
	switch (evencik->getCo())
	{
	case Call::ruch:
		//czy ktoœ tam ju¿ jest?
		if (mapa[evencik->getKto().m][evencik->getKto().n] != -1)
		{
			//jest ktoœ kto chce siê ruszyæ
			if (mapa[evencik->getGdzie().m][evencik->getGdzie().n] == -1)
			{
				//je¿eli nie to mo¿na tu wejœæ
				Organizm * org = organizmy[mapa[evencik->getKto().m][evencik->getKto().n]];
				if (org != NULL && org->reakcja(evencik))
				{
					int buf = mapa[evencik->getKto().m][evencik->getKto().n];
					mapa[evencik->getKto().m][evencik->getKto().n] = -1;
					mapa[evencik->getGdzie().m][evencik->getGdzie().n] = buf;
				}
			}
			else
			{
				Organizm * napastnik = organizmy[mapa[evencik->getKto().m][evencik->getKto().n]];
				Organizm * ofiara = organizmy[mapa[evencik->getGdzie().m][evencik->getGdzie().n]];
				Event * odpowiedz = ofiara->kolizja(napastnik);
				if (odpowiedz != NULL)
				{
					eventManager(odpowiedz); //wysy³am listê eventów do rozpatrzenia
				}
				odpowiedz = NULL;
			}
		}
		delete evencik;
		break;
	case Call::morduje:
	{

		Organizm * ofiara = NULL;
		Organizm *napastnik = NULL;
		if(mapa[evencik->getGdzie().m][evencik->getGdzie().n] != -1)
			ofiara = organizmy[mapa[evencik->getGdzie().m][evencik->getGdzie().n]];
		else
		{
			delete evencik;
			break;
		}
		if (mapa[evencik->getKto().m][evencik->getKto().n] != -1)
		{
			napastnik = organizmy[mapa[evencik->getKto().m][evencik->getKto().n]];
			cout << napastnik->getTyp() << " zabija " << ofiara->getTyp() << endl;
		}
		else
		{
			//apastnik zabi³ poœmiertnie
			cout<<"ginie "<< ofiara->getTyp() << endl;
		}
		//usuwanie:
		organizmy[mapa[evencik->getGdzie().m][evencik->getGdzie().n]] = NULL;
		mapa[evencik->getGdzie().m][evencik->getGdzie().n] = -1;
		delete ofiara;
		delete evencik;
		break;
	}
	case Call::truje:
	{

		Organizm * ofiara = NULL;
		Organizm *napastnik = NULL;
		if (mapa[evencik->getGdzie().m][evencik->getGdzie().n] != -1)
			ofiara = organizmy[mapa[evencik->getGdzie().m][evencik->getGdzie().n]];
		else
		{
			delete evencik; //nikogo nie ma do otrucia
			break;
		}
		if (mapa[evencik->getKto().m][evencik->getKto().n] != -1)
		{
			napastnik = organizmy[mapa[evencik->getKto().m][evencik->getKto().n]];
			if (napastnik != NULL && napastnik->getTyp() == ofiara->getTyp())
			{
				delete evencik; // ten sam gatunek jest na siebie odporny
				break;
			}
		}
		 //gdy napastnik jest nulem to jest to zatrucie poœmiertne
	
		if (ofiara->reakcja(evencik))
		{
			//jest podaty na zatrucia
			eventManager(new Event(evencik->getKto(), Call::morduje, evencik->getGdzie()));
		}
		delete evencik;
		break;
	}
	case Call::tworzy:
		if (mapa[evencik->getKto().m][evencik->getKto().n] != -1 && mapa[evencik->getGdzie().m][evencik->getGdzie().n] == -1 && this->iloscOrganizmow <  N*M + 10)
		{
			Organizm * rodzic = organizmy[mapa[evencik->getKto().m][evencik->getKto().n]];

			Organizm * dziecko = rodzic->noweDziecko(this->tura, evencik->getGdzie());
			if (dziecko != NULL)
			{
				this->organizmy[iloscOrganizmow] = dziecko;
				mapa[evencik->getGdzie().m][evencik->getGdzie().n] = this->iloscOrganizmow;
				this->iloscOrganizmow++;
				//cout << "rodzi sie " << dziecko->getTyp() << endl;
			}
		}
		delete evencik;
		break;
	case Call::eventlist:
	{
		EventList *lista = (EventList*) evencik;
		if (lista->czyNiePusta())
		{
			for (Event * wewevent = lista->pop(); wewevent != NULL; wewevent = lista->pop())
			{
				eventManager(wewevent);
			}
		}
		delete evencik;
		break;
	}
	default:
		delete evencik;
		break;
	}
}

void Swiat::rysujSwiat()
{
	//system("cls");
	cout << " __";
	for (int n = 0; n < this->N - 2; n++)
	{
		cout << "____";
	}
	if (N == 1)
		cout << "_";
	else
		cout << "_____";
	cout << endl;


	for (int m = 0; m < this->M; m++)
	{
		for (int n = 0; n < this->N; n++)
		{
			cout << "| ";
			if (mapa[m][n] == -1)
			{
				cout << " ";
			}
			else
			{
				if (organizmy[mapa[m][n]] != NULL)
				{
					organizmy[mapa[m][n]]->rysuj();;
				}
				else
				{
					mapa[m][n] = -1;
					cout << " ";
				}
			}
			cout<< " ";
		}
		cout <<"|"<< endl;
		if (m != this->M - 1)
		{
			if (N == 1 && M == 1)
				cout << "|";
			else
				cout << "|---";
			for (int n = 0; n < this->N - 2; n++)
			{
				cout << "----";
			}
			if (N == 1)
				cout << "|";
			else
				cout << "----|";
			cout << endl;
		}	
		
	}
	if (N == 1)
		cout << "|___";
	else
		cout << "|___";
	for (int n = 0; n < this->N - 2; n++)
	{
		cout << "____";
	}
	if (N == 1)
		cout << "|";
	else
		cout << "____|";
	cout << endl;
}

void Swiat::sortOrganizmy()
{
	//po turze nale¿y przesortowaæ organizmy;
	int iloscPustych = 0;
	for (int j = 0; j < this->iloscOrganizmow; j++)
	{
		Organizm* org = NULL;
		int maxindex = -1;
		for (int index = j; index < this->iloscOrganizmow; index++)
		{
			if (org != NULL && this->organizmy[index] != NULL)
			{
				//porównanie organizmów
				//ten z wiêksz¹ inicjatyw¹ idzie na pocz¹tek
				//gdy maj¹ t¹ sam¹ inicjatywe na pocz¹tek idzie 
				//starszy
				if (this->organizmy[index]->getInicjatywa() > org->getInicjatywa()
					|| (org->getInicjatywa() == this->organizmy[index]->getInicjatywa()
						&& this->organizmy[index]->getWiek() < org->getWiek()))
				{
					org = this->organizmy[index];
					maxindex = index;
				}
			}
			else if (this->organizmy[index] != NULL)
			{
				org = this->organizmy[index];
				maxindex = index;
			}
		
		}
		//wybrano najlepszego:
		//i wstawienie go na pocz¹tek
		if (org != NULL && maxindex != -1)
		{
			if (organizmy[j] != NULL)
			{
				this->mapa[organizmy[j]->getKordy().m][organizmy[j]->getKordy().n] = maxindex;
			}
		//	else
		//	{
		//		iloscPustych++;
		//	}
			this->mapa[org->getKordy().m][org->getKordy().n] = j;
			this->organizmy[maxindex] = organizmy[j];
			organizmy[j] = org;
		}
		else if (org == NULL)
		{
			iloscPustych++;
			if (organizmy[j] != NULL)
			{
				this->mapa[organizmy[j]->getKordy().m][organizmy[j]->getKordy().n] = -1;
			}
			organizmy[j] = NULL;

		}
	}
	this->iloscOrganizmow -= iloscPustych;
}

int Swiat::getN()
{
	return this->N;
}

int Swiat::getM()
{
	return this->M;
}

int Swiat::getMapaIndex(Pozycja kordy)
{
	return mapa[kordy.m][kordy.n];
}

Organizm* Swiat::getOrganizm(int index)
{
	if (index != -1)
	{
		return this->organizmy[index];
	}
	return NULL;
}

void Swiat::save()
{
	ofstream plik(path, ios::in);
	plik << N << " " << M << endl;
	plik << tura << " " << iloscOrganizmow << endl;
	for (int index = 0; index < iloscOrganizmow; index++)
	{
		organizmy[index]->save(plik);
	}
	plik.close();
}
void Swiat::load(ifstream &plik)
{
	plik >> this->tura;
	plik >> this->iloscOrganizmow;
	for (int index = 0; index < iloscOrganizmow; index++)
	{
		char c;
		plik >> c;
		char znak;
		plik >> znak;
		switch (znak)
		{
		case 'A':
			organizmy[index] = new Antylopa(this->tura, this, Pozycja(0, 0));
			break;
		case 'C':
			organizmy[index] = new Czlowiek(this->tura, this, Pozycja(0, 0));
			break;
		case 'W':
			organizmy[index] = new Wilk(this->tura, this, Pozycja(0, 0));
			break;
		case 'O':
			organizmy[index] = new Owca(this->tura, this, Pozycja(0, 0));
			break;
		case 'L':
			organizmy[index] = new Lis(this->tura, this, Pozycja(0, 0));
			break;
		case 'Z':
			organizmy[index] = new Zolw(this->tura, this, Pozycja(0, 0));
			break;
		case 't':
			organizmy[index] = new Trawa(this->tura, this, Pozycja(0, 0));
			break;
		case 'm':
			organizmy[index] = new Mlecz(this->tura, this, Pozycja(0, 0));
			break;
		case 'g':
			organizmy[index] = new Guarana(this->tura, this, Pozycja(0, 0));
			break;
		case 'j':
			organizmy[index] = new WilczeJagody(this->tura, this, Pozycja(0, 0));
			break;
		case 'b':
			organizmy[index] = new BarszczSosnowskiego(this->tura, this, Pozycja(0, 0));
			break;
		}
		organizmy[index]->load(plik);
		mapa[organizmy[index]->getKordy().m][organizmy[index]->getKordy().n] = index;
	}
	plik.close();
	//mo¿na zaczynaæ :)
}