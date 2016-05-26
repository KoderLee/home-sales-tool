package org.yynn.resm.web.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRImageRenderer;
import net.sf.jasperreports.engine.JRRenderable;

/**
 * <p>
 * Title: ImageServlet.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 18, 2007
 */
public class ImageServlet extends HttpServlet {
	public static final String IMAGE_NAME_REQUEST_PARAMETER = "image";

	public static final String DEFAULT_BASE_IMAGE_PATH = "/images/";

	private String baseImagePath = DEFAULT_BASE_IMAGE_PATH;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] imageData = null;
		String imageMimeType = null;

		String imageName = request.getParameter(IMAGE_NAME_REQUEST_PARAMETER);
		if ("px".equals(imageName)) {
			try {
				JRRenderable pxRenderer = JRImageRenderer.getInstance("net/sf/jasperreports/engine/images/pixel.GIF",
						JRImage.ON_ERROR_TYPE_ERROR);
				imageData = pxRenderer.getImageData();
			}
			catch (JRException e) {
				throw new ServletException(e);
			}

			if (imageData != null && imageData.length > 0) {
				if (imageMimeType != null) {
					response.setHeader("Content-Type", imageMimeType);
				}
				response.setContentLength(imageData.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(imageData, 0, imageData.length);
				ouputStream.flush();
				ouputStream.close();
			}
		}
		else {
			request.getRequestDispatcher(baseImagePath + imageName).forward(request, response);
		}
	}

}
