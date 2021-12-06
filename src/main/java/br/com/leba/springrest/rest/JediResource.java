package br.com.leba.springrest.rest;

import java.util.List;

import javax.validation.Valid;


import br.com.leba.springrest.model.Jedi;
import br.com.leba.springrest.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class JediResource {
    @Autowired
    private JediService service;

    //lista os nomes cadastrados
    //consulta por http://localhost:8080/api/jedi
    @GetMapping("/api/jedi")  //como ja tem o metodo /jedi no JediController, usamos o /api/jedi aqui
    public List<Jedi> getAllJedi() {

        return service.findAll();

    }

    //consultar um recurso especifico --> consulta por http://localhost:8080/api/jedi/1 ou Get: no postman
    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id) {
        final Jedi jedi = service.findById(id);

        return ResponseEntity.ok(jedi);
    }

    //no postman ao usar o metod post tem q passar um body json
    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody Jedi jedi){

        return service.save(jedi);
    }

    //no postman fazer o put assim: http://localhost:8080/api/jedi/3 e colcar o json com as alterações
    //{
    //    "name":"LebaOne",
    //    "lastName":"Carvalho"
    //}
    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi dto) {

        final Jedi jedi = service.update(id, dto);

        return ResponseEntity.ok(jedi);

    }

    @DeleteMapping("/api/jedi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
