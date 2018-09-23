import java.util.Scanner;


public class Priority {

	int n;
	int at[],bt[];
	int p[],pr[];
	int wt[],tt[];
	float avg_wt,avg_tt;
	Scanner sc;
	
	Priority()
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
		p=new int[n];
		pr=new int[n];
		wt=new int[n];
		tt=new int[n];
		
		for(int i=0;i<n;i++)
		{
			//System.out.println("\nEnter process id of P["+i+"]: ");
			//p[i]=sc.nextInt();
		
			System.out.println("\nEnter arrival time of P["+i+"]: ");
			at[i]=sc.nextInt();
			
			System.out.println("Enter burst time of P["+i+"]: ");
			bt[i]=sc.nextInt();

			System.out.println("Enter priority of P["+i+"]: ");
			pr[i]=sc.nextInt();
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
		at[i]=p[j];
		at[j]=temp;
		
		//swap burst time 
		temp=bt[i];
		bt[i]=bt[j];
		bt[j]=temp;
		
		//swap arrival time
		temp=pr[i];
		pr[i]=pr[j];
		pr[j]=temp;		
		
	}
	
	void sort_pri()
	{
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(pr[i]<pr[j])
					my_swap(i,j);
		
	}
	
	void calc()
	{
		//calculate waiting time	
		wt[0]=0;
		
		for(int i=1;i<n;i++)
		{
			wt[i]=wt[i-1]+bt[i-1];
			wt[i]=wt[i]-at[i];
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

		for(int i=0;i<n;i++)
			System.out.println(pr[i]);
		
		System.out.println("\n\nAverage waiting time= "+avg_wt);
		System.out.println("\nAverage waiting time= "+avg_tt);		
	}
	
	public static void main(String[] args) {
		
		Priority obj=new Priority();
		obj.input();
		obj.sort_pri();
		obj.calc();
		obj.calc_avg();
		obj.output();
	}
}


