package spring.model.dictionaries;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Саша on 07.01.2017.
 */
@Entity
@Table(name="periodservice")
public class PeriodService implements Serializable {
    @Id
    @GenericGenerator(name="inc" , strategy="increment")
    @GeneratedValue(generator="inc")
    @Column(name="id")
    private int id;

    @Column(name="value")
    private String value;

    public PeriodService() {
    }

    public PeriodService(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
