package pojos.JsonPlaceHolderPojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

    /*
    1) Tüm keyler için private variable' lar olusturuyoruz
    2) Tüm parametrelerle  ve parametresiz constructor' larimizi olsturuyoruz
    3)  Getters ve Setters' larimizi olusturuyoruz
    4) toString() methodumuzu olusturuyoruz.
     */




    // 1) Tüm keyler için private variable' lar olusturuyoruz
    private int userId;
    private String title;
    private boolean completed;

   //  2) Tüm parametrelerle  ve parametresiz constructor' larimizi olsturuyoruz

    public JsonPlaceHolderPojo() {
    }

    public JsonPlaceHolderPojo(int userId, String title, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    // 3)  Getters ve Setters' larimizi olusturuyoruz

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // 4) toString() methodumuzu olusturuyoruz.
    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }


}
