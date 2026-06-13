package pe.edu.tecsup.lms.admin.infraestructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.tecsup.lms.admin.infraestructure.web.dto.DLQResponse;
import pe.edu.tecsup.lms.shared.infrastructure.dlq.DeadLetterQueue;

@RestController
@RequestMapping("/api/admin/dlq")
@RequiredArgsConstructor
public class DLQController {

    private final DeadLetterQueue deadLetterQueue;

    @GetMapping
    public ResponseEntity<DLQResponse> getFailedEvents() {

        DLQResponse response
                = DLQResponse.builder()
                .failedEvents(this.deadLetterQueue.getFailedEvents())
                .build();

        return ResponseEntity.ok(response);
    }

}