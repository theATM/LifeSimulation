#pragma once
#include "Pozycja.h"

enum class Call
{
	ruch,
	morduje,
	truje,
	tworzy,
	ruchCzlowieka,
	eventlist
};

class Event
{
protected:
	Pozycja kto;
	Pozycja gdzie;
	Call co;
public:
	Event();
	Event(Pozycja kto, Call co, Pozycja gdzie);
	Call getCo();
	Pozycja getKto();
	Pozycja getGdzie();
};