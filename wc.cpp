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
	FILE *fp; //file pointer
	char input[100];

	
	//Check for arguments
	if (argc > 1) //if argc has more than the executable name
	{
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
					break;
				case 'w':
					commands[commandCount] = argv[f];
					commandCount++;
					f++;
					break;
				case 'c':
					commands[commandCount] = argv[f];
					commandCount++;
					f++;
					break;
				default :
					printf("Error");
					break;
			}
		}
			
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
						else if ((strcmp(commands[j],"-w")) == 0)
						{
							printf("%d ", words);
						}
						else if ((strcmp(commands[j],"-c")) == 0)
						{
							printf("%d ",characters);
						}
					}
				}
				else
				{
					printf("%d %d %d ", lines, words, characters);
				}
			}
			else //FileName input 
			{
				fileNames[fileCount] = argv[i]; //store filename in array 
				fileCount++;
			}
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
					//	for (int j = 0; j < commandCount; j++)
					//	{
							if (((strcmp(commands[0],"-l")) == 0) || ((strcmp(commands[1],"-l")) == 0) || ((strcmp(commands[2],"-l")) == 0))
							{
								if (fileRead == '\n'){++lines;}
							}
							else if (((strcmp(commands[0],"-w")) == 0) || ((strcmp(commands[1],"-w")) == 0) || ((strcmp(commands[2],"-l")) == 0))
							{
								if (fileRead == ' ' || fileRead == '\n'){++words;}
							}
							else if (((strcmp(commands[0],"-c")) == 0) || ((strcmp(commands[1],"-c")) == 0) || ((strcmp(commands[2],"-c")) == 0))
							{
								if (fileRead != ' ' && fileRead != '\n'){++characters;}
							}
							else 
							{
								printf("ERROR While Reading Command");
							}
					//	}
					}
					else 
					{
						//display everything
						if (fileRead == '\n'){++lines;}
						if (fileRead == ' ' || fileRead == '\n'){++words;}
						if (fileRead != ' ' && fileRead != '\n'){++characters;}
					}
				}
				
				//Print lines, words, characters
				if (commandCount > 0) //if command is greater than 0
				{
					for (int j = 0; j < commandCount; j++)
					{
						if (((strcmp(commands[0],"-l")) == 0) || ((strcmp(commands[1],"-l")) == 0) || ((strcmp(commands[2],"-l")) == 0))
						{
							printf("%d ", lines);
						}
						
						else if (((strcmp(commands[0],"-w")) == 0) || ((strcmp(commands[1],"-w")) == 0) || ((strcmp(commands[2],"-l")) == 0))	
						{
							printf("%d ", words);
						}
						
						else if (((strcmp(commands[0],"-c")) == 0) || ((strcmp(commands[1],"-c")) == 0) || ((strcmp(commands[2],"-c")) == 0))	
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

return 0;
}
