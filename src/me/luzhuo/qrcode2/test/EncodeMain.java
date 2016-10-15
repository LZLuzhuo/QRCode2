/* Copyright 2016 Luzhuo. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.luzhuo.qrcode2.test;

import java.io.File;
import java.io.IOException;

import com.google.zxing.WriterException;

import me.luzhuo.qrcode2.encode.QRCode;
import me.luzhuo.qrcode2.encode.QRGraphical;
import me.luzhuo.qrcode2.encode.QRMatrix;
import me.luzhuo.qrcode2.encode.QRStyle;
import me.luzhuo.qrcode2.encode.QRStyle.QRStyles;
import me.luzhuo.qrcode2.encode.QRWriteToFile;

/**
 * 生成二维码示范代码
 * @author Luzhuo
 *
 */
public class EncodeMain {
	private static String contents = "ZXing 二维码内容1234!"; // 二维码内容 
	private static int width = 800; // 二维码图片宽度
	private static int height = 800; // 二维码图片高度
	private static File savefile = new File("C:\\我的电脑\\" + File.separator + System.currentTimeMillis() + ".png"); // 保存文件
	private static File basemapfile =  new File("C:\\我的电脑\\logo.jpg"); // 底图
	private static File logofile = new File("C:\\我的电脑\\logo.jpg"); // logo
	
	public static void main(String[] args) throws WriterException, IOException {
    	// 1. 创建QRCode源数据组
    	QRCode qrcode = new QRCode().createQRCode(contents);
    	
    	// 2. 样式类处理QRCode源数据组
    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.Default);
    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.OnlyBlack);
    	// QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucent);
    	QRStyle qrstyle = new QRStyle(qrcode, QRStyles.DefaultTranslucentBorder);
    	
    	// 3. 将QRCode生成图形数据阵
    	QRMatrix qrMatrix = new QRMatrix().createMatrix(qrcode, width, height, qrstyle);
    	
    	// 4. 将图形数据阵生成图形
    	//QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix);
    	QRGraphical qrGraphical = new QRGraphical().decode(qrMatrix, basemapfile);
    	
    	// 5. 将生成的图形写入文件
    	// new QRWriteToFile().writeToFile(qrGraphical, savefile);
    	new QRWriteToFile().writeToFile(qrGraphical, savefile, logofile);
	}

}
