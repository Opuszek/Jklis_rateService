package rate.application.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DateUtilities {

    @Value("${date.range}")
    private int range;

    public List<LocalDate> getDates() {
        return getFirstDaysOfMonths(LocalDate.now().minusDays(range), LocalDate.now());
    }

    private static List<LocalDate> getFirstDaysOfMonths(LocalDate startFrom, LocalDate endWith) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startFrom, endWith);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startFrom.plusDays(i))
                .filter(dt -> dt.getDayOfMonth()==1)
                .collect(Collectors.toList());
    }

}
