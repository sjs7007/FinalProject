import java.awt.*;

class testRGBHSV
{
	public static void main(String args[])
	{
		int r = 10;
		int g = 10; 
		int b = 20;
		float[] hsv = new float[3];
		Color.RGBtoHSB(r,g,b,hsv);
		System.out.println(hsv[0]+" "+hsv[1]+" "+hsv[2]);
		int temp = Color.HSBtoRGB(hsv[0],hsv[1],hsv[2]);
		Color c = new Color(temp);
		System.out.println(c.getRed()+" "+c.getBlue()+" "+c.getGreen());
	}
}
