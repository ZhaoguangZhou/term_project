#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define M 10   //ѧ���ַ���
#define N 10   //�����ַ���
#define C 10   //�༶�ַ���
#define S 4    //ѧ������Ŀ
struct STUDENT
{
	char num[M];      //ѧ��
	char name[N];     //����
	char Class[C];    //�༶  
	int score[S];     //�ɼ�
	struct STUDENT* next;
};
typedef struct STUDENT student;
student* head = NULL;    //����ͷ�ڵ�
//ѧ������
char course[S][10] = { "����","Ӣ��","��ѧ","רҵ��" };
void Menu();                //���ܽ���

void GetStu(void);    //����ѧ����Ϣ
void PrtStu(void);    //��ӡѧ����Ϣ
void RevStu(void);    //�޸�ѧ����Ϣ
void FindStu(void);   //����ѧ����Ϣ
void DelStu(void);    //ɾ��ѧ����Ϣ
void StuNum(void);    //ͳ��ѧ������

void Write(void);     //����������Ϣ���ļ�
void Read(void);      //��ȡ�ļ���Ϣ������
void Test();          //�ж��ļ��Ƿ���ڻ򴴽��ļ�

student* AppNode(student* head);            //��������Ϣ��������ڵ�
student* AppRead(student* head, FILE* fp);   //���ļ���Ϣ��������ڵ�
student* DelNode(student* head);            //������ڵ�ɾ��

void DelNote(void);       //��������
int main()
{
	int ch = 0;
	Test();       //�ж��ļ��Ƿ���ڻ򴴽��ļ�
	Read(head);   //���ļ����ȡ���ݵ�����
	while (1)
	{
		Menu();
		printf("������������еĲ�����Ӧ������: ");
		scanf_s("%d", &ch);
		switch (ch)
		{
		case 1:  //����ѧ����Ϣ
			GetStu();
			break;
		case 2:  //�޸�ѧ����Ϣ
			RevStu();
			break;
		case 3:  //����ѧ����Ϣ
			FindStu();
			break;
		case 4:  //ȫ��ѧ����Ϣ
			PrtStu();
			break;
		case 5:  //ͳ��ѧ������
			StuNum();
			break;
		case 6:  //ɾ��ѧ����Ϣ
			DelStu();
			break;
		case 7:  //�˳�����ϵͳ 
			Write();     //���������ļ�
			DelNote();    //�ͷŽڵ�ռ�
			return 0;
		default:
			printf("��������û�иù���");
			system("pause");  //��ͣ
			system("cls");    //����
			break;
		}
	}
	return 0;
}
//�������ܣ���ӡ���ܽ���
void Menu()
{
	printf("		**************ѧ���ɼ�����ϵͳ**************\n");
	printf("		*  1.¼���µ�ѧ���ɼ�                      *\n");
	printf("		*  2.��ѧ���޸�ѧ���ɼ�                    *\n");
	printf("		*  3.��ѧ�Ų�ѯѧ���ɼ�                    *\n");
	printf("		*  4.���ȫ��ѧ���ĳɼ�                    *\n");
	printf("		*  5.ͳ������ѧ������                      *\n");
	printf("		*  6.ɾ��ѧ����Ϣ                          *\n");
	printf("		*  7.�˳�ѧ���ɼ�����ϵͳ                  *\n");
	printf("		********************************************\n");
}

