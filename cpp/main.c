#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define M 10   //学号字符数
#define N 10   //姓名字符数
#define C 10   //班级字符数
#define S 4    //学科总数目
struct STUDENT
{
	char num[M];      //学号
	char name[N];     //姓名
	char Class[C];    //班级  
	int score[S];     //成绩
	struct STUDENT* next;
};
typedef struct STUDENT student;
student* head = NULL;    //创建头节点
//学科名称
char course[S][10] = { "政治","英语","数学","专业课" };
void Menu();                //功能界面

void GetStu(void);    //输入学生信息
void PrtStu(void);    //打印学生信息
void RevStu(void);    //修改学生信息
void FindStu(void);   //查找学生信息
void DelStu(void);    //删除学生信息
void StuNum(void);    //统计学生人数

void Write(void);     //保存链表信息到文件
void Read(void);      //读取文件信息到链表
void Test();          //判断文件是否存在或创建文件

student* AppNode(student* head);            //将输入信息存入链表节点
student* AppRead(student* head, FILE* fp);   //将文件信息存入链表节点
student* DelNode(student* head);            //将链表节点删除

void DelNote(void);       //销毁链表
int main()
{
	int ch = 0;
	Test();       //判断文件是否存在或创建文件
	Read(head);   //从文件里读取数据到链表
	while (1)
	{
		Menu();
		printf("请输入你想进行的操作对应的数字: ");
		scanf_s("%d", &ch);
		switch (ch)
		{
		case 1:  //输入学生信息
			GetStu();
			break;
		case 2:  //修改学生信息
			RevStu();
			break;
		case 3:  //查找学生信息
			FindStu();
			break;
		case 4:  //全部学生信息
			PrtStu();
			break;
		case 5:  //统计学生人数
			StuNum();
			break;
		case 6:  //删除学生信息
			DelStu();
			break;
		case 7:  //退出管理系统 
			Write();     //保存链表到文件
			DelNote();    //释放节点空间
			return 0;
		default:
			printf("输入有误，没有该功能");
			system("pause");  //暂停
			system("cls");    //清屏
			break;
		}
	}
	return 0;
}
//函数功能：打印功能界面
void Menu()
{
	printf("		**************学生成绩管理系统**************\n");
	printf("		*  1.录入新的学生成绩                      *\n");
	printf("		*  2.按学号修改学生成绩                    *\n");
	printf("		*  3.按学号查询学生成绩                    *\n");
	printf("		*  4.输出全部学生的成绩                    *\n");
	printf("		*  5.统计所有学生人数                      *\n");
	printf("		*  6.删除学生信息                          *\n");
	printf("		*  7.退出学生成绩管理系统                  *\n");
	printf("		********************************************\n");
}

