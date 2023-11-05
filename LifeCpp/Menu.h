#pragma once
#include <windows.h>
#include <iostream>
#include <string>
//#include <math.h>
//#include <unistd.h>
class Menu
{
private:
	static std::string showNM(int NM, int space);
	static int readNM();
public:
	static void iniMenu();
	static int getMenu(int &N, int &M);
};