package com.next.demandanteGenerator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class CustomRepositoryImpl<T> implements CustomRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<T> listSearch(Map<String, String> allParams, Pageable pageable, String entity, String addFilter) {

        var jpql = new StringBuilder();
        jpql.append(" from "+entity+" as e where 1=1 ");

        var parameters = new HashMap<String, Object>();

        if (allParams != null) {
            for (String key : allParams.keySet()) {
                if (!key.equals("page") && !key.equals("size") && !key.equals("sort")) {

                    String column = key.replaceAll("_", ".");
                    String value = remove_accent(allParams.get(key).toUpperCase());

                    jpql.append(" and upper(public.remove_accent(e."+column+")) like :"+key+" ");
                    parameters.put(key, "%" + value + "%");

                }
            }
        }

        if (!addFilter.equals("")) {
            jpql.append(" and "+addFilter+" ");
        }

        if (allParams != null) {
            if (allParams.get("sort") != null) {
                jpql.append(" order by "+allParams.get("sort").replaceAll(",", " ").replaceAll("_", ".")+" ");
            } else {
                jpql.append(" order by e.id ");
            }
        }

        TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery(jpql.toString());

        parameters.forEach((key, value) -> query.setParameter(key, value));

        Page<T> page = new PageImpl<>(query.getResultList());

        if (pageable != null) {
            int totalRows = query.getResultList().size();

            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());

            page = new PageImpl<T>(query.getResultList(), pageable, totalRows);
        }


        return page;

    }

    private String remove_accent(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}
