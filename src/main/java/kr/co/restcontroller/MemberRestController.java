package kr.co.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.MemberDTO;
import kr.co.persistence.GrantsDTO;
import kr.co.service.MemberService;

@RestController
public class MemberRestController {
	
	@Autowired 
	private MemberService memberService;
	
@RequestMapping(value = "/changeAuthority/{id}", method = RequestMethod.PUT)	
	public String changeAuthority(@PathVariable("id") String id,@RequestBody Map<String, String> map) {
		
    String auth = map.get("auth"); 

	int grno = memberService.selectGrno(auth);
	 
	MemberDTO originalDTO = memberService.selectMeberDTO(id);
	
     originalDTO.setGrno(grno);
     
    int i = memberService.updateGrno(originalDTO);
	
	 if(i == 1) {
		 return "success";
	 }
	

		return "fail";
	
}

}
