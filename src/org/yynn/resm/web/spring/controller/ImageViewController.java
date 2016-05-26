package org.yynn.resm.web.spring.controller;

import java.io.OutputStream;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.yynn.resm.service.CrudService;

/**
 * <p>
 * Title: ImageViewController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-18
 */
public class ImageViewController<T, PK extends Serializable> extends BasePkCRUDController<T, PK> {
  private String imagePropertyName;

  /**
   * @return the imagePropertyName
   */
  public String getImagePropertyName() {
    return imagePropertyName;
  }

  /**
   * @param imagePropertyName
   *          the imagePropertyName to set
   */
  public void setImagePropertyName(String imagePropertyName) {
    this.imagePropertyName = imagePropertyName;
  }

  public ImageViewController(CrudService<T, PK> service) {
    super(service);
  }

  /*
   * @see org.yynn.resm.web.spring.controller.AbstractCRUDController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Object model = null;
    if (service.getPKType().isAssignableFrom(Integer.class)) {
      model = service.read((PK) new Integer(request.getParameter(pkName)));
    } else if (service.getPKType().isAssignableFrom(String.class)) {
      model = service.read((PK) request.getParameter(pkName));
    } else if (service.getPKType().isAssignableFrom(Long.class)) {
      model = service.read((PK) new Long(request.getParameter(pkName)));
    } else {
      throw new UnsupportedOperationException("PkType Must Be Long or String or Integer,sorry!");
    }
    
    byte[] imageValue = (byte[])(new ModelBeanWrapperImpl(model)).getPropertyValue(imagePropertyName);
    response.setContentType("image/jpeg"); 
    OutputStream outs = response.getOutputStream(); 
    outs.write(imageValue);
    outs.flush();
    
    return null;
  }

}
