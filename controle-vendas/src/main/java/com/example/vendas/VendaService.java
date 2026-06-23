package com.example.vendas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendaService {

    private final VendaRepository repositorio;

    @PersistenceContext
    private EntityManager em;

    public VendaService(VendaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional
    public void excluirERenumerar(Long id) {
        repositorio.deleteById(id);
        repositorio.flush();

        em.createNativeQuery("UPDATE venda SET id = id + 1000000").executeUpdate();

        em.createNativeQuery(
            "UPDATE venda v SET id = s.rn FROM " +
            "(SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM venda) s " +
            "WHERE v.id = s.id").executeUpdate();

        em.createNativeQuery(
            "SELECT setval(pg_get_serial_sequence('venda','id'), " +
            "COALESCE((SELECT MAX(id) FROM venda), 0) + 1, false)").getSingleResult();

        em.clear();
    }
}
