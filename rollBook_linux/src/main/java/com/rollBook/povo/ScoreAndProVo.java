package com.rollBook.povo;

import com.rollBook.po.Proportion;
import com.rollBook.po.Score;

import java.io.Serializable;

/**
 * @author 周太宇
 * @date 2017年8月30日 下午6:40:18
 **/

public class ScoreAndProVo implements Serializable {
	private Score score;
	private Proportion proportion;

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}


	public Proportion getProportion() {
		return proportion;
	}

	public void setProportion(Proportion proportion) {
		this.proportion = proportion;
	}

}
