--  소통공간 커뮤니티
INSERT INTO COMMUNITY(CO_NAME) 
VALUES ('소통공간');

-- 관리자 아이디 추가 , AUTHORITY : ADMIN
INSERT INTO MEMBER(ME_ID, ME_PW, ME_EMAIL, ME_AUTHORITY, ME_MS_NAME)
VALUES('admin123', 'admin123', 'admin123@naver.com', 'ADMIN', '사용');

-- ME_POINT 이용해서 랭킹 << 순서대로 구현하기용 
INSERT INTO MEMBER(ME_ID, ME_PW, ME_EMAIL, ME_POINT, ME_MS_NAME)
VALUES('ranker1', 'ranker1', 'ranker1@naver.com', 1000, '사용');

INSERT INTO MEMBER(ME_ID, ME_PW, ME_EMAIL, ME_POINT, ME_MS_NAME)
VALUES('ranker2', 'ranker2', 'ranker2@naver.com', 990, '사용');

INSERT INTO MEMBER(ME_ID, ME_PW, ME_EMAIL, ME_POINT, ME_MS_NAME)
VALUES('ranker4', 'ranker4', 'ranker4@naver.com', 500, '사용');

INSERT INTO MEMBER(ME_ID, ME_PW, ME_EMAIL, ME_POINT, ME_MS_NAME)
VALUES('ranker3', 'ranker3', 'ranker3@naver.com', 700, '사용');

INSERT INTO MEMBER(ME_ID, ME_PW, ME_EMAIL, ME_POINT, ME_MS_NAME)
VALUES('ranker5', 'ranker5', 'ranker5@naver.com', 100, '사용');

-- 퀴즈 테스트 용

INSERT INTO COMMUNITY(CO_NAME) VALUES
('테스트1'),('테스트2'),('테스트3'),('테스트4');

INSERT INTO QUIZ_TYPE VALUES
(1, '넌센스 퀴즈'), (2, '역사 퀴즈');

INSERT INTO QUIZ VALUES
(1, '개그맨들이 찾아서 헤매는 거리는?',
'텅빈거리', '웃음거리', '충무로거리', '웃찾사', 2, 1),
(2, '하늘에서 애 낳으면?',
'하이마트', '하이웨이', '하이애나', '옴메 나죽어', 3, 1),
(3, '세상 사람들의 머리카락 수를 모두 곱하면?',
'불계', '계산 불가', '무한대', 0, 4, 1),
(4, '정말 멋진 신사가 자기 소개하는 것은?',
'신사정장', '신사참배', '신사임당', '신사도', 3, 1),
(5, '거꾸로 매달린 집에 천문 만호(천 개의 문과 만 개의 방)가 무엇인가?',
'백악관', '63빌딩', '벌집', '청와대', 3, 1),
(6, '젊은 여자가 남탕에 들어가면 무슨 죄인가?',
'천고죄', '방화죄', '무죄', '풍기물란죄', 2, 1),
(7, '재수 없는데 재수 있다고 하는 것은?',
'군대 입대', '대입 낙방', '급여 삭감', '승진 누락', 2, 1),
(8, '세상에서 가장 먼저 자는 가수는?',
'심수봉', '토끼소녀', '조용필', '이미자', 4, 1),
(9, '날씨가 더워서 창문을 열었더니 신이 날고 있었다. 이런 이야기를 뭐라고 할까?',
'신나는 이야기', '황당한 이야기', '무서운 이야기', '무더운 이야기', 1, 1),
(10, '사람의 몸무게가 가장 많이 나갈 때는?',
'밥 먹을 때', '철 들 때', '나이 먹을 때', '공부 할 때', 2, 1),
(11, '속이 끓어오르는 사람이 쓴 글은?',
'싱글벙글', '이글이글', '부글부글', '방글방글', 3, 1),
(12, '거지가 싫어하는 색은?',
'검정색', '인색', '살색', '흰색', 2, 1),
(13, '아침 저녁으로 발만 동동 구르고 못 얻어 먹는 것은?',
'솥', '그릇', '숟가락', '젓가락', 4, 1),
(14, '바느질을 하기 위해 실을 찾는 남자를 다섯 글자로 줄이면?',
'실쏘는 남자', '실없는 남자', '바느질 남자', '잘하는 남자', 2, 1),
(15, '입으로 먹지 않고 귀로 먹는 것은?',
'칭찬', '욕', '귀약', '빵', 2, 1);

INSERT INTO MEMBER(ME_ID, ME_PW, ME_EMAIL, ME_MS_NAME) VALUES
('abc123', 'abc123', 'abc123@naver.com', '사용');
