#include <iostream>
#include <fstream>
#include <map>
#include <string>
using namespace std;

#include <stdio.h>
#include <stdlib.h>

int main (int argc, char* argv[])
{
	FILE* pf;
	ofstream of;
	map<unsigned int, string> dict;
	string cur_string;
	string old_string;
	unsigned char cur_char;
	unsigned int cur_code;
	unsigned int next_code = 256;
  
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
			size_t lastIndex = fileName.find_last_of("."); 
			string rawName = fileName.substr(0, lastIndex); 
			string newFileName = rawName + ".orig";
			of.open(newFileName.c_str());
		}
		 else
		 {printf("Error: File Extension\n"); return 0;}
	}
	
	//
	//PSEUDOCODE START!
	//
	//
	for (unsigned int i = 0; i < 256; i++)
	{
		dict[i] = string(1,i);
	}
	
	//FIRST INPUT CODE WORD TO CURRENT CODE
	cur_code = fgetc(pf);
	string temp = dict.find(cur_code)->second;
	cur_char = temp[0];
	of << cur_char;
	
	
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
		
		of << cur_string;	
		cur_char = cur_string[0];	
		old_string = dict.find(cur_code)-> second;

		if(next_code <= 4096)
		{
			old_string += cur_char;
			dict.insert(make_pair(cur_code,(old_string)));
		}
		cur_code = next_code;
		next_code++;
	}
	fclose(pf);
	of.close();
	return 0;
}
