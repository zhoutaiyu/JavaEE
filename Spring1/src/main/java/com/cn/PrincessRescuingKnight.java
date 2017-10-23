package com.cn;

/**
 * Created by 周太宇 on 2017/9/25.
 */
public class PrincessRescuingKnight implements Knight {
    private Quest quest;

    public PrincessRescuingKnight(Quest quest) {
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
