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
 * @author Juan D. Delgado Robles
 */
public class EnviarCorreo implements java.io.Serializable {

    private static Logger logger = Logger.getLogger("[EnviarCorreo]");
    private static final String ORIGINAL = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT = "AaEeIiOoUuNnUu";

    public EnviarCorreo() {
    }

    /*
      String nombrePersonalFrom = "AYUDANTÍA USM";      
      String destinatario = "juan.delgado@usm.cl";
      String copia = "juan.delgador@gmail.com";
      String asunto = "Prueba Desarrollo USM";
      String mensaje = "Alo!! Probando!! D: <br/>";     
     */
    public boolean correo(String nombrePersonalFrom, String destinatario, String copia, String asunto, String mensaje) {

        if (FuncionCorreo.validarCorreo(destinatario)) {

            //DESARROLLLO
            //destinatario = "juan.delgado@usm.cl";//DESARROLLLO
            //copia = "";//DESARROLLLO

            //System.out.println("destinatario: " + destinatario);
            //System.out.println("mensaje: " + mensaje);
            Properties properties = new Properties();

            //properties.put("mail.smtp.host", "spam01.dti.utfsm.cl");
            properties.put("mail.smtp.host", "smtp03.usm.cl");
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.auth", "false");
            properties.setProperty("mail.smtp.starttls.enable", "false");
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            //this.properties.setProperty("mail.smtp.ssl.trust", "smtpserver");
            properties.setProperty("mail.smtp.port", "25");
            try {
                SMTPAuthentication auth = new SMTPAuthentication();
                Session session = Session.getInstance(properties, auth);

                MimeMessage msg = obtenerMensaje(session, nombrePersonalFrom, destinatario, copia, mensaje, asunto
                );

                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(mensaje, "text/html");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                Transport.send(msg);

            } catch (MessagingException e) {
                //System.out.println("[Error][EnviarCorreo][MessagingException]: " + e.getMessage());
                logger.log(Level.SEVERE, "[Error][EnviarCorreo][correo][MessagingException]: {0}", e.getMessage());
                return false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            //DESARROLLO
            System.out.println("(Desarrollo) Correo enviado: " + destinatario);

            return true;
        } else {
            logger.log(Level.SEVERE, "[Error][EnviarCorreo][correo][Formato Correo Incorrecto]: {0}", destinatario);
            return false;
        }
    }

    private static synchronized MimeMessage obtenerMensaje(Session session, String nombrePersonalFrom, String to, String cc, String mensaje, String asunto) {
        try {
            //MimeMessage msg = new MimeMessage(session);        
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject(asunto, "utf-8");
            msg.setContent(mensaje, "text/html");
            // msg.setText(mensaje, "ISO-8859-1", "html");

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            if (cc != null && !cc.trim().isEmpty()) {
                if (FuncionCorreo.validarCorreo(cc)) {
                    msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                } else {
                    //System.out.println("[Error][EnviarCorreo][CC]: Formato Correo Copia Incorrecto");
                    logger.log(Level.SEVERE, "[Error][EnviarCorreo][obtenerMensaje][CC]: Formato Correo Copia Incorrecto");
                }
            }
            //msg.setFrom(new InternetAddress(from, "RREE - Regularización de deuda", "text/html"));

            InternetAddress objInternetAddress = new InternetAddress("noresponder@usm.cl");
            try {
                objInternetAddress.setPersonal(nombrePersonalFrom);
                objInternetAddress.setAddress("noresponder@usm.cl");
            } catch (UnsupportedEncodingException e) {
                //System.out.println("[Error][EnviarCorreo][UnsupportedEncodingException]: " + e.getMessage());
                logger.log(Level.SEVERE, "[Error][EnviarCorreo][obtenerMensaje][UnsupportedEncodingException]: {0}", e.getMessage());

            }

            msg.setFrom(objInternetAddress);

            return msg;
        } catch (MessagingException e) {
            //System.out.println("[Error][EnviarCorreo][obtenerMensaje][MessagingException]: " + e.getMessage());
            logger.log(Level.SEVERE, "[Error][EnviarCorreo][obtenerMensaje][MessagingException]: {0}", e.getMessage());
            return null;
        }
    }

    public static String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int index = 0; index < array.length; index++) {
            int pos = ORIGINAL.indexOf(array[index]);
            if (pos > -1) {
                array[index] = REPLACEMENT.charAt(pos);
            }
        }
        return new String(array);
    }

    class SMTPAuthentication extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "pam.usm";
            String password = "ryksay53";//"Pam.dti.usm";
            return new PasswordAuthentication(username, password);
        }
    }
}
