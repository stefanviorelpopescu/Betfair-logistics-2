package org.digitalstack.logistics.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Getter
public class CompanyInformationService {

    private LocalDate currentDate = LocalDate.of(2021, 12, 14);

    private Long profit = 0L;

    public long getCurrentDateAsMilis() {
        return currentDate.toEpochDay() * 24 * 3_600 * 1000;
    }

    public void advanceDate() {
        currentDate = currentDate.plusDays(1);
    }

    public synchronized void addToProfit(long profitToAdd) {
        profit += profitToAdd;
    }

}
