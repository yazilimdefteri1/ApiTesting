package pojos.HerokuappPojos;

import base_urls.HerOkuAppBaseUrl;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {

    // 1) Tüm keyler için private variable' lar olusturuyoruz
    private  String checkin;
    private  String checkout;

    //  2) Tüm parametrelerle  ve parametresiz constructor' larimizi olsturuyoruz

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {
    }

    // 3)  Getters ve Setters' larimizi olusturuyoruz

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 4) toString() methodumuzu olusturuyoruz.

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
