package org.example;

import org.example.model.Item;
import org.example.model.Person;
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
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person = session.get(Person.class, 3);
//
//            System.out.println(person);
//
//            List<Item> items = person.getItems(); //Доставать items можно только внутри транзакции, после завершения транзакции он не будет работать так как во время транзакции он будет под капотом еще один запрос направлять с Join-ами и доставать items принадлежащий этому человеку.
//
//            System.out.println(items);

//            Item item = session.get(Item.class, 5);
//            System.out.println(item);
//
//            Person person = item.getOwner();
//            System.out.println(person);
//
//            session.getTransaction().commit();

//            Person person = session.get(Person.class, 2);
//            Item newItem = new Item("Item from Hibernate", person);
//            person.getItems().add(newItem);
//            session.save(newItem);

//            Person person = new Person("Test person", 30);
//            Item newItem = new Item("Item from Hibernate2", person);
//
//            person.setItems(new ArrayList<Item>(Collections.singletonList(newItem)));
//
//            session.save(person);
//            session.save(newItem);
            //Сохранить newItem без сохранения person не получиться, так как если нового человека не будет в таблице Person, он не сможет поставить в person_id id нового созданного человека.

//            Person person = session.get(Person.class, 3);
//            List<Item> items = person.getItems();
//
//            for (Item item : items){
//                session.remove(item);
//            }
//
//            //Не порождает SQL запрос, но необходимо для того, чтобы в кэше все было верно
//            person.getItems().clear();


//            Person person = session.get(Person.class, 2);
//
//            session.remove(person);
//
//            person.getItems().forEach(i -> i.setOwner(null));

//            Person person = session.get(Person.class, 4);
//            Item item = session.get(Item.class, 1);
//
//            item.getOwner().getItems().remove(item);
//            item.setOwner(person);
//            person.getItems().add(item);

            //Cascading Hibernate

            Person person = new Person("Test cascading", 30);

            Item item1 = new Item("Test cascading item1");
            Item item2 = new Item("Test cascading item2");
            Item item3 = new Item("Test cascading item3");

            person.addItem(item1);
            person.addItem(item2);
            person.addItem(item3);

            session.save(person);

            session.getTransaction().commit();
        }
        finally {
            sessionFactory.close();
        }
    }
}
