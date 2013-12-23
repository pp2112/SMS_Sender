import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SMTPSend {

	public SMTPSend() {
	}

	public void msgsend() {
		String username = "id";
		String password = "pass";
		String smtphost = "ipipi.com";
		String compression = "";
		String from = "id@ipipi.com";
		String to = "Number@sms.ipipi.com"; // eg for India 919078******

		String body = "Message";
		Transport tr = null;

		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.auth", "true");

			// Get a Session object
			Session mailSession = Session.getDefaultInstance(props, null);

			// construct the message
			Message msg = new MimeMessage(mailSession);

			// Set message attributes
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(compression);
			msg.setText(body);
			msg.setSentDate(new Date());

			tr = mailSession.getTransport("smtp");
			tr.connect(smtphost, username, password);
			msg.saveChanges();
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] argv) {
		SMTPSend smtpSend = new SMTPSend();
		smtpSend.msgsend();
	}
}