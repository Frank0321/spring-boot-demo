package tw.com.softleader.springbootdemo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table (name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String empId;
}
