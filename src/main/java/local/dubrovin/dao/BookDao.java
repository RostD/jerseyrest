package local.dubrovin.dao;

import local.dubrovin.models.Book;

import java.util.List;

public interface BookDao {
    public Book findById(Integer id);

    public void save(Book emailType);

    public void update(Book emailType);

    public void delete(Book emailType);

    public List<Book> findAll();
}
