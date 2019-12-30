import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bright {
	float brightness = (float) 1.0; 
	BufferedImage bright_img;
	public float getBrightness() {
		return brightness;
	}
	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}
	Bright(String fname,float b) throws IOException{
		this.brightness = b;
		File imgf = new File(fname);
		BufferedImage bi = ImageIO.read(imgf);
		bright_img = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
		RescaleOp rescaleOp = new RescaleOp(brightness, 0, null);
		rescaleOp.filter(bi, bright_img);
		ImageIO.write(bright_img, "jpg", new File("./image/bright.jpg"));//jpg=>이미지 확장자이다. 이미지가 png이면 png로주면됨.
	}
	BufferedImage img () {
		return bright_img;
	}
	
}
