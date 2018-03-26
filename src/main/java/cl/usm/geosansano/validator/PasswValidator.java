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
@FacesValidator("passwValidator")
public class PasswValidator implements javax.faces.validator.Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String str = (String) value;

        UIInput passUI = (UIInput) component.getAttributes().get("password");
        String pass = "";
        
        if (passUI != null) {
            pass = (String) passUI.getValue().toString();
        }

        System.out.println("str: " + str + " correo: " + pass);
        FacesMessage msg;

        if (str == null || str.trim().equals("")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe Ingresar contraseņa.", "");
            throw new ValidatorException(msg);
        }

        if (pass != null && !pass.trim().equals("") && !pass.trim().equals(str.trim())) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Las contraseņas ingresadas no coinciden.", "");
            throw new ValidatorException(msg);
        }

    }

}
