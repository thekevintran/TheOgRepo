#include <stdio.h>
#include <fstream>
#include <iostream>
#include <map>
#include <string>
#include <string.h>

using namespace std;

//function to get characters
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
	map<string, unsigned int> dict; //map containing string and int
	string cur_string;
	char cur_char;
        unsigned int codeword;
	unsigned int next_int = 256;

	if (argc > 2)
	{
		printf("Error: More than 1 argument found\n");
		return 0;
	}
	string filename(argv[1]); //setting filename as string
	pf = fopen (argv[1], "r"); //read the "file.txt"
	string ofilename = filename + ".zip"; //string with extension .zip
	of.open(ofilename.c_str()); //open the zip

	if (pf == NULL)
	{cout  << "Error opening file\n";}
	
	//initialize dictionary 
	for (unsigned int i = 0; i < 256; i++)
	{
		dict[string(1,i)] = i;
	}
	
	//map<unsigned char*, unsigned int>::iterator it;
	
	//current string = first input character
	cur_string = get_input(pf); 

	//while there are more input characters
	while (!feof(pf))
	{	
		//current char = next input char
		cur_char = get_input(pf)[0];
 
		//current string is not in the dictionary
		if(dict.find(cur_string) == dict.end())
		{
			//codeword = currentstring's code in dict
			codeword = dict.find(cur_string)->second;
			//output to file
                        of << codeword;

			//add currentstring + currentchar to dic
			cur_string = cur_string + cur_char; 
			if (next_int <= 4096)
			{
				dict.insert(make_pair(cur_string, next_int++));
			}
			
			cur_string = cur_char;	
		}
		//current string is in the dictionary
		else
		{
			cur_char = get_input(pf)[0];
			cur_string = cur_string + cur_char;
			//cout << "Found: cur_string = " << cur_string << '\t';
		}
	}	

	codeword = dict.find(cur_string)->second;
	of << codeword;

	fclose(pf);
	of.close();
	return 0;
}
