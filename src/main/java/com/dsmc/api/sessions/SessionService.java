package com.dsmc.api.sessions;

import com.dsmc.data.tables.pojos.InstructionSession;
import com.dsmc.data.tables.pojos.Student;
import com.dsmc.data.tables.records.InstructionSessionRecord;
import com.google.common.base.CaseFormat;
import org.jooq.DSLContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SessionService {
    private final DSLContext context;
    private Collection<String> nonUpdatableFields = Arrays.asList("id", "companyId", "createdOn");

    public SessionService(DSLContext context) {
        this.context = context;
    }

    public List<InstructionSession> getSessions(Integer companyId) {
        com.dsmc.data.tables.InstructionSession s = com.dsmc.data.tables.InstructionSession.INSTRUCTION_SESSION.as("s");
        return context.selectFrom(s)
                .where(s.COMPANY_ID.equal(companyId))
                .fetch()
                .into(InstructionSession.class);
    }

    public Student getInstructionSessionById(Integer companyId, Integer instructionSessionId) {
        com.dsmc.data.tables.InstructionSession s = com.dsmc.data.tables.InstructionSession.INSTRUCTION_SESSION.as("s");
        return context.selectFrom(s)
                .where(s.COMPANY_ID.equal(companyId).and(s.ID.equal(instructionSessionId)))
                .fetchOne()
                .into(Student.class);
    }

    public void create(Integer companyId, InstructionSession instructionSession) {
        InstructionSessionRecord instructionSessionRecord = context.newRecord(com.dsmc.data.tables.InstructionSession.INSTRUCTION_SESSION, instructionSession);
        instructionSessionRecord.setCompanyId(companyId);
        instructionSessionRecord.store();
    }

    public void update(Integer companyId, Integer instructionSessionId, Map<String, ?> updates) {
        com.dsmc.data.tables.InstructionSession s = com.dsmc.data.tables.InstructionSession.INSTRUCTION_SESSION.as("s");
        InstructionSessionRecord instructionSessionRecord = context.selectFrom(s)
                .where(s.COMPANY_ID.equal(companyId).and(s.ID.equal(instructionSessionId)))
                .fetchOne();
        if (instructionSessionRecord == null) {
            throw new RuntimeException("Attempt to update unknown record");
        }
        Map<String, Object> recordUpdates = updates.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> !nonUpdatableFields.contains(entry.getKey()))
                .collect(Collectors.toMap(entry -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), Map.Entry::getValue));
        instructionSessionRecord.fromMap(recordUpdates);
        instructionSessionRecord.store();
    }
}
