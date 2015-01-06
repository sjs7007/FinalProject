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
import extendingLibrary.MyBitMatrix;
import java.awt.*; // #1,#2
import java.awt.image.BufferedImage; //#1
import java.io.*; // #1
import javax.imageio.ImageIO; //#1
//import javax.swing.JFrame;  //#1
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

class ML
{ 
    public static hsv[][] img2RGB2HSV(String ip) throws IOException
    {
        BufferedImage image;
        int width, height,count=0; 
        
        File input = new File(ip);
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        
        hsv hsvImage[][]=new hsv[height][width];       
    
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
              //  count++;
                Color c = new Color(image.getRGB(j, i));
                //System.out.println("S.No: "+count+" Red : " + c.getRed() +" Green: " + c.getGreen() + " Blue: " + c.getBlue());
                hsvImage[i][j]=new hsv(c.getRed(),c.getGreen(),c.getBlue());
                //System.out.println("S.No: "+count+" Hue : " + hsvImage[j][i].h +" Saturation : " + hsvImage[j][i].s + " Value : " + hsvImage[j][i].v);
            }
        }
        
        HSV2File(hsvImage,width,height);
        
        return hsvImage;
    }
	
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
        ImageIO.write(image,"PNG",new File("hsv2file.png"));
    }
    
    public static BitVector colorFeatureBuilder(hsv hsvImage[][],int N,int C_h,int C_s,int C_v)
    {
        BitVector feature=new BitVector(N*N*C_h*C_s*C_v); //color feature vector
        //MyMyMyBitMatrix temp=new MyMyMyBitMatrix(N,N,C_h,C_s,C_v); // true if for cell i,j at least one pixel belongs to specific hsv range
        
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
                        //System.out.println(i+" "+j+" "+p+" "+q);
                        hsvCell[p-(i*cellSize)][q-(j*cellSize)]=hsvImage[p][q];
                       // hsvImage[p][q].display();
                    }
                  //  System.out.println();
                }              
               // System.out.println();
                //now hsvCell = small seperate portion of whole hsv image
                int x=0;
                String temp4="";
                
                for(int h=0;h<C_h;h++)
                {
                    MyBitMatrix hP=new MyBitMatrix(cellSize,cellSize), sP=new MyBitMatrix(cellSize, cellSize) ,vP=new MyBitMatrix(cellSize,cellSize);
                    float low=(h/Ch),high=(h+1)/Ch;
                    // if any h value in hsvCell belongs to range [h/C_h,h+1/C_h] hP=true
                    hP = isInRange(hsvCell,cellSize,low,high,0);
                    /*if(count==x)
                    {            
                        String temp4="hp from Java \n";
                        System.out.print(temp4+ hP.toStringEasyCompare());
                    }*/
                    for(int s=0;s<C_s;s++)
                    {
                        low=s/Cs;
                        high=(s+1)/Cs;
                        sP = isInRange(hsvCell, cellSize, low, high, 1);
                        /*if(count==x)
                        {            
                            String temp4="sp from Java \n";
                            System.out.print(temp4+ sP.toStringEasyCompare());
                        }*/

                        for(int v=0;v<C_v;v++)
                        {
                            low=v/Cv;
                            high=(v+1)/Cv;
                            vP = isInRange(hsvCell, cellSize, low, high, 2);
                            
                            //sP = isInRange(hsvCell, cellSize, low, high, 1);
                           /* if (count == x) 
                            {
                                String temp4 = "vp from Java \n";
                                System.out.print(temp4 + vP.toStringEasyCompare());
                            }*/
                            
                            //if(count==0)
                              //  System.out.println("vp \n"+vP.toString());

                           /* temp[i][j][h][s][v]=(hP)&(sP)&(vP);
                            feature[count]=temp[i][j][h][s][v];
                            System.out.println(count+" "+feature[count]+" "+hsvImage[i][j].h);
                            //System.out.println(i+" "+j+" "+h+" "+s+" "+v+" "+hsvImage[i][j].h); //
                            */
                            
                            //MyMyBitMatrix temp = new MyMyBitMatrix(cellSize,cellSize);
                           
                            
                            if(count==x)
                            {
                                temp4 = "hP from Java \n";
                                System.out.print(temp4 + hP.toStringEasyCompare());
                                
                                temp4 = "sP from Java \n";
                                System.out.print(temp4 + sP.toStringEasyCompare());
                                
                                temp4 = "vP from Java \n";
                                System.out.print(temp4 + vP.toStringEasyCompare());
                                
                            }
                            
                            sP.and(vP);
                            hP.and(sP);
                            
                            if(count==x)
                            {
                                temp4 = "Final from Java \n";
                                System.out.print(temp4 + hP.toStringEasyCompare());
                            }
                            
                            feature.put(count, isAnyTrue(hP));
                           // System.out.println(count+" "+feature.get(count)+" "+hsvImage[i][j].h);
                            count++;
                        }
                    }
                }
            }
        }
        return feature;
    }
    
    public static MyBitMatrix isInRange(hsv hsvCell[][],int cellSize,float low,float high,int type) //type=0,compare hue, 1 saturation
    {
        MyBitMatrix ans=new MyBitMatrix(cellSize,cellSize);
        
        for(int i=0;i<cellSize;i++)
        {
            for(int j=0;j<cellSize;j++)
            {
                float temp = hsvCell[i][j].getHSV()[type];
               // System.out.println(temp+" "+low+" "+high);
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
    
    public static boolean isAnyTrue(MyBitMatrix temp)
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
    
    public static void main(String args[]) throws IOException
    {
        // img2RGB2HSV("dog.55.jpg");
        /*hsv test = new hsv(10,10,20);
        test.display();*/
       int N=5,C_h=10,C_s=6,C_v=6;
       colorFeatureBuilder(img2RGB2HSV("/home/shinchan/FinalProject/PaperImplementation/netBeans/ML/src/ml/dog.55.jpg"), N, C_h, C_s, C_v);
        
        /*for(int i=0;i<colorFeatures.length;i++)
        {
            System.out.println(i+" : "+colorFeatures[i]);
        }*/
        
        
    }
}