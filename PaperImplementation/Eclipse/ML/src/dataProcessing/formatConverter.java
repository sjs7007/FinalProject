package dataProcessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class formatConverter 
{
	//fileNames = txt file with file names
	//match each line of filename with prediction result and store in output 
	public static void toKaggleCSV(File fileNames,File predictionResult,File output) throws IOException
	{
		/*  FileInputStream inputStream = new FileInputStream("/home/shinchan/Downloads/tezt.tx");
		  String everything = IOUtils.toString(inputStream);
		  System.out.println(everything);	  */
		
		BufferedReader ip1 = new BufferedReader(new FileReader(fileNames));
		BufferedReader ip2 = new BufferedReader(new FileReader(predictionResult));
		FileWriter writer = new FileWriter(output);
		String lineA,lineB;
		writer.append("id"+","+"label"+"\n");
		while ((lineA= ip1.readLine()) != null) 
		{
			lineB = ip2.readLine();
			
			writer.append(lineA);
			writer.append(",");
			writer.append(lineB+"\n");
			writer.flush();
		}
		ip1.close();
		ip2.close();
		writer.close();
	}
	
	public static void main(String args[]) throws IOException
	{
		File fileNames=new File("/home/shinchan/results/kaggleTestData.list");
		File predictionResult=new File("/home/shinchan/results/SVMKaggleTestData2.result");
		File kaggleCSV = new File("/home/shinchan/results/kaggleTestData2.csv");
		toKaggleCSV(fileNames,predictionResult,kaggleCSV);
	}
}
