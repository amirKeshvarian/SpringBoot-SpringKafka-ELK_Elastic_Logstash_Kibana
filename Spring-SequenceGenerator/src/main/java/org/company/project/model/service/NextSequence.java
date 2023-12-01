package org.company.project.model.service;

import org.company.project.model.repository.SequenceGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Scope("singleton")
public class NextSequence {
    private final SequenceGenerator sequenceGenerator;

    public NextSequence(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    public Long nextSequence () throws SQLException {
        return sequenceGenerator.nextVal();
    }
}
