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

import me.luzhuo.qrcode2.encode.QRStyle.QRStyleGroup;

/**
 * 创建QR矩阵
 * @author Luzhuo
 *
 */
public class QRMatrix {
	private int matrixWidth;
	private int matrixHeight;
	private int[][] imageMeatrix;
	private int ratio;
	private int left, top;
	
	public QRMatrix createMatrix(QRCode qrcode, int width, int height, QRStyle style){
		if(qrcode == null) throw new IllegalArgumentException("Found QRCode == null.");
		if(width <= 0 || height <= 0) new IllegalArgumentException("Width or height can not be less than or equal to 0");
		
		if(qrcode.getWidth() > width || qrcode.getHeight() > height) throw new IllegalArgumentException("Found picture size too small.");
		
		this.matrixHeight = this.matrixWidth = Math.min(width, height);
		
		if(style.getGroup() == QRStyleGroup.FullColor) fullColor(qrcode);
		if(style.getGroup() == QRStyleGroup.TranslucentColor) translucentColor(qrcode);
		
		return this;
	}
	
	/**
	 * 全色块
	 */
	private void fullColor(QRCode qrcode){
		int qrWidth = qrcode.getWidth();
		int qrHeight = qrcode.getHeight();
		
	    this.imageMeatrix = new int[matrixWidth][matrixHeight];
	    this.ratio = Math.min(matrixWidth, matrixHeight) / Math.max(qrWidth + 2, qrHeight + 2); // 计算比例
	    this.left = (matrixWidth - qrWidth * ratio) / 2 - 1; // 计算左右边距
	    this.top = (matrixHeight - qrHeight * ratio) / 2 - 1;
	    
	    // 生成二维码1位矩阵
		for(int y = 0; y < qrHeight; y++){ // y轴
			for(int x = 0; x < qrWidth; x++){ // x轴
				int colortyle = qrcode.get(y, x);
				for(int ry = 1; ry <= ratio; ry++){
					int tempy = y * ratio + ry + top;
					for(int rx = 1; rx <= ratio; rx++){
						if(colortyle == QRColor.Black){ imageMeatrix[tempy][x * ratio + rx + left] = QRColor.Black; }
						else if(colortyle == QRColor.White){ imageMeatrix[tempy][x * ratio + rx + left] = QRColor.White; }
						else if(colortyle == QRColor.Empty){ imageMeatrix[tempy][x * ratio + rx + left] = QRColor.Empty; }
						else if(colortyle == QRColor.Black30){ imageMeatrix[tempy][x * ratio + rx + left] = QRColor.Black30; }
						else if(colortyle == QRColor.Black80){ imageMeatrix[tempy][x * ratio + rx + left] = QRColor.Black80; }
						else if(colortyle == QRColor.White30){ imageMeatrix[tempy][x * ratio + rx + left] = QRColor.White30; }
						else if(colortyle == QRColor.White80){ imageMeatrix[tempy][x * ratio + rx + left] = QRColor.White80; }
					}
				}
			}
		}
	}
	
	/**
	 * 半透明色块
	 */
	private void translucentColor(QRCode qrcode){
	    int qrwidth = qrcode.getWidth();
	    int qrheight = qrcode.getHeight();
	    
	    this.imageMeatrix = new int[matrixWidth][matrixHeight];
	    this.ratio = Math.min(matrixWidth, matrixHeight) / Math.max((qrwidth + 2) * 3, (qrheight + 2) * 3);
	    this.left = (matrixWidth - qrwidth * ratio * 3) / 2 - 1 * 3;
	    this.top = (matrixHeight - qrheight * ratio * 3) / 2 - 1 * 3;
		
	    // 生成二维码9位矩阵
		for(int y = 0; y < qrheight; y++){ // y轴
			for(int x = 0; x < qrwidth; x++){ // x轴
				int colortyle = qrcode.get(y, x);
				
				for(int cy = 0; cy < 3; cy++){
					for(int cx = 0; cx < 3; cx++){
						// [y * 3 + cy][x * 3 + cx]
						for(int ry = 1; ry <= ratio; ry++){
							for(int rx = 1; rx <= ratio; rx++){
								
								if(colortyle == QRColor.Black){
									if(cy == 1 && cx == 1) imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.Black80;
									else imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.Black30;
									
								}else if(colortyle == QRColor.White){
									if(cy == 1 && cx == 1) imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.White80;
									else imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.White30;
									
								}else if(colortyle == QRColor.Empty){
									continue;
								}else if(colortyle == QRColor.Black30){
									imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.Black30;
								}else if(colortyle == QRColor.Black80){
									imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.Black80;
								}else if(colortyle == QRColor.White30){
									imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.White30;
								}else if(colortyle == QRColor.White80){
									imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.White80;
								}else if(colortyle == QRColor.PointBlack80){
									if(cy == 1 && cx == 1) imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.Black80;
								}else if(colortyle == QRColor.PointWhite80){
									if(cy == 1 && cx == 1) imageMeatrix[(y * 3 + cy) * ratio  + ry + top][(x * 3 + cx) * ratio + rx + left] = QRColor.White80;
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	public int getMatrixWidth(){
		return matrixWidth;
	}
	
	public int getMatrixHeight(){
		return matrixHeight;
	}

	public int get(int x, int y) {
		if(imageMeatrix[x][y] == QRColor.Black) return QRColor.Black;
		if(imageMeatrix[x][y] == QRColor.White) return QRColor.White;
		if(imageMeatrix[x][y] == QRColor.Empty) return QRColor.Empty;
		if(imageMeatrix[x][y] == QRColor.Black30) return QRColor.Black30;
		if(imageMeatrix[x][y] == QRColor.Black80) return QRColor.Black80;
		if(imageMeatrix[x][y] == QRColor.White30) return QRColor.White30;
		if(imageMeatrix[x][y] == QRColor.White80) return QRColor.White80;
		else return QRColor.Empty;
	}

}