//函数功能：向链表输入学生信息
void GetStu(void)
{
	head = AppNode(head);
	printf("学生信息录入成功。");
	system("pause");
	system("cls");
}
//函数功能：打印链表中学生信息
void PrtStu(void)
{
	system("cls");
	student* p = head;
	int i;
	if (p == NULL)
	{
		printf("系统中暂无学生信息，请录入后再来打印查看。");
	}
	else
	{
		printf("学号\t姓名\t班级\t");
		for (i = 0; i < S; i++)
		{
			printf("%s\t", course[i]);
		}
		while (p != NULL)
		{
			printf("\n%s\t%s\t%s\t", p->num, p->name, p->Class);
			for (i = 0; i < S; i++)
			{
				printf("%d\t", p->score[i]);
			}
			p = p->next;
		}
	}
	system("pause");
	system("cls");
}
//函数功能：修改链表中学生信息
void RevStu(void)
{
	int i, flag = 0, score[S] = { 0 };
	char num[M];
	student* p = head;
	//让用户输入学号
	printf("请输入学生学号：\n");
	scanf_s("%s", num, (int)sizeof(num));
	num[M - 1] = 0;       //字符串结尾加终止符      
	//遍历链表对比
	while (p)
	{
		//符合则修改并结束函数
		if (strcmp(p->num, num) == 0)
		{
			printf("请输入学号：\n");
			scanf_s("%s", p->num, (int)sizeof(p->num));
			printf("请输入学生姓名：\n");
			scanf_s("%s", p->name, (int)sizeof(p->name));
			printf("请输入班级：\n");
			scanf_s("%s", p->Class, (int)sizeof(p->Class));
			for (i = 0; i < S; i++)
			{
				printf("请输入%s成绩：\n", course[i]);
				scanf_s("%d", &score[i]);
				p->score[i] = score[i];
			}
			flag = 1;
			break;
		}
		//不符合则指向下一节点
		p = p->next;
	}
	//遍历链表未找到相关学号
	if (flag == 0)
		printf("学号输入有误，系统中暂无该学生信息。");

	system("pause");
	system("cls");
}
//函数功能：遍历链表查找学生信息
void FindStu(void)
{
	int i, flag = 0;
	char num[M];
	student* p = head;
	//让用户输入学号
	printf("请输入学生学号：\n");
	scanf_s("%s", num, (int)sizeof(num));
	num[M - 1] = 0;       //字符串结尾加终止符      
	//遍历链表对比
	while (p != NULL)
	{
		//符合则输出并结束函数
		if (strcmp(p->num, num) == 0)
		{
			printf("学号\t姓名\t班级\t");
			for (i = 0; i < S; i++)
			{
				printf("%s\t", course[i]);
			}
			printf("\n%s\t%s\t%s\t", p->num, p->name, p->Class);
			for (i = 0; i < S; i++)
			{
				printf("%d\t", p->score[i]);
			}
			flag = 1;
			break;
		}
		//不符合则指向下一节点
		p = p->next;
	}
	//遍历链表未找到相关学号
	if (flag == 0)
		printf("学号输入有误，系统中暂无该学生信息。");

	system("pause");
	system("cls");
}
//函数功能：删除链表中学生信息
void DelStu(void)
{
	head = DelNode(head);
	system("pause");
	system("cls");
}
//函数功能：统计所有学生人数
void StuNum(void)
{
	int number = 0;
	student* p = head;
	while (p != NULL)  //遍历链表
	{
		number++;
		p = p->next;
	}
	printf("学生总人数：%d", number);
	system("pause");
	system("cls");
}

//函数功能：保存链表信息到文件
void Write(void)
{
	student* p = head, * pr = NULL;
	int i, score[S] = { 0 };
	//打开文件
	FILE* fp;
	errno_t err;
	err = fopen_s(&fp, "D:\\student.txt", "w");

	if (err != 0)
	{
		printf("打开文件失败。");
		exit(0);
	}

	//写入数据
	while (p != NULL)
	{
		fprintf(fp, "%s ", p->num);
		fprintf(fp, "%s ", p->name);
		fprintf(fp, "%s ", p->Class);
		for (i = 0; i < S; i++)
		{
			score[i] = p->score[i];
			fprintf(fp, "%d ", score[i]);
		}
		p = p->next;
	}
	//关闭文件
	fclose(fp);

	printf("数据保存成功。\n");
	system("pause");
	system("cls");
}
//函数功能：读取文件信息到链表
void Read(void)
{
	//打开文件流
	FILE* fp;
	errno_t err;
	err = fopen_s(&fp, "D:\\student.txt", "r");
	if (err != 0)
	{
		printf("文件打开失败");
		exit(0);
	}
	//读取数据
	while (fgetc(fp) != EOF)
	{
		head = AppRead(head, fp);
	}
	//关闭文件流
	fclose(fp);
}
//函数功能：判断文件是否存在或创建文件
void Test()
{
	//打开文件流
	FILE* fp;
	errno_t err;
	err = fopen_s(&fp, "D:\\student.txt", "r");
	if (fp == NULL)
	{
		err = fopen_s(&fp, "D:\\student.txt", "w");
		if (err != 0)
		{
			printf("打开文件失败。");
			exit(0);
		}
	}
	//关闭文件
	fclose(fp);
}

