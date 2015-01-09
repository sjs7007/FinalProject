/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml;

/**
 *
 * @author shinchan
 */

// 1. Image to RGB : http://www.tutorialspoint.com/java_dip/understand_image_pixels.htm
// 2. RGB to HSV : http://stackoverflow.com/a/2399174
// 3. write hsv image back as rgb image to file : http://www.lac.inpe.br/JIPCookbook/1300-create-rgb.jsp
// 4. Splite hsv image into smaller cells : trash/testExtractCells

import cern.colt.bitvector.BitMatrix;
import cern.colt.bitvector.BitVector;
import java.awt.*; // #1,#2
import java.awt.image.BufferedImage; //#1
import java.io.*; // #1

import javax.imageio.ImageIO; //#1

import modifiedLibraries.MyBitMatrix;

import java.awt.image.WritableRaster; //#3


class  hsv //ADT to store HSV values.
{
    float h,s,v;
    float[] hsv = new float[3];
    
    hsv(int r,int g,int b) //convert rgb to hsv and store
    {
        
        Color.RGBtoHSB(r,g,b,hsv);
        h=hsv[0];
        s=hsv[1];
        v=hsv[2];
    }
    
    void display()
    {
        System.out.println("HSV : "+h+","+s+","+v);
    }
    
    public float[] getHSV()
    {
        return hsv;
    }
}

class fileData
{
	int type; //type=1 for dog and type=-1 for cats
	BitVector colorFeatureVector;
	
	fileData(int type,BitVector colorFeatureVector)
	{
		this.type=type;
		this.colorFeatureVector=colorFeatureVector;
	}
}

