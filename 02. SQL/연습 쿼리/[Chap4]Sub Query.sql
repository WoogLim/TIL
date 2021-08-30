SELECT T0.CUS_ID, T0.CUS_NM, T0.BASE_YM, NVL(T2.ORD_CNT,0) ORD_CNT
  FROM ( SELECT T1.CUS_ID
                ,T1.CUS_NM
                ,T4.BASE_YM
           FROM M_CUS T1
                ,(
                SELECT TO_CHAR(ADD_MONTHS(TO_DATE('20170201','YYYYMMDD'), ROWNUM - 1),'YYYYMM') BASE_YM
                  FROM DUAL
                  CONNECT BY ROWNUM <= 3
                  ) T4
          WHERE T1.CUS_ID IN('CUS_0003','CUS_0004')) T0
         ,(
         SELECT A.CUS_ID
                ,TO_CHAR(A.ORD_DT,'YYYYMM') ORD_YM
                ,COUNT(*) ORD_CNT
           FROM T_ORD A
          WHERE A.CUS_ID IN('CUS_0003','CUS_0004')
            AND A.ORD_DT >= TO_DATE('20170201','YYYYMMDD')
            AND A.ORD_DT < TO_DATE('20170501','YYYYMMDD')
          GROUP BY A.CUS_ID
                   , TO_CHAR(A.ORD_DT,'YYYYMM')) T2
 WHERE T0.CUS_ID = T2.CUS_ID(+)
   AND T0.BASE_YM = T2.ORD_YM(+)
 ORDER BY T0.CUS_ID, T0.BASE_YM;