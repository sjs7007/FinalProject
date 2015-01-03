// 1. Image to RGB : http://www.tutorialspoint.com/java_dip/understand_image_pixels.htm
// 2. RGB to HSV : http://stackoverflow.com/a/2399174

import java.awt.*; // #1,#2
import java.awt.image.BufferedImage; //#1
import java.io.*; // #1
import javax.imageio.ImageIO; //#1
//import javax.swing.JFrame;  //#1

class  hsv //ADT to store HSV values.
{
    float h,s,v;
    
    hsv(int r,int g,int b) //convert rgb to hsv and store
    {
        float[] hsv = new float[3];
        Color.RGBtoHSB(r,g,b,hsv);
        h=hsv[0];
        s=hsv[1];
        v=hsv[2];
    }
    
    void display()
    {
        System.out.println("HSV : "+h+","+s+","+v);
    }
}

class ML
{
		
   /* public static void img2RGB2HSV(String ip) throws IOException
    {
        BufferedImage image;
        int width, height,count=0; 
            
        File input = new File(ip);
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                count++;
                Color c = new Color(image.getRGB(j, i));
                System.out.println("S.No: "+count+" Red: " + c.getRed() +" Green: " + c.getGreen() + " Blue: " + c.getBlue());
            }
        }
    }
    */
    
    public static hsv[][] img2RGB2HSV(String ip) throws IOException
    {
        BufferedImage image;
        int width, height,count=0; 
        
        hsv hsvImage[][]=new hsv[250][250]; //read only 250*250 images
            
        File input = new File(ip);
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                count++;
                Color c = new Color(image.getRGB(j, i));
                System.out.println("S.No: "+count+" Red: " + c.getRed() +" Green: " + c.getGreen() + " Blue: " + c.getBlue());
            }
        }
        
        return hsvImage;
    }
	
    public static void main(String args[]) throws IOException
    {
        /*img2RGB2HSV("blackandwhite.jpg");*/
        hsv test = new hsv(10,10,20);
        test.display();
    }
}