package com.bossien.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bossien.common.util.*;
import com.bossien.entity.Attachment;
import com.bossien.entity.Course;
import com.bossien.entity.LatelyStudyRecord;
import com.bossien.entity.VideoPosition;
import com.bossien.entity.enumeration.CourseTypeEnum;
import com.bossien.entity.request.CourseTypeList;
import com.bossien.entity.request.LatelyStudyCourse;
import com.bossien.mapper.ap.CompanyCourseMapper;
import com.bossien.mapper.ap.CompanyCourseTypeMapper;
import com.bossien.mapper.ap.CourseMapper;
import com.bossien.mapper.tp.LatelyStudyMapper;
import com.bossien.service.IAttachmentService;
import com.bossien.service.ICourseService;
import com.bossien.service.IFastDFSService;
import com.bossien.service.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.ArrayList;
import net.sf.json.JSONObject;
/**
 *
 * ex_course 表数据服务层实现类
 *
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private IAttachmentService attachmentService;
    @Autowired
    private IFastDFSService fastDFSService;
    @Autowired
    private CompanyCourseTypeMapper companyCourseTypeMapper;
    @Autowired
    private CompanyCourseMapper companyCourseMapper;
    @Autowired
    private IVideoPositionService videoPositionService;
    @Autowired
    private LatelyStudyMapper latelyStudyMapper;
    @Autowired
    private ISequenceService sequenceService;

    public Course selectOne(String intId) {

        return courseMapper.selectOne(intId);
    }

    public Map<String, Object> assemblyCourseAndAttachment(Map<String,Object> map) {
       //查询最新学习记录
        LatelyStudyRecord latelyStudyRecord = latelyStudyMapper.selectOne(map);
        Map latelyStudyRecordMap = MapUtil.getInstance();
        if(null == latelyStudyRecord){
            latelyStudyRecordMap.put("id", sequenceService.generator());
            latelyStudyRecordMap.put("user_id",map.get("user_id").toString());
            latelyStudyRecordMap.put("course_id",map.get("course_id").toString());
            latelyStudyRecordMap.put("create_time",DateUtils.formatDateTime(new Date()));
            latelyStudyRecordMap.put("oper_time",DateUtils.formatDateTime(new Date()));
            //添加数据
            latelyStudyMapper.insert(latelyStudyRecordMap);
        }else{
            latelyStudyRecordMap.put("id",latelyStudyRecord.getId());
            latelyStudyRecordMap.put("oper_time",DateUtils.formatDateTime(new Date()));
            //更新数据
            latelyStudyMapper.update(latelyStudyRecordMap);
        }

        Map resp = MapUtil.getInstance();
        resp.put("course_id",map.get("course_id").toString());
        resp.put("course_name",map.get("course_name").toString());
        resp.put("course_desc",map.get("course_desc").toString());

        List<Attachment> attachmentList = attachmentService.selectList(map);
        List<Map<String,Object>> videos = new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> files = new ArrayList<Map<String, Object>>();
        for (Attachment attachment:attachmentList) {
            Map map1 = MapUtil.getInstance();
            //查询user_id、course_id和intId查询视频播放位置
            VideoPosition videoPosition = videoPositionService.selectOne(new VideoPosition(map.get("user_id").toString(),map.get("course_id").toString(),attachment.getIntId()));
            int lastPosition = 0;
            if(videoPosition!=null && videoPosition.getLast_position()!=null){
                lastPosition = videoPosition.getLast_position();
            }
            map1.put("last_position",lastPosition);
            map1.put("file_id",attachment.getIntId());
            map1.put("file_name",attachment.getVarLocalName());
            map1.put("url",fastDFSService.getURLToken(attachment.getVarFid()));

            if(PropertiesUtils.getValue("video").contains(attachment.getVarExt())){
                videos.add(map1);
                continue;
            }
            if(PropertiesUtils.getValue("file").contains(attachment.getVarExt())){
                files.add(map1);
            }

        }

        resp.put("video_info",videos);
        resp.put("file_info",files);


        return resp;
    }

    public Page<Map<Object, Object>> searchAllCourseList(Page<Map<Object, Object>> page, CourseTypeList ctl) {
        Map<String, Object> params = new HashMap<String,Object>();
        Map<String, Object> para = new HashMap<String,Object>();
        List<Map<Object, Object>> list=new ArrayList<Map<Object, Object>>();
        List<String> clist=new ArrayList<String>();
        int useCount=0;
        params.put("intCompanyId",ctl.getCompany_id());
        clist=companyCourseMapper.selectCourseIds(params);
        if(ctl.getCourse_name()!=null){
            para.put("varName", ParamsUtil.joinLike(ctl.getCourse_name()));
        }
        if(clist.size()>0){
            para.put("intIds",clist);
        }
        List<Map<Object, Object>> selist = courseMapper.selectList(page, para);
        int count=courseMapper.selectListCount(para);
        if(selist.size()>0){
            for(Map<Object, Object> umap:selist){
                if(umap.get("course_id")!=null){
                    params.put("course_id", umap.get("course_id").toString());
                    useCount=latelyStudyMapper.queryStudyCount(params);
                    umap.put("use_num", useCount);
                }
                //课程封面
                if(StringUtils.isNotEmpty((String)umap.get("cover_url"))){
                    //循环外层数据得到map
                    Map<String, Object> resultMap = JsonUtil.jsonToMap(umap.get("cover_url").toString());
                    if(resultMap!=null && resultMap.get("images")!=null){
                        //继续循环内层json数组
                        List<Map<String, Object>> dataListMap = JsonUtil.jsonArrayToMap(resultMap.get("images"));
                        if(dataListMap.size()>0 && !dataListMap.isEmpty()){
                            for(Map<String, Object> dataMap:dataListMap){
                                if(dataMap.get("fileId")!=null){
                                    umap.put("cover_url", FastDFSUtils.getURLToken(dataMap.get("fileId").toString()) );
                                }
                            }
                        }
                    }
                }else{
                    umap.put("cover_url", "");
                }
                list.add(umap);
            }

        }
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    public Page<Map<Object, Object>> queryCourseListByType(Page<Map<Object, Object>> page, CourseTypeList ctl) {
        Map<String, Object> params = new HashMap<String,Object>();
        Map<String, Object> para = new HashMap<String,Object>();
        List<Map<Object, Object>> clist=new ArrayList<Map<Object, Object>>();
        List<Map<Object, Object>> ctlist=new ArrayList<Map<Object, Object>>();
        List<String> cids=new ArrayList<String>();
        List<String> courseids=new ArrayList<String>();
        int useCount=0; int countStr=0;

        if(StringUtils.isNotEmpty(ctl.getCourse_type())){
            String ctype=ctl.getCourse_type();
            params.put("ctype",ctype);
            if(ctype.equals(CourseTypeEnum.WEIKE_VIDEO.getValue())){
                params.put("varName",CourseTypeEnum.WEIKE_VIDEO.getName());
            }else if(ctype.equals(CourseTypeEnum.SHIGU_ANLI.getValue())){
                params.put("varName", CourseTypeEnum.SHIGU_ANLI.getName());
            }else if(ctype.equals(CourseTypeEnum.ZHISHI_JX.getValue())){
                //知识库 查询所有分类下课程 除去公共安全 事故管理两个分类
                String str = CourseTypeEnum.WEIKE_VIDEO.getName() + "," + CourseTypeEnum.SHIGU_ANLI.getName();
                params.put("varNames", Arrays.asList(str.split(",")));
            }
        }
        //根据类型名称和公司查出课程类型id
        params.put("intCompanyId", ctl.getCompany_id());
        List<Map<String, Object>> typeMap = companyCourseTypeMapper.selectByParams(params);

        if(typeMap!=null && typeMap.size()>0){
            for(Map<String, Object> map:typeMap){
                if(map.get("varId")!=null){
                    params.put("intCompanyCourseTypeId",map.get("varId").toString());
                    //根据类型id查询所有课程id集合
                    cids=companyCourseMapper.selectCourseIds(params);
                }
                courseids.addAll(cids);
            }
        }


        if(courseids.size()>0){
            para.put("intIds",courseids);

            //查询所有课程集合
            clist = courseMapper.selectList(page,para);
            countStr=courseMapper.selectListCount(para);
            if(clist.size()>0){
                for(Map<Object,Object> cmap:clist){
                    if(cmap.get("course_id")!=null){
                        params.put("course_id", cmap.get("course_id").toString());
                        useCount=latelyStudyMapper.queryStudyCount(params);
                        cmap.put("use_num", useCount);
                    }
                    if(StringUtils.isNotEmpty((String)cmap.get("cover_url"))){
                        //循环外层数据得到map
                        Map<String, Object> resultMap = JsonUtil.jsonToMap(cmap.get("cover_url").toString());
                        if(resultMap!=null && resultMap.get("images")!=null){
                            //继续循环内层json数组
                            List<Map<String, Object>> dataListMap = JsonUtil.jsonArrayToMap(resultMap.get("images"));
                            if(dataListMap.size()>0 && !dataListMap.isEmpty()){
                                for(Map<String, Object> dataMap:dataListMap){
                                    if(dataMap.get("fileId")!=null){
                                        cmap.put("cover_url", FastDFSUtils.getURLToken(dataMap.get("fileId").toString()) );
                                    }
                                }
                            }
                        }
                    }else{
                        cmap.put("cover_url", "");
                    }
                    ctlist.add(cmap);
                }

            }
        }
        page.setTotal(countStr);
        page.setRecords(ctlist);
        return page;
    }

    public List<Map<Object, Object>> queryLatelyStudyCourseList(LatelyStudyCourse lsc) {
        Map<String, Object> params = new HashMap<String,Object>();
        Map<String, Object> para = new HashMap<String,Object>();
        List<Map<Object, Object>> list=new ArrayList<Map<Object, Object>>();
        int useCount=0;
        //默认只查询两个
        if(StringUtils.isEmpty(lsc.getCourse_type())){
            para.put("course_type", "-1");
        }
      /*  if(StringUtils.isEmpty(lsc.getCompany_id())){
            para.put("company_id", lsc.getCompany_id());
        }*/
        if(StringUtils.isNotEmpty(lsc.getUser_id())){
            para.put("user_id", lsc.getUser_id());
        }
        //查询用户学习了哪些课程
        List<String> lclist = latelyStudyMapper.selectLatelyStudyCourse(para);
        if(lclist.size()>0){
            para.put("intIds", lclist);
            //查询出课程信息
            List<Map<Object, Object>> crlist = courseMapper.selectCourseList(para);
            if(crlist.size()>0){
                for(Map<Object, Object> smap:crlist){
                    if(smap.get("course_id")!=null){
                        params.put("course_id", smap.get("course_id").toString());
                        //统计该课程下学习人数
                        useCount=latelyStudyMapper.queryStudyCount(params);
                        smap.put("use_num", useCount);
                    }
                    if(StringUtils.isNotEmpty((String)smap.get("cover_url"))){
                        //循环外层数据得到map
                        Map<String, Object> resultMap = JsonUtil.jsonToMap(smap.get("cover_url").toString());
                        if(resultMap!=null && resultMap.get("images")!=null){
                            //继续循环内层json数组
                            List<Map<String, Object>> dataListMap = JsonUtil.jsonArrayToMap(resultMap.get("images"));
                            if(dataListMap.size()>0 && !dataListMap.isEmpty()){
                                for(Map<String, Object> dataMap:dataListMap){
                                    if(dataMap.get("fileId")!=null){
                                        smap.put("cover_url", FastDFSUtils.getURLToken(dataMap.get("fileId").toString()) );
                                    }
                                }
                            }
                        }
                    }else{
                        smap.put("cover_url", "");
                    }
                    list.add(smap);
                }

            }
        }

        return list;
    }

    public Page<Map<Object, Object>> queryLatelyStudyCourseList(Page<Map<Object, Object>> page, Map<String, Object> param) {
        Map<String, Object> params = new HashMap<String,Object>();
        Map<String, Object> para = new HashMap<String,Object>();
        List<Map<Object, Object>> list=new ArrayList<Map<Object, Object>>();
        List<String> cids=new ArrayList<String>();
        int useCount=0;
        //点击更多查询所有的
        if(param.get("course_type").toString().equals(CourseTypeEnum.MORE.getValue())){
            param.put("course_type", "");
        }
        List<Map<Object, Object>> lulist = latelyStudyMapper.selectLatelyStudyRecord(page, param);

        if(lulist.size()>0){
            for(Map<Object, Object> map:lulist){
                if(map.get("courseId")!=null){
                    cids.add(map.get("courseId").toString());
                }
            }

        }
        if(cids.size()>0){
            para.put("intIds", cids);
            //查询出课程信息
            List<Map<Object, Object>> crlist = courseMapper.selectCourseList(para);
            for(Map<Object, Object> umap:crlist){
                if(umap.get("course_id")!=null){
                    params.put("course_id", umap.get("course_id").toString());
                    useCount=latelyStudyMapper.queryStudyCount(params);
                    umap.put("use_num", useCount);
                }else{
                    umap.put("use_num", useCount);
                }
                if(StringUtils.isNotEmpty((String)umap.get("cover_url"))){
                    //循环外层数据得到map
                    Map<String, Object> resultMap = JsonUtil.jsonToMap(umap.get("cover_url").toString());
                    if(resultMap!=null && resultMap.get("images")!=null){
                        //继续循环内层json数组
                        List<Map<String, Object>> dataListMap = JsonUtil.jsonArrayToMap(resultMap.get("images"));
                        if(dataListMap.size()>0 && !dataListMap.isEmpty()){
                            for(Map<String, Object> dataMap:dataListMap){
                                if(dataMap.get("fileId")!=null){
                                    umap.put("cover_url", FastDFSUtils.getURLToken(dataMap.get("fileId").toString()) );
                                }
                            }
                        }
                    }
                }else{
                    umap.put("cover_url", "");
                }
                list.add(umap);
            }
        }

        page.setRecords(list);
        return page;
    }

    public List<Map<String, Object>> queryLatelyUploadCourseList(LatelyStudyCourse latelyStudyCourse) {
        Map<String, Object> params = new HashMap<String,Object>();
        Map<String, Object> param = new HashMap<String,Object>();
        List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        int useCount=0;
        if(latelyStudyCourse.getCompany_id()!=null){
            param.put("intCompanyId",latelyStudyCourse.getCompany_id());
        }
        //查询公司下所有的课程
        List<String> cclist = companyCourseMapper.selectCourseIds(param);
        if(cclist.size()>0){
            param.put("intIds",cclist);
            //从所有课程中查询最近上传的两个课程
            List<Map<String, Object>> lulist=courseMapper.selectLatelyUploadCourse(param);
            if(lulist.size()>0){
                for(Map<String, Object> umap:lulist){
                    if(umap.get("course_id")!=null){
                        params.put("course_id", umap.get("course_id").toString());
                        useCount=latelyStudyMapper.queryStudyCount(params);
                        umap.put("use_num", useCount);
                    }
                    if(StringUtils.isNotEmpty((String)umap.get("cover_url"))){
                        //循环外层数据得到map
                        Map<String, Object> resultMap = JsonUtil.jsonToMap(umap.get("cover_url").toString());
                        if(resultMap!=null && resultMap.get("images")!=null){
                            //继续循环内层json数组
                            List<Map<String, Object>> dataListMap = JsonUtil.jsonArrayToMap(resultMap.get("images"));
                            if(dataListMap.size()>0 && !dataListMap.isEmpty()){
                                for(Map<String, Object> dataMap:dataListMap){
                                    if(dataMap.get("fileId")!=null){
                                        umap.put("cover_url", FastDFSUtils.getURLToken(dataMap.get("fileId").toString()) );
                                    }
                                }
                            }
                        }
                    }else{
                        umap.put("cover_url", "");
                    }
                    list.add(umap);
                }

            }
        }

        return list;
    }

    public int selectLatelyStudyCount(LatelyStudyCourse latelyStudyCourse) {
        Map<String, Object> params = new HashMap<String,Object>();
        int result=0;
       /* if(latelyStudyCourse.getCompany_id()!=null){
            params.put("intCompanyId", latelyStudyCourse.getCompany_id());
        }*/
        if(latelyStudyCourse.getUser_id()!=null){
            params.put("user_id", latelyStudyCourse.getUser_id());
        }
     /*   List<String> clist = companyCourseMapper.selectCourseIds(params);
        if(clist.size()>0){
            params.put("courseIds", clist);
        }*/
        result=latelyStudyMapper.queryStudyCount(params);
        return result;
    }
}
