package local.dubrovin.dao;

import local.dubrovin.models.Contact;
import local.dubrovin.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    @Override
    public Contact findById(Integer bookId, Integer contactId) {
        Session session = HibernateSessionFactoryUtil.getFactory().openSession();
        Query query = session.createQuery("From Contact as c WHERE c.id=:contactId AND c.book.id=:bookId");
        query.setParameter("bookId", bookId);
        query.setParameter("contactId", contactId);
        try {

            return (Contact) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public List<Contact> findAll(Integer bookId) {
        Session session = HibernateSessionFactoryUtil.getFactory().openSession();
        Query query = session.createQuery("From Contact as c WHERE c.book.id=:bookId");
        query.setParameter("bookId", bookId);
        return (List<Contact>) query.list();
    }
}
