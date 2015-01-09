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

	Fixed : RGB order was off. No more problem now. 

Step 2 : 

+ Cell size = 250/N.
+ Split each h,s,v into c_h, c_s and c_v bands of equal width.
+ Create feature vector of size(N*N*Ch*Cs*Cv) : data type bitvector.
+ extract cells from matrix : [code](trash/testExtractCells.java)
+ For each cell, say hsvCell,  store in FinalMat of data type MyBitMatrix, the boolean result for that particular cell.
+ Final feature vector : use data type BitVector. 

-----

Comparision of output from python and java 

[python code](pythonEasyCompare.py)    
[java code](ML.java)    
 
[diff for x = 256 on diff checker](https://www.diffchecker.com/ec85wi5z)

[python output](color_from_pythonx256.txt)
[java output](color_from_Javax256.txt)

###Color Features all done and working. Tested on dog 55 image.

Tested again on cat55 : 

[diff for x = 256 on diff checker on cat55](https://www.diffchecker.com/psca0yxo)

------

Step 3 : Figure out how to work with SVMs in Java.

+ Use LIBSVM from [here](http://www.csie.ntu.edu.tw/~cjlin/cgi-bin/libsvm.cgi?+http://www.csie.ntu.edu.tw/~cjlin/libsvm+zip).
	+ [LIBSVM's official page](http://www.csie.ntu.edu.tw/~cjlin/libsvm/)
	+ [Example video of using LIBSVM in Java](https://www.youtube.com/watch?v=gePWtNAQcK8)
	+ [To run train and predict in Eclipse you'll need to know how to load command line arguments in eclipse.](http://www.cs.colostate.edu/helpdocs/eclipseCommLineArgs.html)

	Steps :
	+ Run train.java with following 2 command line arguments : input training file, location and file name to store generated model.
	+ Run predict.java with following 3 command line arguments : input test file, model generated in earlier step, location and file name to store prediction output.

+ Now, next step is to make it work with your data.
	+ 1st you need to convert in format needed by libsvm. 
	+ [If in CSV..](https://nayefreza.wordpress.com/2013/09/18/converting-csv-file-to-libsvm-compatible-data-file-using-java/)
	+ Either convert step 2 output to CSV and then use above code or implement code to directly store in needed format of libsvm...
		CSV files can be easily viewed using excel and are displayed on github and this option will mean less code writing since above script is available....

	+ Data format of LIBSVM [here](http://stats.stackexchange.com/questions/61328/libsvm-data-format):

		```
		The data is stored in a sparse array/matrix form. Essentially, it means only the non-zero data are stored, and any missing data is taken as holding value zero. 

		In short, +1 1:0.7 2:1 3:1 translates to:

		Assign to class +1, the point (0.7,1,1).
		```

		Also each line should end with '\n'.

		So, for let's say label for cat : -1, dog: +1

		and there are 9000 features in total, so say if only feature 1,9,900 is true it will be stored as 

		1 1:1 9:1 900:1 for image of dog
	   -1 1:1 9:1 900:1 for image of cat

	   both will also have a '\n' at the end of the line.

	+ Before this, first modify main program "ML.java" to take multiple files as input and automatically detect using name whether cat or not.

	[Code for example file IO](PaperImplementation/Eclipse/ML/src/generalTestArea/testFileIO.java)
	Done!

	+ Now produce output in format needed for SVM. 

		+ Take as input allImageData and convert into libsvm format : toLibsvmFormat() method
			Done!

+ Next step : give actual data to train method!


