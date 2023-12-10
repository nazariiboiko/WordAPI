package net.nazariiboiko.wordapi.util;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import net.nazariiboiko.wordapi.entity.WordEntity;
import net.nazariiboiko.wordapi.model.WordFilterModel;

import java.util.ArrayList;
import java.util.List;

public class WordFilterBuilder {
    public static List<WordEntity> buildQuery(WordFilterModel filter, EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WordEntity> criteriaQuery = criteriaBuilder.createQuery(WordEntity.class);
        Root<WordEntity> root = criteriaQuery.from(WordEntity.class);

        Predicate finalPredicate = getPredicates(filter, criteriaBuilder, root);
        criteriaQuery.where(finalPredicate);

        List<WordEntity> resultList = entityManager
                .createQuery(criteriaQuery)
                .getResultList();
        return resultList;
    }

    private static Predicate getPredicates(WordFilterModel filter, CriteriaBuilder criteriaBuilder, Root root) {
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.isNotEmpty(filter.getKeyword()) && StringUtils.isNotBlank(filter.getKeyword())) {
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + filter.getKeyword() + "%");
            predicates.add(namePredicate);
        }

        if(filter.getLevel() != null && !filter.getLevel().isEmpty()) {
            List<Predicate> levelPredicates = new ArrayList<>();
            for(String level : filter.getLevel()) {
                String f1 = "%" + level.toLowerCase() + "%";
                levelPredicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("level")), f1
                ));
            }
            predicates.add(criteriaBuilder.or(levelPredicates.toArray(new Predicate[0])));
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        return finalPredicate;
    }
}
