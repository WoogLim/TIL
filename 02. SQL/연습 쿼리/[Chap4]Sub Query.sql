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

-- 코드값을 가져오는 SELECT 절 상관 서브쿼리
SELECT  T1.ITM_TP
        ,(SELECT    A.BAS_CD_NM
          FROM      C_BAS_CD A
          WHERE     A.BAS_CD_DV = 'ITM_TP' AND A.BAS_CD = T1.ITM_TP AND A.LNG_CD = 'KO') ITM_TP_NM
        ,T1.ITM_ID, T1.ITM_NM
FROM    M_ITM T1;

-- 고객 정보를 가져오는 SELECT 절 상관 서브쿼리
SELECT  T1.CUS_ID
        ,TO_CHAR(T1.ORD_DT,'YYYYMMDD') ORD_YMD
        ,(SELECT A.CUS_NM FROM M_CUS A WHERE A.CUS_ID = T1.CUS_ID) CUS_NM
        ,(SELECT A.CUS_GD FROM M_CUS A WHERE A.CUS_ID = T1.CUS_ID) CUS_GD
        ,T1.ORD_AMT
FROM    T_ORD T1
WHERE   T1.ORD_DT >= TO_DATE('20170801','YYYYMMDD')
AND     T1.ORD_DT < TO_DATE('20170901','YYYYMMDD');

-- 인라인-뷰 안에서 SELECT 절 서브쿼리를 사용한 예
