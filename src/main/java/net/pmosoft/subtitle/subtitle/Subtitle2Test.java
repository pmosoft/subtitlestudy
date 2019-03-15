package net.pmosoft.subtitle.subtitle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.pmosoft.subtitle.SubtitlestudyApplication;
import net.pmosoft.subtitle.usr.Usr;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SubtitlestudyApplication.class)
public class Subtitle2Test {

    @Autowired
    private SubtitleCtrl subtitleCtrl;
    
	@Autowired
	private SubtitleSrv subtitleSrv;

	@Autowired
	private SubtitleDao subtitleDao;


	
	
	@Test @Ignore
	public void insertUsrSttlDtlDao() { 
		
		List<Subtitle> usrSttlListVo = new ArrayList<Subtitle>();
		Subtitle Subtitle = new Subtitle();
		
		Subtitle.setUsrId("lifedomy@gmail.com");
		Subtitle.setSttlNm("file01");
		Subtitle.setSttlCd("1");
		Subtitle.setSttlStm("1");
		Subtitle.setSttlEtm("2");
		Subtitle.setSttlDesc("test2");
		usrSttlListVo.add(Subtitle);
		//subtitleDao.insertUsrSttlDtl(usrSttlListVo);
		
	}
	
	@Test @Ignore
	public void insertTestBulkDao() { 
		String qry = "";
		qry += "INSERT INTO STTL.TSSCM00020 VALUES ('lifedomy@gmail.com','Silicon.Valley.S01E02.720p.HDTV.x264-2HD.smi','2','1602830','1606670','- Thank you.<BR>- Sorry. Ok.',now(),NULL,now(),NULL)";
		qry += "INSERT INTO STTL.TSSCM00020 VALUES ('lifedomy@gmail.com','Silicon.Valley.S01E02.720p.HDTV.x264-2HD.smi','2','1602830','1606670','- Thank you.<BR>- Sorry. Ok.',now(),NULL,now(),NULL)";
		//subtitleDao.insertTestBulk(qry);
		subtitleDao.insertTestBulk2(qry);
	}
		
    @Test @Ignore
    public void testSaveUsrSubtitles() throws IOException {
        FileInputStream inputFile = new FileInputStream( "path of the file");  
        MockMultipartFile file = new MockMultipartFile("file", "NameOfTheFile", "multipart/form-data", inputFile);
        //subtitleSrv.saveUsrSubtitles(file);
    }
}

