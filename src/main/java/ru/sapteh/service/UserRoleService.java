package ru.sapteh.service;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserRoleService implements Dao<User, Integer> {
    private final SessionFactory factory;

    public UserRoleService(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User read(Integer id) {
        try (Session session =factory.openSession()){
            User result = session.get(User.class,id);
            if(result !=null){
                Hibernate.initialize((result.getUserRoles()));
            }
        }
        return null;
    }

    @Override
    public List<User> findByAll() {
        try (Session session = factory.openSession()){
            Query<User> result = session.createQuery("FROM User",User.class);
            return  result.list();
        }

    }

    @Override
    public void create(User user) {
        try
            (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }


    }

    @Override
    public void update(User user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }


    }

    @Override
    public void delete(User user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}
