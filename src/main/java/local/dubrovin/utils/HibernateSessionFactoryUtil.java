package local.dubrovin.utils;

import local.dubrovin.models.Book;
import local.dubrovin.models.EmailType;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory factory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getFactory() {
        if (factory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(EmailType.class);
            configuration.addAnnotatedClass(Book.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            factory = configuration.buildSessionFactory(builder.build());
        }

        return factory;
    }
}
