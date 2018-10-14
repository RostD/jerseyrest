package local.dubrovin.services;

import local.dubrovin.dao.BookDao;
import local.dubrovin.dao.BookDaoImpl;
import local.dubrovin.models.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDaoImpl();

    public Book find(Integer id) {
        return this.bookDao.findById(id);
    }

    public void save(Book book) {
        this.bookDao.save(book);
    }

    public void update(Integer bookId, Book book) {
        this.bookDao.update(bookId, book);
    }

    public void delete(Integer bookId) {
        this.bookDao.delete(bookId);
    }

    public List<Book> findAll() {
        return this.bookDao.findAll();
    }
}
