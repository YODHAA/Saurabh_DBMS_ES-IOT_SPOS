import java.util.Scanner;


public class RoundRobin {

	int n;
	int at[],bt[];
	int wt[],tt[];
	int rt[];
	int rem_bt[];
	int ts;
	float avg_wt,avg_tt;
	Scanner sc;
	
	RoundRobin()
	{
		sc=new Scanner(System.in);
		avg_wt=0;
		avg_tt=0;
		ts=0;
	}
	
	void input()
	{
		System.out.println("Enter number of processes: ");
		n=sc.nextInt();
		
		at=new int[n];
		bt=new int[n];
		wt=new int[n];
		tt=new int[n];
		rem_bt=new int [n];

		
		for(int i=0;i<n;i++)
		{
			System.out.println("\nEnter arrival time of P["+i+"]: ");
			at[i]=sc.nextInt();
			
			System.out.println("Enter burst time of P["+i+"]: ");
			bt[i]=sc.nextInt();
		}
		
		System.out.println("Enter time slice:");
		ts=sc.nextInt();
		
	}
	
	void calc()
	{
		//--------calculate waiting time----------
		
	    for (int i = 0 ; i < n ; i++)
	        rem_bt[i] =  bt[i];
	    
	    int t=0;
	    
	    while(true)
	    {
	    	boolean done=true;
	    	
	    	for(int i=0;i<n;i++)
	    	{
	    		if(rem_bt[i]>0)
	    		{
	    			done =false;
	    			if(rem_bt[i]>ts)
	    			{
		    			t=t+ts;
		    			rem_bt[i]=rem_bt[i]-ts;	
	    			}
	    			else
		    		{
		    			t=t+rem_bt[i];
		    			wt[i]=t-bt[i];
		    			rem_bt[i]=0;
		    		}	
	    		}
	    	}
	    	
	    	if(done)
	    		break;
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
		
		RoundRobin obj=new RoundRobin();
		obj.input();
		obj.calc();
		obj.calc_avg();
		obj.output();
	}
}


