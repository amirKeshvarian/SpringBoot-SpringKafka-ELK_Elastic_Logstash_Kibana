package org.company.project.model.repository;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class MyGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {try {
        return ClientWebSocket.sendRequestByGet("http://localhost:9909/client/nextVal.do");
    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
    }
}
