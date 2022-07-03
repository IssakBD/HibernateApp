package org.example;

import org.example.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Подключаем конфигурацию из файла hibernate.properties (hibernate сам получает из него конфигурацию под капотом).
       Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class); //Этой конфигурации мы должны передать класс, который является нашей сущностью (класс который помечен @Entity)
        //Теперь Hibernate будет видеть этот класс Person и будет понимать что в базе есть таблица Person которая соответствует классу Person.

        //Получаем SessionFactory чтобы из него получить сессию для работы с Hibernate
        SessionFactory sessionFactory = configuration.buildSessionFactory();


//        //try with resources
//        try (sessionFactory){
//            Session session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//
//            Person person = session.get(Person.class, 1);
//            System.out.println("Person is got");
//
//            // Получим связанные сущности (Lazy)
//            Hibernate.initialize(person.getItems());
//
////            Item item = session.get(Item.class, 1);
////            System.out.println("Item is got");
////
////            System.out.println(item.getOwner());
//
//            session.getTransaction().commit();
//            //after commit() -> session.close()
//
//            //EAGER downloading будет работать и после сессии так как уже подгружены
//            System.out.println(person.getItems());

            //try with resources
//        try (sessionFactory){
//            Session session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//
//            Person person = session.get(Person.class, 1); //Привязан к текущей сессии
//            System.out.println("Person is got");
//
//            session.getTransaction().commit();
//            System.out.println("Session is closed");
//            //after commit() -> session.close()
//
//            //Открываем сессию и транзакцию еще раз
//            session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//
//            System.out.println("In second transaction");
//            person = (Person)session.merge(person);
//
//            Hibernate.initialize(person.getItems());
//
//            session.getTransaction().commit();
//
//            System.out.println("Out of the second session");
//            System.out.println(person.getItems());

            //try with resources
        try (sessionFactory){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 1); //Привязан к текущей сессии
            System.out.println("Person is got");

            session.getTransaction().commit();
            System.out.println("Session is closed");
            //after commit() -> session.close()

            //Открываем сессию и транзакцию еще раз
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("In second transaction");

            List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId", Item.class)
                            .setParameter("personId", person.getId()).getResultList();

            session.getTransaction().commit();

            System.out.println("Out of the second session");
            //оно не будет работать так как мы не подвязали его к person, а просто сделали hql запрос
            System.out.println(person.getItems());
        }
    }
}
