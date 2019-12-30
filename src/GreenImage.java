
import java.io.File; 
import java.io.IOException; 
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO; 

public class GreenImage 
{ 
	GreenImage(String fname)
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
				int g = (p>>8)&0xff; 
				p = (a<<24) | (0<<16) | (g<<8) | 0; 
				img.setRGB(x, y, p); 
			} 
		} 
		try
		{ 
			f = new File("./image/green.jpg"); 
			ImageIO.write(img, "jpg", f); 
		} 
		catch(IOException e) 
		{ 
			System.out.println(e); 
		} 
	} 
} 
