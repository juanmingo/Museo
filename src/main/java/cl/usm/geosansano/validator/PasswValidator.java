/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.validator;

import cl.usm.geosansano.functions.FuncionTexto;
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
        String confirm = (String) component.getAttributes().get("confirm");
        String pass = "";

        if (passUI != null && passUI.getValue() != null) {
            pass = (String) passUI.getValue().toString();
        }

        System.out.println("str: " + str + " correo: " + pass);
        FacesMessage msg;

        if (confirm != null && (str == null || str.trim().equals(""))) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Ingresar la Confirmación de la Contraseña.", "");
            throw new ValidatorException(msg);
        } else {
            if (str == null || str.trim().equals("")) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Ingresar un Contraseña.", "");
                throw new ValidatorException(msg);
            } else if (!FuncionTexto.validarContraseña(str)) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña debe contener por lo menos 1 letra mayúscula, 1 letra minúscula, 1 número y debe tener un largo de 8 caracteres o mas.", "");
                throw new ValidatorException(msg);
            }
        }
        if (pass != null && !pass.trim().equals("") && !pass.trim().equals(str.trim())) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas ingresadas no coinciden.", "");
            throw new ValidatorException(msg);
        }

    }

}
