package com.bowling.model.validator;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.bowling.model.Shot;

@Component
@ConfigurationProperties(prefix = "shot")
@PropertySource("classpath:bowling.properties")
public class ShotValidator implements Validator<Shot> {

	// this properties are injected from the property file.
	private Integer value_max;
	private Integer value_min;
	private String value_foul;
	private String value_spare;
	private String value_strike;

	@Override
	public boolean validate(Shot s) {

		if (s == null)
			return false;

		String shot = s.getShot();

		if (shot == null || shot == "")
			return false;

		if (NumberUtils.isDigits(shot)) {
			Integer shotScore = s.getShotScore();
			if (!(shotScore >= value_min && shotScore <= value_max))
				return false;
		}else {
			if (!(value_foul.equalsIgnoreCase(shot) && (s.getFoul().equals(Boolean.TRUE))))  {
				return false;
			}else {
				if (!s.getFoul() && (!value_spare.equals(shot) || !value_strike.equalsIgnoreCase(shot)))
					return false;
			}
		}

		return true;
	}

	public Integer getValue_max() {
		return value_max;
	}

	public void setValue_max(Integer value_max) {
		this.value_max = value_max;
	}

	public Integer getValue_min() {
		return value_min;
	}

	public void setValue_min(Integer value_min) {
		this.value_min = value_min;
	}

	public String getValue_foul() {
		return value_foul;
	}

	public void setValue_foul(String value_foul) {
		this.value_foul = value_foul;
	}

	public String getValue_strike() {
		return value_strike;
	}

	public void setValue_strike(String value_strike) {
		this.value_strike = value_strike;
	}

	public String getValue_spare() {
		return value_spare;
	}

	public void setValue_spare(String value_spare) {
		this.value_spare = value_spare;
	}
}
