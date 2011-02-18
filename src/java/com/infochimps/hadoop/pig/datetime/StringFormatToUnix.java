package com.infochimps.hadoop.pig.datetime;

import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.DataType;
import org.apache.pig.impl.util.WrappedIOException;
import org.apache.pig.impl.logicalLayer.schema.Schema;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StringFormatToUnix extends EvalFunc<Long> {
    
    public Long exec(Tuple input) throws IOException {
        if (input == null || input.size() < 1)
            return null;

        // Set the time to default or the output is in UTC
        DateTimeZone.setDefault(DateTimeZone.UTC);
        String format            = "YYYYMMddHHmmss";
        String date              = input.get(0).toString();
        DateTimeFormatter parser = DateTimeFormat.forPattern(format);
        DateTime result          = parser.parseDateTime(date);
        return result.getMillis();
    }
}
