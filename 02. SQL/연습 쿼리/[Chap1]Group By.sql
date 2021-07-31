-- 1. Group BY 이해하기
-- [1] 주문일시, 지불유형별 주문금액
SELECT T1.ORD_DT, T1.PAY_TP
       , SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY T1.ORD_DT, T1.PAY_TP
 ORDER BY T1.ORD_DT, T1.PAY_TP;
 
-- [2] CASE를 이용해 가격유형(ORD_AMT_TP)별로 주문 건수를 카운트
SELECT T1.ORD_ST
          , CASE WHEN T1.ORD_AMT >= 5000 THEN 'High Order'
                 WHEN T1.ORD_AMT >= 3000 THEN 'Middle Order'
                 ELSE 'Low Order'
            END OPRD_AMT_TP
            ,COUNT(*) ORD_CNT
  FROM T_ORD T1
 GROUP BY T1.ORD_ST
          , CASE WHEN T1.ORD_AMT >= 5000 THEN 'High Order'
                 WHEN T1.ORD_AMT >= 3000 THEN 'Middle Order'
                 ELSE 'Low Order'
            END
ORDER BY 1, 2;
-- 테이블의 1, 2 컬럼 기준으로 정렬

-- 일회성으로 사용하는 SQL이라면 이렇게 작성해도 괜찮지만 실제 운영 화면에서는 사용하지말것.

-- [3] TO_CHAR 변형을 이용한 주문년월, 지불유형별 주문건수
SELECT TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM ,T1.PAY_TP
       , COUNT(*) ORD_CNT
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY TO_CHAR(T1.ORD_DT,'YYYYMM') ,T1.PAY_TP
 ORDER BY TO_CHAR(T1.ORD_DT,'YYYYMM') ,T1.PAY_TP;
 
 -- ORDER BY에도 적용할 수 있다.
 
-- 2. 집계함수에서 case 활용
-- [1] 주문년월별 계좌이체(PAY_TP=BANK) 건수와 카드결제(PAY_TP=CARD) 건수

SELECT TO_CHAR(T1.ORD_DT, 'YYYYMM') ORD_YM
       , SUM(CASE WHEN T1.PAY_TP = 'BANK' THEN 1 END) BANK_PAY_CNT -- ELSE를 선언하지 않았기 때문에 BANK가 아닌 경우는 자동으로 NULL 처리 연산되지 않음.
       , SUM(CASE WHEN T1.PAY_TP = 'CARD' THEN 1 END) CARD_PAY_CNT
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY TO_CHAR(T1.ORD_DT, 'YYYYMM')
 ORDER BY TO_CHAR(T1.ORD_DT, 'YYYYMM');
 
-- 분석용 데이터를 보여주기 위해 자주 사용하는 기법이다.

-- [2] 지불유형(PAY_TP)별 주문건수(주문 건수를 주문년월별로 컬럼으로 표시)

SELECT T1.PAY_TP
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201701' THEN 'X' END) ORD_CNT_1701
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201702' THEN 'X' END) ORD_CNT_1702
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201703' THEN 'X' END) ORD_CNT_1703
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201704' THEN 'X' END) ORD_CNT_1704
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201705' THEN 'X' END) ORD_CNT_1705
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201706' THEN 'X' END) ORD_CNT_1706
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201707' THEN 'X' END) ORD_CNT_1707
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201708' THEN 'X' END) ORD_CNT_1708
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201709' THEN 'X' END) ORD_CNT_1709
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201710' THEN 'X' END) ORD_CNT_1710
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201711' THEN 'X' END) ORD_CNT_1711
       , COUNT(CASE WHEN TO_CHAR(T1.ORD_DT, 'YYYYMM') = '201712' THEN 'X' END) ORD_CNT_1712
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY T1.PAY_TP
 ORDER BY T1.PAY_TP;
 
 -- THEN 뒤 'X'는 에러 표시를 없애기위해 형식상 아무 의미 없는 값을 넣어준것. 
 -- 여러 분석에서는 월이나 주와 같은 시간속성을 가로로 보여주길 원한다.
 
 -- 아래와 같은 방식으로도 변경 가능하다.
 
