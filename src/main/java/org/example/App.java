package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
       Configuration configuration = new Configuration().addAnnotatedClass(Person.class); //Этой конфигурации мы должны передать класс, который является нашей сущностью (класс который помечен @Entity)
        //Теперь Hibernate будет видеть этот класс Person и будет понимать что в базе есть таблица Person которая соответствует классу Person.

        //Получаем SessionFactory чтобы из него получить сессию для работы с Hibernate
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //List<Person> people = session.createQuery("FROM Person where name LIKE 'T%'").getResultList(); //HQL обратится к сущности Person, он не знает по БД.
//            session.createQuery("update Person set name = 'Test' where age < 30").executeUpdate();
            session.createQuery("delete from Person where age < 30").executeUpdate();
//            for (Person person : people) {
//                System.out.println(person);
//            }
            session.getTransaction().commit();

        }
        finally {
            sessionFactory.close();
        }
    }
}
