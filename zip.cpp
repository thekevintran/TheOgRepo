#include <stdio.h>
#include <fstream>
#include <iostream>
#include <map>
#include <string>
#include <string.h>

using namespace std;

string get_input (FILE *file)
{	
	string cur;
	cur = fgetc(file);
	if (feof(file)) {return cur;}
return cur;
}

int main (int argc, char* argv[])
{
	FILE* pf; //input C
	ofstream of; //output C++
	map<unsigned char, unsigned int> dict;
	string cur_string;
	unsigned int next_int = 256;

	if (argc > 2)
	{
		printf("Error: More than 1 argument found\n");
		return 0;
	}
	string filename(argv[1]);
	//cout << filename << '\n';
	//fp.open(argv[1]);
	pf = fopen (argv[1], "r");
	string ofilename = filename + ".zip";
	//cout << ofilename;
	//of.open(ofilename);

	if (pf == NULL)
	{cout  << "Error opening file\n";}
	else
	{cout << "File opened\n";}
	
	for (unsigned int i = 0; i < 256; i++)
	{
		dict[char(i)] = i;
	}
	//use fgetc to read char by char
	 //Print dict for check
	map<unsigned char, unsigned int>::iterator it;
	//for (it = dict.begin(); it != dict.end(); it++)
	//{ cout << it->first << "==>" << it->second << '\n'; }
	
	
	cur_string = get_input(pf);
	//to check if cur_string works
	cout << "cur string = " << cur_string << '\n'; 
	unsigned char cur_char;
	//unsigned char addedChar;
	while (!feof(pf))
	
	{
		cur_char = get_input(pf); //ERROR need to conver string to char 
		//cout << "it ==> " << it;
		//addedChar = strcat(cur_string,cur_char);
		//cout << "added char  " << addedChar << '\n'; 
		if(dict.find(cur_string) == dict.end())
		{
			//codeword = currentstring's code in dict
                        //output code word

			//add currentstring + currentchar to dic
			addedChar = 
			if (next_int <= 4096)
			{
				dict.insert(make_pair(cur_string, next_int++));
			}
			
			cur_string = cur_char;	
			cout << "cannot find";//fputc(dict[cur_string],
			
		}
		else
		{
			cout << "can find";
			cur_string=cur_string+get_input(cur_string,pf);
			cout << cur_string << '\n';
		}
	//}	

	fclose(pf);
	ofilename.close();
	return 0;
}
