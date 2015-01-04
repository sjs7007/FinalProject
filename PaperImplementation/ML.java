// 1. Image to RGB : http://www.tutorialspoint.com/java_dip/understand_image_pixels.htm
// 2. RGB to HSV : http://stackoverflow.com/a/2399174
// 3. write hsv image back as rgb image to file : http://www.lac.inpe.br/JIPCookbook/1300-create-rgb.jsp

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
    
    public static void main(String args[]) throws IOException
    {
        img2RGB2HSV("dog.55.jpg");
        /*hsv test = new hsv(10,10,20);
        test.display();*/
    }
}