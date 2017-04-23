package robotpatrol;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Kattio extends PrintWriter {
	public Kattio(InputStream i) {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(i));
	}
	public Kattio(InputStream i, OutputStream o) {
		super(new BufferedOutputStream(o));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public boolean hasMoreTokens() {
		return peekToken() != null;
	}

	public int getInt() {
		return Integer.parseInt(nextToken());
	}

	public double getDouble() {
		return Double.parseDouble(nextToken());
	}

	public long getLong() {
		return Long.parseLong(nextToken());
	}

	public String getWord() {
		return nextToken();
	}



	private BufferedReader r;
	private String line;
	private StringTokenizer st;
	private String token;

	private String peekToken() {
		if (token == null)
			try {
				while (st == null || !st.hasMoreTokens()) {
					line = r.readLine();
					if (line == null) return null;
					st = new StringTokenizer(line);
				}
				token = st.nextToken();
			} catch (IOException e) { }
		return token;
	}

	private String nextToken() {
		String ans = peekToken();
		token = null;
		return ans;
	}
}

public class robotpatrol {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		int a = 1;
		long[][] matrix = new long[2][10000];
		while (a!=0){
			a = io.getInt();
			for (int i = 0; i<a;i++){
				matrix[0][i] = io.getLong();
				matrix[1][i] = io.getLong();
			}
			System.out.printf("%.1f\n", area(findvertexes(matrix,a)));	

		}

		io.close();
	}

	public static long[][] findvertexes(long[][] matrix,int asd){
		long[][] matrix2 = new long[2][asd];
		matrix2[0][0]=matrix[0][0];
		matrix2[1][0]=matrix[1][0];
		int counter = 1;
		int test = 0;
		long currx;
		long curry;
		long initx = matrix2[0][0];
		long inity = matrix2[1][0];
		boolean check = true;
		//find the leftmost point
		for (int i = 0; i<asd;i++){
			if (matrix[0][i]<matrix2[0][0]||((matrix[0][i]==matrix2[0][0])&&(matrix[1][i]>matrix2[1][0]))){
				initx=matrix2[0][0]=matrix[0][i];
				inity=matrix2[1][0]=matrix[1][i];		
				test = i;
			}
		}
		//find the other vertices
		int other = 0;
		while (check){
			other++;
			
			if(test>0){
				currx = matrix[0][test-1];
				curry = matrix[1][test-1];
			}else{
				currx = matrix[0][test+1];
				curry = matrix[1][test+1];
			}
			for (int j = 0; j<asd; j++){
					if (!(initx == matrix[0][j]&&inity==matrix[1][j])){
						if ((currx-initx)*(matrix[1][j]-inity)-(curry-inity)*(matrix[0][j]-initx)>=0){
							if(!(matrix2[0][0]==matrix[0][j]&&matrix2[1][0]==matrix[1][j])){
								currx = matrix2[0][counter]=matrix[0][j];
								curry = matrix2[1][counter]= matrix[1][j];
								test = j;
								check = true;
							}else{
								check = false;
								currx = matrix[0][j];
								curry = matrix[1][j];
							}
						}
					}
			}
			counter +=1;
			initx = currx;
			inity = curry;

		}
		long[][] matrix3 = new long[2][other];
		for (int q=0; q<other; q++){
			matrix3[0][q] = matrix2[0][q];
			matrix3[1][q] = matrix2[1][q];
		}
		
		return matrix3;
	}
	public static double area(long[][] matrix){
		int result = 0;
		int j = matrix[0].length-1;
		for (int i = 0; i<matrix[0].length;i++){
			result +=(matrix[0][j]+matrix[0][i])*(matrix[1][j]-matrix[1][i]);
			j = i;
		}
		return result/2;
	}
}
