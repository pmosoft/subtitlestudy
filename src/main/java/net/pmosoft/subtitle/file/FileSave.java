package net.pmosoft.subtitle.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import net.pmosoft.subtitle.comm.util.FileUtil;

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


        try {
           // 유저 파일폴더 생성
            String usrFilePathNm = FileInfo.basicPath+usrId+"/";
            new File(usrFilePathNm).mkdir();

            //-------------------------------
            logger.info("외국어 자막 파일 저장");
            //-------------------------------
            String foreignSubtitleFilePath = usrFilePathNm + foreignSubtitleFile.getOriginalFilename();
            new File(foreignSubtitleFilePath).delete();
            logger.info(foreignSubtitleFilePath);
            Path foreignPath = Paths.get(foreignSubtitleFilePath);
            byte[] foreignBytes = foreignSubtitleFile.getBytes();
            Files.write(foreignPath, foreignBytes);
            foreignSubtitleList = FileUtil.fileToList(foreignSubtitleFilePath);
            subtitleFilePathList.add(foreignSubtitleFilePath);

            //-------------------------------
            logger.info("자국어 자막 파일 저장");
            //-------------------------------
            String motherSubtitleFilePath = usrFilePathNm + motherSubtitleFile.getOriginalFilename();
            new File(motherSubtitleFilePath).delete();
            logger.info("motherSubtitleFilePath="+motherSubtitleFilePath);
            Path motherPath = Paths.get(motherSubtitleFilePath);
            byte[] motherBytes = motherSubtitleFile.getBytes();
            Files.write(motherPath, motherBytes);
            motherSubtitleList  = FileUtil.fileToList(motherSubtitleFilePath);
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
