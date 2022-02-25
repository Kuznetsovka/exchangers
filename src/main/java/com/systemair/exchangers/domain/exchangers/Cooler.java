package com.systemair.exchangers.domain.exchangers;

import com.systemair.exchangers.domain.Process;
import lombok.Getter;

@Getter
public abstract class Cooler extends Exchanger {
    protected final Process process = Process.COOL;

    public Cooler() {
        super();
    }
}
