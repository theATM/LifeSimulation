#include "Event.h"

Event::Event() {};

Event::Event(Pozycja kto, Call co, Pozycja gdzie)
{
	this->kto.n = kto.n;
	this->kto.m = kto.m;
	this->co = co;
	this->gdzie.n = gdzie.n;
	this->gdzie.m = gdzie.m;
}

Call Event::getCo()
{
	return this->co;
}

Pozycja Event::getKto()
{
	return this->kto;
}

Pozycja Event::getGdzie()
{
	return this->gdzie;
}