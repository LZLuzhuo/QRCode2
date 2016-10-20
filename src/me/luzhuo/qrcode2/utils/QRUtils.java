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
package me.luzhuo.qrcode2.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * QR工具类
 * @author Luzhuo
 *
 */
public class QRUtils {
	
	/**
	 * 获取图片文件的像素宽高(读取文件头信息获取), 单位px, 返回一个2位长度的数组<br>
	 * 该方法代码改自该网址( http://blog.jdk5.com/zh/java-get-image-size-without-loading-the-whole-data/ ), 非常感谢!
	 * @return int[0] 为宽, int[1]为高, 不支持该格式则返回null
	 */
	public static int[] getImageFilePxSize(File imageFile) throws IOException{
		int[] imagepx = new int[]{-1, -1};
		
        InputStream is = new FileInputStream(imageFile);
        try {
            int c1 = is.read();
            int c2 = is.read();
            int c3 = is.read();
            
            if(c1 == 'G' && c2 == 'I' && c3 == 'F'){ // GIF
                is.skip(3);
                imagepx[0] = readInt(is, 2, false);
                imagepx[1] = readInt(is, 2,false);
                return imagepx;
            }
            
            if (c1 == 0xFF && c2 == 0xD8) { // JPG
                while (c3 == 255) {
                    int marker = is.read();
                    int len = readInt(is, 2, true);
                    if (marker == 192 || marker == 193 || marker == 194) {
                        is.skip(1);
                        imagepx[1] = readInt(is,2,true);
                        imagepx[0] = readInt(is,2,true);
                        break;
                    }
                    is.skip(len - 2);
                    c3 = is.read();
                }
                return imagepx;
            }
            
            if (c1 == 137 && c2 == 80 && c3 == 78) { // PNG
                is.skip(15);
                imagepx[0] = readInt(is,2,true);
                is.skip(2);
                imagepx[1] = readInt(is,2,true);
                return imagepx;
            }

            if (c1 == 66 && c2 == 77) { // BMP
                is.skip(15);
                imagepx[0] = readInt(is,2,false);
                is.skip(2);
                imagepx[1] = readInt(is,2,false);
                return imagepx;
            }
            
            return null;
        } finally {
            is.close();
        }
	}
	
    private static int readInt(InputStream is, int noOfBytes, boolean bigEndian) throws IOException {
        int ret = 0;
        int sv = bigEndian ? ((noOfBytes - 1) * 8) : 0;
        int cnt = bigEndian ? -8 : 8;
        for(int i=0;i<noOfBytes;i++) {
            ret |= is.read() << sv;
            sv += cnt;
        }
        return ret;
    }

}
