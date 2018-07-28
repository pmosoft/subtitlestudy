package net.pmosoft.subtitle.usr;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsrDao {

	void insertUsr(Usr usr);
	
	List<Usr> selectUsrList(Usr usr);
	Usr selectUsr(String usrEmail);
	int selectUsrCnt(Usr usr);
	
	void updateUsr(Usr usr);
	void deleteUsr(Usr usr);
}

