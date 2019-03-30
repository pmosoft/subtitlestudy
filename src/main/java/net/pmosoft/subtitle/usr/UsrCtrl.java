package net.pmosoft.subtitle.usr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 *
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UsrCtrl {

  	@Autowired
	private UsrSrv usrSrv;

	@RequestMapping(value = "/usr/insertUsr")
	public Map<String, Object> insertUsr(@RequestBody Usr usr) {
		return usrSrv.insertUsr(usr);
	}

	@RequestMapping(value = "/usr/saveUsr")
	public Map<String, Object> saveUsr(@RequestBody Usr usr) {
		return usrSrv.saveUsr(usr);
	}

	@RequestMapping(value = "/usr/saveUsrLang")
	public Map<String, Object> saveUsrLang(@RequestBody Usr usr) {
		return usrSrv.saveUsrLang(usr);
	}

	@RequestMapping(value = "/usr/selectUsrLogin")
	public Map<String, Object> selectUsrLogin(@RequestBody Usr usr) {
		return usrSrv.selectUsrLogin(usr);
	}


	@RequestMapping(value = "/usr/selectUsrList")
	public Map<String, Object> selectUsrList(@RequestBody Usr usr) {
		return usrSrv.selectUsrList(usr);
	}

	@RequestMapping(value = "/usr/selectUsr")
	public Map<String, Object> selectUsr(@RequestBody Usr usr) {
		return usrSrv.selectUsr(usr);
	}



/*	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new UsrValidator());
	}
*/

	@RequestMapping(value = "/usr/deleteUsr")
	public Map<String, Object> deleteUsr(@RequestParam Usr usr) {
		return usrSrv.deleteUsr(usr);
	}

	@RequestMapping(value = "/test")
	public Map<String, Object> test() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", "피승현");
		result.put("age", 50);
		return result;
	}

}
