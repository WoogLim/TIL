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

- 같은 서브쿼리가 두 번이나 사용되어 SQL 성능에 문제도 있지만 유지보수에도 번거로움이 발생한다.
- 같은 서브쿼리가 사용된다면 인라인-뷰로 변경하는 것이 좋다.
