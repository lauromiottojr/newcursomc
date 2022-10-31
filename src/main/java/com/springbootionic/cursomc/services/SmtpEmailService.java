package com.springbootionic.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	/*
	 * @Autowired private MailSender mailSender;
	 */

	@Autowired
	private JavaMailSender javaMailtSender;

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviado email...");
		// desativei o envio de email no ato de cadastrar pedido, é só ativar o comando
		// abaixo que deve enviar o e-mail
		// mailSender.send(msg);
		LOG.info("Email enviado!");

	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviado email...");
		// javaMailtSender.send(msg);
		LOG.info("Email enviado!");

	}

}
