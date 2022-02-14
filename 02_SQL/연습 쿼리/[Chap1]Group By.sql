-- 1. Group BY �����ϱ�
-- [1] �ֹ��Ͻ�, ���������� �ֹ��ݾ�
SELECT T1.ORD_DT, T1.PAY_TP
       , SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY T1.ORD_DT, T1.PAY_TP
 ORDER BY T1.ORD_DT, T1.PAY_TP;
 
-- [2] CASE�� �̿��� ��������(ORD_AMT_TP)���� �ֹ� �Ǽ��� ī��Ʈ
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
-- ���̺��� 1, 2 �÷� �������� ����

-- ��ȸ������ ����ϴ� SQL�̶�� �̷��� �ۼ��ص� �������� ���� � ȭ�鿡���� �����������.

-- [3] TO_CHAR ������ �̿��� �ֹ����, ���������� �ֹ��Ǽ�
SELECT TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM ,T1.PAY_TP
       , COUNT(*) ORD_CNT
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY TO_CHAR(T1.ORD_DT,'YYYYMM') ,T1.PAY_TP
 ORDER BY TO_CHAR(T1.ORD_DT,'YYYYMM') ,T1.PAY_TP;
 
 -- ORDER BY���� ������ �� �ִ�.
 
-- 2. �����Լ����� case Ȱ��
-- [1] �ֹ������ ������ü(PAY_TP=BANK) �Ǽ��� ī�����(PAY_TP=CARD) �Ǽ�

SELECT TO_CHAR(T1.ORD_DT, 'YYYYMM') ORD_YM
       , SUM(CASE WHEN T1.PAY_TP = 'BANK' THEN 1 END) BANK_PAY_CNT -- ELSE�� �������� �ʾұ� ������ BANK�� �ƴ� ���� �ڵ����� NULL ó�� ������� ����.
       , SUM(CASE WHEN T1.PAY_TP = 'CARD' THEN 1 END) CARD_PAY_CNT
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY TO_CHAR(T1.ORD_DT, 'YYYYMM')
 ORDER BY TO_CHAR(T1.ORD_DT, 'YYYYMM');
 
-- �м��� �����͸� �����ֱ� ���� ���� ����ϴ� ����̴�.

-- [2] ��������(PAY_TP)�� �ֹ��Ǽ�(�ֹ� �Ǽ��� �ֹ�������� �÷����� ǥ��)

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
 
 -- THEN �� 'X'�� ���� ǥ�ø� ���ֱ����� ���Ļ� �ƹ� �ǹ� ���� ���� �־��ذ�. 
 -- ���� �м������� ���̳� �ֿ� ���� �ð��Ӽ��� ���η� �����ֱ� ���Ѵ�.
 
 -- �Ʒ��� ���� ������ε� ���� �����ϴ�.
 
-- [2] ��������(PAY_TP)�� �ֹ��Ǽ�(�ֹ� �Ǽ��� �ֹ�������� �÷����� ǥ��) - �ζ���-�� Ȱ��

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

-- ���������� �̿�. ������������ �ֹ� �����͸� PAY_TP, ORD_YM ���� ���� ī��Ʈ�� �� �������� �ۿ���
-- PAY_TP(���ҹ��)���� GROUP BY ~ CASE ó���ߴ�.

-- 3. COUNT �����Լ�

-- [1] NULL�� ���� COUNT #1
SELECT COUNT(T1.COL1) CNT_COL1
       ,COUNT(T1.COL2) CNT_COL2
       ,COUNT(T1.COL3) CNT_COL3
  FROM  (
        SELECT 'A' COL1 ,NULL COL2, 'C' COL3 FROM DUAL UNION ALL
        SELECT 'B' COL1 ,NULL COL2, NULL COL3 FROM DUAL
        ) T1;
        
-- ī��Ʈ �����Լ��� NULL���� 0���� ī��Ʈ�Ѵ�. COL1���� NUIL ���� ����. �׷��Ƿ� 2��� ����� ���´�.
-- COL2 ���� ��� NULL�̴�. ������ 0�� ���´�. COL3�� �� �� �� �� �Ǹ� NULL�̴�. �׷��Ƿ� 1�� �ȴ�.

-- [2] NULL�� ���� COUNT #2
SELECT COUNT(T1.COL1) CNT_COL1, COUNT(*) CNT_ALL
  FROM  (
        SELECT NULL COL1 FROM DUAL UNION ALL
        SELECT NULL COL1 FROM DUAL
        ) T1;
        
