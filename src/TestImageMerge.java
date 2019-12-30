import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestImageMerge {
 public static void main(String[] args) {
  try {
   BufferedImage image1 = ImageIO.read(new File("./image/back.png"));
   BufferedImage image2 = ImageIO.read(new File("./image/face.png"));

   int width = Math.max(image1.getWidth(), image2.getWidth());
   int height = image1.getHeight() + image2.getHeight();

   BufferedImage mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
   Graphics2D graphics = (Graphics2D) mergedImage.getGraphics();

   graphics.setBackground(Color.WHITE);
   graphics.drawImage(image1, 0, 0, null);
   graphics.drawImage(image2, 0, image1.getHeight(), null);
   
   ImageIO.write(mergedImage, "jpg", new File("./image/result.jpg"));
   // ImageIO.write(mergedImage, "jpg", new File("c:\\mergedImage.jpg"));
   // ImageIO.write(mergedImage, "png", new File("c:\\mergedImage.png"));
  } catch (IOException ioe) {
   ioe.printStackTrace();
  }

  System.out.println("이미지 합성이 완료되었습니다... 에헤라 디야~~");
 }
}