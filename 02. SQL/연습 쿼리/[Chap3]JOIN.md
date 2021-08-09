### JOIN의 종류와 이해
- 조인에는 세 가지 방법이 있다.

### [1] INNER JOIN
- 보통 조인은 INNER JOIN 이다. 이너-조인은 조인 조건을 만족하는 데이터만 결합해 결과로 내보낸다.
P1.PRO_ID = P2.PRO_ID 와 같은 조건

- SQL의 WHERE 절에 사용하는 조건은 '필터 조건'과 '조인 조건' 두 가지가 있다. FROM절에 사용된 테이블이 하나면 WHERE절엔 필터만 존재하고 두 개 이상이라면 필터 조건과 조인 조건이 동시에 존재할 수 있다. 

```SQL
SELECT T1.COL_1, T2.COL_2
FROM   ( SELECT 'A' COL_1 FROM DUAL UNION ALL
         SELECT 'B' COL_1 FROM DUAL UNION ALL
         SELECT 'C' COL_1 FROM DUAL ) T1
      ,( SELECT 'A' COL_1 FROM DUAL UNION ALL
         SELECT 'B' COL_1 FROM DUAL UNION ALL
         SELECT 'B' COL_1 FROM DUAL UNION ALL
         SELECT 'D' COL_1 FROM DUAL ) T2
WHERE T1.COL_1 = T2.COL_1;
```
![image](https://user-images.githubusercontent.com/51357635/122784408-6942ce00-d2ed-11eb-959a-59438c29140f.png)
---
- 조인 조건을 만족하느 데이터만 결합되어 결과에 나올 수 있다.
(조인 조건은 (=)만이 아닌 다른 조건식도 가능)
- 한 건과 N건이 조인되면 N건의 결과가 나온다.
(위 B가 두번 나온것과 같이)

1. INNER JOIN의 처리 과정

```SQL
SELECT  T1.CUS_ID, T1.CUS_GD, T2.ORD_SEQ, T2.CUS_ID, T2.ORD_DT
FROM    M_CUS T1
        ,T_ORD T2
WHERE   T1.CUS_ID = T2.CUS_ID
AND     T1.CUS_GD = 'A'
AND     T2.ORD_DT >= TO_DATE('20170101','YYYYMMDD')
AND     T2.ORD_DT <  TO_DATE('20170201','YYYYMMDD');
```
위 SQL은 고객과 주문 테이블을 이너 조인. 고객등급은 'A'이면서 주문일시가 '2017년 1월' 주문만 처리한다.
만일 1:1 대응이면 하나만 조회되지만 CUS_ID가 CUS_0003인 결과는 두개가 나온다. 1:M의 대응인 경우 M개가 나오기 때문이다.(INNER)
기억할 것은 조인 조건이 = 뿐 아닌 != 같은 조인도 가능하다는 것이다.

2. 여러 테이블의 조인
- 만일 여러 테이블의 조인이 일어나는 경우 이를 테이블로 생각할 것이 아닌 데이터의 집합으로 생각해야한다.
- A, B, C, D 테이블의 경우
```TEXT
첫번째
[1] A와 B를 조인한다.
[2] C와 D를 조인한다.
[3] [A,B]의 데이터 집합체 [C,D]의 데이터 집합체를 조인한다.
두번째
[1] A와 B를 조인한다.
[2] [A,B]와 C를 조인한다.
[3] [A,B,C]와 D를 조인한다.
```
- 조인의 순서가 변경되어도 성능 차이는 있을 수 있겠지만 조인 결과는 다르지 않다.
```TEXT
여러 테이블 조인 시 생각할 것
- 한순간에는 두 개의 데이터 집합에 대해서만 조인이 발생
- 조인이 이루어진 두 개의 데이터 집합은 새로운 하나의 데이터 집합
- 테이블 간의 관계를 이해하고 조인을 작성할 것
```
---
3. 잘 못 작성된 조인(M:1:M 조인)
```TEXT
관계 차수에 따른 조인 결과는 다음과 같다.
- 1:1 관계 조인 = 1건의 결과 발생
- 1:M 관계 조인 = M건의 결과 발생
- M:M 관계 조인 = M*M건의 결과 발생
```
- 위에 말한 M:M관계는 다대다 관계를 뜻.
- 다대다 관계의 경우 OLTP 환경에서 발생할 일이 거의 없으며 있다면 이는 설계가 잘못된 것임을 인지해야 함.
- 조인 실수는 보통 세 개의 테이블이 M:1:M관계일 때 발생한다.
- 여기서 1은 PK를 가진 테이블 M은 FK를 지닌 테이블일 것이다.

- 잘 못 작성한 쿼리의 예.
```SQL
1  SELECT  T1.CUS_ID, T1.CUS_NM
2          ,COUNT(DISTINCT T2.ITM_ID||'-'||TO_CHAR(T2.EVL_LST_NO)) EVAL_CNT
3          ,COUNT(DISTINCT T3.ORD_SEQ) ORD_CNT
4  FROM    M_CUS T1
5          ,T_ITM_EVL T2
6          ,T_ORD T3
7  WHERE   T1.CUS_ID = T2.CUS_ID
8  AND     T1.CUS_ID = T3.CUS_ID
9  AND     T1.CUS_ID = 'CUS_0023'
10 AND     T2.EVL_DT >= TO_DATE('20170301','YYYYMMDD')
11 AND     T2.EVL_DT <  TO_DATE('20170401','YYYYMMDD')
12 AND     T3.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
13 AND     T3.ORD_DT <  TO_DATE('20170401','YYYYMMDD')
14 GROUP BY T1.CUS_ID, T1.CUS_NM;
```
![image](https://user-images.githubusercontent.com/51357635/122789173-fc7e0280-d2f1-11eb-9bb1-1496c01e6fee.png)
```TEXT
- 위 SQL은 조인을 잘못 작성한 예이다.
SQL 문법상으로는 문제가 없지만 DISTINCT를 사용해 최종 결과를 강제로 맞힌 SQL이기 때문에 조인을 잘 못 사용한 것이다.
- 먼저 7, 9, 10, 11 순으로 T1과 T2가 필터에 따른 데이터가 조인되고 8, 12, 13 순으로 조인이 된다.
- 이 단계에서 7, 9, 10, 11의 조인 결과로 2개의 아이템평가 기록
- 두 번째 조인에서 3개의 주문 건수로 다음과 같이 2:3(M:M)조인이 발생해 최종 결과 건수는 맞지 않는 결과인 6건이 된다. 
- 아래는 (DISTINCT를 제외한 경우의 결과)
```
![image](https://user-images.githubusercontent.com/51357635/122790196-0b18e980-d2f3-11eb-8042-19c5c3515df6.png)

***
>해결 방법

(1)M:1M의 조인 해결 - UNION ALL을 사용#1

```SQL
SELECT T1.CUS_ID, MAX(T1.CUS_NM) CUS_NM, SUM(T1.EVL_CNT) EVL_CNT, SUM(T1.ORD_CNT) ORD_CNT
  FROM (
        SELECT  T1.CUS_ID, MAX(T1.CUS_NM) CUS_NM, COUNT(*) EVL_CNT, NULL ORD_CNT
        FROM    M_CUS T1
                , T_ITM_EVL T2
        WHERE   T1.CUS_ID = T2.CUS_ID
        AND     T2.CUS_ID = 'CUS_0023'
        AND     T2.EVL_DT >= TO_DATE('20170301','YYYYMMDD')
        AND     T2.EVL_DT <  TO_DATE('20170401','YYYYMMDD')
        GROUP BY T1.CUS_ID, T1.CUS_NM
        UNION ALL
        SELECT T1.CUS_ID, MAX(T1.CUS_NM) CUS_NM, NULL EVL_CNT, COUNT(*) ORD_CNT
        FROM    M_CUS T1
                , T_ORD T3
        WHERE   T1.CUS_ID = T3.CUS_ID
        AND     T3.CUS_ID = 'CUS_0023'
        AND     T3.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
        AND     T3.ORD_DT <  TO_DATE('20170401','YYYYMMDD')
        GROUP BY T1.CUS_ID, T1.CUS_NM
        ) T1
  GROUP BY T1.CUS_ID;
```
이 방법은 UNION ALL을 수행할 대상이 많고 SELECT절에 사용하는 컬럼이 만흥면 SQL작성이 번거로워지지만 이해가 쉽다.
![image](https://user-images.githubusercontent.com/51357635/127189037-7b8d5c9d-ba52-4054-aa19-71bab28ce6c7.png)

(2)M:1M의 조인 해결 - UNION ALL을 사용#2

성능을 고려해 인라인-뷰 바깥으로 옮길 수 있다.
```SQL
SELECT T1.CUS_ID, MAX(T1.CUS_NM) CUS_NM, SUM(T4.EVL_CNT) EVL_CNT, SUM(T4.ORD_CNT) ORD_CNT
  FROM M_CUS T1
        ,(
        SELECT  T2.CUS_ID, COUNT(*) EVL_CNT, NULL ORD_CNT
        FROM    T_ITM_EVL T2
        WHERE   T2.CUS_ID = 'CUS_0023'
        AND     T2.EVL_DT >= TO_DATE('20170301','YYYYMMDD')
        AND     T2.EVL_DT <  TO_DATE('20170401','YYYYMMDD')
        GROUP BY T2.CUS_ID
        UNION ALL
        SELECT  T3.CUS_ID, NULL EVL_CNT, COUNT(*) ORD_CNT
        FROM    T_ORD T3
        WHERE   T3.CUS_ID = 'CUS_0023'
        AND     T3.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
        AND     T3.ORD_DT <  TO_DATE('20170401','YYYYMMDD')
        GROUP BY T3.CUS_ID
        ) T4
  WHERE T1.CUS_ID = T4.CUS_ID
  GROUP BY T1.CUS_ID;
```
M_CUS에 대한 반복 사용을 제거해 성능에 이득이 있을 수 있다.
![image](https://user-images.githubusercontent.com/51357635/127188984-db267d12-707b-4a88-b25d-c4bb6021fd8b.png)

(3)M:1M의 조인 해결 - 1:1:1을 사용
GROUP BY를 통해 1:1:1조인을 한다.
```SQL
SELECT  T1.CUS_ID
        ,T1.CUS_NM
        ,T2.EVL_CNT
        ,T3.ORD_CNT
  FROM  M_CUS T1
        ,(
        SELECT  T2.CUS_ID
                ,COUNT(*) EVL_CNT
          FROM T_ITM_EVL T2
         WHERE T2.CUS_ID = 'CUS_0023'
           AND T2.EVL_DT >= TO_DATE('20170301','YYYYMMDD')
           AND T2.EVL_DT < TO_DATE('20170401','YYYYMMDD')
         GROUP BY T2.CUS_ID
         ) T2
         ,(
        SELECT  T3.CUS_ID
                ,COUNT(*) ORD_CNT
          FROM T_ORD T3
         WHERE T3.CUS_ID = 'CUS_0023'
           AND T3.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
           AND T3.ORD_DT < TO_DATE('20170401','YYYYMMDD')
         GROUP BY T3.CUS_ID
         ) T3
  WHERE  T1.CUS_ID = T2.CUS_ID
    AND  T1.CUS_ID = T3.CUS_ID
    AND  T1.CUS_ID = 'CUS_0023';
```
![image](https://user-images.githubusercontent.com/51357635/127343913-7ffb4bf8-acb3-4209-a085-15e4bf90c278.png)

---
### RANGE-JOIN
- 대부분 조인은 '같다(=)'조건을 이용한다. 하지만 반드시 '같다(=)'조건으로 조인히자 읺고 '범위(LIKE, >, <)'을 줄 수도 있다.
또한 때에 따라 '같지않다(!=)'를 이용할 수 있다.

- 1. CASE를 이용해 가격유형(ORD_AMT_TP)별로 주문 건수를 카운트
```SQL
SELECT  T1.ORD_ST
        ,CASE WHEN T1.ORD_AMT >= 5000 THEN 'HIGH ORDER'
              WHEN T1.ORD_AMT >= 3000 THEN 'MIDDLE ORDER'
              ELSE 'LOW ORDER'
              END ORD_AMT_TP
        ,COUNT(*) ORD_CNT
  FROM  T_ORD T1
GROUP BY T1.ORD_ST
        ,CASE WHEN T1.ORD_AMT >= 5000 THEN 'HIGH ORDER'
              WHEN T1.ORD_AMT >= 3000 THEN 'MIDDLE ORDER'
              ELSE 'LOW ORDER'
              END
ORDER BY 1, 2;
```
![image](https://user-images.githubusercontent.com/51357635/127346274-7fdaf055-97a5-45e3-b31b-5b2239642e3a.png)
위와 같은 SQL이 일회성이 아니라면 '주문금액유형'테이블을 만들어 조인으로 해결하는 것이 바람직하다.

- 주문금액유형 테이블 생성
```SQL
CREATE TABLE M_ORD_AMT_TP
(
    ORD_AMT_TP VARCHAR2(40) NOT NULL,
    FR_AMT NUMBER(18, 3) NULL,
    TO_AMT NUMBER(18, 3) NULL
);

CREATE UNIQUE INDEX PK_M_ORD_AMT_TP ON M_ORD_AMT_TP(ORD_AMT_TP);

ALTER TABLE M_ORD_AMT_TP
        ADD CONSTRAINT PK_M_ORD_AMT_TP PRIMARY KEY(ORD_AMT_TP) USING INDEX;

-- 테스트 데이터 생성.
INSERT INTO M_ORD_AMT_TP(ORD_AMT_TP, FR_AMT, TO_AMT)
SELECT 'Low Order', 0, 3000 FROM DUAL UNION ALL
SELECT 'Middle Order', 3000, 5000 FROM DUAL UNION ALL
SELECT 'High Order', 5000, 999999999999 FROM DUAL;

COMMIT;
```
![image](https://user-images.githubusercontent.com/51357635/127347520-f8e52944-0923-4e40-afa5-fca06f4d1990.png)
![image](https://user-images.githubusercontent.com/51357635/127347603-3b71453c-edd3-4a74-8450-d2df8f6305e6.png)

- RANGE-JOIN을 이용해 가격유형(ORD_AMT_TP)별로 주문 건수를 카운트
```SQL
SELECT  T1.ORD_ST, T2.ORD_AMT_TP, COUNT(*) ORD_CNT
  FROM  T_ORD T1
        , M_ORD_AMT_TP T2
 WHERE  NVL(T1.ORD_AMT,0) >= T2.FR_AMT
   AND  NVL(T1.ORD_AMT,0) <  T2.TO_AMT
 GROUP BY T1.ORD_ST, T2.ORD_AMT_TP
 ORDER BY 1, 2;
```
![image](https://user-images.githubusercontent.com/51357635/127348826-8efe9901-a880-4b1d-9a35-41d50c175f2e.png)


이처럼 '같다(=)'라는 표현 말고도 다른 조인 방법이 있다는 것을 알아야 한다.

---

### OUTER-JOIN

- INNER 조인의 경우 조건에 부합한 데이터만 결과로 나오지만 OUTER 조인의 경우 그러하지 않다.

- OUTER 조인을 위해 **기준 데이터 집합**과 **참조 데이터 집합**개념을 알아야 한다.
```TEXT
1. 기준 데이터 집합 : OUTER 조인의 기준이 되는 집합
2. 참조 데이터 집합 : OUTER 조인의 참조가 되는 집합
```
- **기준 데이터 집합(OUTER)** 은 조인 조건을 만족하지 않아도 모두 결과에 포함된다
- **'(+)'** 표시가 붙은 컬럼은 OUTER 조인의 **참조 데이터 집합**이다.
- **'(+)'** 표시가 없는 컬럼은 OUTER 조인의 **기준 데이터 집합**이다.
: 기준 데이터 집합은 조인 조건에 부합하지 않더라도 결과가 나온다
(이때, 기준 데이터 집합의 참조쪽 결과는 NULL로 값이 채워진다.)

- OUTER 조인, 한 명은 평가가 있지만 한 명은 평가가 없음.
```SQL
SELECT  T1.CUS_ID, T1.CUS_NM
        ,T2.CUS_ID, T2.ITM_ID, T2.EVL_LST_NO
  FROM  M_CUS T1
        ,T_ITM_EVL T2
 WHERE  T1.CUS_ID IN ('CUS_0002', 'CUS_0011')
   AND  T1.CUS_ID = T2.CUS_ID(+)
 ORDER BY T1.CUS_ID;
```
![image](https://user-images.githubusercontent.com/51357635/127352232-21d14090-064c-43b8-a993-89aa24bfd939.png)

- SQL 참조 순서는 다음과 같다.
```TEXT
- T1_CUS_ID = T2_CUS_ID(+)조건으로 아우터 조인을 수행한 결과
  : 조인 조건에 (+)가 붙지 않은 T1은 기준 데이터 집합
  : 조인 조건에 (+)가 붙은 T2는 참조 데이터 집합
  : 참조 데이터 집합에 CUS_0002는 존재하지 않음으로 NULL 값으로 채워짐.
  : 참조 데이터 집합에 CUS_0011은 존재하므로 정상적으로 조인이 성사
  (+)는 달라붙는쪽이며 달라붙는값이 없다면 NULL이라 생각하면 된다.
```

### 1. OUTER-JOIN의 필터 조건

- OUTER 조인 시 '참조 데이터 집합'에도 '(+)'표시를 반드시 해주어야 한다.
```SQL
-- 조건에 (+)가 없는 경우
SELECT  T1.CUS_ID, T1.CUS_NM
        ,T2.CUS_ID, T2.ITM_ID
        ,T2.EVL_LST_NO, T2.EVL_DT
  FROM  M_CUS T1
        ,T_ITM_EVL T2
 WHERE  T1.CUS_ID IN('CUS_0073')
   AND  T1.CUS_ID = T2.CUS_ID(+)
   AND  T2.EVL_DT >= TO_DATE('20170201','YYYYMMDD')
   AND  T2.EVL_DT <  TO_DATE('20170301','YYYYMMDD');
```
![image](https://user-images.githubusercontent.com/51357635/127873339-6b13694f-d188-4d6d-b29e-c987392cf78a.png)

```SQL
-- 조건에 (+)가 있는 경우
SELECT  T1.CUS_ID, T1.CUS_NM
        ,T2.CUS_ID, T2.ITM_ID
        ,T2.EVL_LST_NO, T2.EVL_DT
  FROM  M_CUS T1
        ,T_ITM_EVL T2
 WHERE  T1.CUS_ID IN('CUS_0073')
   AND  T1.CUS_ID = T2.CUS_ID(+)
   AND  T2.EVL_DT(+) >= TO_DATE('20170201','YYYYMMDD')
   AND  T2.EVL_DT(+) <  TO_DATE('20170301','YYYYMMDD');
```
![image](https://user-images.githubusercontent.com/51357635/127873472-35efbaed-872a-47cc-9cc6-1ea74e47aa65.png)

```TEXT
1. 참조 쪽 필터 조건에(+)사용한 경우 : OUTER조인 전 필터 조건이 사용된다.
2. 참조 쪽 필터 조건에(+)미사용 : OUTER조인 후, 조인 결과에 필터 조건이 사용된다.
즉, OUTER조인이 이루어지면 참조 데이터 집합의 EVL_DT는 NULL값이 되므로 필터 조건을 적용하면 데이터가 없다. 결과적으로 INNER조인과 같아진다.
```

### 2. 실행이 불가능한 OUTER조인

- OUTER조인에서 '(+)'표시가 된 참조 데이터 집합은 두 개 이상의 기준 데이터 집합을 동시에 가질 수 없다.

```SQL
-- 불가능한 OUTER 조인
SELECT  T1.CUS_ID, T2_ITM_ID, T1.ORD_DT, T3.ITM_ID, T3.EVL_PT
  FROM  T_ORD T1
        , T_ORD_DET T2
        , T_ITM_EVL T3
 WHERE  T1.ORD_SEQ = T2.ORD_SEQ
   AND  T1.CUS_ID = 'CUS_0002'
   AND  T1.ORD_DT >= TO_DATE('20170122','YYYYMMDD')
   AND  T1.ORD_DT < TO_DATE('20170123','YYYYMMDD')
   AND  T3.CUS_ID(+) = T1.CUS_ID
   AND  T3.ITM_ID(+) = T2.ITM_ID;
```
OUTER 조인에서 참조 데이터 집합은 기준 데이터 집합을 두 개 가질 수 없다. 
이를 해결하기 위해선 인라인-뷰를 이용해야 한다.

```SQL
-- 인라인-뷰를 사용한 OUTER 조인
SELECT  T0.CUS_ID, T0.ITM_ID, T0.ORD_DT
        ,T3.ITM_ID, T3.EVL_PT
  FROM  (
        SELECT  T1.CUS_ID, T2.ITM_ID, T1.ORD_DT
          FROM  T_ORD T1
                ,T_ORD_DET T2
         WHERE  T1.ORD_SEQ = T2.ORD_SEQ
           AND  T1.CUS_ID = 'CUS_0002'
           AND  T1.ORD_DT >= TO_DATE('20170122','YYYYMMDD')
           AND  T1.ORD_DT < TO_DATE('20170123','YYYYMMDD')
        ) T0
        , T_ITM_EVL T3
 WHERE  T3.CUS_ID(+) = T0.CUS_ID
   AND  T3.ITM_ID(+) = T0.ITM_ID
 ORDER BY T0.CUS_ID;
```
![image](https://user-images.githubusercontent.com/51357635/127875626-8f1dee80-9a03-4b9a-be6b-f688731dc582.png)

```SQL
-- ANSI 표준 SLQ
SELECT  T1.CUS_ID, T2.ITM_ID, T1.ORD_DT
        ,T3.ITM_ID, T3.EVL_PT
  FROM  T_ORD T1
        INNER JOIN T_ORD_DET T2
          ON(T1.ORD_SEQ = T2.ORD_SEQ
            AND T1.CUS_ID = 'CUS_0002'
            AND T1.ORD_DT >= TO_DATE('20170122','YYYYMMDD')
            AND T1.ORD_DT < TO_DATE('20170123','YYYYMMDD'))
        LEFT OUTER JOIN T_ITM_EVL T3
          ON(T3.CUS_ID = T1.CUS_ID
            AND T3.ITM_ID = T2.ITM_ID);
```
대부분 DBMS가 ANSI표준을 이용하기 때문에 ANSI표준을 써도 무방하다.

### 3. OUTER-JOIN이 포함된 여러 테이블의 조인
```SQL
SELECT  T1.CUS_ID, T2.ORD_SEQ, T2.ORD_DT, T3.ORD_SEQ, T3.ITM_ID
  FROM  M_CUS T1
        ,T_ORD T2
        ,T_ORD_DET T3
 WHERE  T1.CUS_ID = 'CUS_0073'
   AND  T1.CUS_ID = T2.CUS_ID(+)
   AND  T2.ORD_DT(+) >= TO_DATE('20170122','YYYYMMDD')
   AND  T2.ORD_DT(+) < TO_DATE('20170123','YYYYMMDD')
   AND  T3.ORD_SEQ = T2.ORD_SEQ;
```
위 쿼리의 결과는 없다.

![image](https://user-images.githubusercontent.com/51357635/128044857-ed606614-0bcc-472b-9294-7dd930c87bf2.png)

먼저 6번 라인의 OUTER조인의 ORD_SEQ가 NULL값이기 때문이다.
NULL 값은 비교할 수 없으며 마지막 라인의 이너 조인으로 인해 성공하는 데이터가 없다.
조인 결과가 나오게 하려면 마지막 라인을 "T3.ORD_SEQ(+) = T2.ORD_ESQ"와 같이 OUTER조인으로 변경해야한다.

### 4. OUTER-JOIN의 응용

- OUTER 조인은 조인에 성공하지 못하더라도 기준 데이터 집합은 무조건 조회되는 특성이 있으며 참조 데이터는 NULL 값이라도 표현해준다.
- 분석 리포트에서 실적이 없는 마스터도 결과에 포함시킬때 응용한다.

```SQL
-- 고객ID별 주문건수, 주문이 없는 고객도 나오도록 처리
SELECT  T1.CUS_ID
        ,COUNT(*) ORD_CNT_1
        ,COUNT(T2.ORD_SEQ) ORD_CNT_2
  FROM  M_CUS T1
        ,T_ORD T2
 WHERE  T1.CUS_ID = T2.CUS_ID(+)
   AND  T2.ORD_DT(+) >= TO_DATE('20170101','YYYYMMDD')
   AND  T2.ORD_DT(+) < TO_DATE('20170201','YYYYMMDD')
 GROUP BY T1.CUS_ID
 ORDER BY COUNT(*), T1.CUS_ID;
```

![image](https://user-images.githubusercontent.com/51357635/128046086-269eb33a-b7e1-43da-97e1-30b3d91c781a.png)(결과가 많아 일부 생략)

OUTER조인과 집계함수 이용 시 주의가 필요하다. COUNT 대상에 따라 결과가 다르기 때문이다.
ORD_CNT_1은 NULL을 포함한 결과의 갯수 ORD_CNT_2는 주문 건수 이다.

```SQL
-- 아이템 ID별 주문수량
-- 'PC, ELEC' 아이템 유형의 아이템별 주문수량 조회(주문이 없더라도 결과가 0으로 나와야 한다.)
SELECT  T1.ITM_ID, T1.ITM_NM, NVL(T2.ORD_QTY,0)
  FROM  M_ITM T1
        ,(  SELECT  B.ITM_ID, SUM(B.ORD_QTY) ORD_QTY
              FROM  T_ORD A
                    ,T_ORD_DET B
             WHERE  A.ORD_SEQ = B.ORD_SEQ
               AND  A.ORD_ST = 'COMP' --주문상태 = 완료
               AND  A.ORD_DT >= TO_DATE('20170101','YYYYMMDD')
               AND  A.ORD_DT < TO_DATE('20170201','YYYYMMDD')
             GROUP BY B.ITM_ID ) T2
 WHERE  T1.ITM_ID = T2.ITM_ID(+)
   AND  T1.ITM_TP IN ('PC','ELEC')
 ORDER BY T1.ITM_TP, T1.ITM_ID;
```

![image](https://user-images.githubusercontent.com/51357635/128048020-8f462d76-c9f6-49b6-b893-9cf99088bbf6.png)

인라인 뷰를 이용해 실적 데이터를 M_ITM 테이블의 ITM_ID로 GROUP BY 한 후 조인하고 있다.
적절히 가독성 좋은 SQL은 이렇듯 유지보수에 도움을 준다.

- 마무리. OUTER 조인의 특징
```TEXT
1. OUTER 조인은 기준 데이터 집합과 참조 데이터 집합으로서 조인이 이루어진다.
2. 참조 데이터 집합은 조인 조건에 (+)가 표시된 쪽이며 반대쪽은 기준 데이터 집합이 된다.
3. 기준 데이터 집합은 조인 조건을 만족하지 않아도 필터 조건만 만족하면 결과가 나온다.
(이 때, 참조 데이터 집합의 결과는 NULL 값으로 채워진다.)
4. 참조 데이터 집합의 필터 조건에 (+)를 표시하면 OUTER조인 전 필터가 된다.
5. 참조 데이터 집합의 필터 조건에 (+)를 표시하지 않으면 OUTER조인 후 필터가 된다.
6. 일반적으로 참조 데이터 집합의 필터 조건에는 (+)를 표시한다.
7. 참조 데이터 집합이 다른 집합과 조인될 때는 기준 집합으로서 OUTER조인을 해야 한다.
```

---
### CATESIAN-JOIN

### 1. CATESIAN-JOIN 이해하기
- 일명 '묻지마 조인'이라 불린다.
- FROM절에 테이블 A와 B가 있는 경우 A에는 2건, B에는 4건이 있을 때, 조인 조건을 주지 않는 다면 카테시안-조인이 된다. 결국 8건의 데이터가 만들어진다.

```SQL
-- 고객등급(M_CUS, CUS_GD)과 아이템 유형(M_ITM, ITM_TP)의 조합 가능한 모든 데이터
SELECT  T1.CUS_GD, T2.ITM_TP
  FROM  (SELECT DISTINCT A.CUS_GD FROM M_CUS A) T1
        ,(SELECT DISTINCT A.ITM_TP FROM M_ITM A) T2
 ORDER BY T1.CUS_GD, T2.ITM_TP;
```
![image](https://user-images.githubusercontent.com/51357635/128734653-74f9aea0-657c-47d9-a498-2e5d748f87f4.png)

```TEXT
1. CUS_GD의 종류를 가져온 데이터 집합
2. ITM_TP의 종류를 가져온 데이터 집합
3. 1번과 2번의 조인 조건이 없다. CATESIAN조인을 수행
- 1번 집합의 CUS_GD A는 2번 집합의 4건과 조인되어 4건이 만들어진다.
- 1번 집합의 CUS_GD B도 2번 집합의 4건과 조인되어 4건이 만들어진다.
- 최종 8건의 결과 건수가 만들어진다.
```

### 2. CATESIAN-JOIN의 위험성
- 카테시안-조인은 매우 위험한 조인이다. 시스템 장애를 유발할 수 있기 때문이다.
- 조인 조건의 누락이 일어나거나 별칭에 실수가 발생 하면 이 문제를 야기할 수 있다. 때문에 SQL 실행 전 조인 조건을 한 번 더 확인해야 한다.
  
```SQL
-- 조인 조건의 누락
SELECT  COUNT(*)
  FROM  T_ORD T1
        ,T_ORD_DET T2;
```
```SQL
-- 조인 조건의 별칭 실수
SELECT  COUNT(8)
  FROM  T_ORD T1
        ,T_ORD_DET T2
 WHERE  T1.ORD_SEQ = T1.ORD_SEQ
```

### 3. 분석마스터 만들기
- 카테시안-조인은 분석 마스터를 만들 때 유용하다.

```SQL
-- 특정 고객 두 명의 2월, 3월, 4월별 주문 건수
SELECT  T1.CUS_ID, T1.CUS_NM, T2.ORD_YM, T2.ORD_CNT
  FROM  M_CUS T1
        ,(  SELECT  A.CUS_ID
                    ,TO_CHAR(A.ORD_DT,'YYYYMM') ORD_YM
                    ,COUNT(*) ORD_CNT
              FROM  T_ORD A
             WHERE  A.CUS_ID IN ('CUS_0003','CUS_0004')
               AND  A.ORD_DT >= TO_DATE('20170201','YYYYMMDD')
               AND  A.ORD_DT < TO_DATE('20170501','YYYYMMDD')
             GROUP BY   A.CUS_ID
                        ,TO_CHAR(A.ORD_DT,'YYYYMM')
        ) T2
 WHERE  T1.CUS_ID IN ('CUS_0003','CUS_0004')
   AND  T1.CUS_ID = T2.CUS_ID(+)
 ORDER BY  T1.CUS_ID, T2.ORD_YM;
```
![image](https://user-images.githubusercontent.com/51357635/128737470-a5b4a806-bd38-4d5a-ba45-d0c86b9fed2a.png)