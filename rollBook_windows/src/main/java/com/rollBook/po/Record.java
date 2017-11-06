package com.rollBook.po;

import java.io.Serializable;
import java.util.Date;

public class Record {
    private Integer id;

    private String cname;

    private Long tid;

    private String sno;

    private Integer thing;

    private Date time;

    private Byte rweek;

    private Byte rday;


    private Byte rsection;

    private Date modTime;

    private Boolean isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public Integer getThing() {
        return thing;
    }

    public void setThing(Integer thing) {
        this.thing = thing;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Byte getRweek() {
        return rweek;
    }

    public void setRweek(Byte rweek) {
        this.rweek = rweek;
    }

    public Byte getRday() {
        return rday;
    }

    public void setRday(Byte rday) {
        this.rday = rday;
    }

    public Byte getRsection() {
        return rsection;
    }

    public void setRsection(Byte rsection) {
        this.rsection = rsection;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}