//�������ܣ�����������ѧ����Ϣ
void GetStu(void)
{
	head = AppNode(head);
	printf("ѧ����Ϣ¼��ɹ���");
	system("pause");
	system("cls");
}
//�������ܣ���ӡ������ѧ����Ϣ
void PrtStu(void)
{
	system("cls");
	student* p = head;
	int i;
	if (p == NULL)
	{
		printf("ϵͳ������ѧ����Ϣ����¼���������ӡ�鿴��");
	}
	else
	{
		printf("ѧ��\t����\t�༶\t");
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
//�������ܣ��޸�������ѧ����Ϣ
void RevStu(void)
{
	int i, flag = 0, score[S] = { 0 };
	char num[M];
	student* p = head;
	//���û�����ѧ��
	printf("������ѧ��ѧ�ţ�\n");
	scanf_s("%s", num, (int)sizeof(num));
	num[M - 1] = 0;       //�ַ�����β����ֹ��      
	//��������Ա�
	while (p)
	{
		//�������޸Ĳ���������
		if (strcmp(p->num, num) == 0)
		{
			printf("������ѧ�ţ�\n");
			scanf_s("%s", p->num, (int)sizeof(p->num));
			printf("������ѧ��������\n");
			scanf_s("%s", p->name, (int)sizeof(p->name));
			printf("������༶��\n");
			scanf_s("%s", p->Class, (int)sizeof(p->Class));
			for (i = 0; i < S; i++)
			{
				printf("������%s�ɼ���\n", course[i]);
				scanf_s("%d", &score[i]);
				p->score[i] = score[i];
			}
			flag = 1;
			break;
		}
		//��������ָ����һ�ڵ�
		p = p->next;
	}
	//��������δ�ҵ����ѧ��
	if (flag == 0)
		printf("ѧ����������ϵͳ�����޸�ѧ����Ϣ��");

	system("pause");
	system("cls");
}
//�������ܣ������������ѧ����Ϣ
void FindStu(void)
{
	int i, flag = 0;
	char num[M];
	student* p = head;
	//���û�����ѧ��
	printf("������ѧ��ѧ�ţ�\n");
	scanf_s("%s", num, (int)sizeof(num));
	num[M - 1] = 0;       //�ַ�����β����ֹ��      
	//��������Ա�
	while (p != NULL)
	{
		//�������������������
		if (strcmp(p->num, num) == 0)
		{
			printf("ѧ��\t����\t�༶\t");
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
		//��������ָ����һ�ڵ�
		p = p->next;
	}
	//��������δ�ҵ����ѧ��
	if (flag == 0)
		printf("ѧ����������ϵͳ�����޸�ѧ����Ϣ��");

	system("pause");
	system("cls");
}
//�������ܣ�ɾ��������ѧ����Ϣ
void DelStu(void)
{
	head = DelNode(head);
	system("pause");
	system("cls");
}
//�������ܣ�ͳ������ѧ������
void StuNum(void)
{
	int number = 0;
	student* p = head;
	while (p != NULL)  //��������
	{
		number++;
		p = p->next;
	}
	printf("ѧ����������%d", number);
	system("pause");
	system("cls");
}

//�������ܣ�����������Ϣ���ļ�
void Write(void)
{
	student* p = head, * pr = NULL;
	int i, score[S] = { 0 };
	//���ļ�
	FILE* fp;
	errno_t err;
	err = fopen_s(&fp, "D:\\student.txt", "w");

	if (err != 0)
	{
		printf("���ļ�ʧ�ܡ�");
		exit(0);
	}

	//д������
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
	//�ر��ļ�
	fclose(fp);

	printf("���ݱ���ɹ���\n");
	system("pause");
	system("cls");
}
//�������ܣ���ȡ�ļ���Ϣ������
void Read(void)
{
	//���ļ���
	FILE* fp;
	errno_t err;
	err = fopen_s(&fp, "D:\\student.txt", "r");
	if (err != 0)
	{
		printf("�ļ���ʧ��");
		exit(0);
	}
	//��ȡ����
	while (fgetc(fp) != EOF)
	{
		head = AppRead(head, fp);
	}
	//�ر��ļ���
	fclose(fp);
}
//�������ܣ��ж��ļ��Ƿ���ڻ򴴽��ļ�
void Test()
{
	//���ļ���
	FILE* fp;
	errno_t err;
	err = fopen_s(&fp, "D:\\student.txt", "r");
	if (fp == NULL)
	{
		err = fopen_s(&fp, "D:\\student.txt", "w");
		if (err != 0)
		{
			printf("���ļ�ʧ�ܡ�");
			exit(0);
		}
	}
	//�ر��ļ�
	fclose(fp);
}

//�������ܣ��½�һ���ڵ㲢��ӵ�����ĩβ��������ӽڵ�������ͷָ��
student* AppNode(student* head)
{
	student* p = NULL, * pr = head;
	int i, score[S] = { 0 };          //�ɼ�
	p = (student*)malloc(sizeof(student));  //��pָ���½��ڵ�
	if (p == NULL)                          //��Ϊ�½��ڵ������ڴ�ʧ�ܣ��˳�����
	{
		printf("Ϊ�½��ڵ������ڴ�ʧ��");
		exit(0);
	}
	if (head == NULL)                       //��ԭ����Ϊ�ձ�
	{
		head = p;                           //���½��ڵ���Ϊͷ�ڵ�
	}
	else                                    //��ԭ����Ϊ�ǿգ����½��ڵ���ӵ���β
	{
		while (pr->next != NULL)            //��δ����β�����ƶ�prֱ��prָ���β
		{
			pr = pr->next;                  //��prָ����һ�ڵ�
		}
		pr->next = p;                       //��ĩ�ڵ��ָ����ָ���½��ڵ�
	}
	//¼��ѧ����Ϣ
	printf("������ѧ�ţ�\n");
	scanf_s("%s", p->num, (int)sizeof(p->num));
	printf("������ѧ��������\n");
	scanf_s("%s", p->name, (int)sizeof(p->name));
	printf("������༶��\n");
	scanf_s("%s", p->Class, (int)sizeof(p->Class));
	for (i = 0; i < S; i++)
	{
		printf("������%s�ɼ���\n", course[i]);
		scanf_s("%d", &score[i]);
		p->score[i] = score[i];
	}
	p->next = NULL;                         //���½��ڵ���Ϊ��β
	return head;                            //������ӽڵ�������ͷָ��
}
//�������ܣ��½�һ���ڵ㲢��ӵ�����ĩβ��������ӽڵ�������ͷָ��
student* AppRead(student* head, FILE* fp)
{
	student* p = NULL, * pr = head;
	int i, score[S] = { 0 };
	p = (student*)malloc(sizeof(student));  //��pָ���½��ڵ�
	if (p == NULL)                          //��Ϊ�½��ڵ������ڴ�ʧ�ܣ��˳�����
	{
		printf("Ϊ�½��ڵ������ڴ�ʧ��");
		exit(0);
	}
	if (head == NULL)                       //��ԭ����Ϊ�ձ�
	{
		head = p;                           //���½��ڵ���Ϊͷ�ڵ�
	}
	else                                    //��ԭ����Ϊ�ǿգ����½��ڵ���ӵ���β
	{
		while (pr->next != NULL)            //��δ����β�����ƶ�prֱ��prָ���β
		{
			pr = pr->next;                  //��prָ����һ�ڵ�
		}
		pr->next = p;                       //��ĩ�ڵ��ָ����ָ���½��ڵ�
	}
	//��ȡѧ����Ϣ
	fscanf_s(fp, "%s", p->num, (int)sizeof(p->num));
	fscanf_s(fp, "%s", p->name, (int)sizeof(p->name));
	fscanf_s(fp, "%s", p->Class, (int)sizeof(p->Class));
	for (i = 0; i < S; i++)
	{
		fscanf_s(fp, "%d", &score[i]);
		p->score[i] = score[i];
	}
	p->next = NULL;
	return head;                            //������ӽڵ�������ͷָ��
}
//�������ܣ�ɾ������ڵ�
student* DelNode(student* head)
{
	char num[M];
	student* p = head, * pr = head;
	//���û�����ѧ��
	printf("������ѧ��ѧ�ţ�\n");
	scanf_s("%s", num, (int)sizeof(num));
	num[M - 1] = 0;       //�ַ�����β����ֹ�� 
	if (head == NULL)
	{
		printf("ϵͳ������ѧ����Ϣ\n");
		return(head);
	}
	while (strcmp(p->num, num) != 0 && p->next != NULL)
	{
		pr = p;
		p = p->next;
	}
	if (strcmp(p->num, num) == 0)   //���ҵ���ɾ���ڵ�
	{
		if (p == head)              //����ɾ���ڵ�Ϊͷ�ڵ�
		{
			head = p->next;         //��ͷ�ڵ�ָ���ɾ���ڵ����һ�ڵ�
		}
		else                        //��ɾ���ڵ㲻��ͷ�ڵ�
		{
			pr->next = p->next;
		}
		free(p);                    //�ͷ�Ϊ��ɾ���ڵ������ڴ�
		printf("ѧ����Ϣɾ���ɹ���");
	}
	else                            //��������δ�ҵ����ѧ��
	{
		printf("ѧ����������ϵͳ�����޸�ѧ����Ϣ��");
	}
	return head;                    //����ɾ���ڵ�������ͷָ��
}
//�ͷ�headָ������������нڵ�ռ�õ��ڴ�
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