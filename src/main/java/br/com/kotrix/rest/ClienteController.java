package br.com.kotrix.rest;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.kotrix.model.entity.Cliente;
import br.com.kotrix.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	
	private ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvarCliente( @RequestBody Cliente cliente ) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente acharPorId(@PathVariable Integer id) {
		return clienteRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Integer id) {
		// Outro modo: clienteRepositoy.deleteById(id);
		clienteRepository
			.findById(id) // Aqui eu acho o perfil primeiro, antes de deletar ele
			.map( cliente -> {
				clienteRepository.delete(cliente);
				return Void.TYPE;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));	
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
		clienteRepository
			.findById(id)
			.map( cliente -> {
				clienteAtualizado.setId(cliente.getId());
				return clienteRepository.save(clienteAtualizado);
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	/* OUTRA FORMA DE FAZER
	 
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
		clienteRepository
			.findById(id)
			.map( cliente -> {
				cliente.setNome(clienteAtualizado.getNome())
				cliente.setCpf(clienteAtualizado.getCpf());
				retrun clienteRepository.save(cliente)
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	} 
	 * */
	
}