-- [2] 지불유형(PAY_TP)별 주문건수(주문 건수를 주문년월별로 컬럼으로 표시) - 인라인-뷰 활용

SELECT T1.PAY_TP
       , MAX(CASE WHEN T1.ORD_YM = '201701' THEN T1.ORD_CNT END) ORD_CNT_1701
       , MAX(CASE WHEN T1.ORD_YM = '201702' THEN T1.ORD_CNT END) ORD_CNT_1702
       , MAX(CASE WHEN T1.ORD_YM = '201703' THEN T1.ORD_CNT END) ORD_CNT_1703
       , MAX(CASE WHEN T1.ORD_YM = '201704' THEN T1.ORD_CNT END) ORD_CNT_1704
       , MAX(CASE WHEN T1.ORD_YM = '201705' THEN T1.ORD_CNT END) ORD_CNT_1705
       , MAX(CASE WHEN T1.ORD_YM = '201706' THEN T1.ORD_CNT END) ORD_CNT_1706
       , MAX(CASE WHEN T1.ORD_YM = '201707' THEN T1.ORD_CNT END) ORD_CNT_1707
       , MAX(CASE WHEN T1.ORD_YM = '201708' THEN T1.ORD_CNT END) ORD_CNT_1708
       , MAX(CASE WHEN T1.ORD_YM = '201709' THEN T1.ORD_CNT END) ORD_CNT_1709
       , MAX(CASE WHEN T1.ORD_YM = '201710' THEN T1.ORD_CNT END) ORD_CNT_1710
       , MAX(CASE WHEN T1.ORD_YM = '201711' THEN T1.ORD_CNT END) ORD_CNT_1711
       , MAX(CASE WHEN T1.ORD_YM = '201712' THEN T1.ORD_CNT END) ORD_CNT_1712
FROM (
     SELECT T1.PAY_TP, TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM
            , COUNT(*) ORD_CNT
       FROM T_ORD T1
      WHERE T1.ORD_ST = 'COMP'
      GROUP BY T1.PAY_TP, TO_CHAR(T1.ORD_DT,'YYYYMM')
     ) T1
GROUP BY T1.PAY_TP;

-- 서브쿼리를 이용. 서브쿼리에서 주문 데이터를 PAY_TP, ORD_YM 별로 먼저 카운트한 뒤 서브쿼리 밖에서
-- PAY_TP(지불방법)별로 GROUP BY ~ CASE 처리했다.

-- 3. COUNT 집계함수

-- [1] NULL에 대한 COUNT #1
SELECT COUNT(T1.COL1) CNT_COL1
       ,COUNT(T1.COL2) CNT_COL2
       ,COUNT(T1.COL3) CNT_COL3
  FROM  (
        SELECT 'A' COL1 ,NULL COL2, 'C' COL3 FROM DUAL UNION ALL
        SELECT 'B' COL1 ,NULL COL2, NULL COL3 FROM DUAL
        ) T1;
        
-- 카운트 집계함수는 NULL값을 0으로 카운트한다. COL1에는 NUIL 값이 없다. 그러므로 2라는 결과가 나온다.
-- COL2 값은 모두 NULL이다. 때문에 0이 나온다. COL3는 두 건 중 한 건만 NULL이다. 그러므로 1이 된다.

-- [2] NULL에 대한 COUNT #2
SELECT COUNT(T1.COL1) CNT_COL1, COUNT(*) CNT_ALL
  FROM  (
        SELECT NULL COL1 FROM DUAL UNION ALL
        SELECT NULL COL1 FROM DUAL
        ) T1;
        
-- COL1 컬럼의 값은 NULL로 0이라는 결과가 나오지만 COUNT(*)는 컬럼의 갯수를 구하는 것이므로 2가 나온다.

