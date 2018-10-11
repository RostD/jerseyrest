package local.dubrovin.dao;

import local.dubrovin.models.EmailType;

import java.util.List;

public interface EmailTypeDao {
    public EmailType findById(Integer id);

    public void save(EmailType emailType);

    public void update(EmailType emailType);

    public void delete(EmailType emailType);

    public List<EmailType> findAll();
}
