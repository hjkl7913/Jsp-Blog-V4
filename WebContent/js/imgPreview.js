var d = "";
	$("#img__preview").on("change", function(e){  //(자바스크립트 on 이벤트) change 이벤트가 발생하면 fuction 실행   e에 이벤트의 모든정보가 담김
		console.log(e.target.files);
		console.log(e.target.files[0].type);
		var f = e.target.files[0];
		console.log(f.size);
		if(!f.type.match("image*")){
			alert("이미지만 첨부할 수 있습니다.");
			$("#img__preview").val('');
			//return;
		}
		
		if(f.size>=1024*1024*2){
			alert("파일크기는 2mb를 넘길수 없습니다.");
			$("#img__preview").val('');
			return;
		}
		
		var reader = new FileReader();

		reader.onload = function(e){ //다운로드 완료가 한줄을 읽어내는 시간보다 빠를수도 있음으로 위에 써야함 
			$("#img__wrap").attr("src", e.target.result); //reader 객체에 이미지가 로드완료되면 e.target.result 에 들어간다.
		};
		
		reader.readAsDataURL(f); //비동기 저장장치에 일을 맡기고 밑으로 내려가서 실행하면서 
				
	}); 