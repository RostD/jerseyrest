package local.dubrovin.models;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "books")
@XmlRootElement
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name is cannot be empty")
    @Length(max = 20, message = "The field must be less than 20 characters")
    private String name;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<Contact> contacts;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }
}
