#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int getPathlen(char *path) {
	int i= 0;
	for( i = strlen(path); i > 0; i--) { 
		if(path[i-1] == '\\' || path[i] =='/') break;
	}
	return i;
}
/*
char* openPath(){  
	char *buf = malloc(255);
	int i = 0;
	FILE *file;
	char ch = 0;
	file = fopen("path.txt","r");
	ch = fgetc(file);
	while(!feof(file))
	{
		buf[i++] = ch;
		ch = fgetc(file);
		
	}
	buf[i] = '\0';
	fclose(file);
	return buf;
}
*/


char * getpath(){
	char *buf = malloc(255);
	int i = 0;
	for( i = 0; i < 255; i++){
		buf[i] = 0;
	}
	strncpy(buf,_pgmptr,getPathlen(_pgmptr));
	//printf("buf:%s",buf);
	return buf;
}

int main(int argc,char* args[]) {
	int i  = 0; 
	char *path = getpath();
	
	char comm[200] = {"java -Dfile.encoding=utf-8 -jar "};
	strcat(comm,path);
	strcat(comm,"\\s.jar --force ");
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
