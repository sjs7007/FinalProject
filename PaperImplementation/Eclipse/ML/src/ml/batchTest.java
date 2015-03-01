package ml;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class batchTest 
{
	public static void main(String args[]) throws IOException
	{
		int N=5,C_h=10,C_s=6,C_v=6;
		int count=1;
		
		for(int n=3;n<=10;n=n+3)
		{
			for(int h=3;h<=10;h=h+3)
			{
				for(int s=3;s<=10;s=s+3)
				{
					for(int v=3;v<=10;v=v+3)
					{
						predictM(count,n,h,s,v);
						count++;
					}
				}
			}
			
		}
		
	


	}
	
	public static void predictM(int count,int N,int C_h,int C_s,int C_v) throws IOException
	{
		Date start,end;
		
		System.out.println("Test Number : "+count);
		System.out.println("N="+N+",C_h="+C_h+",C_s="+C_s+",C_v="+C_v);
		
	    String ip = new String("/home/shinchan/Downloads/zipFiles/batchTest");

		start =new Date();
		//System.out.print("Start time for feature extraction : "+start.toString()+"\n");	
	    
	    ML.batchColorFeatureBuilder(ip, N, C_h, C_s, C_v,new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/results/batchTest.test"),new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/results/batchTest.list")); 
	    
	    end = new Date();
		//System.out.print("End time for feature extraction : "+end.toString()+"\n");
		


		long executionTimeMS = end.getTime()-start.getTime(); //in milliseconds
		String executionTime = Float.toString((float)executionTimeMS/(60*1000))+" minutes";
		
		System.out.print("Time taken for feature extraction : "+executionTime+"\n");

	    
	    
	    File testFeatures = new File("results/batchTest.test");
	    File modelFile = new File("results/SVM6.model");
	    
	
		//System.out.print("\nTest file used  : "+testFeatures.getName()+"\n");
		//System.out.print("SVM Model used : "+modelFile.toString()+"\n");

		String resultsName = "results/batchTest.result";				


		start =new Date();

		//System.out.print("Start time for prediction : "+start.toString()+"\n");	


	
		String argv[]={testFeatures.toString(),modelFile.toString(),resultsName};
		svm_predict.main(argv);

		end = new Date();
		//System.out.print("End time for prediction : "+end.toString()+"\n");


		executionTimeMS = end.getTime()-start.getTime(); //in milliseconds
		executionTime = Float.toString((float)executionTimeMS/(60*1000))+" minutes";

		System.out.print("Time taken for prediction : "+executionTime+"\n----x----\n");
	}
}
