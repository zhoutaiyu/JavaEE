package com.rollBook.service;

import com.rollBook.po.Score;
import com.rollBook.povo.ScoreAndProVo;

import javax.servlet.http.HttpSession;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午10:58:48
 **/

public interface ScoreService {
    // 修改扣分分值,设置原始得分分为2种情况
    public String updateReduceMarks(ScoreAndProVo scoreAndProVo, HttpSession session) throws Exception;

    // 根据id查找记录
    public Score selectByPrimaryKey(Integer id) throws Exception;

    // 根据tid(session)查找记录
    public Score selectByTid(HttpSession session) throws Exception;

    // 根据tid查找记录
    public Score selectByTid(Long tid) throws Exception;

}
