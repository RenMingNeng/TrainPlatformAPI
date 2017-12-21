package com.bossien.entity.request;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/19.
 */
public class StudyCourseResponse {
    @XmlElement
    private List<Map<Object, Object>> studylist;

    @XmlElement
    private int studylistCount;

    @XmlElement
    private List<Map<String, Object>> uploadlist;

    public List<Map<Object, Object>> getStudylist() {
        return studylist;
    }

    public void setStudylist(List<Map<Object, Object>> studylist) {
        this.studylist = studylist;
    }

    public int getStudylistCount() {
        return studylistCount;
    }

    public void setStudylistCount(int studylistCount) {
        this.studylistCount = studylistCount;
    }

    public List<Map<String, Object>> getUploadlist() {
        return uploadlist;
    }

    public void setUploadlist(List<Map<String, Object>> uploadlist) {
        this.uploadlist = uploadlist;
    }
}
