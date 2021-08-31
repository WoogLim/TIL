-- 17�� 8�� �� �ֹ��ݾ� ���ϱ� - SELECT�� �ܵ� ��������
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

-- 17�� 8�� �� �ֹ��ݾ�, �ֹ������� �ֹ��ݾ׺��� ���ϱ� - SELECT�� �ܵ� ��������
-- �ֹ��ݾ� ���� = �ֹ����ں� �ֹ��ݾ�(ORD_AMT) / 17��8�� �ֹ� �� �ݾ�(TOTAL_ORD_AMT) * 100.00
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

-- �ζ���-�並 ����� �ݺ� ���������� �����ϴ� ���