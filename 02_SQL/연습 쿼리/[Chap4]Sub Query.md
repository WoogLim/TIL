### 서브쿼리

---

### 4.1.1 서브쿼리의 종류

- 서브쿼리는 성능이 좋지 못할 수 있다는 점을 알아야한다. 특정된 방법으로 제약될 가능성이 있기 때문이다.
- 허나 때에 따라서는 서브쿼리가 더 좋은 성능을 내기도 한다.
- 서브쿼리는 사용 위치와 방법에 따라 네 가지로 분류할 수 있다.

```TEXT
1. SELECT 절의 단독 서브쿼리
2. SELECT 절의 상관 서브쿼리
3. WHERE 절의 단독 서브쿼리
4. WHERE 절의 상관 서브쿼리
5. FROM 절에 사용하는 인라인-뷰

단독 서브쿼리 : 메인 SQL과 상관없이 실행
상관 서브쿼리 : 메인 SQL에서 값을 받아 처리하는 서브쿼리
메인 SQL : 서브쿼리를 제외한 모든 SQL 쿼리문
스칼라 서브쿼리 : SELECT절에서 사용된느 서브쿼리
```

---

### 4.1.2 SELECT절의 단독 서브쿼리

```SQL
-- 17년 8월 총 주문금액 구하기 - SELECT절 단독 서브쿼리
SELECT  TO_CHAR(T1.ORD_DT, 'YYYYMMDD') ORD_YMD
        ,SUM(T1.ORD_AMT) ORD_AMT
        ,(
        SELECT  SUM(A.ORD_AMT)
        FROM    T_ORD A
        WHERE   A.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
        AND     A.ORD_DT < TO_DATE('20170901','YYYYMMDD')
        ) TOTAL_ORD_AMT
FROM    T_ORD T1
WHERE   T1.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
AND     T1.ORD_DT < TO_DATE('20170901','YYYYMMDD')
GROUP BY TO_CHAR(T1.ORD_DT, 'YYYYMMDD');
```

