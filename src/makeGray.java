import java.awt.*;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class makeGray
{
	makeGray(String fname) throws IOException{
		BufferedImage image = ImageIO.read(new File(fname));
		for(int y = 0; y < image.getHeight(); y++) {
		   for(int x = 0; x < image.getWidth(); x++) {
		       Color colour = new Color(image.getRGB(x, y));
		       int Y = (int) (0.2126 * colour.getRed() + 0.7152 * colour.getGreen() + 0.0722 * colour.getBlue());
		       image.setRGB(x, y, new Color(Y, Y, Y).getRGB());
		   }
		}
		ImageIO.write(image, "png", new File("./image/gray.png"));
	}
}

