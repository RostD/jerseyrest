package local.dubrovin.dao;

import local.dubrovin.models.Book;
import local.dubrovin.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookDaoImpl extends AbstractDao implements BookDao {
    @Override
    public Book findById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Book book = session.get(Book.class, id);
            session.close();
            if (book == null) {
                throw new NotFoundException("Book not found");
            }
            return book;
        }
    }

    @Override
    public void save(Book book) {
        this.validateModel(book);
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        }
    }

    @Override
    public void update(Integer bookId, Book book) {
        Book oldBook = this.findById(bookId);
        this.validateModel(book);
        book.setId(oldBook.getId());
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        }
    }

    @Override
    public void delete(Integer bookId) {
        Book book = this.findById(bookId);
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            return (List<Book>) session.createQuery("From Book").list();
        }
    }
}
