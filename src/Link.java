import java.time.Duration;
import java.time.LocalDateTime;

public class Link {
    private String url;
    private String shortLink;
    private int clickLimit;
    private int clickUsed = 0;
    private LocalDateTime timeExpire;

    public Link(String url, String shortLink, int clickLimit, int timeLimit) {
        this.url = url;
        this.shortLink = shortLink;
        this.clickLimit = clickLimit;
        this.timeExpire = LocalDateTime.now().plusMinutes(timeLimit);
    }

    public void setClickLimit(int newClick) {
            this.clickLimit = newClick;
    }

    public String useUrl() {
        clickUsed++;
        return url;
    }

    public int getRemainedClicks() {
        return clickLimit - clickUsed;
    }

    public boolean timeIsNotExpired() {
        return timeExpire.isAfter(LocalDateTime.now());
    }

    public boolean validation() {
        return getRemainedClicks() > 0 && timeExpire.isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        String str = "";
        Duration duration = Duration.between(LocalDateTime.now(),timeExpire);
        if (validation()) str = " ВАЛИДНА";
        if (getRemainedClicks() <= 0) str = " ЗАБЛОКИРОВАНА";
        if (!timeIsNotExpired()) str = " будет УДАЛЕНА";
        return "\t- Ссылка " + shortLink + str + " для " + url + "\n\t  осталось "
                + getRemainedClicks() + " кликов" + " и " +
                Math.max(duration.toMinutes(),0) + " минут "
                + Math.max(duration.toSecondsPart(),0) + " секунд до истечения срока.";
    }
}