-- COL1 �÷��� ���� NULL�� 0�̶�� ����� �������� COUNT(*)�� �÷��� ������ ���ϴ� ���̹Ƿ� 2�� ���´�.

-- 4. �ߺ��� ������ COUNT
-- SELECT ������ DISTINCT�� ����ϸ� �ߺ��� ���ŵȴ�. COUNT�ȿ����� DISTINCT�� ����� �� �ִ�.

-- �ֹ������ �ֹ���(�ߺ����� �����ؼ� ī��Ʈ), �ֹ��Ǽ�
SELECT TO_CHAR(T1.ORD_DT, 'YYYYMM') ORD_YM
       , COUNT(DISTINCT T1.CUS_ID) CUS_CNT -- �ߺ�����
       , COUNT(*) ORD_CNT -- �ߺ�������
  FROM  T_ORD T1
 WHERE T1.ORD_DT >= TO_DATE('20170101', 'YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170401', 'YYYYMMDD')
 GROUP BY TO_CHAR(T1.ORD_DT, 'YYYYMM')
 ORDER BY TO_CHAR(T1.ORD_DT, 'YYYYMM');
 
-- �ֹ�����(ORD_ST)�� ��������(PAY_TP)�� ���տ� ���� ���� ��
SELECT COUNT(DISTINCT T1.ORD_ST || '-' || T1.PAY_TP)
  FROM T_ORD T1;
  
-- �ֹ�����(ORD_ST)�� ��������(PAY_TP)�� ���տ� ���� ���� �� - �ζ���-��
SELECT COUNT(*)
  FROM  (
        SELECT DISTINCT T1.ORD_ST, T1.PAY_TP
          FROM T_ORD T1
        ) T2;

-- �ѹ��̶� �α����� �ִ� �� ��

-- COUNT(DISTINCT) �̿�
SELECT COUNT(DISTINCT T1.��ID)
  FROM �α��� T1;

--SELECT~EXISTS�� �̿�  
SELECT COUNT(*)
  FROM �� T1
 WHERE EXISTS(
            SELECT *
              FROM �α��� A
             WHERE A.��ID = T1.��ID);
             
-- 5. HAVING
-- HAVING���� GROUP BY�� ����� ��� ���տ� ������ �� �� ��� WHERE���� ���� ����̶�� �����ϸ� �ȴ�.
-- ����� �ֹ� �ݾ��� Ư�� �ݾ�  �̻��� �����͸� ��ȸ�ϰ��� �� �� HAVING�� ����� �� �ִ�.
SELECT T1.CUS_ID, T1.PAY_TP, SUM(T1.ORD_AMT) ORD_TTL_MT
  FROM T_ORD T1
 WHERE T1.ORD_ST = 'COMP'
 GROUP BY T1.CUS_ID, T1.PAY_TP
 HAVING SUM(T1.ORD_AMT) >= 10000
 ORDER BY SUM(T1.ORD_AMT) ASC;
 
-- HAVING�� GROUP BY �ڿ� ��ġ�Ѵ�. SQL�� ORDER BY�� �ִٸ� HAVING�� GROUP BY�� ORDER BY ���̿� ��ġ�Ѵ�. HAVING�� AND�� OR�� ��밡���ϴ�.

-- HAVING ������ GROUP BY�� ����� �÷� �Ǵ� �����Լ��� ����� �÷��� ��� �����ϴ�.
SELECT T1.CUS_ID, T1.PAY_TP, SUM(T1.ORD_AMT) ORD_TTL_MT
  FROM T_ORD T1
 GROUP BY T1.CUS_ID, T1.PAY_TP
 HAVING T1.ORD_ST = 'COMP' -- ERROR
 ORDER BY SUM(T1.ORD_AMT) ASC;
-- ORD_ST�� WHERE���� �ְų� �����Լ��� ����ߴٸ� ��� �����ϴ�.

--HAVING�� ��� �ζ��� �� ���
SELECT T1.*
FROM    (
        SELECT  T1.CUS_ID, T1.PAY_TP, SUM(T1.ORD_AMT) ORD_TTL_AMT
        FROM    T_ORD T1
        WHERE   T1.ORD_ST = 'COMP'
        GROUP BY T1.CUS_ID ,T1.PAY_TP
        ) T1
WHERE   T1.ORD_TTL_AMT >= 10000
ORDER BY T1.ORD_TTL_AMT ASC;
 