//函数功能：新建一个节点并添加到链表末尾，返回添加节点后的链表头指针
student* AppNode(student* head)
{
	student* p = NULL, * pr = head;
	int i, score[S] = { 0 };          //成绩
	p = (student*)malloc(sizeof(student));  //让p指向新建节点
	if (p == NULL)                          //若为新建节点申请内存失败，退出程序
	{
		printf("为新建节点申请内存失败");
		exit(0);
	}
	if (head == NULL)                       //若原链表为空表
	{
		head = p;                           //将新建节点置为头节点
	}
	else                                    //若原链表为非空，则将新建节点添加到表尾
	{
		while (pr->next != NULL)            //若未到表尾，则移动pr直至pr指向表尾
		{
			pr = pr->next;                  //让pr指向下一节点
		}
		pr->next = p;                       //让末节点的指针域指向新建节点
	}
	//录入学生信息
	printf("请输入学号：\n");
	scanf_s("%s", p->num, (int)sizeof(p->num));
	printf("请输入学生姓名：\n");
	scanf_s("%s", p->name, (int)sizeof(p->name));
	printf("请输入班级：\n");
	scanf_s("%s", p->Class, (int)sizeof(p->Class));
	for (i = 0; i < S; i++)
	{
		printf("请输入%s成绩：\n", course[i]);
		scanf_s("%d", &score[i]);
		p->score[i] = score[i];
	}
	p->next = NULL;                         //将新建节点置为表尾
	return head;                            //返回添加节点后的链表头指针
}
//函数功能：新建一个节点并添加到链表末尾，返回添加节点后的链表头指针
student* AppRead(student* head, FILE* fp)
{
	student* p = NULL, * pr = head;
	int i, score[S] = { 0 };
	p = (student*)malloc(sizeof(student));  //让p指向新建节点
	if (p == NULL)                          //若为新建节点申请内存失败，退出程序
	{
		printf("为新建节点申请内存失败");
		exit(0);
	}
	if (head == NULL)                       //若原链表为空表
	{
		head = p;                           //将新建节点置为头节点
	}
	else                                    //若原链表为非空，则将新建节点添加到表尾
	{
		while (pr->next != NULL)            //若未到表尾，则移动pr直至pr指向表尾
		{
			pr = pr->next;                  //让pr指向下一节点
		}
		pr->next = p;                       //让末节点的指针域指向新建节点
	}
	//读取学生信息
	fscanf_s(fp, "%s", p->num, (int)sizeof(p->num));
	fscanf_s(fp, "%s", p->name, (int)sizeof(p->name));
	fscanf_s(fp, "%s", p->Class, (int)sizeof(p->Class));
	for (i = 0; i < S; i++)
	{
		fscanf_s(fp, "%d", &score[i]);
		p->score[i] = score[i];
	}
	p->next = NULL;
	return head;                            //返回添加节点后的链表头指针
}
//函数功能：删除链表节点
student* DelNode(student* head)
{
	char num[M];
	student* p = head, * pr = head;
	//让用户输入学号
	printf("请输入学生学号：\n");
	scanf_s("%s", num, (int)sizeof(num));
	num[M - 1] = 0;       //字符串结尾加终止符 
	if (head == NULL)
	{
		printf("系统中暂无学生信息\n");
		return(head);
	}
	while (strcmp(p->num, num) != 0 && p->next != NULL)
	{
		pr = p;
		p = p->next;
	}
	if (strcmp(p->num, num) == 0)   //若找到待删除节点
	{
		if (p == head)              //若待删除节点为头节点
		{
			head = p->next;         //让头节点指向待删除节点的下一节点
		}
		else                        //若删除节点不是头节点
		{
			pr->next = p->next;
		}
		free(p);                    //释放为已删除节点分配的内存
		printf("学生信息删除成功。");
	}
	else                            //遍历链表未找到相关学号
	{
		printf("学号输入有误，系统中暂无该学生信息。");
	}
	return head;                    //返回删除节点后的链表头指针
}
//释放head指向的链表中所有节点占用的内存
void DelNote(void)
{
	student* p = head, * pr = NULL;
	while (p != NULL)
	{
		pr = p;
		p = p->next;
		free(pr);
	}
}
