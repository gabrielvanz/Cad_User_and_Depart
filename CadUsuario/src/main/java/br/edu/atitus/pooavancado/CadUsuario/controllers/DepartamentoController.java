package br.edu.atitus.pooavancado.CadUsuario.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Departamento;
import br.edu.atitus.pooavancado.CadUsuario.services.DepartamentoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/departamentos")
public class DepartamentoController {

	
	@Autowired
	private DepartamentoService departamentoService;
	
	private ResponseEntity<Object> salvar(Departamento departamento) {
		try {
			departamentoService.save(departamento);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(departamento);
	}
	
	@PostMapping
	public ResponseEntity<Object> postDepartamento(@RequestBody Departamento departamento) {
		return salvar(departamento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putDepartamentos(@PathVariable long id, @RequestBody Departamento departamento){
		departamento.setId(id);
		return salvar(departamento);
	}
	
	@GetMapping()
	public ResponseEntity<Object> getDepartamentos(@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam String nome) {
		Page<Departamento> departamento = departamentoService.findByNome(pageable, nome);
		return ResponseEntity.status(HttpStatus.OK).body(departamento);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getDepartamentobyId(@PathVariable long id) {
		Optional<Departamento> departamento = departamentoService.findById(id);
		if (departamento.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não encontrado com o Id " + id);
		else
			return ResponseEntity.status(HttpStatus.OK).body(departamento);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDepartamento(@PathVariable long id) {
		departamentoService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Departamento com o Id " + id + " deletado com sucesso!");
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Object> alterarEmail(@PathVariable long id, @RequestBody String email) {
		departamentoService.alteraEmailById(id, email);
		return ResponseEntity.status(HttpStatus.OK).body("Email do Id " + id + " alterado para " + email);
	}
	
}
