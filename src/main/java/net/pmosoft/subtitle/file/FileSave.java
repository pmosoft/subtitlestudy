package net.pmosoft.subtitle.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import net.pmosoft.subtitle.parse.ParseSubtitle;

public class FileSave {

	private static Logger logger = LoggerFactory.getLogger(FileSave.class);	
	
    public Map<String,ArrayList<String>> saveUsrSubtitles( String usrId
                                 , MultipartFile foreignSubtitleFile
                                 , MultipartFile motherSubtitleFile) 
    {
        Map<String,ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        ArrayList<String> foreignSubtitleList = new ArrayList<String>(); 
        ArrayList<String> motherSubtitleList = new ArrayList<String>();
        ArrayList<String> subtitleFilePathList = new ArrayList<String>();
        
        FileUtil fileUtil = new FileUtil();
        
        try {
           // 유저 파일폴더 생성
            String usrFilePathNm = FileInfo.basicPath+usrId+"/";
            fileUtil.makeDir(usrFilePathNm);
            
            // 외국어 자막 파일 저장            
            String foreignSubtitleFilePath = usrFilePathNm + foreignSubtitleFile.getOriginalFilename();
            logger.info("foreignSubtitleFilePath="+foreignSubtitleFilePath);
            Path foreignPath = Paths.get(foreignSubtitleFilePath);
            byte[] foreignBytes = foreignSubtitleFile.getBytes();
            Files.write(foreignPath, foreignBytes);
            
            // 자국어 자막 파일 저장
            String motherSubtitleFilePath = usrFilePathNm + motherSubtitleFile.getOriginalFilename();
            logger.info("motherSubtitleFilePath="+motherSubtitleFilePath);
            Path motherPath = Paths.get(motherSubtitleFilePath);
            byte[] motherBytes = motherSubtitleFile.getBytes();
            Files.write(motherPath, motherBytes);
            
            foreignSubtitleList = fileUtil.readFileList(foreignSubtitleFilePath);
            motherSubtitleList  = fileUtil.readFileList(motherSubtitleFilePath);
            
            subtitleFilePathList.add(foreignSubtitleFilePath);
            subtitleFilePathList.add(motherSubtitleFilePath);
            
            map.put("foreignSubtitleList", foreignSubtitleList);
            map.put("motherSubtitleList" , motherSubtitleList);
            map.put("subtitleFilePathList", subtitleFilePathList);
            
            return map;
        } catch (Exception e) {
            return map;
        }
    }
    
}
