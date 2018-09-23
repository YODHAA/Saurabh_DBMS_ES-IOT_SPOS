import java.util.Scanner;


public class SJFS {

	int n;
	int at[],bt[];
	int wt[],tt[];
	int rem_t[];
	int done[];
	float avg_wt,avg_tt;
	Scanner sc;
	
	SJFS()
	{
		sc=new Scanner(System.in);
		avg_wt=0;
		avg_tt=0;
	}
	
	void input()
	{
		System.out.println("Enter number of processes: ");
		n=sc.nextInt();
		
		at=new int[n];
		bt=new int[n];
		wt=new int[n];
		tt=new int[n];
		rem_t=new int [n];
		done=new int[n];
		
		for(int i=0;i<n;i++)
		{
			System.out.println("\nEnter arrival time of P["+i+"]: ");
			at[i]=sc.nextInt();
			
			System.out.println("Enter burst time of P["+i+"]: ");
			bt[i]=sc.nextInt();
		}
	}

	void my_swap(int i,int j)
	{
		int temp;
		
		/*
		
		//swap process id
		temp=p[i];
		p[i]=p[j];
		p[j]=temp;
		
		*/
		
		//swap arrival time
		temp=at[i];
		at[i]=at[j];
		at[j]=temp;
		
		//swap burst time 
		temp=rem_t[i];
		rem_t[i]=rem_t[j];
		rem_t[j]=temp;
		
	}
		

	void my_sort()
	{
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(rem_t[i]<rem_t[j])
					my_swap(i,j);
	}
	
	
	void calc()
	{
		//calculate waiting time	
		for(int i=0;i<n;i++)
			rem_t[i]=bt[i];
		
		
		int max_t=0;
		
		for(int i=0;i<n;i++)
		{
			max_t=max_t+bt[i];
			done[i]=0;
		}
		
		for(int t=0;t<max_t;t++)
		{
			my_sort();
			
			for(int i=0;i<n;i++)
			{
				if(at[i]<=t && done[i] == 0 && rem_t[i]>0)
				{
					rem_t[i]=rem_t[i]-1;
					if(rem_t[i]==0)
					{
						
						done[i]=1;
						wt[i]=(t+1)-bt[i]-at[i];	
					}
					break;
				} 
			}	
		} 
			
		//calculate turn around time
		for(int i=0;i<n;i++)
			tt[i]=wt[i]+bt[i];
	}
	
	void calc_avg()
	{	
		for(int i=0;i<n;i++)
		{
			avg_wt=avg_wt+wt[i];
			avg_tt=avg_tt+tt[i];
		}
		
		avg_wt=avg_wt/n;
		avg_tt=avg_tt/n;
	}

	void output()
	{	
		System.out.println("\n\nAverage waiting time= "+avg_wt);
		System.out.println("\nAverage waiting time= "+avg_tt);		
	}
	
	public static void main(String[] args) {
		
		SJFS obj=new SJFS();
		obj.input();
		obj.calc();
		obj.calc_avg();
		obj.output();
	}
}


