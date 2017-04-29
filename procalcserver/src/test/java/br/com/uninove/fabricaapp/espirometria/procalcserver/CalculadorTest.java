package br.com.uninove.fabricaapp.espirometria.procalcserver;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.uninove.fabricaapp.espirometria.procalcserver.models.Pessoa;

public class CalculadorTest {

	private HttpServer servidor;
	private WebTarget target;
	private Client client;
	
	@Before
	public void startaServidor(){
		this.servidor = Servidor.startaServidor();
		
		ClientConfig clientConfig = new ClientConfig();
		//configura o cliente para logar o que esta acontecendo nas requisicoes. 
		clientConfig.register(new LoggingFilter());
		
		this.client = ClientBuilder.newClient(clientConfig);
		this.target = this.client.target("http://localhost:8081");
	}
	
	@After
	public void paraServidor(){
		this.servidor.stop();
	}
	
	@Test
	public void testaCalculoEspirometria(){
		Pessoa pessoa = new Pessoa();
		pessoa.setAltura(1.80);
		pessoa.setIdade(27);
		pessoa.setMassa(90);
		pessoa.setSexo('M');
		
		String json = pessoa.toJson();
		
		System.out.println(json);
		
		Entity<String> entity = Entity.entity(json, MediaType.APPLICATION_JSON);
		Response response = target.path("/calc").request().post(entity);
		
		String responseJson = response.readEntity(String.class);
		System.out.println(responseJson);
	}
}
