package br.com.treinaweb.springbootapiexemplo.Repository;

import br.com.treinaweb.springbootapiexemplo.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    void delete(Pessoa pessoa);
}
