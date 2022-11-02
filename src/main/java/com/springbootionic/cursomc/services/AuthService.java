package com.springbootionic.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootionic.cursomc.domain.Cliente;
import com.springbootionic.cursomc.repositories.ClienteRepository;
import com.springbootionic.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		String newPass = newPassoword();
		cliente.setSenha(pe.encode(newPass));
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassoword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() { // funcao de senha randomica
		int opt = rand.nextInt(3);
		if (opt == 0) {// gera digito
			return (char) (rand.nextInt(10) + 48); // (10) gera um número aleatório de 0 a 9
			// + 48 é para adicionar 48 nessa solução, ou seja, irá gerar de 48 até 57
		} else if (opt == 1) {// gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else if (opt == 2) {// gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
		return 0;
	}

}
