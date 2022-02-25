package com.systemair.exchangers.domain.exchangers;

import com.systemair.exchangers.domain.Process;
import lombok.Getter;

@Getter
public abstract class Heater extends Exchanger {
    protected final Process process = Process.HEAT;

    public Heater() {
        super();
    }
}
