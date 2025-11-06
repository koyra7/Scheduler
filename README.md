# Scheduler
<img width="329" height="203" alt="image" src="https://github.com/user-attachments/assets/1a91b679-80ca-42ef-a06f-03c0272db86d" />

<img width="223" height="144" alt="image" src="https://github.com/user-attachments/assets/b2c6e7ad-74e6-4a35-927f-a696ef649d6f" />

Lv1,2단계 완료
-
POSTMAPPING, GETMAPPING 부분 만들어 API를 생성하고 조회할수 있도록 만들고, 비밀번호 표시X  , 이름에 따라서 조회 할 수 있도록 함.


Lv3,4단계 완료
- 
PUTMAPPING, DELETEMAPPING을 통해 수정과 삭제를 할 수 있도록 함.
수정은 작성자의 이름과 제목만 수정이 가능하며, DELETE를 통해 삭제할수있음 물론 3,4단계는 비밀번호를 입력받고 불일치시 에러, 일치시 수정 및 삭제가 가능함.


기본적으로 dto에 사용할 변수를 집어넣고 생성 및 삭제를 할때는 request 요청과 responed 받기 dto가 필요했음,
그것을 가지고 controller에서 사용하고 그것을 또 서비스에서 사용, 전달하여 서비스에서 기능을 구현하여 비밀번호로 거르면되었음. 막상 기본적으로 할것하고 비밀번호 제약을 추가하려니 오류도 뜨고 빨간불도 들어왔지만, 해결하는 과정에서 dto에 변수를 넣어야 하는것, 그를 이용하여 컨트롤에서 @RequestBody로 dto에 입력받을수 있게하는것, 
입력받은것을 이용해 , 서비스에서 조건을 걸고 필터링을 하는것으로 해당 문제를 해결했음.

