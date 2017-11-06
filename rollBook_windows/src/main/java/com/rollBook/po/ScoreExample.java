package com.rollBook.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Long value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Long value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Long value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Long value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Long value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Long value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Long> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Long> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Long value1, Long value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Long value1, Long value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andAbsentIsNull() {
            addCriterion("absent is null");
            return (Criteria) this;
        }

        public Criteria andAbsentIsNotNull() {
            addCriterion("absent is not null");
            return (Criteria) this;
        }

        public Criteria andAbsentEqualTo(Integer value) {
            addCriterion("absent =", value, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentNotEqualTo(Integer value) {
            addCriterion("absent <>", value, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentGreaterThan(Integer value) {
            addCriterion("absent >", value, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentGreaterThanOrEqualTo(Integer value) {
            addCriterion("absent >=", value, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentLessThan(Integer value) {
            addCriterion("absent <", value, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentLessThanOrEqualTo(Integer value) {
            addCriterion("absent <=", value, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentIn(List<Integer> values) {
            addCriterion("absent in", values, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentNotIn(List<Integer> values) {
            addCriterion("absent not in", values, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentBetween(Integer value1, Integer value2) {
            addCriterion("absent between", value1, value2, "absent");
            return (Criteria) this;
        }

        public Criteria andAbsentNotBetween(Integer value1, Integer value2) {
            addCriterion("absent not between", value1, value2, "absent");
            return (Criteria) this;
        }

        public Criteria andEarlyIsNull() {
            addCriterion("early is null");
            return (Criteria) this;
        }

        public Criteria andEarlyIsNotNull() {
            addCriterion("early is not null");
            return (Criteria) this;
        }

        public Criteria andEarlyEqualTo(Integer value) {
            addCriterion("early =", value, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyNotEqualTo(Integer value) {
            addCriterion("early <>", value, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyGreaterThan(Integer value) {
            addCriterion("early >", value, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyGreaterThanOrEqualTo(Integer value) {
            addCriterion("early >=", value, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyLessThan(Integer value) {
            addCriterion("early <", value, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyLessThanOrEqualTo(Integer value) {
            addCriterion("early <=", value, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyIn(List<Integer> values) {
            addCriterion("early in", values, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyNotIn(List<Integer> values) {
            addCriterion("early not in", values, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyBetween(Integer value1, Integer value2) {
            addCriterion("early between", value1, value2, "early");
            return (Criteria) this;
        }

        public Criteria andEarlyNotBetween(Integer value1, Integer value2) {
            addCriterion("early not between", value1, value2, "early");
            return (Criteria) this;
        }

        public Criteria andLateIsNull() {
            addCriterion("late is null");
            return (Criteria) this;
        }

        public Criteria andLateIsNotNull() {
            addCriterion("late is not null");
            return (Criteria) this;
        }

        public Criteria andLateEqualTo(Integer value) {
            addCriterion("late =", value, "late");
            return (Criteria) this;
        }

        public Criteria andLateNotEqualTo(Integer value) {
            addCriterion("late <>", value, "late");
            return (Criteria) this;
        }

        public Criteria andLateGreaterThan(Integer value) {
            addCriterion("late >", value, "late");
            return (Criteria) this;
        }

        public Criteria andLateGreaterThanOrEqualTo(Integer value) {
            addCriterion("late >=", value, "late");
            return (Criteria) this;
        }

        public Criteria andLateLessThan(Integer value) {
            addCriterion("late <", value, "late");
            return (Criteria) this;
        }

        public Criteria andLateLessThanOrEqualTo(Integer value) {
            addCriterion("late <=", value, "late");
            return (Criteria) this;
        }

        public Criteria andLateIn(List<Integer> values) {
            addCriterion("late in", values, "late");
            return (Criteria) this;
        }

        public Criteria andLateNotIn(List<Integer> values) {
            addCriterion("late not in", values, "late");
            return (Criteria) this;
        }

        public Criteria andLateBetween(Integer value1, Integer value2) {
            addCriterion("late between", value1, value2, "late");
            return (Criteria) this;
        }

        public Criteria andLateNotBetween(Integer value1, Integer value2) {
            addCriterion("late not between", value1, value2, "late");
            return (Criteria) this;
        }

        public Criteria andPlayIsNull() {
            addCriterion("play is null");
            return (Criteria) this;
        }

        public Criteria andPlayIsNotNull() {
            addCriterion("play is not null");
            return (Criteria) this;
        }

        public Criteria andPlayEqualTo(Integer value) {
            addCriterion("play =", value, "play");
            return (Criteria) this;
        }

        public Criteria andPlayNotEqualTo(Integer value) {
            addCriterion("play <>", value, "play");
            return (Criteria) this;
        }

        public Criteria andPlayGreaterThan(Integer value) {
            addCriterion("play >", value, "play");
            return (Criteria) this;
        }

        public Criteria andPlayGreaterThanOrEqualTo(Integer value) {
            addCriterion("play >=", value, "play");
            return (Criteria) this;
        }

        public Criteria andPlayLessThan(Integer value) {
            addCriterion("play <", value, "play");
            return (Criteria) this;
        }

        public Criteria andPlayLessThanOrEqualTo(Integer value) {
            addCriterion("play <=", value, "play");
            return (Criteria) this;
        }

        public Criteria andPlayIn(List<Integer> values) {
            addCriterion("play in", values, "play");
            return (Criteria) this;
        }

        public Criteria andPlayNotIn(List<Integer> values) {
            addCriterion("play not in", values, "play");
            return (Criteria) this;
        }

        public Criteria andPlayBetween(Integer value1, Integer value2) {
            addCriterion("play between", value1, value2, "play");
            return (Criteria) this;
        }

        public Criteria andPlayNotBetween(Integer value1, Integer value2) {
            addCriterion("play not between", value1, value2, "play");
            return (Criteria) this;
        }

        public Criteria andAssignmentIsNull() {
            addCriterion("assignment is null");
            return (Criteria) this;
        }

        public Criteria andAssignmentIsNotNull() {
            addCriterion("assignment is not null");
            return (Criteria) this;
        }

        public Criteria andAssignmentEqualTo(Integer value) {
            addCriterion("assignment =", value, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentNotEqualTo(Integer value) {
            addCriterion("assignment <>", value, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentGreaterThan(Integer value) {
            addCriterion("assignment >", value, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentGreaterThanOrEqualTo(Integer value) {
            addCriterion("assignment >=", value, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentLessThan(Integer value) {
            addCriterion("assignment <", value, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentLessThanOrEqualTo(Integer value) {
            addCriterion("assignment <=", value, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentIn(List<Integer> values) {
            addCriterion("assignment in", values, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentNotIn(List<Integer> values) {
            addCriterion("assignment not in", values, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentBetween(Integer value1, Integer value2) {
            addCriterion("assignment between", value1, value2, "assignment");
            return (Criteria) this;
        }

        public Criteria andAssignmentNotBetween(Integer value1, Integer value2) {
            addCriterion("assignment not between", value1, value2, "assignment");
            return (Criteria) this;
        }

        public Criteria andExperimentIsNull() {
            addCriterion("experiment is null");
            return (Criteria) this;
        }

        public Criteria andExperimentIsNotNull() {
            addCriterion("experiment is not null");
            return (Criteria) this;
        }

        public Criteria andExperimentEqualTo(Integer value) {
            addCriterion("experiment =", value, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentNotEqualTo(Integer value) {
            addCriterion("experiment <>", value, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentGreaterThan(Integer value) {
            addCriterion("experiment >", value, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentGreaterThanOrEqualTo(Integer value) {
            addCriterion("experiment >=", value, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentLessThan(Integer value) {
            addCriterion("experiment <", value, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentLessThanOrEqualTo(Integer value) {
            addCriterion("experiment <=", value, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentIn(List<Integer> values) {
            addCriterion("experiment in", values, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentNotIn(List<Integer> values) {
            addCriterion("experiment not in", values, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentBetween(Integer value1, Integer value2) {
            addCriterion("experiment between", value1, value2, "experiment");
            return (Criteria) this;
        }

        public Criteria andExperimentNotBetween(Integer value1, Integer value2) {
            addCriterion("experiment not between", value1, value2, "experiment");
            return (Criteria) this;
        }

        public Criteria andQuizIsNull() {
            addCriterion("quiz is null");
            return (Criteria) this;
        }

        public Criteria andQuizIsNotNull() {
            addCriterion("quiz is not null");
            return (Criteria) this;
        }

        public Criteria andQuizEqualTo(Integer value) {
            addCriterion("quiz =", value, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizNotEqualTo(Integer value) {
            addCriterion("quiz <>", value, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizGreaterThan(Integer value) {
            addCriterion("quiz >", value, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizGreaterThanOrEqualTo(Integer value) {
            addCriterion("quiz >=", value, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizLessThan(Integer value) {
            addCriterion("quiz <", value, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizLessThanOrEqualTo(Integer value) {
            addCriterion("quiz <=", value, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizIn(List<Integer> values) {
            addCriterion("quiz in", values, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizNotIn(List<Integer> values) {
            addCriterion("quiz not in", values, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizBetween(Integer value1, Integer value2) {
            addCriterion("quiz between", value1, value2, "quiz");
            return (Criteria) this;
        }

        public Criteria andQuizNotBetween(Integer value1, Integer value2) {
            addCriterion("quiz not between", value1, value2, "quiz");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModTimeIsNull() {
            addCriterion("mod_time is null");
            return (Criteria) this;
        }

        public Criteria andModTimeIsNotNull() {
            addCriterion("mod_time is not null");
            return (Criteria) this;
        }

        public Criteria andModTimeEqualTo(Date value) {
            addCriterion("mod_time =", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotEqualTo(Date value) {
            addCriterion("mod_time <>", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeGreaterThan(Date value) {
            addCriterion("mod_time >", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mod_time >=", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeLessThan(Date value) {
            addCriterion("mod_time <", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeLessThanOrEqualTo(Date value) {
            addCriterion("mod_time <=", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeIn(List<Date> values) {
            addCriterion("mod_time in", values, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotIn(List<Date> values) {
            addCriterion("mod_time not in", values, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeBetween(Date value1, Date value2) {
            addCriterion("mod_time between", value1, value2, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotBetween(Date value1, Date value2) {
            addCriterion("mod_time not between", value1, value2, "modTime");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Boolean value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Boolean> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Boolean> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}