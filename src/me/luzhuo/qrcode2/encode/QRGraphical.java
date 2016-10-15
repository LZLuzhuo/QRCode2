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
package me.luzhuo.qrcode2.encode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * QR图形生成类, 用于将QR矩阵解析成QR图形
 * @author Luzhuo
 *
 */
public class QRGraphical {
	private BufferedImage qrimage;
	
	/**
	 * 普通方式生成的二维码
	 * @param qrMatrix
	 */
	public QRGraphical decode(QRMatrix qrMatrix){
        int width = qrMatrix.getMatrixWidth();
        int height = qrMatrix.getMatrixHeight();
        
        qrimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	if(qrMatrix.get(x, y) == QRColor.Black) qrimage.setRGB(x, y, new Color(0, 0, 0, 0).getRGB());
            	else if(qrMatrix.get(x, y) == QRColor.White) qrimage.setRGB(x, y, new Color(255, 255, 255, 255).getRGB());
            	else if(qrMatrix.get(x, y) == QRColor.Empty) qrimage.setRGB(x, y, new Color(255, 255, 255, 255).getRGB());
            	else if(qrMatrix.get(x, y) == QRColor.Black80) qrimage.setRGB(x, y, new Color(0, 0, 0, 0).getRGB());
            	else if(qrMatrix.get(x, y) == QRColor.White80) qrimage.setRGB(x, y, new Color(255, 255, 255, 255).getRGB());
            	else if(qrMatrix.get(x, y) == QRColor.Black30) qrimage.setRGB(x, y, new Color(0, 0, 0, 0).getRGB());
            	else if(qrMatrix.get(x, y) == QRColor.White30) qrimage.setRGB(x, y, new Color(255, 255, 255, 255).getRGB());
            	else qrimage.setRGB(x, y, new Color(255, 255, 255, 255).getRGB());
            }
        }
        qrimage.flush();
        
        return this;
	}
	
	/**
	 * 覆盖于图片之上生成的二维码
	 * @param qrMatrix
	 * @param image
	 * @throws IOException 
	 */
	public QRGraphical decode(QRMatrix qrMatrix, File image) throws IOException{
		int width = qrMatrix.getMatrixWidth();
		int height = qrMatrix.getMatrixHeight();
		
		qrimage = ImageIO.read(image);
		Graphics2D g2 = qrimage.createGraphics();
		  
		for (int y = 0; y < height; y++) {
		    for (int x = 0; x < width; x++) {
		    	if(qrMatrix.get(x, y) == QRColor.Black){ g2.setColor(new Color(0, 0, 0, 255)); }
		    	else if(qrMatrix.get(x, y) == QRColor.White){ g2.setColor(new Color(255, 255, 255, 255)); }
		    	else if(qrMatrix.get(x, y) == QRColor.Empty){ continue; }
		    	else if(qrMatrix.get(x, y) == QRColor.Black80) g2.setColor(new Color(0,0,0,80));
		    	else if(qrMatrix.get(x, y) == QRColor.White80) g2.setColor(new Color(255,255,255,80));
		    	else if(qrMatrix.get(x, y) == QRColor.Black30) g2.setColor(new Color(0,0,0,30));
		    	else if(qrMatrix.get(x, y) == QRColor.White30) g2.setColor(new Color(255,255,255,30));
		    	
				g2.draw(new Rectangle(x, y, 1, 1));
		    }
		}
		g2.dispose();
		qrimage.flush();
		
		return this;
	}
	
	public BufferedImage getBufferedImage(){
		return qrimage;
	}

}
