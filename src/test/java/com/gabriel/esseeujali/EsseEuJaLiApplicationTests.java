package com.gabriel.esseeujali;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabriel.esseeujali.controller.ClienteController;

@SpringBootTest
class EsseEuJaLiApplicationTests {

	@Autowired
	private ClienteController clienteController;

	@Test
	void contextLoads() {
		assertThat(clienteController).isNotNull();
	}

}
