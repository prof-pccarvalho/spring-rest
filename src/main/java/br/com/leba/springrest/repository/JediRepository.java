package br.com.leba.springrest.repository;

import java.util.List;

import br.com.leba.springrest.model.Jedi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface JediRepository extends JpaRepository<Jedi,Long> {
    List<Jedi> findByNameContainingIgnoreCase(final String name);
}
