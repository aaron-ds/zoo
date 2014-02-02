package com.whiteleys.zoo.dao.hibernate;

import com.whiteleys.zoo.dao.AnimalDao;
import com.whiteleys.zoo.domain.Animal;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class HibernateAnimalDao extends HibernateDaoSupport implements AnimalDao {


    @SuppressWarnings("unchecked")
    public List<Animal> findAll() {
        return getHibernateTemplate().loadAll(Animal.class);
    }
    
    @SuppressWarnings("unchecked")
    public Animal find(Long id) {
    	List<Animal> animals = getHibernateTemplate().find("FROM Animal WHERE id = ?", id);
    	return (animals != null && animals.size() == 1) ? animals.get(0) : null;
    }
}