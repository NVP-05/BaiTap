package ra.edu.ss15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss15.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderRepository orderRepo;

    @Override
    public BigDecimal getRevenue(String type) {
        LocalDateTime start, end;
        LocalDate now = LocalDate.now();

        switch (type) {
            case "day":
                start = now.atStartOfDay();
                end = now.plusDays(1).atStartOfDay();
                break;
            case "month":
                start = now.withDayOfMonth(1).atStartOfDay();
                end = now.plusMonths(1).withDayOfMonth(1).atStartOfDay();
                break;
            case "year":
                start = now.withDayOfYear(1).atStartOfDay();
                end = now.plusYears(1).withDayOfYear(1).atStartOfDay();
                break;
            default:
                throw new IllegalArgumentException("Type phải là day/month/year");
        }

        return orderRepo.sumRevenueBetween(start, end);
    }
}

