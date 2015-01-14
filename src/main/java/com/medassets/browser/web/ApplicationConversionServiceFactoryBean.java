package com.medassets.browser.web;

import com.medassets.browser.Timer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	public Converter<Timer, String> getTimerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.medassets.browser.Timer, java.lang.String>() {
            public String convert(Timer timer) {
                return new StringBuilder().append(timer.getMessage()).toString();
            }
        };
    }

	public Converter<Long, Timer> getIdToTimerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.medassets.browser.Timer>() {
            public com.medassets.browser.Timer convert(java.lang.Long id) {
                return Timer.findTimer(id);
            }
        };
    }

	public Converter<String, Timer> getStringToTimerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.medassets.browser.Timer>() {
            public com.medassets.browser.Timer convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Timer.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getTimerToStringConverter());
        registry.addConverter(getIdToTimerConverter());
        registry.addConverter(getStringToTimerConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
