package local.dubrovin.models;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

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
    private String name;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }
}
