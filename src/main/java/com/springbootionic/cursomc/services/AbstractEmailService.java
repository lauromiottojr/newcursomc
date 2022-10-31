package com.springbootionic.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.springbootionic.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	// pega o valor do sender no application.properties
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleEmailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleEmailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado, código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		obj.toString();
		return sm;
	}

}
