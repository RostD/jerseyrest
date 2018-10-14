package local.dubrovin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contacts")
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name cannot be empty")
    @Length(max = 50, message = "The field must be less than 50 characters")
    @Getter
    @Setter
    private String name;

    @Column(name = "surname")
    @Length(max = 50, message = "The field must be less than 50 characters")
    @Getter
    @Setter
    private String surname;

    @Column(name = "address")
    @Length(max = 200, message = "The field must be less than 200 characters")
    @Getter
    @Setter
    private String address;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @Getter
    @Setter
    @JsonIgnore
    private Book book;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Email> emails;
}
