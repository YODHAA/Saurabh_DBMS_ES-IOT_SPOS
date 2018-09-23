
// wait syscall code in  c

a call to the wait() blocks the the calling process until one of its child process exits or a signal is recieved .
After child process terminates parent continues its execution After wait system call instructions.

// wait : takes one argument status and return a process id of dead children

if a process does not have a child process then wait() return immediately -1 .

// code :

#include<stdio.h>
#include<stdlib.h>
#include<sys/wait.h>
#include<unistd.h>

int main(){
	pid_t cpid;
	if(fork()==0){
		exit(0);
	}
	else
	{
		cpid=wait(NULL);
	}

	printf("parent ID: %d \n",getpid());
	printf("child ID : %d \n",cpid);

}


done: fork and wait syscall .


// exec syscall 

it replaces the current process by the newly created process. 
it can be used to run another prog. 
<unistd.h> must be included .
members of exec family:
  1. execvp : using this command the created child process doesnot have to run the same programme as the 
              parent process does. 

 // code :

              1. exec.c

                    #include<stdio.h>
                    #include<unistd.h>

                    int main(){
                    	int i ;
                    	printf(" I am exec.c called by execvp() \n");
                    	return 0;
                    }
                  gcc exec.c -o exec
                 
               2.  execdemo.c 

                     #include<stdio.h>
                     #include<stdlib.h>
                     #include<unistd.h>

                    int main(){

                    	char *args[]={"./exec",NULL};
                    	execvp(args[0],args);

                    	printf("ending ---");
                    	return 0;
                    }

                    gcc execdemo.c -o execdemo
                    ./execdemo 


  // ps syscall 

its used to provide the info about currently running proccesses including their proccess id.
PID ,TTY, TIME , CMD : 

 take a snapshot of current proccess. 
    1. ps T : print basic list of current terminals
    2. ps X : print extensive list 

     ps -e : gives all the running proccess .

     ps -ef | grep pid(12303)

     ps -fu root : cmd run by root .


     ps -aux | less 
      -a: list all the users procees 
      -u: provide detailed info about each proccess.
      -x: it adds to list the process with no control terminals such as demons proccess.

      less: list of processes can be quite long and occupy more than a single screen to avoid it.


 // join cmd

      it joins to files.

      1. craete one file a.txt
      2. create another file b.txt
      3. join -1 2 -2 1 -1 ":" a.txt b.txt > c.txt

      