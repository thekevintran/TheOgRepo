#include <string>
#include <stdio.h>
#include <stdlib.h>

using namespace std;
int main (int argc, char* argv[])
{
  //FILE CHECKS
	if (argc > 2)
	{
		printf("Error: More than 1 argument found\n");
		return 0;
	}
  
  if(fn.substr(fn.find_last_of(".") + 1) == "zip"))
  {
	printf("Error: File ext\n");
	return 0;
  }
  else
  {
	printf("Opened file\n");
  }
	pf = fopen (argv[1], "r");

	if (pf == NULL)
	{cout  << "Error opening file\n";}
	else
	{cout << "File opened\n";}
  
  return 0;
  }
