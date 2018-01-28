#include <stdio.h>
#include <fstream>
#include <iostream>
#include <map>
#include <string>
#include <string.h>

using namespace std;

string get_input (string cur, FILE *file)
{	
	unsigned char next;
	if (cur.length() == 0)
	{
		next = fgetc(file);
		if (feof(file)) {return cur;}
		cur = next;
		next = fgetc(file);
		if (feof(file)) {return cur;}
		cur += next;
		next = fgetc(file);
		if (feof(file)) {return cur;}
		cur += next;	
	}
	else 
	{
		next = fgetc(file);
		if (feof(file)) {return cur;}
		cur += next;
	}
return cur;
}

int main (int argc, char* argv[])
{
	FILE* pf;
	map<unsigned char, unsigned int> dict;
	string cur_string;

	if (argc > 2)
	{
		printf("Error: More than 1 argument found\n");
		return 0;
	}
	//string filename = argv[1];
	//cout << argv[1];
	//fp.open(argv[1]);
	pf = fopen (argv[1], "r");

	if (pf == NULL)
	{cout  << "Error opening file\n";}
	else
	{cout << "File opened\n";}
	
	for (unsigned int i = 0; i < 256; i++)
	{
		dict[char(i)] = i;
	}
	//use fgetc to read char by char
	/* //Print dict for check
	for (map<unsigned char, unsigned int>::const_iterator i = dict.begin(); i != dict.end(); i++)
	{ cout << i->first << "==>" << i->second << '\n'; }
	*/
	
	cur_string = get_input(cur_string, pf);
	//to check if cur_string works
	cout << "cur string = " << cur_string << '\n'; 
	

	fclose(pf);
	return 0;
}
