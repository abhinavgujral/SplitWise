package Splitwise.Entity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Getter
@Setter
public class Users{

    @Id
    private String userName ;
    private String Name ;
    private String email_address;
    private String Password;

}