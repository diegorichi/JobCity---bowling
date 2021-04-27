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
	private Integer valueMax;
	private Integer valueMin;
	private String valueFoul;
	private String valueSpare;
	private String valueStrike;

	@Override
	public boolean validate(Shot s) {

		if (s == null)
			return false;

		String shot = s.getShot();

		if (isNullOrEmpty(shot))
			return false;

		if (NumberUtils.isDigits(shot)) {
			Integer shotScore = s.getShotScore();
			if (!(shotScore >= valueMin && shotScore <= valueMax))
				return false;
		} else {
			if (!isfoulOrSpareOrStrike(shot, s))
				return false;

		}

		return true;
	}

	private boolean isfoulOrSpareOrStrike(String shot, Shot s) {
		boolean foul = (valueFoul.equalsIgnoreCase(shot) && (s.getFoul().equals(Boolean.TRUE)));
		boolean spareOrStrike = (!s.getFoul() && (valueSpare.equals(shot) || valueStrike.equalsIgnoreCase(shot)));

		return foul || spareOrStrike;
	}

	private boolean isNullOrEmpty(String shot) {
		return shot == null || "".equals(shot);
	}

	public Integer getValueMax() {
		return valueMax;
	}

	public void setValueMax(Integer valueMax) {
		this.valueMax = valueMax;
	}

	public Integer getValueMin() {
		return valueMin;
	}

	public void setValueMin(Integer valueMin) {
		this.valueMin = valueMin;
	}

	public String getValueFoul() {
		return valueFoul;
	}

	public void setValueFoul(String valueFoul) {
		this.valueFoul = valueFoul;
	}

	public String getValueSpare() {
		return valueSpare;
	}

	public void setValueSpare(String valueSpare) {
		this.valueSpare = valueSpare;
	}

	public String getValueStrike() {
		return valueStrike;
	}

	public void setValueStrike(String valueStrike) {
		this.valueStrike = valueStrike;
	}


}
