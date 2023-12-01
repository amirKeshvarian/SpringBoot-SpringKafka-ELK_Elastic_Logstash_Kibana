package org.company.project.api;

import org.company.project.model.service.NextSequence;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Scope("singleton")
@RequestMapping("/sequenceGenerator")
public class SequenceAPI {
    private final NextSequence nextSequence;

    public SequenceAPI(NextSequence nextSequence) {
        this.nextSequence = nextSequence;
    }

    @GetMapping("/nextVal.do")
    public ResponseEntity<Long> nextVal () throws SQLException {
        return ResponseEntity.status(HttpStatus.OK).body(nextSequence.nextSequence());
    }
}
