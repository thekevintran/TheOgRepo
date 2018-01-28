#include <iostream>
#include <map>
#include <string>
using namespace std;

#include <stdio.h>
#include <stdlib.h>

int main (int argc, char* argv[])
{
	FILE* pf;
	map<unsigned char, unsigned int> dict;
	string cur_string;
  string old_string;
  unsigned char cur_char;
  unsigned char cur_code;
  unsigned char next_code;
  
  //FILE CHECKS
	if (argc > 2)
	{
		printf("Error: More than 1 argument found\n");
		return 0;
	}
	pf = fopen (argv[1], "r");

	if (pf == NULL)
	{cout  << "Error opening file\n";}
	else
	{cout << "File opened\n";}
	
  //PSEUDOCODE START!
  //INITIALIZE DICTIONARY
	for (unsigned int i = 0; i < 256; i++)
	{
		dict[char(i)] = i;
	}
	
  //FIRST INPUT CODE WORD TO CURRENT CODE
  cur_code = fgetc(pf);
  cur_char = dict.find(cur_code)-> second;
  fputc(cur_char, stdout);
  
  //WHILE NOT EOF && NOT END OF DICT
  while(!feof(pf))
  {
	next_code = fgetc(pf);
	if(dict.find(next_code) != dict.end())
	{
	  cur_string = dict[cur_code].c_str();
	  cur_string += cur_char;
	}
	else
	{
	  cur_string = dict[next_code].c_str();
	}
	
	fputc(cur_char, stdout);
	cur_char = cur_string[0];	
	old_string = dict[cur_code].c_str();
	//ADD OLD+CUR CHAR TO DICT
	old_string = old_string + cur_char;
	  dict[i] = old_string;
	  i++;
	cur_code = next_code;
  }


	fclose(pf);
	return 0;
}
}
