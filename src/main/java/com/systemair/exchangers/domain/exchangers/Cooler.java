package com.systemair.exchangers.domain.exchangers;

import lombok.Getter;
import com.systemair.exchangers.domain.Process;
@Getter
public abstract class Cooler extends Exchanger {
    protected final Process process = Process.COOL;
    public Cooler() {
        super();
    }
}
