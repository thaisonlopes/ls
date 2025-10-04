package br.com.lopessolutions.base.util;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author Thaison
 *
 */
public class ImageUtil {

	public static BufferedImage resizeImage(BufferedImage bufferedImage, int width, int height) {
		int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(bufferedImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

	public static byte[] compressImage(BufferedImage bufferedImage, int quality) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		compressImage(bufferedImage, baos, quality);
		return baos.toByteArray();
	}

	public static void compressImage(BufferedImage bufferedImage, File outfile, int quality) throws IOException {
		compressImage(bufferedImage, new FileOutputStream(outfile), quality);
	}

	public static void compressImage(BufferedImage bufferedImage, OutputStream output, int quality) throws IOException {
		if (quality <= 0 || quality > 100) {
			throw new IllegalArgumentException("quality not in 1-100");
		}
		ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
		try {
			ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
			jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			jpgWriteParam.setCompressionQuality(quality * 0.01f);
			try (ImageOutputStream ios = ImageIO.createImageOutputStream(output)) {
				jpgWriter.setOutput(ios);
				IIOImage outputImage = new IIOImage(bufferedImage, null, null);
				jpgWriter.write(null, outputImage, jpgWriteParam);
			}
		} finally {
			jpgWriter.dispose();
		}
	}

}
