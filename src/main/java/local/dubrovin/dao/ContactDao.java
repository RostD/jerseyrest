package local.dubrovin.dao;

import local.dubrovin.models.Contact;

import java.util.List;

public interface ContactDao {
    public Contact findById(Integer bookId, Integer contactId);

    public void save(Integer bookId, Contact contact);

    public void update(Integer bookId, Integer contactId, Contact contact);

    public void delete(Integer bookId, Integer contactId);

    public List<Contact> findAll(Integer bookId);
}
