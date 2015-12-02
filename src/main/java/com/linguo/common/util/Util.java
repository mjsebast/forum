package com.linguo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by michael on 11/27/2015.
 */
public class Util {

    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    private Util() {
    }

    public static Double stringToDouble(String data) {
        return stringToDouble(data, null);
    }

    public static Double stringToDouble(String data, Double defaultValue) {
        Double convertedValue = defaultValue;

        if (data != null) {
            try {
                convertedValue = Double.parseDouble(data);
            } catch (NumberFormatException e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Invalid Double value passed in String: [" + data + "]", e);
                }
            }
        }

        return convertedValue;
    }

    public static Integer stringToInteger(String data) {
        return stringToInteger(data, null);
    }

    public static Integer stringToInteger(String data, Integer defaultValue) {
        Integer convertedValue = defaultValue;

        if (data != null) {
            try {
                convertedValue = Integer.parseInt(data);
            } catch (NumberFormatException e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Invalid Integer value passed in String: [" + data + "]", e);
                }
            }
        }

        return convertedValue;
    }

    public static Long stringToLong(String data) {
        return stringToLong(data, null);
    }

    public static Long stringToLong(String data, Long defaultValue) {
        Long convertedValue = defaultValue;

        if (data != null) {
            try {
                convertedValue = Long.parseLong(data);
            } catch (NumberFormatException e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Invalid Long value passed in String: [" + data + "]", e);
                }
            }
        }

        return convertedValue;
    }
}
