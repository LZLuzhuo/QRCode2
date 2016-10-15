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

/**
 * QR样式
 * @author Luzhuo
 *
 */
public class QRStyle {
	private QRStyleGroup styleGroup;
	
	public enum QRStyles{
		/** 默认, 只有白底黑块 */
		Default,
		/** 只有黑块, 其余透明, 适用于有彩色背景图的 */
		OnlyBlack,
		/** 默认半透明, Default的半透明版, 白色和黑色块都为半透明, 适用于有彩色背景图的 */
		DefaultTranslucent,
		/** 默认半透明并带有识别框, 比DefaultTranslucent版多了个识别框, 适用于有彩色背景图的 */
		DefaultTranslucentBorder
	}
	
	public enum QRStyleGroup{
		/** 全色 */
		FullColor,
		/** 半透明色 */
		TranslucentColor
	}
	
	public QRStyle(QRCode qrcode, QRStyles style){
		if(qrcode == null || style == null) throw new IllegalArgumentException("Found QRCode == null or QRStyles == null.");
		
		switch (style) {
		case Default:
		case OnlyBlack:
			styleGroup = QRStyleGroup.FullColor;
			break;
		case DefaultTranslucent:
		case DefaultTranslucentBorder:
			styleGroup = QRStyleGroup.TranslucentColor;
			break;
		}
		
		analyticStyle(qrcode, style);
	}
	
	/**
	 * 样式处理
	 * @param qrcode
	 */
	private void analyticStyle(QRCode qrcode, QRStyles style){
		byte[][] qrarray = qrcode.getArray();
		
		for(int x = 0; x < qrarray.length; x++){
			for(int y = 0; y < qrarray[x].length; y++){
				if(style == QRStyles.OnlyBlack){
					if(qrarray[x][y] == 0){ qrarray[x][y] = QRColor.Empty; }
					else if(qrarray[x][y] == 1){ qrarray[x][y] = QRColor.Black; }
					
				}else{
					if(qrarray[x][y] == 0){ qrarray[x][y] = QRColor.White; }
					else if(qrarray[x][y] == 1){ qrarray[x][y] = QRColor.Black; }
				}
			}
		}
		
		if(style == QRStyles.DefaultTranslucentBorder){
			// 对数组进行扩展
			byte[][] newQR = new byte[qrarray.length + 2][];
			int newQRLength = newQR.length; // 新数组的第一维长度
			int newQRTemLength = qrarray[0].length + 2; // 新数组的第二维长度
			
			for(int x = 0; x < qrarray.length; x++){
				byte[] newTem = new byte[newQRTemLength];
				System.arraycopy(qrarray[x], 0, newTem, 1, qrarray[x].length);
				newQR[x + 1] = newTem;
			}
			
			newQR[0] = new byte[newQRTemLength];
			newQR[newQRLength - 1] = new byte[newQRTemLength];
			
			// 设置边框
			setBorder(newQR);
			
			qrcode.setArray(newQR);
		}
	}
	
	private void setBorder(byte[][] newQR){
		int height = newQR.length;
		int wight = newQR[0].length;
		
		// x
		// 左
		for(int x = 0; x < 9; x++){ newQR[0][x] = QRColor.White80; newQR[8][x] = QRColor.White80; newQR[height - 1][x] = QRColor.White80; newQR[height - 9][x] = QRColor.White80;}
		for(int x = 1; x < 8; x++){ newQR[1][x] = QRColor.Black80; newQR[7][x] = QRColor.Black80; newQR[height - 2][x] = QRColor.Black80; newQR[height - 8][x] = QRColor.Black80; }
		for(int x = 2; x < 7; x++){ newQR[2][x] = QRColor.White80; newQR[6][x] = QRColor.White80; newQR[height - 3][x] = QRColor.White80; newQR[height - 7][x] = QRColor.White80; }
		for(int x = 3; x < 6; x++){ newQR[3][x] = QRColor.Black80; newQR[4][x] = QRColor.Black80; newQR[5][x] = QRColor.Black80; newQR[height - 4][x] = QRColor.Black80; newQR[height - 5][x] = QRColor.Black80; newQR[height - 6][x] = QRColor.Black80; }
		
		// 右
		for(int x = wight - 9; x < wight; x++){ newQR[0][x] = QRColor.White80; newQR[8][x] = QRColor.White80; }
		for(int x = wight - 8; x < wight - 1; x++){ newQR[1][x] = QRColor.Black80; newQR[7][x] = QRColor.Black80; }
		for(int x = wight - 7; x < wight - 2; x++){ newQR[2][x] = QRColor.White80; newQR[6][x] = QRColor.White80; }
		for(int x = wight - 6; x < wight - 3; x++){ newQR[3][x] = QRColor.Black80; newQR[4][x] = QRColor.Black80; newQR[5][x] = QRColor.Black80; }
		
		// y
		// 上
		for(int y = 1; y < 8; y++){ newQR[y][0] = QRColor.White80; newQR[y][8] = QRColor.White80; newQR[y][height - 1] = QRColor.White80; newQR[y][height - 9] = QRColor.White80; }
		for(int y = 2; y < 7; y++){ newQR[y][1] = QRColor.Black80; newQR[y][7] = QRColor.Black80; newQR[y][height - 2] = QRColor.Black80; newQR[y][height - 8] = QRColor.Black80;}
		for(int y = 3; y < 6; y++){ newQR[y][2] = QRColor.White80; newQR[y][6] = QRColor.White80; newQR[y][height - 3] = QRColor.White80; newQR[y][height - 7] = QRColor.White80; }
		
		// 下
		for(int y = height - 8; y < height - 1; y++){ newQR[y][0] = QRColor.White80; newQR[y][8] = QRColor.White80; }
		for(int y = height - 7; y < height - 2; y++){ newQR[y][1] = QRColor.Black80; newQR[y][7] = QRColor.Black80; }
		for(int y = height - 6; y < height - 3; y++){ newQR[y][2] = QRColor.White80; newQR[y][6] = QRColor.White80; }
	}
	
	public QRStyleGroup getGroup(){
		return styleGroup;
	}
}
