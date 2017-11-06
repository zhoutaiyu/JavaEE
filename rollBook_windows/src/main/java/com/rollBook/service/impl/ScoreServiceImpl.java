package com.rollBook.service.impl;

import com.rollBook.mapper.ScoreMapper;
import com.rollBook.po.Proportion;
import com.rollBook.po.Score;
import com.rollBook.po.ScoreExample;
import com.rollBook.po.ScoreExample.Criteria;
import com.rollBook.po.Teacher;
import com.rollBook.povo.ScoreAndProVo;
import com.rollBook.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午11:00:14
 **/
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 通过老师id查询score表，查到score记录。
     *
     * @return
     * @throws Exception
     */

    public Score selectScoreByTid(Long id) throws Exception {
        ScoreExample example = new ScoreExample();
        Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(id);
        List<Score> list = scoreMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 修改扣分值 步骤：1，查找session中老师中设置分数那条记录 2.若存在，即修改 3.若不存在，则增加
     */
    @Override
    public String updateReduceMarks(ScoreAndProVo scoreAndProVo, HttpSession session) throws Exception {
        Teacher teacher = (Teacher) session.getAttribute("activeUser");
        Score score = scoreAndProVo.getScore();
        Proportion proportion = scoreAndProVo.getProportion();
        int selectRadio=proportion.getSradio();
        Score result = new Score();
        Score DateScore = this.selectScoreByTid(teacher.getId());
        if (DateScore != null) {
            DateScore.setModTime(new Date());
            DateScore.setAbsent(score.getAbsent());
            DateScore.setEarly(score.getEarly());
            DateScore.setLate(score.getLate());
            DateScore.setPlay(score.getPlay());
            DateScore.setAssignment(score.getAssignment());
            DateScore.setExperiment(score.getExperiment());
            DateScore.setQuiz(score.getQuiz());
            //==1，计入课堂表现，==2，计入其他成绩
            if (selectRadio == 1) {
                DateScore.setTotal(100 - proportion.getPerformance());
            } else {
                DateScore.setTotal(100 - proportion.getOther());
            }
            scoreMapper.updateByPrimaryKeySelective(DateScore);
        } else {
            result.setTid(teacher.getId());
            result.setCreateTime(new Date());
            result.setModTime(new Date());
            result.setAbsent(score.getAbsent());
            result.setEarly(score.getEarly());
            result.setLate(score.getLate());
            result.setPlay(score.getPlay());
            result.setIsDel(false);
            result.setAssignment(score.getAssignment());
            result.setExperiment(score.getExperiment());
            result.setQuiz(score.getQuiz());
            //==1，计入课堂表现，==2，计入其他成绩
            if (selectRadio == 1) {
                result.setTotal(100 - proportion.getPerformance());
            } else {
                result.setTotal(100 - proportion.getOther());
            }
            scoreMapper.insert(result);
        }
        return "修改成功";
    }

    /**
     * 根据id查找记录
     */
    @Override
    public Score selectByPrimaryKey(Integer id) throws Exception {
        // 出错
        // Score score = scoreMapper.selectByPrimaryKey(id);
        // 原因：early为mysql关键字，查询失败，报错，改字段后完美运行。
        ScoreExample example = new ScoreExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Score> list = scoreMapper.selectByExample(example);
        Score score = null;
        // 判断list中是否为空
        if (list != null && list.size() > 0) {
            score = list.get(0);
            return score;
        }
        return score;
    }

    /**
     * 根据session里的tid查询score
     */
    @Override
    public Score selectByTid(HttpSession session) throws Exception {
        Teacher teacher = (Teacher) session.getAttribute("activeUser");
        Score score = selectScoreByTid(teacher.getId());
        return score;
    }

    /**
     * 根据tid查询扣分标准
     *
     * @param tid
     * @return
     * @throws Exception
     */
    @Override
    public Score selectByTid(Long tid) throws Exception {
        Score score = selectScoreByTid(tid);
        return score;
    }

}
