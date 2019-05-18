#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define ABSOUTION 1

#if ABSOUTION != 1
#define getPath getPath
int getPathlen(char *path) {
	int i= 0;
	for( i = strlen(path); i > 0; i--) { 
		if(path[i-1] == '\\' || path[i] =='/') break;
	}
	return i;
}
char * getpath(){
	char *buf = malloc(255);
	int i = 0;
	for( i = 0; i < 255; i++){
		buf[i] = 0;
	}
	strncpy(buf,_pgmptr,getPathlen(_pgmptr));
	printf("buf:\n<%s>\n",buf);
	return buf;
}
#endif
#if ABSOUTION == 1
#define getPath openPath
#define TMPSIZE 256
/*
在path.txt指明绝对路径
*/
char* openPath(){
	char *buf = malloc(TMPSIZE);
	int i = 0;
	FILE *file;
	char ch = 0;
	file = fopen("path.txt","rb");
	if(file == 0) {
		return 0;
	}
	ch = fgetc(file);
	while(!feof(file) && (i < TMPSIZE - 1) )
	{
		if(ch != '\n') buf[i++] = ch;
		ch = fgetc(file);
	}
	buf[i] = '\0';
	fclose(file);
	return buf;
}


#endif
int main(int argc,char* args[]) {
	int i  = 0; 
	char *path = getPath();
	
	char comm[256] = {"java -Dfile.encoding=utf-8 -jar "};
	strcat(comm,path);
	strcat(comm,"s.jar --force ");

	free(path);
	
	for(i = 1; i < argc; i++){
		strcat(comm," ");
		strcat(comm,args[i]);
	}
#if defined (__WIN32) /*解决 win下unicode乱码问题*/
	system("@CHCP 65001");
	system("@cls");
#endif
	system(comm);
	return 0;
}
