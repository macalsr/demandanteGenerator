package com.next.demandanteGenerator.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T, F> extends JpaRepository<T, F> {

    default Page<T> pageableList(Pageable pageable) {

        return this.findAll(pageable);

    }

}
