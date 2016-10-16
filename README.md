# 二维码
---
中文 | [English](https://github.com/LZLuzhuo/QRCode2/blob/master/README_EN.md)

该库用于生成各种常见的二维码

## 导包
拷贝 `qrcode2.1.0.jar` 和 `zxing3.2.1.jar` 到项目中, Build Pah 即可使用.


## 使用
	public class EncodeMain {
		private static String contents = "作者:Luzhuo   博客:http://blog.csdn.net/Rozol   Github:https://github.com/lzluzhuo"; // 二维码内容 
		private static int width = 340; // 二维码图片宽度
		private static int height = 340; // 二维码图片高度
		private static File savefile = new File("C:\\我的电脑\\" + File.separator + System.currentTimeMillis() + ".png"); // 保存文件
		private static File basemapfile =  new File("C:\\我的电脑\\logo2.jpg"); // 底图
		private static File logofile = new File("C:\\我的电脑\\logo.jpg"); // logo
		
		public static void main(String[] args) throws WriterException, IOException {
			// 计算底图的尺寸, 可以根据底图的尺寸计算二维码的大小, 没有该需求则不需要
			int[] size = QRUtils.getImageFilePxSize(basemapfile);
			if(size == null) return;
			width = size[0]; height = size[1];
			
	    	// 1. 创建QRCode源数据组
	    	QRCode qrcode = new QRCode().createQRCode(contents);
	    	
	    	// 2. 样式类处理QRCode源数据组
	    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.Default);
	    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.OnlyBlack);
	    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucent);
	    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucentBorder);
	    	QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultPoint);
	    	
	    	// 3. 将QRCode生成图形数据阵
	    	QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    	
	    	// 4. 将图形数据阵生成图形
	    	// QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix);
	    	QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    	
	    	// 5. 将生成的图形写入文件
	    	new QRWriteToFile().writeToFile(qrGraphical, savefile);
	    	// new QRWriteToFile().writeToFile(qrGraphical, savefile, logofile);
		}
	
	}


## 样式
 - 样式一:QRStyles.Default  
   ![](/screenshot/Default.png)  
	
		参考代码:
	    QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.Default);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);

 - 样式二:QRStyles.Default + logo  
   ![](/screenshot/logo.png)  

		参考代码:
	    QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.Default);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile, logofile);

 - 样式三:QRStyles.OnlyBlack  
   ![](/screenshot/OnlyBlack.png)  

		参考代码:
		QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.OnlyBlack);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);

 - 样式四:QRStyles.DefaultTranslucent  
   ![](/screenshot/DefaultTranslucent.png)  

		参考代码:
		QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucent);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);

 - 样式五:QRStyles.DefaultTranslucentBorder  
   ![](/screenshot/DefaultTranslucentBorder.png)  
   ![](/screenshot/DefaultTranslucentBorder2.png)  

		参考代码:
		QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucentBorder);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);

 - 样式六:QRStyles.DefaultPoint  
   ![](/screenshot/DefaultPoint.png)  
   ![](/screenshot/DefaultPoint2.png)  

		参考代码:
		QRCode qrcode = new QRCode().createQRCode(contents);
	    QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultPoint);
	    QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
	    QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
	    new QRWriteToFile().writeToFile(qrGraphical, savefile);


## 其他
可以将生成的二维码使用一些滤镜算法, 或者使用PS制作出精美的二维码(本库主要用于生成而非美化):  
![](/screenshot/caise.jpg)  
![](/screenshot/github.png)  


## 关于作者

Luzhuo  
Email: `LZ.Luzhuo@gmail.com`  
Blog: `http://blog.csdn.net/Rozol/article/details/52831504`  


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
