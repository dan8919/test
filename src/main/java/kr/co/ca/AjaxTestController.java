package kr.co.ca;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.domain.MemberDTO;

@Controller
public class AjaxTestController {
	
	@ResponseBody
	@RequestMapping(value = "at4", method = RequestMethod.POST)
	public List<Map<String, Object>> at4(@RequestParam Map<String, Object> map) throws Exception {
		//키값이 listStr
		String jsonStr = map.get("listStr").toString();
		
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> list = 
				mapper.readValue(jsonStr, new TypeReference<ArrayList<Map<String, Object>>>() {
		});
		
		for(Map<String, Object> m : list) {
			System.out.println("::::::::::::::::");
			
			//키값 넣어주기
			System.out.println(m.get("id"));
			System.out.println(m.get("name"));
			System.out.println(m.get("age"));			
			
			System.out.println("::::::::::::::::");
		}
		
		return list;
		
	}
	
	
	@RequestMapping(value = "at4", method = RequestMethod.GET)
	public void at4() {
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "at3", method = RequestMethod.POST)
	public MemberDTO at3(MemberDTO dto) {
		return dto;
	}
	
	
	@RequestMapping(value = "at3", method = RequestMethod.GET)
	public void at3() {
		
	}
	
	
	@ResponseBody //리턴값을 jsp로 인식않고 ajax 인식하도록
	@RequestMapping(value = "/at2", method = RequestMethod.POST)
	public String[] at2(String[] arr) {
		for(String x : arr) {
			System.out.println(x);
		}		
		return arr;
	}
	
	
	@RequestMapping(value = "/at2", method = RequestMethod.GET)
	public void at2() {
		
	}
	
	
	@ResponseBody //ajax라는 표시
	@RequestMapping(value = "at1", method = RequestMethod.POST)
	public String at1(String msg) {
		
		return msg+"!!!"; //msg+!!! 라는 이름의 jsp 파일로 가려고 함
//							└@ResponseBody 붙여줘서 ajax로 인식하게 하기
	}

	@RequestMapping(value = "at1", method = RequestMethod.GET)
	public void at1() {
		
	}
}
