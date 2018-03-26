/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.validator;

import cl.usm.geosansano.functions.FuncionCorreo;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alex
 */
@FacesValidator("emailValidator")
public class EmailValidator implements javax.faces.validator.Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String str = (String) value;

        UIInput correoUI = (UIInput) component.getAttributes().get("mail");
        String correo = "";
        
        if (correoUI != null) {
            correo = (String) correoUI.getValue().toString();
        }

        System.out.println("str: " + str + " correo: " + correo);
        FacesMessage msg;

        if (str == null || str.trim().equals("")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe Ingresar Correo.", "");
            throw new ValidatorException(msg);
        }

        if (!FuncionCorreo.validarCorreo(str)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe Ingresar un Correo valido.", "");
            throw new ValidatorException(msg);
        }

        if (correo != null && !correo.trim().equals("") && !correo.trim().equals(str.trim())) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Los correos ingresados no coinciden.", "");
            throw new ValidatorException(msg);
        }

    }

}
