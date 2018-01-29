#include <iostream>
#include <map>
#include <string>
using namespace std;

#include <stdio.h>
#include <stdlib.h>

int main (int argc, char* argv[])
{
	FILE* pf;
	map<string, unsigned int> dict;
	string cur_string;
	string old_string;
	unsigned char cur_char;
	unsigned int cur_code;
	unsigned int next_int = 256;
  
  	//FILE ERROR CHECKS
	if (argc > 2)
	{
		printf("Error: More than 1 argument found\n");
		return 0;
	}
  	else
	{
		string fileName = argv[1];
		if(fileName.substr(fileName.find_last_of(".") + 1) == "zip")
		{
			pf = fopen (argv[1], "r");
			if (pf == NULL)
				{cout  << "Error opening file\n"; return 0;}
			size_t lastindex = fileName.find_last_of("."); 
			string rawname = fileName.substr(0, lastindex); 
			string newFileName = fileName + ".orig";
		}
		 else
		 {printf("Error: File Extension\n"); return 0;}
	}
	
	//PSEUDOCODE START!
	//INITIALIZE DICTIONARY
	for (unsigned int i = 0; i < 256; i++)
	{
		dict[string(1,i)] = i;
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
			cur_string = dict.find(cur_code)-> second;
			cur_string += cur_char;
		}
		else
		{
			cur_string = dict.find(cur_code)-> second;
		}
	
		fputc(cur_string, stdout);
		cur_char = cur_string[0];	
		old_string = dict.find(cur_code)-> second;
		//ADD OLD+CUR CHAR TO DICT
		if(next_code <= 4096)
		{
			dict.insert(make_pair((old_string + cur_char),next_code));
		}
		cur_code = next_code;
		next_code++;
	}
	fclose(pf);
	return 0;
}
