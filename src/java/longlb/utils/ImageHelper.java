/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.servlet.http.Part;

/**
 *
 * @author Long Le
 */
public class ImageHelper {
    public static String writeToDisk(Part imagePart, String destinationPath) throws IOException{
	String imageName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
	String finalPath = destinationPath + "/" + imageName;
	
	InputStream inputStream = null;
	FileOutputStream outputStream = null;
	
	try {
	    inputStream = imagePart.getInputStream();
	    outputStream = new FileOutputStream(finalPath);
	    byte[] buffer = new byte[1024];
	    int length;	    
	    while ((length = inputStream.read(buffer)) > 0){
		outputStream.write(buffer, 0, length);
	    }
   
	} finally {
	    if (inputStream != null){
		inputStream.close();
	    }
	    if (outputStream != null){
		outputStream.close();
	    }
	}
	return finalPath;
    }
    
}