![image](https://user-images.githubusercontent.com/51357635/131352676-7c0fb92a-fa98-44dc-9ee5-d442d67e050a.png)

```SQL
-- 17년 8월 총 주문금액, 주문일자의 주문금액비율 구하기 - SELECT절 단독 서브쿼리
-- 주문금액 비율 = 주문일자별 주문금액(ORD_AMT) / 17년8월 주문 총 금액(TOTAL_ORD_AMT) * 100.00
SELECT  TO_CHAR(T1.ORD_DT, 'YYYYMMDD') ORD_YMD
        ,SUM(T1.ORD_AMT) ORD_AMT
        ,(
        SELECT  SUM(A.ORD_AMT)
        FROM    T_ORD A
        WHERE   A.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
        AND     A.ORD_DT < TO_DATE('20170901','YYYYMMDD')
        ) TOTAL_ORD_AMT
        ,ROUND(
        SUM(T1.ORD_AMT) / (
            SELECT  SUM(A.ORD_AMT)
            FROM    T_ORD A
            WHERE   A.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
            AND     A.ORD_DT < TO_DATE('20170901','YYYYMMDD')
        ) * 100, 2) ORD_AMT_RT
FROM    T_ORD T1
WHERE   T1.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
AND     T1.ORD_DT < TO_DATE('20170901','YYYYMMDD')
GROUP BY TO_CHAR(T1.ORD_DT, 'YYYYMMDD');
```

![image](https://user-images.githubusercontent.com/51357635/131528059-6f3b7890-beb0-4ea1-a869-f56205c06ef8.png)

- 같은 서브쿼리가 두 번이나 사용되어 SQL 성능에 문제도 있지만 유지보수에도 번거로움이 발생한다.
- 같은 서브쿼리가 사용된다면 인라인-뷰로 변경하는 것이 좋다.

```SQL
-- 인라인-뷰를 사용해 반복 서브쿼리를 제거하는 방법
SELECT  T1.ORD_YMD
        ,T1.ORD_AMT
        ,T1.TOTAL_ORD_AMT
        ,ROUND(T1.ORD_AMT / T1.TOTAL_ORD_AMT * 100, 2) ORD_AMT_RT
  FROM  (
        SELECT  TO_CHAR(T1.ORD_DT, 'YYYYMMDD') ORD_YMD
                ,SUM(T1.ORD_AMT) ORD_AMT
                ,(SELECT    SUM(A.ORD_AMT)
                  FROM      T_ORD A
                  WHERE     A.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
                  AND       A.ORD_DT < TO_DATE('20170901','YYYYMMDD')
                ) TOTAL_ORD_AMT
        FROM    T_ORD T1
        WHERE   T1.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
        AND     T1.ORD_DT < TO_DATE('20170901','YYYYMMDD')
        GROUP BY TO_CHAR(T1.ORD_DT,'YYYYMMDD')
        ) T1;
```

![image](https://user-images.githubusercontent.com/51357635/131528162-6a9a7e17-0c2f-40b1-9614-63d6404628f2.png)

해당 SQL문을 카테시안-조인으로 해결할 수 있다.

```SQL
-- 카테시안-조인을 사용해 반복 서브쿼리를 제거하는 방법
SELECT  TO_CHAR(T1.ORD_DT,'YYYYMMDD') ORD_YMD
        ,SUM(T1.ORD_AMT) ORD_AMT
        ,MAX(T2.TOTAL_ORD_AMT) TOTAL_ORD_AMT
        ,ROUND(SUM(T1.ORD_AMT) / MAX(T2.TOTAL_ORD_AMT) * 100, 2) ORD_AMT_RT
FROM    T_ORD T1
        ,(  SELECT  SUM(A.ORD_AMT) TOTAL_ORD_AMT
            FROM    T_ORD A
            WHERE   A.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
            AND     A.ORD_DT < TO_DATE('20170901','YYYYMMDD')
        ) T2
WHERE   T1.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
AND     T1.ORD_DT < TO_DATE('20170901','YYYYMMDD')
GROUP BY TO_CHAR(T1.ORD_DT,'YYYYMMDD');
```

![image](https://user-images.githubusercontent.com/51357635/131528272-c8890c44-1208-4dd1-919c-c914c3c56aed.png)

- MAX(T2.TOTAL_ORD_AMT) MAX를 사용한 이유는 그대로인 값을 가져올 수 있기 때문이다. SUM을 사용한 경우 비정상적으로 큰 값이 나오게 된다. GROUP BY 사용 시 카테시안-조인된 그대로의 값을 가져오려면 MAX를 사용한다.
- 서브쿼리의 반복을 줄이면 성능을 향상시킬 수 있다.

---

### 4.1.3 SELECT절의 상관 서브쿼리

- SELECT 절의 상관 서브쿼리는 메인 SQL에서 조건 값을 받아 처리한다.
- 코드성 데이터의 명칭을 가져오거나 조인으로 가져오기 어려운 값을 처리하기 위해 사용될 수 있다.

```SQL
-- 코드값을 가져오는 SELECT 절 상관 서브쿼리
SELECT  T1.ITM_TP
        ,(SELECT    A.BAS_CD_NM
          FROM      C_BAS_CD A
          WHERE     A.BAS_CD_DV = 'ITM_TP' AND A.BAS_CD = T1.ITM_TP AND A.LNG_CD = 'KO') ITM_TP_NM
        ,T1.ITM_ID, T1.ITM_NM
FROM    M_ITM T1;
```

![image](https://user-images.githubusercontent.com/51357635/131531561-353b062e-754d-4332-9271-864c15300f04.png)

- C_BAS_CD 테이블은 기준 코드 테이블이다. SELECT 절의 서브쿼리에서 메인 SQL의 값을 가져와 사용하는 경우를 SELECT 절의 상관 서브쿼리라고 한다.
- 조인으로도 해결할 수 있으나 SQL이 지저분해진다. 때문에 코드명 처리는 조인보다 SELECT절의 상관 서브쿼리를 사용하는 것이 일반적이다.
- 서브쿼리 사용 시 캐싱 효과로 성능이 향상될 수 있다.

```TEXT
서브쿼리 캐싱
- 서브쿼리의 입력, 결괏값을 캐시에 저장해 놓고 재사용하는 것을 말한다. 코드와 같은 값의 종류가 적은 경우에만 캐싱 효과를 발휘할 수 있다.
```

### 4.1.3.1 SELECT절의 상관 서브쿼리 주의할 패턴

### (1) 반복되는 상관 서브쿼리

```SQL
-- 고객 정보를 가져오는 SELECT 절 상관 서브쿼리
SELECT  T1.CUS_ID
        ,TO_CHAR(T1.ORD_DT,'YYYYMMDD') ORD_YMD
        ,(SELECT A.CUS_NM FROM M_CUS A WHERE A.CUS_ID = T1.CUS_ID) CUS_NM
        ,(SELECT A.CUS_GD FROM M_CUS A WHERE A.CUS_ID = T1.CUS_ID) CUS_GD
        ,T1.ORD_AMT
FROM    T_ORD T1
WHERE   T1.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
AND     T1.ORD_DT < TO_DATE('20170901','YYYYMMDD');
```

![image](https://user-images.githubusercontent.com/51357635/131533064-45079dcc-c850-4a4b-8b14-be0b329feaad.png)

- 서브쿼리가 두 군데 사용되었다. 하나는 고객 명, 하나는 고객 등급 이 같은 경우는 조인으로 변경해야 한다.
