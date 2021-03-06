package net.rossturner.imageuploader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.core.util.Base64;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/image")
public class ImageResource {
	
	static int counter = 0;

	/**
	 * Method processing HTTP GET requests, producing "text/plain" MIME media
	 * type.
	 * 
	 * @return String that will be send back as a response of type "text/plain".
	 */
	@GET
	@Produces("text/plain")
	public String getIt() {
		return "Hi there!";
	}

	@POST
    @Produces("text/plain")
	public String handleImageUpload(final String base64data) throws IOException {
		counter++;
		File of = new File("target/image"+counter+".jpg");
		FileOutputStream osf = new FileOutputStream(of);
		try {
			osf.write(Base64.decode(base64data));
			osf.flush();
		} finally {
			osf.close();
		}
		return Integer.toString(base64data.length());
	}

}
