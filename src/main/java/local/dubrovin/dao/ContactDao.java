package local.dubrovin.dao;

import local.dubrovin.models.Contact;

import java.util.List;

public interface ContactDao {
    public Contact findById(Integer bookId, Integer contactId);

    public void save(Integer bookId, Contact contact);

    public void update(Contact contact);

    public void delete(Contact contact);

    public List<Contact> findAll(Integer bookId);
}
