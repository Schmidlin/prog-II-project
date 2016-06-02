package ch.fhnw.project.DataHandling;

import java.io.File;
import java.io.IOException;

/**
 * Created by fabienne on 16.05.16.
 * Interface DataConverter
 */
public interface DataConverter {

    Data read(File file) throws IOException;

}
