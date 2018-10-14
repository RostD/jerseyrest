package local.dubrovin.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "books")
@XmlRootElement
@Data
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name is cannot be empty")
    @Length(max = 20, message = "The field must be less than 20 characters")
    private String name;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Contact> contacts;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }
}
