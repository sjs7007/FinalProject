// http://docs.oracle.com/javase/1.5.0/docs/api/java/io/File.html#listFiles() : list all files in directory

package generalTestArea;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class testFileIO 
{
	public static void main(String args[]) throws IOException
	{
		// Code below lists name of all files and folders inside Final Project directory
		
		File test = new File("/home/shinchan/FinalProject");
		//System.out.println(test.list());
		File allFiles[]=test.listFiles();
		for(int i=0;i<allFiles.length;i++)
		{
			System.out.println(allFiles[i].getName());
		}
		
		//Code below lists names of all files inside training image directory and... stores whether cat or dog by checking name
		
		File test2 = new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/input/trainingImageData/");
		File allImages[]=test2.listFiles();
		//String imageNames[]=new String[allImages.length];
		for(int i=0;i<allImages.length;i++)
		{
			String name = allImages[i].getName();
			if(name.contains("cat"))
			{
				System.out.println("File "+name+ " is an image of a cat.");
			}
			else if(name.contains("dog"))
			{
				System.out.println("File "+name+ " is an image of a dog.");
			}
			else
			{
				System.out.println("error in name... Neither cat or dog! check list of input files/");
			}
		}
			
		//Code below is used to write a file similar to libsvm format
		// example code for CSV to libsvm : https://nayefreza.wordpress.com/2013/09/18/converting-csv-file-to-libsvm-compatible-data-file-using-java/
		
		int indexCount=1;
		StringBuffer ip = new StringBuffer();
		FileWriter op = new FileWriter("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/output/colorLIBSVM.train");
		
		for(int i=0;i<5;i++)
		{
			ip.append("<classificationLabel>");
			for(int j=0;j<5;j++)
			{
				ip.append(" "+indexCount+":1");
			}
			ip.append("\n");
			indexCount++;
		}
		
		op.write(ip.toString());
		op.flush();
	}
}
