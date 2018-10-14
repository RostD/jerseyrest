package local.dubrovin.dao;

import local.dubrovin.models.Book;

import java.util.List;

public interface BookDao {
    public Book findById(Integer id);

    public void save(Book book);

    public void update(Integer bookId, Book emailType);

    public void delete(Integer bookId);

    public List<Book> findAll();
}
