package com.springbootionic.cursomc.domain.enums;

public enum Perfil {
	// ROLE é mandatório do framework para identificar qual é o nível do usuário
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String desc;
	
	private Perfil(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}	

	public String getDesc() {
		return desc;
	}	
	
	public static Perfil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}		
		throw new IllegalArgumentException("ID inválido: " + cod);
	}
	
}
