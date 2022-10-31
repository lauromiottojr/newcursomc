package com.springbootionic.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.springbootionic.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
