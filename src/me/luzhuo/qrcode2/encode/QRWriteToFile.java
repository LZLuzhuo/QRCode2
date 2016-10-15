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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 将QR写入文件
 * @author Luzhuo
 *
 */
public class QRWriteToFile {
	/**
	 * 将二维码写入文件
	 * @param graphical 二维码数据
	 * @param file 保存文件的位置
	 */
    public void writeToFile(QRGraphical graphical, File savefile) throws IOException{
    	writeToFile(graphical, "png", savefile, null, false);
    }
    
    /**
     * 将二维码写入文件
     * @param graphical 二维码数据
     * @param savefile 保存文件的位置
     * @param logo logo位置
     */
    public void writeToFile(QRGraphical graphical, File savefile, File logo) throws IOException{
    	writeToFile(graphical, "png", savefile, logo, true);
    }
    
    /**
     * 将二维码写入文件
     * @param graphical 二维码数据
     * @param format 格式
     * @param saveFile 保存文件的位置
     * @param logo logo位置
     * @param isaddlogo 是否添加logo
     * @param callback 保存文件的回调
     */
	public void writeToFile(QRGraphical graphical, String format, File saveFile, File logo, boolean isaddlogo) throws IOException {
		if(graphical == null) throw new IllegalArgumentException("Found QRGraphical == null.");
		
		BufferedImage image = graphical.getBufferedImage();
		
		if(isaddlogo){
			if(logo == null || !logo.exists()) throw new IOException("Did not find the logo file or null pointer.");
			image = new LogoConfig().LogoMatrix(image, logo); // 添加logo
		}
		
		
		if(format == null) throw new IllegalArgumentException("Found format is empty.");
		
        if (!ImageIO.write(image, format, saveFile)){ throw new IOException("Does not support the ".concat(format).concat(" format, the failure to generate.")); } // 写入失败
	}  

}
