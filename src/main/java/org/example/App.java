package org.example;

import org.example.model.*;
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
       Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class); //Этой конфигурации мы должны передать класс, который является нашей сущностью (класс который помечен @Entity)
        //Теперь Hibernate будет видеть этот класс Person и будет понимать что в базе есть таблица Person которая соответствует классу Person.

        //Получаем SessionFactory чтобы из него получить сессию для работы с Hibernate
        SessionFactory sessionFactory = configuration.buildSessionFactory();


        //try with resources
        try (sessionFactory){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie.getActors());

//            Movie movie = new Movie("Reservoir Dogs", 1992);
//            Actor actor = session.get(Actor.class, 1);
//
//            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
//
//            actor.getMovies().add(movie);
//
//            session.save(movie);

            Actor actor = session.get(Actor.class, 2);
            System.out.println(actor.getMovies());

            Movie movieToRemove = actor.getMovies().get(0);

            actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor);

            session.getTransaction().commit();
        }
    }
}
