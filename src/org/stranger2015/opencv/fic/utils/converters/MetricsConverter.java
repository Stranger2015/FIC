package org.stranger2015.opencv.fic.utils.converters;

import com.beust.jcommander.IStringConverter;
import org.stranger2015.opencv.fic.core.search.EMetrics;

/**
 *
 */
public
class MetricsConverter implements IStringConverter<EMetrics> {

    /**
     * @param metric
     * @return
     */
    @Override
    public EMetrics convert(String metric) {
        return EMetrics.valueOf(metric.toUpperCase());
    }
}
