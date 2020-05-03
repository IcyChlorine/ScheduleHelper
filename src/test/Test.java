package test;
import java.io.*;

public class Test {
	public static void main(String[] args) throws IOException {
		FileWriter fout=new FileWriter("somefile.txt");
		fout.write("some textttt");
		fout.close();
	}
}
