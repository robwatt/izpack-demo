package org.raw.izpack;

import com.izforge.izpack.panels.process.AbstractUIProcessHandler;

import java.util.Arrays;

public class UpgradeProcessHandler {

    public void run(AbstractUIProcessHandler handler, String[] args) {
        Arrays.stream(args).forEach(arg -> handler.logOutput(arg, false));
        handler.finishProcess();
    }
}
