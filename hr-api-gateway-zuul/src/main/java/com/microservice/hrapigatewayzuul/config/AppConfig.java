package com.microservice.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
public class AppConfig {
	
	/*Aula 44. Configurações de segurança no servidor de configuração
	 * 
	 * Na aula, o professor criou no repositório do github do projeto
	 * um arquivo 'application.properties' e lá, ele colocou os seguintes dados:
	 *
	 * ======================================
	 * oauth.client.name=myappname123
	 * oauth.client.secret=myappsecret123
	 * jwt.secret=MY-JWT-SECRET
	 * ======================================
	 * 
	 * E nessa classe, ele criou o atributo para ler a chave JWT do arquivo de
	 * configuração
	 * 
	 * @Value("${jwt-secret}")
	 * private String jwtSecret;
	 * 
	 * Depois ele adicionou a anotação @RefreshScope na classe para atualizar as
	 * variáveis em tempo de execução.
	 * 
	 * Como não criei repositorio para esse projeto, vou adicionar os valores no 
	 * 'application.properties' que fica dentro do projeto mesmo.
	 * */
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtSecret);
		return tokenConverter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}