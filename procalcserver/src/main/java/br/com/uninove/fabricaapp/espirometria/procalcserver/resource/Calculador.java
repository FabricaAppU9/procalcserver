package br.com.uninove.fabricaapp.espirometria.procalcserver.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.uninove.fabricaapp.espirometria.procalcserver.Calculo;
import br.com.uninove.fabricaapp.espirometria.procalcserver.models.Pessoa;
import br.com.uninove.fabricaapp.espirometria.procalcserver.models.Resultado;

@Path("/calc")
public class Calculador {

	@POST
	public Response calcula(String pessoaJson){
		Pessoa pessoa = new Gson().fromJson(pessoaJson, Pessoa.class);
		Calculo calculador = new Calculo();
		Resultado resultadoCalc = calculador.calcular(pessoa);
		return Response.ok(resultadoCalc.toJson()).build();
	}
}
