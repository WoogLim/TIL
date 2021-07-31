### ROLLUP 이해하기
- 소계와 전체합계를 구하는 방법에는 여러 가지가 있지만, BI툴 없이 순수 SQL만 사용한다면 ROLLUP이 가장 효율적이다.

- ROLLUP은 'GROUP BY'뒤에 'ROLLUP' 이라고 적어서 사용한다.
1. GROUP BY
```sql
SELECT TO_CHAR(T1.ORD_DT, 'YYYYMM') ORD_YM
       , T1.CUS_ID
       , SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN ('CUS_0001','CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
   GROUP BY TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID;
```
![image](https://user-images.githubusercontent.com/51357635/115246849-36e7e980-a161-11eb-9d8a-98e30ffe98d8.png)
---

2. ROLLUP
```sql
SELECT TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM
       , T1.CUS_ID
       , SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN ('CUS_0001','CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
 GROUP BY
 ROLLUP(TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID);
```
![image](https://user-images.githubusercontent.com/51357635/115247418-baa1d600-a161-11eb-95dc-ef292c900101.png)
- GROUP BY절과는 달리 주문년월별 소계 두 건과 전체합계 한 건이 추가되어 있다.
  주문년월별 소계는 ORD_YM값은 있으나 CUS_ID는 NULL이다. 주문년월별 소계이므로 CUS_ID를 특정 짓지 못해 CUS_ID는 NULL로 표시가 되었다.
  이처럼 ROLLUP을 사용하면 소계(중간합계)와 전체합계를 추가할 수 있다.
  
---

2. ROLLUP의 컬럼 순서
- ROLLUP은 컬럼 순으로 계층 소계를 만든다. 
  ex) GROUP BY ROLLUP(A,B,C,D) : A+B+C별 소계, A+B별 소계, A별 소계, 전체합계
  
```sql
SELECT T1.ORD_ST, TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM, T1.CUS_ID
       , SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN ('CUS_0001', 'CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
 GROUP BY ROLLUP(T1.ORD_ST, TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID)
 ORDER BY T1.ORD_ST, TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID;
```
![image](https://user-images.githubusercontent.com/51357635/115250280-5f251780-a164-11eb-965b-0b6019e35890.png)
- ORD_ST별 소계가 포함되어 있다.
  ORD_YM별 소계가 포함되어 있지 않다.

```sql
SELECT T1.ORD_ST, TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM, T1.CUS_ID
       , SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN ('CUS_0001', 'CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
 GROUP BY ROLLUP(TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.ORD_ST, T1.CUS_ID)
 ORDER BY T1.ORD_ST, TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID;
```
![image](https://user-images.githubusercontent.com/51357635/115250476-94ca0080-a164-11eb-8eb3-4f39cdd8eba5.png)
- ORD_ST별 소계가 없다.
  ORD_YM별 소계가 포함되어 있다.
  
---
3. GROUPING
- GROUPING함수는 특정 컬럼의 값이 소계인지 아닌지 구분해준다.<br/>
- ROLLUP에서 만들어진 소계에서 ROLLUP된 컬럼은 NULL로 표시된다. 그러므로 원래 NULL값인 데이터가 ROLLUP되면, 원래 데이터인지 ROLLUP된 결과인지 구분할 수 없다.

```sql
SELECT T1.ORD_ST, T1.PAY_TP, COUNT(8) ORD_CNT
  FROM T_ORD T1
 GROUP BY ROLLUP(T1.ORD_ST, T1.PAY_TP);
```
![image](https://user-images.githubusercontent.com/51357635/115563550-a2ad8c00-a2f2-11eb-8f35-626aaf0d3034.png)
- ORD_ST가 WAIT이고 PAY_TP가 NULL인 데이터가 두 건 나온다.<br/>
- 한 건은 ROLLUP된 처리 소계, 한 건은 GROUP BY만 처리된 값.<br/>
- 두 건다 값이 NULL이므로 구별이 쉽지 않다.

```sql
SELECT T1.ORD_ST, GROUPING(T1.ORD_ST) GR_ORD_ST
       , T1.PAY_TP, GROUPING(T1.PAY_TP) GR_PAY_TP
       , COUNT(*) ORD_CNT
  FROM T_ORD T1
 GROUP BY ROLLUP(T1.ORD_ST, T1.PAY_TP);
```
![image](https://user-images.githubusercontent.com/51357635/115564916-e1901180-a2f3-11eb-93a2-af1f2b005f42.png)
- 소계에 해당하는 데이터는 1로 결과가 나온다.

```sql
SELECT CASE WHEN GROUPING(T1.ORD_ST) = 1 THEN 'TOTAL' ELSE T1.ORD_ST END ORD_ST,
       CASE WHEN GROUPING(T1.PAY_TP) = 1 THEN 'TOTAL' ELSE T1.PAY_TP END PAY_TP,
       COUNT(*) ORD_CNT
  FROM T_ORD T1
 GROUP BY ROLLUP(T1.ORD_ST, T1.PAY_TP)
 ORDER BY T1.ORD_ST, T1.PAY_TP;
```
![image](https://user-images.githubusercontent.com/51357635/115566436-34b69400-a2f5-11eb-9b3d-8bfddbc3db62.png)
- 결과 값에 따라 표시를 달리할 수 있다.

---

4. ROLLUP 컬럼의 선택 ~~모르겠다면 참고만!~~
- 특정 컬럼의 소계 및 전체 합계만 필요할 때가 있다.

```sql
SELECT CASE WHEN GROUPING(TO_CHAR(T2.ORD_DT, 'YYYYMM'))=1 THEN 'TOTAL'
            ELSE TO_CHAR(T2.ORD_DT,'YYYYMM') END ORD_YM,
       CASE WHEN GROUPING(T1.RGN_ID) = 1 THEN 'TOTAL' ELSE T1.RGN_ID END RGN_ID,
       CASE WHEN GROUPING(T1.CUS_GD) = 1 THEN 'TOTAL' ELSE T1.CUS_GD END CUS_GD,
       SUM(T2.ORD_AMT) ORD_AMT
  FROM M_CUS T1,
       T_ORD T2
 WHERE T1.CUS_ID = T2.CUS_ID
   AND T2.ORD_DT >= TO_DATE('20170201','YYYYMMDD')
   AND T2.ORD_DT < TO_DATE('20170401','YYYYMMDD')
   AND T1.RGN_ID IN ('A','B')
   GROUP BY ROLLUP(TO_CHAR(T2.ORD_DT,'YYYYMM'), T1.RGN_ID, T1.CUS_GD)
   ORDER BY TO_CHAR(T2.ORD_DT,'YYYYMM'), T1.RGN_ID, T1.CUS_GD;
```
![image](https://user-images.githubusercontent.com/51357635/115571353-de981f80-a2f9-11eb-8682-a901671dfc7e.png)

- 위 SQL은 다음과 같은 소계와 전체합계를 만든다.<br/>- TO_CHAR(T2.ORD_DT,'YYYYMM'), T1.RGN_ID별 소계<br/>- TO_CHAR(T2.ORD_DT,'YYYYMM')별 소계<br/>- 전체합계

5. ROLLUP 컬럼 묶기
- ROLLUP 사용시 여러 컬럼을 하나의 괄호로 묶을 수 있다.<br/>묶인 여러 컬럼은 하나의 단위로 ROLLUP된다.

```sql
SELECT CASE WHEN GROUPING(TO_CHAR(T2.ORD_DT, 'YYYYMM'))=1 THEN 'TOTAL'
            ELSE TO_CHAR(T2.ORD_DT,'YYYYMM') END ORD_YM,
       CASE WHEN GROUPING(T1.RGN_ID) = 1 THEN 'TOTAL' ELSE T1.RGN_ID END RGN_ID,
       CASE WHEN GROUPING(T1.CUS_GD) = 1 THEN 'TOTAL' ELSE T1.CUS_GD END CUS_GD,
       SUM(T2.ORD_AMT) ORD_AMT
  FROM M_CUS T1,
       T_ORD T2
 WHERE T1.CUS_ID = T2.CUS_ID
   AND T2.ORD_DT >= TO_DATE('20170201','YYYYMMDD')
   AND T2.ORD_DT < TO_DATE('20170401','YYYYMMDD')
   AND T1.RGN_ID IN ('A','B')
 GROUP BY ROLLUP((TO_CHAR(T2.ORD_DT,'YYYYMM'), T1.RGN_ID, T1.CUS_GD))
 ORDER BY TO_CHAR(T2.ORD_DT,'YYYYMM'), T1.RGN_ID, T1.CUS_GD;
```
![image](https://user-images.githubusercontent.com/51357635/115572661-10f64c80-a2fb-11eb-9567-452a646e0641.png)
- 이처럼 ROLLUP안에 정의된 모든 컬럼이 하나로 인식되어, 전체합계를 구할 수 있다.
- **만일 GROUP BY A, B, C, D, E, F 와 같이 여러 컬럼 중 앞쪽 3개 컬럼까지의 소계와 전체합계가 필요하다면 <br/>GROUP BY ROLLUP(A, B, C, (D,E,F))해주면 된다.**

---

### 소계를 구하는 다른 방법

1. ROLLUP을 대신하는 방법

- [!] ROLLUP을 사용할때.
```sql
SELECT TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM, T1.CUS_ID,
       SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN('CUS_0001','CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
 GROUP BY ROLLUP(TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID);
```
![image](https://user-images.githubusercontent.com/51357635/115575334-6d5a6b80-a2fd-11eb-8696-e815c9f917a2.png)

- [1] UNION ALL로 대신하기
```sql
SELECT TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM, T1.CUS_ID,
       SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN ('CUS_0001','CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
 GROUP BY TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID
UNION ALL
SELECT TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM, 'TOTAL' CUS_ID,
       SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN ('CUS_0001','CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
 GROUP BY TO_CHAR(T1.ORD_DT,'YYYYMM')
UNION ALL
SELECT 'TOTAL' ORD_YM, 'TOTAL' CUS_ID,
       SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN('CUS_0001','CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD');
```
![image](https://user-images.githubusercontent.com/51357635/115576593-a0512f00-a2fe-11eb-997e-32a272497d36.png)
- UNION ALL을 사용한 방법은 T_ORD를 세 번 접급하기 때문에 성능면에서 손해를 본다.<br/>소계가 필요한 만큼 UNION ALL이 늘어가 성능은 나빠진다.

- [2] 카테시안-조인 대신하기
```text
[!] 카테시안 조인이란?
FROM절에 두 개 이상의 테이블이 있는 경우 조인 조건을 주지 않으면 카테시안 조인이 발생한다.
카테시안 조인은 대상의 건수를 곱한 만큼의 결과가 만들어진다.
예로 A테이블에 5건, B테이블에 10건 존재시 카테시안 조인이 하면 50건의 조인 결과가 나온다.
```
```sql
SELECT CASE WHEN T2.RNO = 1 THEN TO_CHAR(T1.ORD_DT,'YYYYMM')
            WHEN T2.RNO = 2 THEN TO_CHAR(T1.ORD_DT,'YYYYMM')
            WHEN T2.RNO = 3 THEN 'TOTAL' END ORD_YM,
       CASE WHEN T2.RNO = 1 THEN T1.CUS_ID
            WHEN T2.RNO = 2 THEN 'TOTAL'
            WHEN T2.RNO = 3 THEN 'TOTAL' END CUS_ID,
       SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
       ,(
            SELECT ROWNUM RNO FROM DUAL CONNECT BY ROWNUM <= 3
       ) T2
 WHERE T1.CUS_ID IN ('CUS_0001','CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
 GROUP BY CASE WHEN T2.RNO = 1 THEN TO_CHAR(T1.ORD_DT,'YYYYMM')
               WHEN T2.RNO = 2 THEN TO_CHAR(T1.ORD_DT,'YYYYMM')
               WHEN T2.RNO = 3 THEN 'TOTAL' END,
          CASE WHEN T2.RNO = 1 THEN T1.CUS_ID
               WHEN T2.RNO = 2 THEN 'TOTAL'
               WHEN T2.RNO = 3 THEN 'TOTAL' END;
```
![image](https://user-images.githubusercontent.com/51357635/115580000-a8f73480-a301-11eb-9966-f3be895a7ac8.png)
-  WHERE 절을 보면 인라인 뷰에 대한 조인조건이 없다. 조인 조건이 없다면 집합을 구성하는 모든 데이터 간 조인이 발생한다.<br/>때문에 인라인 뷰에 의해 데이터가 세 배로 늘어난다.
-  이 방법은 배로 늘어나는 데이터로 성능 저하가 발생하며 SQL구문 또한 어렵기 때문에 타인이 이해하기 힘들다.

- [3] WITH와 UNION ALL로 대신하기

```sql
WITH T_RES AS(
    SELECT TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM, T1.CUS_ID,
           SUM(T1.ORD_AMT) ORD_AMT
      FROM T_ORD T1
     WHERE T1.CUS_ID IN ('CUS_0001','CUS_0002')
       AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
       AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
     GROUP BY TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID
     )
SELECT T1.ORD_YM, T1.CUS_ID, T1.ORD_AMT
  FROM T_RES T1
UNION ALL
SELECT T1.ORD_YM, 'TOTAL', SUM(T1.ORD_AMT)
  FROM T_RES T1
 GROUP BY T1.ORD_YM
UNION ALL
SELECT 'TOTAL', 'TOTAL', SUM(T1.ORD_AMT)
  FROM T_RES T1;
```
![image](https://user-images.githubusercontent.com/51357635/115581418-f45e1280-a302-11eb-9dc9-a42e62e6e7f9.png)
- WITH 절을 이용해 GROUP BY된 결과를 T_RES로 정의. 아래의 메인 SQL에서 T_RES를 UNION ALL 하는 방법이다.
- 작성양도 적고 보기에도 가독성이 좋다.

```
**ROLLUP을 대신할 다양한 방법이 있지만, ROLLUP을 사용하는 것이 일반적으로 좋다. 필요한 결과를 만들어내는 방법은 다양하다.**
```

---

### CUBE
- CUBE는 조합 가능한 모든 소계를 만들어 낸다 사용 방법은 ROLLUP과 같으며 ROLLUP이 사용된 부분을 CUBE로 변경해 주기만 하면 된다.
- ex) GROUP BY CUBE(A, B, C)<br/>- A+B별 소계<br/>- A별 소계<br/>- A+C별 소계<br/>- B+C별 소계<br/>- B별 소계<br/>- C별 소계

```sql
SELECT CASE WHEN GROUPING(T1.ORD_ST) = 1 THEN 'TOTAL' ELSE T1.ORD_ST END ORD_ST,
       CASE WHEN GROUPING(TO_CHAR(T1.ORD_DT,'YYYYMM')) = 1 THEN 'TOTAL' ELSE TO_CHAR(T1.ORD_DT,'YYYYMM') END ORD_YM,
       CASE WHEN GROUPING(T1.CUS_ID) = 1 THEN 'TOTAL' ELSE T1.CUS_ID END CUS_ID,
       SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.CUS_ID IN('CUS_0001','CUS_0002')
   AND T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
 GROUP BY CUBE(T1.ORD_ST, TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID)
 ORDER BY T1.ORD_ST, TO_CHAR(T1.ORD_DT,'YYYYMM'), T1.CUS_ID;
```
![image](https://user-images.githubusercontent.com/51357635/115583432-d7c2da00-a304-11eb-9252-87de2a607f0e.png)
- 실제로 CUBE를 사용하는 경우는 드물며, 컬럼이 많고 처리할 데이터가 많을수록 성능이 좋지 못하다.
- 만일 CUBE를 사용해야 한다면 ROLLUP과 추가적인 UNION ALL, WITH절과 ROLLUP으로 해결할 수 있는지 고민해야한다.

### GROUPING SETS
- GROUPING SETS도 ROLLUP이나 CUBE처럼 소계를 만든다.
- GROUP BY 뒤에 ROLLUP이나 CUBE대신 GROUPING SETS라고 표기한 후 그룹화할 컬럼을 괄호로 묶는다.

```sql
SELECT TO_CHAR(T1.ORD_DT,'YYYYMM') ORD_YM,
       T1.CUS_ID,
       COUNT(*) ORD_CNT,
       SUM(T1.ORD_AMT) ORD_AMT
  FROM T_ORD T1
 WHERE T1.ORD_DT >= TO_DATE('20170301','YYYYMMDD')
   AND T1.ORD_DT < TO_DATE('20170501','YYYYMMDD')
   AND T1.CUS_ID IN ('CUS_0061','CUS_0062')
 GROUP BY GROUPING SETS(
                    (TO_CHAR(T1.ORD_DT,'YYYYMM'),T1.CUS_ID) -- GROUP BY 기본 데이터
                    ,(TO_CHAR(T1.ORD_DT,'YYYYMM')) -- 주문년월별 소계
                    ,(T1.CUS_ID) -- 고객ID별 소계
                    ,() -- 전체합계
                    );
```
![image](https://user-images.githubusercontent.com/51357635/115584725-f8d7fa80-a305-11eb-8a10-2d2cba36d442.png)

