/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alexander Riquelme
 */
@FacesValidator("stringObligatorioValidator")
public class StringObligatorioValidator implements javax.faces.validator.Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        System.out.println("stringObligatorioValidator");

        String str = (String) value;
        String strReturnValidator = (String) component.getAttributes().get("strReturnValidator");
        System.out.println("str: " + str + " strReturnValidator: " + strReturnValidator);
        FacesMessage msg;

        if (str == null || str.trim().equals("")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, strReturnValidator, "");
            throw new ValidatorException(msg);

        }

    }

}
