package local.dubrovin.dao;


import local.dubrovin.models.Contact;
import local.dubrovin.models.Email;
import local.dubrovin.models.EmailType;
import local.dubrovin.services.ContactService;
import local.dubrovin.services.EmailTypeService;
import local.dubrovin.utils.HibernateSessionFactoryUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Iterator;
import java.util.List;

public class EmailDaoImpl extends AbstractDao implements EmailDao {
    @Override
    public Email findById(Integer bookId, Integer contactId, Integer emailId) {

        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Query query = session.createQuery("From Email as c WHERE c.id=:emailId AND c.contact.id=:contactId AND c.contact.book.id=:bookId");
            query.setParameter("emailId", emailId);
            query.setParameter("bookId", bookId);
            query.setParameter("contactId", contactId);
            Email email = (Email) query.getSingleResult();
            initRelations(email);
            return email;
        } catch (NoResultException e) {
            throw new NotFoundException("Email not found");
        }
    }

    private void initRelations(Email email) {
        Hibernate.initialize(email.getType());
    }

    @Override
    public void save(Integer bookId, Integer contactId, Email email) {
        Contact contact = new ContactService(bookId).findById(contactId);
        EmailType type = new EmailTypeService().find(email.getTypeId());
        this.validateModel(email);

        email.setContact(contact);
        email.setType(type);

        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(email);
            transaction.commit();
        }
    }

    @Override
    public void update(Integer bookId, Integer contactId, Integer emailId, Email email) {
        Email oldEmail = this.findById(bookId, contactId, emailId);
        email.setContact(oldEmail.getContact());
        email.setId(emailId);
        this.validateModel(email);

        if (oldEmail.getType().getId().equals(email.getTypeId())) {
            email.setType(oldEmail.getType());
        } else {
            EmailType type = new EmailTypeService().find(email.getTypeId());
            email.setType(type);
        }

        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(email);
            transaction.commit();
            this.initRelations(email);
        }
    }

    @Override
    public void delete(Integer bookId, Integer contactId, Integer emailId) {
        Email email = this.findById(bookId, contactId, emailId);

        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(email);
            transaction.commit();
        }
    }

    @Override
    public List<Email> findAll(Integer bookId, Integer contactId) {
        Contact contact = new ContactService(bookId).findById(contactId);

        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Query query = session.createQuery("From Email as c WHERE c.contact.id=:contactId AND c.contact.book.id=:bookId");
            query.setParameter("bookId", bookId);
            query.setParameter("contactId", contact.getId());
            List<Email> list = (List<Email>) query.list();
            Iterator<Email> iterator = list.iterator();
            while (iterator.hasNext()) {
                Hibernate.initialize(iterator.next().getType());
            }
            return list;
        }
    }


}
