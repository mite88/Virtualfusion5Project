생성규칙

<Prefix><Version>__<Description>.sql

<Prefix>
1. V : Versioned Migration 
   - 버전 순서대로 한번만 실행(가장 많이 사용됨)
2. R : Repeatable Migration
   - 체크섬 변경시 재실행
   - version 기제 x
3. U : Undo - 잘사용하지 않음

<Version>
- 숫자(V1, V2)
- `. _` (V1_1_1, V1.1)

<Description>
github message, 설명
(띄어쓰기할땐 `_` 로 처리)