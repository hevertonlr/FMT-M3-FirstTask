package com.lab365.app.fmtm3firsttask.infra.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.empty();
    }
}
