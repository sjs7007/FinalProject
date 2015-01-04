// 1. Image to RGB : http://www.tutorialspoint.com/java_dip/understand_image_pixels.htm
// 2. RGB to HSV : http://stackoverflow.com/a/2399174
// 3. write hsv image back as rgb image to file : http://www.lac.inpe.br/JIPCookbook/1300-create-rgb.jsp
// 4. Splite hsv image into smaller cells : trash/testExtractCells

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
        
        hsv hsvImage[][]=new hsv[width][height];       
    
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
              //  count++;
                Color c = new Color(image.getRGB(j, i));
                //System.out.println("S.No: "+count+" Red : " + c.getRed() +" Green: " + c.getGreen() + " Blue: " + c.getBlue());
                hsvImage[j][i]=new hsv(c.getRed(),c.getGreen(),c.getBlue());
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
                int RGB = Color.HSBtoRGB(hsvImage[j][i].h,hsvImage[j][i].s,hsvImage[j][i].v);
                Color c = new Color(RGB);
                int temp[]={c.getRed(),c.getGreen(),c.getBlue()};
                raster.setPixel(j,i,temp);
            }
        }
        ImageIO.write(image,"PNG",new File("hsv2file.png"));
    }
    
    public static boolean[] colorFeatureBuilder(hsv hsvImage[][],int N,int C_h,int C_s,int C_v)
    {
        boolean feature[]=new boolean[N*N*C_h*C_s*C_v]; //color feature vector
        boolean temp[][][][][]=new boolean[N][N][C_h][C_s][C_v]; // true if for cell i,j at least one pixel belongs to specific hsv range
        
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
                    }
                }              
                //now hsvCell = small seperate portion of whole hsv image
                for(int h=0;h<C_h;h++)
                {
                    boolean hP=false,sP=false,vP=false;
                    float low=(h/C_h),high=(h+1)/C_h;
                    // if any h value in hsvCell belongs to range [h/C_h,h+1/C_h] hP=true
                    hP = isInRange(hsvCell,N,low,high,0);
                    
                    for(int s=0;s<C_s;s++)
                    {
                        low=s/C_s;
                        high=(s+1)/C_s;
                        sP = isInRange(hsvCell, N, low, high, 1);
                        
                        for(int v=0;v<C_v;v++)
                        {
                            low=v/C_v;
                            high=(v+1)/C_v;
                            vP = isInRange(hsvCell, N, low, high, 2);
                            
                            temp[i][j][h][s][v]=(hP)&(sP)&(vP);
                            feature[count]=temp[i][j][h][s][v];
                            System.out.println(count+" "+feature[count]+" "+hsvImage[i][j].h);
                            count++;
                        }
                    }
                }
            }
        }
        return feature;
    }
    
    public static boolean isInRange(hsv hsvCell[][],int N,float low,float high,int type) //type=0,compare hue, 1 saturation
    {
        boolean ans=false;
        
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                float temp = hsvCell[i][j].getHSV()[type];
                if(temp>=low && temp<high)
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
        boolean colorFeatures[] = colorFeatureBuilder(img2RGB2HSV("dog.55.jpg"), N, C_h, C_s, C_v);
        
        /*for(int i=0;i<colorFeatures.length;i++)
        {
            System.out.println(i+" : "+colorFeatures[i]);
        }*/
    }
}