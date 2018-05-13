package net.pmosoft.subtitlestudy.parse;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParseDao {


    List<Map<String, Object>> selectParseList(Map<String,String> params);
    void insertParse(Map<String,String> params);
    void updateParse(Map<String,String> params);
    void deleteParse(Map<String,String> params);
}

