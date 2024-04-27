package org.raw.izpack;

import com.izforge.izpack.api.event.AbstractUninstallerListener;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.List;

@Log4j2
public class UninstallListenerExample extends AbstractUninstallerListener {

    @Override
    public void beforeDelete(List<File> files) {
        log.info("Before delete");
    }
}
