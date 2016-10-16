# QRCode2
---
[中文](??) | English

This library is used to generate a variety of common QRCode.

## Guide package
??


## Use
	public class EncodeMain {
		private static String contents = "作者:Luzhuo   博客:http://blog.csdn.net/Rozol   Github:https://github.com/lzluzhuo"; // qrcode content 
		private static int width = 340; // qrcode width
		private static int height = 340; // qrcode height
		private static File savefile = new File("C:\\我的电脑\\" + File.separator + System.currentTimeMillis() + ".png"); // file save path
		private static File basemapfile =  new File("C:\\我的电脑\\logo2.jpg"); // base map
		private static File logofile = new File("C:\\我的电脑\\logo.jpg"); // logo
		
		public static void main(String[] args) throws WriterException, IOException {
			// Calculate the size of the base map, according to the size of the base map to calculate the size of two-dimensional code, there is no such demand is not necessary
			int[] size = QRUtils.getImageFilePxSize(basemapfile);
			if(size == null) return;
			width = size[0]; height = size[1];
			
	    	// 1. Create a QRCode source data set.
	    	QRCode qrcode = new QRCode().createQRCode(contents);
	    	
	    	// 2. The style class handles the QRCode source data set.
	    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.Default);
	    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.OnlyBlack);
	    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucent);
	    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucentBorder);
	    	QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultPoint);
	    	
	    	// 3. QRCode generates a graphic data array.
	    	QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    	
	    	// 4. The pattern data array is patterned.
	    	// QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix);
	    	QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    	
	    	// 5. Writes the generated graph to a file.
	    	new QRWriteToFile().writeToFile(qrGraphical, savefile);
	    	// new QRWriteToFile().writeToFile(qrGraphical, savefile, logofile);
		}
	
	}


## Style
 - 1:QRStyles.Default  
   ![](/screenshot/Default.png)  
	
		Reference code:
	    QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.Default);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);

 - 2:QRStyles.Default + logo  
   ![](/screenshot/logo.png)  

		Reference code:
	    QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.Default);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile, logofile);

 - 3:QRStyles.OnlyBlack  
   ![](/screenshot/OnlyBlack.png)  

		Reference code:
		QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.OnlyBlack);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);

 - 4:QRStyles.DefaultTranslucent  
   ![](/screenshot/DefaultTranslucent.png)  

		Reference code:
		QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucent);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);

 - 5:QRStyles.DefaultTranslucentBorder  
   ![](/screenshot/DefaultTranslucentBorder.png)  
   ![](/screenshot/DefaultTranslucentBorder2.png)  

		Reference code:
		QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucentBorder);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);

 - 6:QRStyles.DefaultPoint  
   ![](/screenshot/DefaultPoint.png)  
   ![](/screenshot/DefaultPoint2.png)  

		Reference code:
		QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultPoint);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);


## Other
You can use the generated two-dimensional code to use some filter algorithms, or use PS to produce beautiful two-dimensional code (the library is mainly used to generate rather than landscaping):  
![](/screenshot/caise.jpg)  
![](/screenshot/github.png)  


## About author

Luzhuo  
Email: `LZ.Luzhuo@gmail.com`  
Blog: `??`  


## License

	Copyright 2016 Luzhuo. All rights reserved.
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
