package com.example;


import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;



/**
 * Hibernate implementation of GenericDao
 * A typesafe implementation of CRUD and finder methods based on Hibernate and Spring AOP
 * The finders are implemented through the executeFinder method. Normally called by the FinderIntroductionInterceptor
 */
public class GenericDaoHibernateImpl<T, PK extends Serializable>{
	
    private SessionFactory sessionFactory;
    
    private final Class<T> type;
    
    
   
	public GenericDaoHibernateImpl(Class<T> type)
    {
        this.type = type;
    }



	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}



	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
    
	public Session getSession()
    {
        final boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
	
}
