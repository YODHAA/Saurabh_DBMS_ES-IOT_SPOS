import java.util.*;
import java.io.*;

class Symbol {
	String name;
	int address;
	public Symbol(String name) {
		this.name = name;
	}
}

class Literal {
	String name;
	int address;
	public Literal(String name) {
		this.name = name;
	}
}

public class pass1 {

	int counts,countl,unlit,lc;
	Symbol sym[];
	Literal lit[];

	public pass1() {
		counts = countl = unlit = lc = 0;
		sym = new Symbol[10];
		lit = new Literal[10];
	}

	int searchTable(String[] table, String element) {
		for(int i=0;i<table.length;i++)
			if(element.equals(table[i]))
				return i;
		return -1;
	}

	int searchSymbolTable(String element) {
		for(int i=0;i<counts;i++)
			if(element.equals(sym[i].name))
				return i;
		sym[counts] = new Symbol(element);
		counts++;
		return counts-1;
	}

	int searchLiteralTable(String element) {
		for(int i=0;i<countl;i++)
			if(element.equals(lit[i].name))
				return i;
		lit[countl] = new Literal(element);
		countl++;
		unlit++;
		return countl-1;
	}

	void writeSymbolTable() throws Exception {
		BufferedWriter out = new BufferedWriter(new FileWriter("symbol.txt"));
		for(int i=0;i<counts;i++)
			out.write(sym[i].name + " " + sym[i].address + "\n");
		out.close();
	}

	void writeLiteralTable() throws Exception {
		BufferedWriter out = new BufferedWriter(new FileWriter("literal.txt"));
		for(int i=0;i<countl;i++)
			out.write(lit[i].name + " " + lit[i].address + "\n");
		out.close();
	}

	public void work() throws Exception {
		String ad[] = {"START", "END", "LTORG"};
		String rg[] = {"AREG", "BREG", "CREG", "DREG"};
		String cc[] = {"EQ", "LT", "GT", "LE", "GE", "NE", "ANY"};
		String imp[] = {"STOP", "ADD", "SUB", "MULT", "MOVER", "MOVEM", "COMP", "BC", "DIV", "READ", "PRINT"};
		String dl[] = {"DS", "DC"};

		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter("intermediate.txt"));
		String s,sout;

		while((s = in.readLine()) != null) {
			sout = "";
			StringTokenizer sin = new StringTokenizer(s, " ");
			while(sin.hasMoreTokens()) {
				String token = sin.nextToken();
				int m = searchTable(ad, token);

				if(m!=-1) {
					sout = "(AD," + (m+1) + ")";
					switch(m) {
						case 0: 
								token = sin.nextToken();
								lc = Integer.parseInt(token);
								sout += " (C," + lc + ")\n";
								out.write(sout);
								break;
						case 1:
								out.write(sout+"\n");
								for(int i=countl-unlit;i<countl;i++) {
									lit[i].address = lc;
									String val = lit[i].name.substring(2,lit[i].name.length() - 1);
									sout = "(DL,2) (C," + val + ")\n";
									out.write(sout);
									lc++;
								}
								//out.close();
								//in.close();
								writeLiteralTable();
								writeSymbolTable();
								break;
						case 2:
								lc++; //increment???
								for(int i=countl-unlit;i<countl;i++) {
									lit[i].address = lc;
									String val = lit[i].name.substring(2,lit[i].name.length() -1);
									sout = "(DL,2) (C," + val + ")\n";
									out.write(sout);
									lc++;
								}
								unlit=0;
								break;
					}
				}	
				else {
					m = searchTable(imp, token);
					if(m!=-1) {
						sout = "(IS," + (m+1) + ")";
						while(sin.hasMoreTokens()) {
							token = sin.nextToken();
							m = searchTable(rg, token);
							if(m!=-1) {
								sout += " (RG," + (m+1) + ")";
							}
							else {
								m = searchTable(cc, token);
								if(m!=-1) {
									sout += " (CC," + (m+1) + ")";
								}
								else {
									if(token.charAt(0)=='=') {
										m = searchLiteralTable(token);
										sout += " (L," + m + ")";
									}
									else {
										m = searchSymbolTable(token);
										sout += " (S," + m + ")";
									}
								}
							}
						}
						lc++;
						out.write(sout + "\n");
					}
					else {
						m = searchTable(dl, token);
						if(m!=-1) {
							sout += " (DL," + (m+1) + ")";
							token = sin.nextToken();
							sout += " (C," + token + ")\n";
							out.write(sout);

							if(m!=0)
								lc++;
							else
								lc += Integer.parseInt(token);
						}
						else {
							m = searchSymbolTable(token);
							sym[m].address = lc;
						}
					}
				}         // main if else for start and end.
			}                 // tokenizer while loop for all tokens in the one line reading
		}         // main while ends here .
		out.close();
		in.close();
	}

	public static void main(String args[]) throws Exception {
		new pass1().work();
	}
}