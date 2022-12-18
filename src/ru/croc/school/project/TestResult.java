package ru.croc.school.project;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class TestResult {

    private LocalDateTime testCompletionDate;
    private Level level;
    private BigDecimal percentageOfCorrect;

    private User user;

    public TestResult(LocalDateTime testCompletionDate, String level, BigDecimal percentageOfCorrect, User user) throws NoSuchLevelException {
        this.testCompletionDate = testCompletionDate;
        this.level = Level.fromString(level);
        this.percentageOfCorrect = percentageOfCorrect.setScale(2, RoundingMode.UP);
        this.user = user;
    }

    @Override
    public String toString(){
        String dateString = "Date: " + testCompletionDate.getDayOfMonth() + " " + testCompletionDate.getMonth().toString() + " " +
                testCompletionDate.getYear() + ", Time: " + testCompletionDate.getHour() + "h, "+ testCompletionDate.getMinute() +"min";
        return dateString+ ", difficulty level: "+level+", percentage of correct answers: "+percentageOfCorrect;
    }

    public LocalDateTime getTestCompletionDate() {
        return testCompletionDate;
    }

    public String getLevel() {
        return level.getLevelName();
    }

    public BigDecimal getPercentageOfCorrect() {
        return percentageOfCorrect;
    }

    public User getUser() {
        return user;
    }
}
