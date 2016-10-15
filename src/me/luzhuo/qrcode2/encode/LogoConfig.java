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

import java.awt.BasicStroke;  
import java.awt.Color;  
import java.awt.Graphics2D;  
import java.awt.geom.RoundRectangle2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import javax.imageio.ImageIO; 

/**
 * 为QR中间添加logo
 * @author Luzhuo
 *
 */
public class LogoConfig {  
    
    /** 
     * 设置 logo
     * @param matrixImage 二维码图片
     * @return logo二维码图片
     */
     public BufferedImage LogoMatrix(BufferedImage matrixImage, File logoFile) throws IOException{
         int minLength = Math.min(matrixImage.getWidth(), matrixImage.getHeight());  
         Graphics2D g2 = matrixImage.createGraphics();  

         BufferedImage logo = ImageIO.read(logoFile);
  
         // 开始绘制图片  logo / x.172 / y.172 / w.86 / h.86 / null  // 图片宽高值有些大,四角都能看到虚边了
         g2.drawImage(logo, minLength/5*2, minLength/5*2, minLength/5, minLength/5, null); // 绘制
         // 图形轮廓  宽度 / 端点的装饰 / 路径线段交汇处的装饰 / 白色
         BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
         g2.setStroke(stroke);// 设置笔画对象 
         // 指定弧度的圆角矩形  x.172 / y.172 / w.86 / y.86 / rw.20 / rh.20
         RoundRectangle2D.Float round = new RoundRectangle2D.Float(minLength/5*2, minLength/5*2, minLength/5, minLength/5,20,20);  
         g2.setColor(Color.white);  
         g2.draw(round);// 绘制圆弧矩形 
           
         // 设置 logo 旁边的小灰色边
         BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
         g2.setStroke(stroke2);// 设置笔画对象
         // x.174 / y.174 / w.82 / h.82 / rw.20 / rh.20
         RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(minLength/5*2+2, minLength/5*2+2, minLength/5-4, minLength/5-4,20,20);  
         g2.setColor(new Color(128,128,128));  
         g2.draw(round2);// 绘制圆弧矩形
  
         g2.dispose();  
         matrixImage.flush();  
         return matrixImage ;  
     }  
      
}  