-- 4. 중복을 제거한 COUNT
-- SELECT 절에서 DISTINCT를 사용하면 중복이 제거된다. COUNT안에서도 DISTINCT를 사용할 수 있다.

-- 주문년월별 주문고객(중복고객은 제거해서 카운트), 주문건수
SELECT TO_CHAR(T1.ORD_DT, 'YYYYMM') ORD_YM
       , COUNT(DISTINCT T1.CUS_ID) CUS_CNT -- 중복제거
       , COUNT(*) ORD_CNT -- 중복미제거
  FROM  T_ORD T1
 WHERE T1.ORD_DT >= TO_DATE('20170101', 'YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170401', 'YYYYMMDD')
 GROUP BY TO_CHAR(T1.ORD_DT, 'YYYYMM')
 ORDER BY TO_CHAR(T1.ORD_DT, 'YYYYMM');
 
-- 주문상태(ORD_ST)와 지불유형(PAY_TP)의 조합에 대한 종류 수
SELECT COUNT(DISTINCT T1.ORD_ST || '-' || T1.PAY_TP)
  FROM T_ORD T1;
  
-- 주문상태(ORD_ST)와 지불유형(PAY_TP)의 조합에 대한 종류 수 - 인라인-뷰
SELECT COUNT(*)
  FROM  (
        SELECT DISTINCT T1.ORD_ST, T1.PAY_TP
          FROM T_ORD T1
        ) T2;

-- 한번이라도 로그인이 있는 고객 수

-- COUNT(DISTINCT) 이용
SELECT COUNT(DISTINCT T1.고객ID)
  FROM 로그인 T1;

--SELECT~EXISTS를 이용  
SELECT COUNT(*)
  FROM 고객 T1
 WHERE EXISTS(
            SELECT *
              FROM 로그인 A
             WHERE A.고객ID = T1.고객ID);
             
-- 5. HAVING
-- HAVING절은 GROUP BY가 수행된 결과 집합에 조건을 줄 때 사용 WHERE절과 같은 기능이라고 생각하면 된다.
-- 집계된 주문 금액이 특정 금액  이상인 데이터만 조회하고자 할 때 HAVING을 사용할 수 있다.
SELECT T1.CUS_ID, T1.PAY_TP, SUM(T1.ORD_AMT) ORD_TTL_MT
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY T1.CUS_ID, T1.PAY_TP
 HAVING SUM(T1.ORD_AMT) >= 10000
 ORDER BY SUM(T1.ORD_AMT) ASC;
 
-- HAVING은 GROUP BY 뒤에 위치한다. SQL에 ORDER BY가 있다면 HAVING은 GROUP BY와 ORDER BY 사이에 위치한다. HAVING은 AND나 OR도 사용가능하다.

-- HAVING 절에는 GROUP BY에 사용한 컬럼 또는 집계함수를 사용한 컬럼만 사용 가능하다.
SELECT T1.CUS_ID, T1.PAY_TP, SUM(T1.ORD_AMT) ORD_TTL_MT
  FROM T_ORD T1
 GROUP BY T1.CUS_ID, T1.PAY_TP
 HAVING T1.ORD_ST = 'COMP' -- ERROR
 ORDER BY SUM(T1.ORD_AMT) ASC;
-- ORD_ST가 WHERE절에 있거나 집계함수를 사용했다면 사용 가능하다.

--HAVING절 대신 인라인 뷰 사용
SELECT T1.*
FROM    (
        SELECT  T1.CUS_ID, T1.PAY_TP, SUM(T1.ORD_AMT) ORD_TTL_AMT
        FROM    T_ORD T1
        WHERE   T1.ORD_ST = 'COMP'
        GROUP BY T1.CUS_ID ,T1.PAY_TP
        ) T1
WHERE   T1.ORD_TTL_AMT >= 10000
ORDER BY T1.ORD_TTL_AMT ASC;
 