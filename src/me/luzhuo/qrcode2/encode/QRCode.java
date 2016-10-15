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

import java.util.Hashtable;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;

/**
 * 创建QRCode源数据
 * @author Luzhuo
 *
 */
public class QRCode {
	private byte[][] qrcodes;
	private int width;
	private int height;

	public QRCode createQRCode(String content) throws WriterException{
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //  指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%） 
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
        hints.put(EncodeHintType.MARGIN, 1); // 设置二维码边的空度，非负数
        
        // 生成二维码
        ByteMatrix bmatrix = new QRCodeWriter().encode(content, hints).getMatrix();
        qrcodes = bmatrix.getArray();
        width = bmatrix.getWidth();
        height = bmatrix.getHeight();
        return this;
	}
	
	public int getHeight() {
	  return height;
	}
	
	public int getWidth() {
	  return width;
	}

	public byte get(int x, int y) {
	  return qrcodes[x][y];
	}
	
	public void set(int x, int y, int value) {
		qrcodes[y][x] = (byte) value;
	}
	
	public byte[][] getArray() {
	  return qrcodes;
	}
	
	public void setArray(byte[][] qrcodes){
		this.qrcodes = qrcodes;
		this.height = qrcodes.length;
		this.width = qrcodes[0].length;
	}

}
