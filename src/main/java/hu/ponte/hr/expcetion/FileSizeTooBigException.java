package hu.ponte.hr.expcetion;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class FileSizeTooBigException extends AbstractThrowableProblem {
    public FileSizeTooBigException(long currentFileSizeInByte, long limitInMegaByte) {
        super(URI.create("file/too-big-file"),
                "File size is too big",
                Status.NOT_ACCEPTABLE,
                String.format("This size of the file was: %d", currentFileSizeInByte) + " byte." +
                        "Max size is: " + limitInMegaByte * 1024 * 1024 +" byte");
    }
}
