package com.example.demo.Repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;

import com.example.demo.Models.Stat;


public class StatRepositoryCustomImpl implements StatRepositoryCustom {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<Object[]> getTopFive(String stField) {
        Query qry = em.createNativeQuery("select concat(p.lastname,', ',p.firstname) pname, sum(s." + stField + ") pt, t.name from  player p" +
        " left join stat s on (p.id = s.playerid)" +
        " left join coach c on ( s.coachid= c.id) left join team t on (c.teamid= t.id)" +
        " group by pname, t.name order by pt desc limit 5");
        
        return qry.getResultList();
    }

    @Override
    public List<Object[]> getButtomFive(String stField) {
        Query qry = em.createNativeQuery("select concat(p.lastname,', ',p.firstname) pname, sum(s." + stField + ") pt, t.name from  player p" +
        " left join stat s on (p.id = s.playerid)" +
        " left join coach c on ( s.coachid= c.id) left join team t on (c.teamid= t.id)" +
        " group by pname, t.name order by pt asc limit 5");

        return qry.getResultList();
    }


    public List<Stat> getProducts(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Stat> cqSql = cb.createQuery(Stat.class);
        CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
     
        Root<Stat> stat = cqCount.from(Stat.class);
        List<Predicate> predicates = new ArrayList<>();
        
        //if (id != null) {
        //    predicates.add(cb.equal(stat.get("id"), name));
        //}
        if (name != null) {
            predicates.add(cb.like(stat.get("category.name") , "%" + name + "%"));
        }
        cqSql.where(predicates.toArray(new Predicate[0]));
        cqCount.select(cb.count(stat)).where(predicates.toArray(new Predicate[0]));
        Long i = em.createQuery(cqCount).getSingleResult();
        return em.createQuery(cqSql).getResultList();
    }
    
    
}