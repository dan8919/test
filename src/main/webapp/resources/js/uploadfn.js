		//원본이미지파일 주소 출력 함수("s_" 제외)
		function getImgLink(result){
//			/2020/07/10/s_xxxxxxx_a.jpg
			if(checkImage(result)){
				//이미지파일의 경우
				return result.substring(0,12) + result.substring(14);
//			/2020/07/10/ + xxxxxxx_a.jpg				
			}else{
				//이미지파일이 아니면
				return result;	
			}
		}
		
		//오리지널네임 받는 함수
		function getOriginalName(fileName){
			if(checkImage(fileName)){
				var idx = fileName.indexOf("_"); //13
				idx = fileName.indexOf("_", idx + 1); //14
				return fileName.substring(idx + 1);
					//└s_이후의 언더바 뒤에있는 오리지널네임 반환				

			}else{
				var idx = fileName.indexOf("_");
				return fileName.substring(idx + 1);
					//└오리지널네임 반환
			} 
		}
		
		//이미지파일인지 확인
		function checkImage(fileName){
			
			var idx = fileName.lastIndexOf(".");
			var format = fileName.substring(idx + 1).toUpperCase();
			if(format == 'PNG' || format == 'JPEG' || format == 'JPG' || format == 'GIF'){
				return true;
			}else{
				return false;
			}
		}
