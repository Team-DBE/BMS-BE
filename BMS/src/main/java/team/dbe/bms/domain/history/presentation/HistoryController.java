package team.dbe.bms.domain.history.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.dbe.bms.domain.history.domain.History;
import team.dbe.bms.domain.history.service.HistoryGetService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/histories")
public class HistoryController {
    private final HistoryGetService historyGetService;

    @GetMapping("/all/{deviceId}")
    public List<History> getHistoryGetService(@PathVariable Long deviceId) {
        return historyGetService.getAllHistory(deviceId);
    }
}
