package br.com.lopessolutions.acesso.util;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class StartupSeeder {

	@Inject
	private AmbienteDesenvolvimento ambienteDesenvolvimento;

	@PostConstruct
	public void init() {
		try {
			ambienteDesenvolvimento.seedIfFirstAccess();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


