import java.io.*;
import java.util.*;
class Banker
{
 static int safe[]=new int[20];
 static boolean safety(int a[],int al[][],int need[][],int n1,int m1)
 {
	int n=n1;
	int m=m1;
	int nd[][]=new int[n][m];
	int work[]=new int[m];
	int all[][]=new int[n][m];
	
	for(int i=0;i<m;i++)
	work[i]=a[i];

	for(int i=0;i<n;i++)
	for(int j=0;j<m;j++)
	all[i][j]=al[i][j];

	for(int i=0;i<n;i++)
	for(int j=0;j<m;j++)
	nd[i][j]=need[i][j];

	boolean fin[]=new boolean[n];

	for(int i=0;i<n;i++)
	fin[i]=false;

	int check=0;
	int check1=0;
	
	do
	{
		for(int i=0;i<n;i++)
		{
		boolean flag=true;
		if(fin[i]==false)
		{
		 for(int j=0;j<m;j++)
		{if(work[j]<nd[i][j])
		 flag=false;
		}
		if(flag)
		{
		 for(int j=0;j<m;j++)
		work[j]+=all[i][j];
		safe[check]=i;
		check++;
		fin[i]=true;
		}
		}	
			
		}
	
		check1++;
	}while(check<n && check1<n);


	if(check>n)
	return false;
	else 
	return true;
	
	

 }

static boolean reqFunc(int a[],int al[][],int need[][],int req[],int pid,int n1,int m1)
{
 int n=n1;
 int m=m1;
 int avail[]=new int[m];
int alloc[][]=new int[n][m];
int need1[][]=new int[n][m];
int req1[]=new int[m];
int r=pid;

       for(int i=0;i<m;i++)
	req1[i]=req[i];

	for(int i=0;i<m;i++)
	avail[i]=a[i];
	
        for(int i=0;i<n;i++)
	for(int j=0;j<m;j++)
	alloc[i][j]=al[i][j];

	for(int i=0;i<n;i++)
	for(int j=0;j<m;j++)
	need1[i][j]=need[i][j];

	boolean flag=true;
	for(int i=0;i<m;i++)
	{
	if(need1[r][i]<req1[i])
	flag=false;
	}

	if(flag)
	{
	for(int i=0;i<m;i++)
	if(avail[i]<req1[i])
	flag=false;
	
	if(flag)
	{
	for(int i=0;i<m;i++)
	{
	alloc[r][i]+=req1[i];
	need1[r][i]-=req1[i];
	avail[i]-=req1[i];
	}
	
	if(safety(avail,alloc,need1,n,m))
	return true;
	else
	System.out.println("It leads to deadlock ,so request can't be granted");
	}
	else
	System.out.println("process p"+r+"should wait");
	}
	else
	System.out.println("Error:process exceeding Limit");
	return false;

	
}

public static void main(String args[])throws IOException
{
	InputStreamReader isr=new InputStreamReader(System.in);
	BufferedReader obj=new BufferedReader(isr);
	
	int n,m;
	System.out.println("enter no. of processes:");
	n=Integer.parseInt(obj.readLine());
	System.out.println("enter no. of resources:");
	m=Integer.parseInt(obj.readLine());

	int a[]=new int[m];
	for(int i=0;i<m;i++)
	{
	 System.out.println("enter no. of available instances resources:"+i);
	 a[i]=Integer.parseInt(obj.readLine());
	
	}
	
	System.out.println("enter allocation of resources:");
	int al[][]=new int[n][m];
	for(int i=0;i<n;i++)
	for(int j=0;j<m;j++)
	{
System.out.println("enter allocation instances of resources:"+j+"for process p"+i);	
	al[i][j]=Integer.parseInt(obj.readLine());
	
	}

System.out.println("enter maximum of resources:");
	int max[][]=new int[n][m];
	for(int i=0;i<n;i++)
	for(int j=0;j<m;j++)
	{
System.out.println("enter max instances of resources:"+j+"for process p"+i);	
	max[i][j]=Integer.parseInt(obj.readLine());
	
	}


	int need[][]=new int[n][m];
	for(int i=0;i<n;i++)
	for(int j=0;j<m;j++)
	{
	
	need[i][j]=max[i][j]-al[i][j];
	
	}
	
	if(safety(a,al,need,n,m))
	{
	 System.out.println("System in Safe State");
	System.out.println("System's Safe sequence:");
	for(int i=0;i<n;i++)
	System.out.println(safe[i]+" ");	
	}

	else
	System.out.println("System in UnSafe State");

	System.out.println("do u wanna to hav a request for any process,then enter Process no. and Requesting resources");
	int pid=Integer.parseInt(obj.readLine());
	int req[]=new int[m];
	for(int i=0;i<m;i++)
	req[i]=Integer.parseInt(obj.readLine());
	if(reqFunc(a,al,need,req,pid,n,m))
	{	
	System.out.println("request can be granted");
	for(int i=0;i<m;i++)
	{al[pid][i]+=req[i];
	need[pid][i]-=req[i];
	a[i]-=req[i];

	}
        if(safety(a,al,need,n,m))
	{
	System.out.println("System in Safe State");
	System.out.println("System's Safe sequence:");
	for(int i=0;i<n;i++)
	System.out.println(safe[i]+" ");
	}
	else
	System.out.println("System in UnSafe State");
	}
	
}
}

/*
OUTPUT:

	C:\Program Files\Java\jdk1.6.0_02\bin>java Banker
enter no. of processes:
5
enter no. of resources:
3
enter no. of available instances resources:0
3
enter no. of available instances resources:1
3
enter no. of available instances resources:2
2
enter allocation of resources:
enter allocation instances of resources:0for process p0
0
enter allocation instances of resources:1for process p0
1
enter allocation instances of resources:2for process p0
0
enter allocation instances of resources:0for process p1
2
enter allocation instances of resources:1for process p1
0
enter allocation instances of resources:2for process p1
0
enter allocation instances of resources:0for process p2
3
enter allocation instances of resources:1for process p2
0
enter allocation instances of resources:2for process p2
2
enter allocation instances of resources:0for process p3
2
enter allocation instances of resources:1for process p3
1
enter allocation instances of resources:2for process p3
1
enter allocation instances of resources:0for process p4
0
enter allocation instances of resources:1for process p4
0
enter allocation instances of resources:2for process p4
2
enter maximum of resources:
enter max instances of resources:0for process p0
7
enter max instances of resources:1for process p0
5
enter max instances of resources:2for process p0
3
enter max instances of resources:0for process p1
3
enter max instances of resources:1for process p1
2
enter max instances of resources:2for process p1
2
enter max instances of resources:0for process p2
9
enter max instances of resources:1for process p2
0
enter max instances of resources:2for process p2
2
enter max instances of resources:0for process p3
2
enter max instances of resources:1for process p3
2
enter max instances of resources:2for process p3
2
enter max instances of resources:0for process p4
4
enter max instances of resources:1for process p4
3
enter max instances of resources:2for process p4
3
System in Safe State
System's Safe sequence:
1
3
4
0
2
do u wanna to hav a request for any process,then enter Process no. and Requestin
g resources
4
3
3
0
request can be granted
System in Safe State
System's Safe sequence:
1
3
4
0
2
*/