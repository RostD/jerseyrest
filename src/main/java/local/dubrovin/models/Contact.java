package local.dubrovin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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
    @Getter
    @Setter
    private String name;

    @Column(name = "surname")
    @Getter
    @Setter
    private String surname;

    @Column(name = "address")
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
