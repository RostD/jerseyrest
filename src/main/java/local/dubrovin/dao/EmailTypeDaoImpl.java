package local.dubrovin.dao;

import local.dubrovin.models.EmailType;
import local.dubrovin.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmailTypeDaoImpl implements local.dubrovin.dao.EmailTypeDao {

    @Override
    public EmailType findById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            EmailType type = session.get(EmailType.class, id);
            if (type == null) {
                throw new NotFoundException("Email-type not found");
            }
            return type;
        }
    }

    @Override
    public void save(EmailType emailType) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(emailType);
            transaction.commit();
        }
    }

    @Override
    public void update(EmailType emailType) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(emailType);
            transaction.commit();
        }
    }

    @Override
    public void delete(EmailType emailType) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(emailType);
            transaction.commit();
        }
    }

    @Override
    public List<EmailType> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            return (List<EmailType>) session.createQuery("From EmailType").list();
        }
    }
}
