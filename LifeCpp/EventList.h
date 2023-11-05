#pragma once
#include "Event.h"
#define NULL 0 //bo NULL jest w iostrem

//Lista FIFO:
class EventList : public Event
{
private:
	struct Lista
	{
		Lista* next;
		Event* evencik;
		Lista(Event * evencik);
	};
	Lista* lista;

public:
	EventList(Pozycja kto);
	Event * pop();
	void push(Event * evencik);
	bool EventList::czyNiePusta();
};