class ML
{ 
	/*
	 * Method to convert image file input to HSV matrix.
	 */
    public static hsv[][] img2RGB2HSV(String ip) throws IOException
    {
        BufferedImage image;
        int width, height; 
        
        File input = new File(ip);
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        
        hsv hsvImage[][]=new hsv[height][width];       
    
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                Color c = new Color(image.getRGB(j, i));
                hsvImage[i][j]=new hsv(c.getRed(),c.getGreen(),c.getBlue());
            }
        }
        
        HSV2File(hsvImage,width,height);
        
        return hsvImage;
    }
    
    //overloaded method
    
    public static hsv[][] img2RGB2HSV(File input) throws IOException
    {
        BufferedImage image;
        int width, height; 
        
       // File input = new File(ip);
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        
        hsv hsvImage[][]=new hsv[height][width];       
    
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                Color c = new Color(image.getRGB(j, i));
                hsvImage[i][j]=new hsv(c.getRed(),c.getGreen(),c.getBlue());
            }
        }
        
        HSV2File(hsvImage,width,height);
        
        return hsvImage;
    }
    
    
    /*
     * Method to convert an hsv matrix back to normal image file.
     */
	
    public static void HSV2File(hsv hsvImage[][], int width, int height) throws IOException //store img output as hsv2file.png
    {
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                int RGB = Color.HSBtoRGB(hsvImage[i][j].h,hsvImage[i][j].s,hsvImage[i][j].v);
                Color c = new Color(RGB);
                int temp[]={c.getRed(),c.getGreen(),c.getBlue()};
                raster.setPixel(j,i,temp);
            }
        }
        ImageIO.write(image,"PNG",new File("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/output/hsv2file.png"));
    }
    
    /*
     * Method that takes in as input and returns feature vector for colors.
     */
    
    public static BitVector colorFeatureBuilder(hsv hsvImage[][],int N,int C_h,int C_s,int C_v)
    {
        BitVector feature=new BitVector(N*N*C_h*C_s*C_v); //color feature vector
        
        float Ch=C_h,Cs=C_s,Cv=C_v;
        
        int cellSize=250/N, count=0;
        
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                hsv hsvCell[][]=new hsv[cellSize][cellSize];
                for(int p=i*cellSize;p<(i+1)*cellSize;p++)
                {
                    for(int q=j*cellSize;q<(j+1)*cellSize;q++)
                    {                 
                        hsvCell[p-(i*cellSize)][q-(j*cellSize)]=hsvImage[p][q];                  
                    }            
                }                
                //now hsvCell = small seperate portion of whole hsv image
                int x=256;
                String temp4="";
                
                for(int h=0;h<C_h;h++)
                {
                    MyBitMatrix hP=new MyBitMatrix(cellSize,cellSize), sP=new MyBitMatrix(cellSize, cellSize) ,vP=new MyBitMatrix(cellSize,cellSize);
                    float low=(h/Ch),high=(h+1)/Ch;
                    // if any h value in hsvCell belongs to range [h/C_h,h+1/C_h] hP=true
                    hP = isInRange(hsvCell,cellSize,low,high,0);
                  
                    for(int s=0;s<C_s;s++)
                    {
                        low=s/Cs;
                        high=(s+1)/Cs;
                        sP = isInRange(hsvCell, cellSize, low, high, 1);
                       
                        for(int v=0;v<C_v;v++)
                        {
                            low=v/Cv;
                            high=(v+1)/Cv;
                            vP = isInRange(hsvCell, cellSize, low, high, 2);
                      
                            BitMatrix temp = vP.copy();
                            temp.and(sP);
                            temp.and(hP);
                            MyBitMatrix FinalMat = MyBitMatrix.toMyBitMatrix(temp);
                            
                            /*if(count==x) debugging code removed..
                            {
                                temp4 = "hP from Java \n";
                                System.out.print(temp4 + hP.toStringEasyCompare());
                                
                                temp4 = "sP from Java \n";
                                System.out.print(temp4 + sP.toStringEasyCompare());
                                
                                temp4 = "vP from Java \n";
                                System.out.print(temp4 + vP.toStringEasyCompare());
                                
                                temp4 = "Final from Java \n";
                                System.out.print(temp4 + FinalMat.toStringEasyCompare());
                            }
                            */
                            feature.put(count, isAnyTrue(FinalMat));
                            count++;
                        }
                    }
                }
            }
        }
        return feature;
    }
    
    
    /*
     * Input : matrix of hsv image, comparison data type(0,1,2) i.e. h,s or v, low=lower limit, high=higher limit
     * 
     * Each element of the input matrix is checked to see whether if it belongs in the range [low,high)
     * If yes, store true, else store false.
     * 
     */
    public static MyBitMatrix isInRange(hsv hsvCell[][],int cellSize,float low,float high,int type) //type=0,compare hue, 1 saturation
    {
        MyBitMatrix ans=new MyBitMatrix(cellSize,cellSize);
        
        for(int i=0;i<cellSize;i++)
        {
            for(int j=0;j<cellSize;j++)
            {
                float temp = hsvCell[i][j].getHSV()[type];         
                if(temp>=low && temp<high)
                {
                    ans.put(i, j, true);
                }
                else
                {
                    ans.put(i, j, false);
                }
            }
        }
        return ans;
    }
    
    public static boolean isAnyTrue(MyBitMatrix temp) //returns true if any value is true in the matrix
    {
        boolean ans=false;
        for(int i=0;i<temp.rows();i++)
        {
            for(int j=0;j<temp.columns();j++)
            {
                if(temp.get(i, j))
                {
                    ans=true;
                    break;
                }
            }
        }
        return ans;
    }
    
    /*
     * batch builds feature vector of all images present in input directory specified by file ip
     * 
     * Each feature vector is stored along with type=cat/dog together as an element in vector of ADT : fileData
     */
    
   
    
    public static fileData[] batchColorFeatureBuilder(String ip,int N,int C_h,int C_s,int C_v) throws IOException
    {
    	//get array of all files 
    	
    	File ipFiles = new File(ip);
    	
    	File allImages[]=ipFiles.listFiles();
    	int nImages=allImages.length;
    	fileData allImageData[]=new fileData[nImages];

    	
    	for(int i=0;i<nImages;i++)
    	{
    		//System.out.println(allImages[i].getName());
    		if(allImages[i].getName().contains("cat")) //1=dog, -1=cat
    		{
    			allImageData[i]=new fileData(-1,colorFeatureBuilder(img2RGB2HSV(new File(allImages[i].toURI())), N, C_h, C_s, C_v));
    		}
    		else 
    		{
    			allImageData[i]=new fileData(1,colorFeatureBuilder(img2RGB2HSV(new File(allImages[i].toURI())), N, C_h, C_s, C_v));
    		}
    	}
    	return allImageData;
    }
    
   //public static File[] 
    
    public static void main(String args[]) throws IOException
    {
        // img2RGB2HSV("dog.55.jpg");
        /*hsv test = new hsv(10,10,20);
        test.display();*/
       int N=5,C_h=10,C_s=6,C_v=6;
       BitVector colorFeatures = colorFeatureBuilder(img2RGB2HSV("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/input/dog.55.jpg"), N, C_h, C_s, C_v);
          
       String temp = "Single input Color features from Java.\n";
       int count=0;
       for(int i=0;i<colorFeatures.size();i++)
       {
           if(colorFeatures.get(i))
           {
               temp+=Integer.toString(count)+"\n";
           }
           count++;
       }
       System.out.print(temp);
       
       
       
       String ip = new String("/home/shinchan/FinalProject/PaperImplementation/Eclipse/ML/input/trainingImageData/");
   
       fileData allImageData[]=batchColorFeatureBuilder(ip, N, C_h, C_s, C_v);
       
       for(int i=0;i<allImageData.length;i++)
       {
    	   System.out.println(allImageData[i].colorFeatureVector.toString()+"\n");
    	 /*  int count2=0;
    	   temp="";
    	   for(int x=0;x<allImageData[i].colorFeatureVector.size();x++)
    	   {
    		   if(allImageData[i].colorFeatureVector.get(x))
    		   {
    			   temp+=Integer.toString(count2)+"\n";
    		   }
    		   count2++;
    	   }
    	   System.out.print(temp);*/
       }
    }
}