#include<stdio.h>
#include<string.h>

int getPathlen(char *path) {
	int i= 0;
	for( i = strlen(path); i > 0; i--) { 
		if(path[i-1] == '\\' || path[i] =='/') break;
	}
	return i;
}

int main(int argc,char* args[]) {
	int i  = 0; 
	//这是程序目录， 不知道怎么获得当前目录，自己填写吧

	char dir[256] = "/home/liubailin/bin";
	char comm[200] = {"java -Dfile.encoding=utf-8 -jar "};
	strcat(comm,dir);
	strcat(comm,"/s.jar ");
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
