package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

            Person person = session.get(Person.class, 1); //Hibernate найдет человека с id=1 и сам под капотом переложит строки в объект

            System.out.println(person.getName());
            System.out.println(person.getAge());

            session.getTransaction().commit();
        }
        finally {
            sessionFactory.close();
        }
    }
}
