import java.util.Scanner;


public class FCFS {

	int n;
	int at[],bt[];
	int wt[],tt[];
	float avg_wt,avg_tt;
	Scanner sc;
	
	FCFS()
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
		
		for(int i=0;i<n;i++)
		{
			System.out.println("\nEnter arrival time of P["+i+"]: ");
			at[i]=sc.nextInt();
			
			System.out.println("Enter burst time of P["+i+"]: ");
			bt[i]=sc.nextInt();
		}
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
		System.out.println("\n\nAverage waiting time= "+avg_wt);
		System.out.println("\nAverage waiting time= "+avg_tt);		
	}
	
	public static void main(String[] args) {
		
		FCFS obj=new FCFS();
		obj.input();
		obj.calc();
		obj.calc_avg();
		obj.output();
	}
}


