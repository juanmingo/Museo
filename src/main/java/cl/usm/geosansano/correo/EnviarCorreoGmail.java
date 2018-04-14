package cl.usm.geosansano.correo;

import cl.usm.geosansano.functions.FuncionCorreo;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Juan D. Delgado Robles.
 */
public class EnviarCorreoGmail implements java.io.Serializable {

    private static Logger logger = Logger.getLogger("[EnviarCorreoGmail]");

    public EnviarCorreoGmail() {
    }

    /*
      String nombrePersonalFrom = "Registro Geo UTFSM - Sansanos por el Mundo";      
      String destinatario = "juan.delgado@usm.cl";
      String copia = "juan.delgador@gmail.com";
      String asunto = "Prueba Desarrollo USM";
      String mensaje = "Alo!! Probando!! D: <br/>";     
     */
    public boolean correo(String nombrePersonalFrom, String destinatario, String copia, String asunto, String mensaje) {

        if (FuncionCorreo.validarCorreo(destinatario)) {

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("geosansano@gmail.com", "utfsm1234");
                }
            });

            try {

                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(mensaje, "text/html");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                Message message = new MimeMessage(session);

                InternetAddress objInternetAddress = new InternetAddress("noresponder@usm.cl");
                try {
                    objInternetAddress.setPersonal(nombrePersonalFrom);
                    objInternetAddress.setAddress("noresponder@usm.cl");
                } catch (UnsupportedEncodingException e) {
                    //System.out.println("[Error][EnviarCorreo][UnsupportedEncodingException]: " + e.getMessage());
                    logger.log(Level.SEVERE, "[Error][EnviarCorreo][correo][UnsupportedEncodingException]: {0}", e.getMessage());

                }

                message.setFrom(objInternetAddress);

                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(destinatario));

                if (copia != null && !copia.trim().isEmpty()) {
                    if (FuncionCorreo.validarCorreo(copia)) {
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(copia));
                    } else {
                        //System.out.println("[Error][EnviarCorreo][correo][CC]: Formato Correo Copia Incorrecto");
                        logger.log(Level.SEVERE, "[Error][EnviarCorreo][correo][CC]: Formato Correo Copia Incorrecto");
                    }
                }

                message.setSubject(asunto);
                message.setContent(mensaje, "text/html");

                Transport.send(message);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                //DESARROLLO
                System.out.println("(Desarrollo) Correo enviado: " + destinatario);

                return true;
            } catch (MessagingException e) {
                //System.out.println("[Error][EnviarCorreo][InterruptedException]: " + e.getMessage());
                logger.log(Level.SEVERE, "[Error][EnviarCorreo][correo][InterruptedException]: {0}", e.getMessage());
                return false;
            }
        } else {
            logger.log(Level.SEVERE, "[Error][EnviarCorreo][correo][Formato Correo Incorrecto]: {0}", destinatario);
            return false;
        }
    }

}
