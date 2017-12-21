package com.bossien.common.util;

import com.bossien.entity.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by A on 2017/10/19.
 */
public class QuestionsUtils {

    /**
     * 组装试题
     * @param questions
     * @return
     */
    public static List<Map<String, Object>> combinQuestion(List<Question> questions, Map<String, String> questionToCourse){

        return combinQuestion(questions, questionToCourse, null, null);
    }

    /**
     * 组装试题、收藏
     * @param questions
     * @param collects
     * @return
     */
    public static List<Map<String, Object>> combinQuestion(List<Question> questions,
                     Map<String, String> questionToCourse, List<QuestionCollection> collects){

        return combinQuestion(questions, questionToCourse, collects, null);
    }

    /**
     * 组装试题、收藏、答题记录
     * @param questions
     * @param collects
     * @param answers
     * @return
     */
    public static List<Map<String, Object>> combinQuestion(List<Question> questions,
                Map<String, String> questionToCourse, List<QuestionCollection> collects, List<ExamAnswers> answers){
        if(null == questions || questions.size() == 0){
            return new ArrayList<Map<String, Object>>();
        }

        List<Map<String, Object>> qList = new ArrayList<Map<String, Object>>();
        Map<String, Object> qMap;
        for (int i = 0; i < questions.size(); i++) {
            qMap = MapUtil.getInstance();
            Question question = questions.get(i);
            //将基本数据导入map中
            MapUtil.putAll(qMap, question);
            qMap.remove("var_content");

            Object json = question.getVar_content();
            Gson gson = new Gson();
            Content content = gson.fromJson(String.valueOf(json), Content.class);
            //选项
            qMap.putAll(setOptionInfo(content.getOptions()));
            qMap.put("var_questions_content", content.getTitle());
            qMap.put("question_title_files", makeFileInfo(content.getTitleImgs()));
            //解析
            qMap.putAll(setAnalysis(question.getVar_analysis()));

            if(null != collects){
                qMap.put("chr_is_collect", checkIsCollect(question, collects));
            }

            if (null != answers) {
                qMap.putAll(checkIsAnswer(question, answers));
            }
            //课程id
            Object courseId = questionToCourse.get(question.getInt_id());
            if(null != courseId){
                qMap.put("course_id", courseId);
            }
            qList.add(qMap);
        }
        return qList;
    }

    /**
     * 解析
     *
     * @param varAnalysis
     * @return
     */
    public static Map<String, Object> setAnalysis(String varAnalysis) {
        Gson gson = new Gson();
        List<Map<String, Object>> analysisFiles = new ArrayList<Map<String, Object>>();
        Map<String, Object> result = MapUtil.getInstance();
        String content ="";
        if(!StringUtil.isEmpty(varAnalysis)){
            Analysis analysis = gson.fromJson(String.valueOf(varAnalysis), Analysis.class);
            content = analysis.getContent();
            analysisFiles = makeFileInfo(analysis.getImages());
        }
        result.put("analysis_content", content);
        result.put("analysis_files", analysisFiles);
        return result;
    }

    /**
     * 组装多个附件对象
     *
     * @param fileInfos
     * @return
     */
    public static List<Map<String, Object>> makeFileInfo(List<FileInfo> fileInfos) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (null == fileInfos || fileInfos.size() < 1) {
            return result;
        }
        for (FileInfo fileInfo : fileInfos) {
            result.add(makeFileInfo(fileInfo));
        }
        return result;
    }

    /**
     * 组装单个附件对象
     *
     * @param fileInfo
     * @return
     */
    public static Map<String, Object> makeFileInfo(FileInfo fileInfo) {
        Map<String, Object> result = MapUtil.getInstance();
        if (null == fileInfo) {
            return result;
        }
        String fileId = fileInfo.getFileId();
        String fileName = fileInfo.getFileName();
        if (null == fileId || fileId.equals("") ||
                null == fileName || fileName.equals("")) {
            return result;
        }
        result.put("mime_type", getMimeType(fileName));
        result.put("mime_url", FastDFSUtils.getURLToken(fileId));
        return result;
    }

    /**
     * //是否收藏：1收藏2未收藏
     *
     * @param question
     * @return
     */
    public static int checkIsCollect(Question question, List<QuestionCollection> collects) {
        String intId = question.getInt_id();
        if (null != intId && intId.length() > 0 && collects.size() > 0) {
            for (QuestionCollection questionCollection : collects) {
                if (null != questionCollection &&
                        !questionCollection.getQuestion_id().equals("") &&
                        questionCollection.getQuestion_id().equals(intId)) {
                    return 1;
                }
            }
        }
        return 2;
    }

    /**
     * 是否答对
     *
     * @param question
     * @return
     */
    public static Map<String, Object> checkIsAnswer(Question question, List<ExamAnswers> answers) {
//        true答对false答错
        Map<String, Object> result = MapUtil.getInstance();
        String intId = question.getInt_id();
        if (null != intId && intId.length() > 0 && answers.size() > 0) {
            for (ExamAnswers examAnswers : answers) {
                String aIntId = String.valueOf(examAnswers.getQuestion_id());
                if (!intId.equals("null") && intId.equals(aIntId)) {
                    result.put("my_answer", examAnswers.getAnswer());
                    result.put("is_correct", examAnswers.getIs_correct());//1答对2答错
                }
            }
        }
        return result;
    }

    /**
     * 组装选项
     *
     * @param options
     * @return
     */
    public static Map<String, Object> setOptionInfo(List<Option> options) {
        Map<String, Object> result = MapUtil.getInstance();
        List<Map<String, Object>> optionList = new LinkedList<Map<String, Object>>();
        if (null == options || options.size() < 1) {
            return result;
        }
        //选项个数
        int intOptions = 0;
        for (Option option : options) {
            intOptions++;
            FileInfo fileInfo = option.getFileInfo();
            Map<String, Object> data = MapUtil.getInstance();
            data.put("o_type", option.getItem());
            data.put("option_content", option.getItemDesc());
            data.putAll(makeFileInfo(fileInfo));
            optionList.add(data);
        }
        result.put("options", optionList);
        result.put("int_options", intOptions);
        return result;
    }

    /**
     * 获取文本类型
     *
     * @param varFileName
     * @return
     */
    public static String getMimeType(String varFileName) {
        String img = "png,PNG,jpeg,JPEG,BMP,bmp,jpg,JPG";
        String mp3 = "mp3,MP3";
        String video = "SWF,swf,FLV,flv,mp4,MP4";
        String suffix = suffix(varFileName);// 获取后缀名称
        if (isContains(img, suffix)) {// 是否包含该后缀
            return "img";
        }
        if (isContains(mp3, suffix)) {
            return "mp3";
        }
        if (isContains(video, suffix)) {
            return "video";
        }
        throw new RuntimeException("文件类型不匹配!");
    }

    /**
     * 获取文件后缀  例如: "文件安全.docx"------>"docx"
     *
     * @param fileName
     * @return
     */
    public static String suffix(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        return suffix;
    }

    /**
     * 目标串是否是源字符串的子串
     */
    public static boolean isContains(String src, String tag) {
        if (tag == null) return false;
        Pattern r = Pattern.compile(tag);
        Matcher matcher = r.matcher(src);
        return matcher.find();
    }
}
