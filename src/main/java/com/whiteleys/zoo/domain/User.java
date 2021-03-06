package com.whiteleys.zoo.domain;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.*;

import com.whiteleys.zoo.domain.Sex;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.io.Serializable;

/**
 * A registered user of the system. 
 */
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 4567532505179465929L;

	private Long userId;
    private String username;
    private String password;
    private transient String password2;
    private transient Integer dobDay;
    private transient Integer dobMonth;
    private transient Integer dobYear;
    private Date dateOfBirth;
    private String postcode;
    private Sex sex;
    private List<Animal> favouriteAnimals;


    public Integer getDobDay() {
        return dobDay;
    }

    public void setDobDay(Integer dobDay) {
        this.dobDay = dobDay;
    }

    public Integer getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(Integer dobMonth) {
        this.dobMonth = dobMonth;
    }

    public Integer getDobYear() {
        return dobYear;
    }

    public void setDobYear(Integer dobYear) {
        this.dobYear = dobYear;
    }




    // Default Constructor
    public User() {

    }

    /**
     * @return the unique id of this user
     */
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    public Long getUserId() {
        return userId;
    }

    /**
     * @return the unique username of this user
     */
    @Column(name="username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    /**
     * @return the user's password
     */
    @Column(name="password", nullable = false)
    public String getPassword() {
        return password;
    }

    /**
     * @return the user's date of birth
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dateofbirth", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return the user's sex
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name="sex", nullable = false, length = 1)
    public Sex getSex() {
        return sex;
    }

    /**
     * @return the user's postcode
     */
    @Column(name="postcode", nullable = false)
    public String getPostcode() {
        return postcode;
    }
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "USER_ANIMAL", 
    			joinColumns = { @JoinColumn(name = "USER_ID") }, 
    			inverseJoinColumns = { @JoinColumn(name = "ANIMAL_ID") })
    public List<Animal> getFavouriteAnimals() {
    	return favouriteAnimals;
    }



    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public void setFavouriteAnimals(List<Animal> animals) {
    	favouriteAnimals = animals;
    }
}
