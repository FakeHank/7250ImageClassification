package backend;

import com.tinify.*;
import java.lang.Exception;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;

public enum ImageCompressor {
	INSTANCE;
	
	/*
	 * Function: Compress Image
	 * Input: File
	 * Output: byte[]
	 */
	public byte[] compress(File file){
		Tinify.setKey("tOI689dj3LfUyHop8214q13rD1Wie04D");
		
		byte[] res = null;
		try {
			if (file.getName().startsWith("."))
				return null;
			Source source = Tinify.fromFile(file.getPath());
			Options options = new Options().with("method", "fit").with("width", 150).with("height", 150);
			Source resized = source.resize(options);
			
			res = resized.toBuffer();
		
		} catch (IOException e) {
			System.err.println("IOEXCEPTION: " + e.getMessage());
		}
		return res;
	}
	
	/*
	 * Function: Compress Image
	 * Input: File, String
	 * Output: File
	 */

	public File compress(File file, String outputPath) {
		Tinify.setKey("tOI689dj3LfUyHop8214q13rD1Wie04D");
		
		File res = null;
		try {
			if (file.getName().startsWith("."))
				return null;
			Source source = Tinify.fromFile(file.getPath());
			Options options = new Options().with("method", "fit").with("width", 32).with("height", 32);
			Source resized = source.resize(options);
			resized.toFile(outputPath + File.separator + file.getName() + "Out.jpeg");
		} catch (IOException e) {
			System.err.println("IOEXCEPTION: " + e.getMessage());
		}
		return new File("outputPath + File.separator + file.getName() + \"Out.jpeg\"");
	}

	
	/*
	 * Function: transfer an image to int[][][]
	 */
	public int[][][] toIntArray(String ImageName) {
		File imgPath = new File(ImageName);
		return toIntArray(imgPath);
	}
	
	public int[][][] toIntArray(byte[] byteArray){
		int[][][] result = null;
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(byteArray));
			result = readImg(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int[][][] toIntArray(File img){
		int[][][] result = null;
		try {
			BufferedImage image = ImageIO.read(img);
			result = readImg(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private int[][][] readImg(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		int[][][] result = new int[3][height][width];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int pix = image.getRGB(col, row);
				result[0][row][col] = (pix >> 16) & 0xFF;
				result[1][row][col] = (pix >> 8) & 0xFF;
				result[2][row][col] = pix & 0xFF;
			}
			System.out.println();
		}
		
		return result;
	}
	
	public String wrapupToString(int[][][] data){
//		StringBuilder sb = new StringBuilder();
//		sb.append('[');
//		for (int row = 0; row < data.length; row++) {
//			sb.append('[');
//			for (int col = 0; col < data[0].length; col++) {
//				sb.append(
//						"[" + data[row][col][0] + "," + data[row][col][1] + "," + data[row][col][2] + "]");
//			}
//			sb.append(']');
//		}
//		sb.append(']');
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++){
			for (int row = 0; row < data[0].length; row++) {
				for (int col = 0; col < data[0][0].length; col++) {
					sb.append(data[i][row][col]+",");
//					if(col < data[0][0].length-1)	sb.append(",");
				}
			}
			sb.append("\n");
		}
		String res = sb.toString();
		System.out.println(res);
		return res;
	}

	public String executePost(String targetURL, String urlParameters) {
		HttpURLConnection connection = null;

		try {
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
