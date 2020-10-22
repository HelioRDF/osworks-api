package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private ClienteRepository clienteRepository;

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();

	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@GetMapping()
	public List<Cliente> listar() {
		return clienteRepository.findAll();

	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());

		}

		return ResponseEntity.notFound().build();

	}

	/*
	 * @GetMapping("/clientes") public List<Cliente> listar() { return
	 * manager.createQuery("from Cliente", Cliente.class).getResultList(); }
	 * 
	 * 
	 * @GetMapping("/clientes") public List<Cliente> listar() { var cliente1 = new
	 * Cliente(); cliente1.setId(1l); cliente1.setNome("Helio Franca");
	 * cliente1.setEmail("heliordf@hotmail.com");
	 * cliente1.setTelefone("(11) 9 7564 0573");
	 * 
	 * var cliente2 = new Cliente(); cliente2.setId(2l);
	 * cliente2.setNome("Aline Franca"); cliente2.setEmail("aline@hotmail.com");
	 * cliente2.setTelefone("(11) 9 9999 0573");
	 * 
	 * return Arrays.asList(cliente1, cliente2); }
	 */

}
