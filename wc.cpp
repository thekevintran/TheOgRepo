#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>

int main(int argc,char *argv[])
{
	//declare variables
	char* fileNames[100]; //store file names
	char* commands[5];  //store commands
	int commandCount = 0, fileCount = 0; //count for command and file
	int words = 0, characters = 0, lines = 0;
	int twords = 0, tcharacters = 0, tlines = 0;
	FILE *fp; //file pointer
	char input[100];
	int argNum = argc;
	
	int c;
	int f = 1;
	while( (c = getopt(argc, argv, "lwc")) != -1)
	{
		switch(c)
		{
			case 'l':
				commands[commandCount] = argv[f];
				commandCount++;
				f++;
				argNum--;
				break;
			case 'w':
				commands[commandCount] = argv[f];
				commandCount++;
				f++;
				argNum--;
				break;
			case 'c':
				commands[commandCount] = argv[f];
				commandCount++;
				f++;
				argNum--;
				break;
			default :
				printf("Error");
				break;
		}
	}
	//Check for arguments
	if (argNum > 1) //if argc has more than the executable name
	{
		for (int i = f; i < argc; i++)
		{			
			if ((strcmp(argv[i],"-")) == 0) //Stdin if "-" declared
			{
				//Read stdin for lines, words, characters
				int d;
                while ((d = getchar()) != EOF)
                {
					if (commandCount > 0) //if command is greater than 0
					{
						for (int j = 0; j < commandCount; j++)
						{
							if ((strcmp(commands[j],"-l")) == 0)
							{
								if (d == '\n'){++lines;}
							}
							else if ((strcmp(commands[j],"-w")) == 0)
							{
								if (d == ' ' || d == '\n'){++words;}
							}
							else if ((strcmp(commands[j],"-c")) == 0)
							{
								if (d != ' ' && d != '\n'){++characters;}
							}
							else 
							{
								printf("ERROR While Reading Command.");
							}
						}
					}
					else 
					{
						//display everything
						if (d == '\n'){++lines;}
						if (d == ' ' || d == '\n'){++words;}
						if (d != ' ' && d != '\n'){++characters;}
					}
                }
				//Print lines,words, characters for stdin
				if (commandCount > 0) //if command is greater than 0
				{
					for (int j = 0; j < commandCount; j++)
					{
						if ((strcmp(commands[j],"-l")) == 0) 
						{
							printf("%d ", lines);
						}
						if ((strcmp(commands[j],"-w")) == 0) 
						{
							printf("%d ", words);
						}
						if ((strcmp(commands[j],"-c")) == 0) 
						{
							printf("%d ",characters);
						}
					}
					printf("\n");
				}
				else
				{
					printf("%d %d %d \n", lines, words, characters);
				}
			}
			else //FileName input 
			{
				fileNames[fileCount] = argv[i]; //store filename in array 
				fileCount++;
			}
		}
	}
	else //stdin input if no arguments
	{
	//Read stdin for lines, words, characters
			int d;
			while ((d = getchar()) != EOF)
			{
				if (commandCount > 0) //if command is greater than 0
				{
					for (int j = 0; j < commandCount; j++)
					{
						if ((strcmp(commands[j],"-l")) == 0)
						{
							if (d == '\n'){++lines;}
						}
						else if ((strcmp(commands[j],"-w")) == 0)
						{
							if (d == ' ' || d == '\n'){++words;}
						}
						else if ((strcmp(commands[j],"-c")) == 0)
						{
							if (d != ' ' && d != '\n'){++characters;}
						}
						else 
						{
							printf("ERROR While Reading Command.");
						}
					}
				}
				else 
				{
					//display everything
					if (d == '\n'){++lines;}
					if (d == ' ' || d == '\n'){++words;}
					if (d != ' ' && d != '\n'){++characters;}
				}
			}
		//Print lines,words, characters for stdin
		if (commandCount > 0) //if command is greater than 0
		{
			for (int j = 0; j < commandCount; j++)
			{
				if ((strcmp(commands[j],"-l")) == 0) 
				{
					printf("%d ", lines);
				}
				if ((strcmp(commands[j],"-w")) == 0) 
				{
					printf("%d ", words);
				}
				if ((strcmp(commands[j],"-c")) == 0) 
				{
					printf("%d ",characters);
				}
			}
			printf("\n");
		}
		else
		{
			printf("%d %d %d \n", lines, words, characters);
		}	
	}
		
	
	//go through each file and calculate specific command
	if(fileCount > 0)
	{
		for (int i = 0; i < fileCount; i++)
		{
			lines = 0;
			words = 0;
			characters = 0;

			int fileRead = '\0';
			fp = fopen(fileNames[i], "r");
			if (fp) //if file is valid
			{
				while ((fileRead=getc(fp)) != EOF) //read character until end of file
				{
					if (commandCount > 0) //if command is greater than 0
					{
						for (int j = 0; j < commandCount; j++)
						{
							if ((strcmp(commands[j],"-l")) == 0)
							{
								if (fileRead == '\n'){++lines;}
							}
							else if ((strcmp(commands[j],"-w")) == 0)
							{
								if (fileRead == ' ' || fileRead == '\n'){++words;}
							}
							else if ((strcmp(commands[j],"-c")) == 0)
							{
								if (fileRead != ' ' && fileRead != '\n'){++characters;}
							}
							else 
							{
								printf("ERROR While Reading Command");
							}
						}
					}
					else 
					{
						//display everything
						if (fileRead == '\n'){++lines;}
						if (fileRead == ' ' || fileRead == '\n'){++words;}
						if (fileRead != ' ' && fileRead != '\n'){++characters;}
					}
				}
				tlines += lines;
				twords += words;
				tcharacters += characters;
			
				//Print lines, words, characters
				if (commandCount > 0) //if command is greater than 0
				{
					for(int j = 0; j < commandCount; j++)
					{
						if ((strcmp(commands[j],"-l")) == 0)
						{
							printf("%d ", lines);
						}
						
						if ((strcmp(commands[j],"-w")) == 0)
						{
							printf("%d ", words);
						}
						
						if ((strcmp(commands[j],"-c")) == 0)
						{
							printf("%d ", characters);
						}
					}
					printf("%s\n", fileNames[i]);
				}
				
				else
				{
					printf("%d %d %d %s\n", lines, words, characters, fileNames[i]);
				}
			}
			else
			{
				printf("Error in opening file: %s", fileNames[i]);
			}
			fclose(fp);
		}
	}

	if(fileCount>1)
	{
		if (commandCount > 0) //if command is greater than 0
				{
					for(int j = 0; j < commandCount; j++)
					{
						if ((strcmp(commands[j],"-l")) == 0)
						{
							printf("%d ", tlines);
						}
						
						if ((strcmp(commands[j],"-w")) == 0)
						{
							printf("%d ", twords);
						}
						
						if ((strcmp(commands[j],"-c")) == 0)
						{
							printf("%d ", tcharacters);
						}
					}
					printf("total\n");
				}
		else
		{
			printf("%d %d %d total\n", tlines, twords, tcharacters);
		}
	}
return 0;
}
