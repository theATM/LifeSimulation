#include "EventList.h"

EventList::Lista::Lista(Event * evencik)
{
	this->evencik = evencik;
	this->next = NULL;
}

EventList::EventList(Pozycja kto) 
{
	this->lista = NULL;
	this->co = Call::eventlist;
	this->kto = kto;
	this->gdzie = kto;

}

void EventList::push(Event* evencik)
{
	//je¿eli nie ma elementów listy:
	if (this->lista == NULL)
	{
		this->lista = new Lista(evencik);
		return;
	}

	//szukamy ostatniego elementu listy:
	Lista* i;
	for (i = this->lista; i->next != NULL; i = i->next);

	i->next = new Lista(evencik);
	return;
}

Event * EventList::pop()
{
	if (this->lista == NULL)
		return NULL; //nie ma elementów

	Event * wynik;
	wynik = this->lista->evencik;
	if (this->lista->next == NULL)
	{
		//jest jeden element:
		delete this->lista;
		this->lista = NULL;
	}
	else
	{
		// jest wiele elementów
		Lista * buf = this->lista;
		this->lista = this->lista->next;
		delete buf;	
	}
	return wynik;
}

bool EventList::czyNiePusta()
{
	return (this->lista != NULL) ? (true) : (false);
}

