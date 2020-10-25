/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.mediaserver.parser;

@SuppressWarnings("serial")
public class ParseException extends Exception {
    public ParseException()
    {
        super();
    }
    
    public ParseException(String exception)
    {
        super(exception);
    }
    
    public ParseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ParseException(Throwable cause)
    {
        super(cause);
    }
}
