package pojos.GorestPojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestDataPojo {

    // 1) Tüm keyler için private variable' lar olusturuyoruz
        private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;

    //    2) Tüm parametrelerle  ve parametresiz constructor' larimizi olsturuyoruz
    public GoRestDataPojo() {
    }

    public GoRestDataPojo(Integer id, String name, String email, String gender, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }
    //    3)  Getters ve Setters' larimizi olusturuyoruz
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //    4) toString() methodumuzu olusturuyoruz.

    @Override
    public String toString() {
        return "GoRestDataPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
