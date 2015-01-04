Doing it in Java to ensure easy portability to android later.

1. Implement SVM for color features first. 

Step 1 : 

+ Load image into Java and get RGB values.
	+ [Image2RGB](http://www.tutorialspoint.com/java_dip/understand_image_pixels.htm)
+ Convert RGB to HSV.
	+ [RGB2HSV](http://stackoverflow.com/a/2399174)
+ Check if conversion is proper by convertion hsv to rgb back to image file.
	+ [How to write RGB image as file](http://www.lac.inpe.br/JIPCookbook/1300-create-rgb.jsp)
	+ [How to convert int RGB to array : use color constructor](http://docs.oracle.com/javase/7/docs/api/java/awt/Color.html)
	+ [HSV2RGB](http://docs.oracle.com/javase/7/docs/api/java/awt/Color.html#HSBtoRGB(float,%20float,%20float))

	Result : succesful, but color seems to change for seem reason. WHY?

	![HSV2RGB](sample/dog.55.jpg)
	![HSV2RGB](sample/dog.55hsv2file.png)