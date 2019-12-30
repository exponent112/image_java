
import java.io.File; 
import java.io.IOException; 
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO; 

public class BlueImage 
{ 
	BlueImage(String fname)
	{ 
		BufferedImage img = null; 
		File f = null; 
		try
		{ 
			f = new File(fname); 
			img = ImageIO.read(f); 
		} 
		catch(IOException e) 
		{ 
			System.out.println(e); 
		} 
		int width = img.getWidth(); 
		int height = img.getHeight(); 
		for (int y = 0; y < height; y++) 
		{ 
			for (int x = 0; x < width; x++) 
			{ 
				int p = img.getRGB(x,y); 

				int a = (p>>24)&0xff; 
				int b = p&0xff; 
				p = (a<<24) | (0<<16) | (0<<8) | b; 

				img.setRGB(x, y, p); 
			} 
		} 
		try
		{ 
			f = new File("./image/blue.jpg"); 
			ImageIO.write(img, "jpg", f); 
		} 
		catch(IOException e) 
		{ 
			System.out.println(e); 
		} 
	} 
} 
