package local.dubrovin.dao;

import local.dubrovin.models.Book;
import local.dubrovin.models.Contact;
import local.dubrovin.services.BookService;
import local.dubrovin.utils.HibernateSessionFactoryUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Iterator;
import java.util.List;

public class ContactDaoImpl extends AbstractDao implements ContactDao {
    @Override
    public Contact findById(Integer bookId, Integer contactId) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Query query = session.createQuery("From Contact as c WHERE c.id=:contactId AND c.book.id=:bookId");
            query.setParameter("bookId", bookId);
            query.setParameter("contactId", contactId);
            Contact contact = (Contact) query.getSingleResult();
            initRelations(contact);
            return contact;
        } catch (NoResultException e) {
            throw new NotFoundException("Contact not found");
        }
    }

    @Override
    public void save(Integer bookId, Contact contact) {

        Book book = new BookService().find(bookId);
        contact.setBook(book);
        this.validateModel(contact);

        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(contact);
            transaction.commit();
        }
    }

    @Override
    public void update(Integer bookId, Integer contactId, Contact contact) {
        Contact oldContact = this.findById(bookId, contactId);
        contact.setBook(oldContact.getBook());
        this.validateModel(contact);
        contact.setId(oldContact.getId());
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(contact);
            transaction.commit();
        }
    }

    @Override
    public void delete(Integer bookId, Integer contactId) {
        Contact contact = this.findById(bookId, contactId);
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(contact);
            transaction.commit();
        }
    }

    @Override
    public List<Contact> findAll(Integer bookId) {
        Book book = new BookService().find(bookId);
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Query query = session.createQuery("From Contact as c WHERE c.book.id=:bookId");
            query.setParameter("bookId", book.getId());
            List<Contact> list = (List<Contact>) query.list();

            Iterator<Contact> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.initRelations(iterator.next());
            }
            return list;
        }
    }

    private void initRelations(Contact contact) {
        Hibernate.initialize(contact.getEmails());
    }
}
