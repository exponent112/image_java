import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Contrast {
	Contrast(String fname){
	// public static void main( String[] args ) throws IOException {
		// String fname = "./image/user2.jpg";
		try {
			BufferedImage img = ImageIO.read(new File(fname));

            int rmax = 0;
            int gmax = 0;
            int bmax = 0;
            int rmin = 1000;
            int gmin = 1000;
            int bmin = 1000;
            for (int y = 0; y < img.getHeight(); y++) {
               for (int x = 0; x < img.getWidth(); x++) {
                  Color colour = new Color(img.getRGB(x, y));
                  if (colour.getRed() > rmax) {
                     rmax = colour.getRed();
                     if(rmax>180) rmax = 180;
                  } else if (colour.getRed() < rmin) {
                     rmin = colour.getRed();
                     
                  }
                  if (colour.getGreen() > gmax) {
                     gmax = colour.getGreen();
                     if(gmax>200) gmax = 200;
                  } else if (colour.getGreen() < gmin) {
                     gmin = colour.getGreen();
                  }
                  if (colour.getBlue() > bmax) {
                     bmax = colour.getBlue();
                     if(bmax>200) bmax = 200;
                  } else if (colour.getBlue() < bmin) {
                     bmin = colour.getBlue();
                  }
               }
            }
            System.out.println(rmax);
            System.out.println(rmin);
            for (int y = 0; y < img.getHeight(); y++) {
               for (int x = 0; x < img.getWidth(); x++) {
                  Color colour = new Color(img.getRGB(x, y));
                  int r;
                  int b;
                  int g;
                  r = ((colour.getRed() - rmin) / (rmax - rmin)) * 255;
                  g = ((colour.getGreen() - gmin) / (gmax - gmin)) * 255;
                  b = ((colour.getBlue() - bmin) / (bmax - bmin)) * 255;
                  if (r == 0 && colour.getRed() > 60) {
                     r = colour.getRed() - 60;
                  }
                  if (g == 0 && colour.getGreen() > 60) {
                     g = colour.getGreen() - 60;
                  }
                  if (b == 0 && colour.getBlue() > 60) {
                     b = colour.getBlue() - 60;
                  }
                  if (r == 0 && colour.getRed() <= 60) {
                     r = 0;
                  }
                  if (g == 0 && colour.getGreen() <= 60) {
                     g = 0;
                  }
                  if (b == 0 && colour.getBlue() <= 60) {
                     b = 0;
                  }
                  img.setRGB(x, y, new Color(r, g, b).getRGB());
               }
            }
            ImageIO.write(img, "png", new File("./image/Cont.png"));

         } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
	}
}
