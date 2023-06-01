# Fakultatív feladat: többszintű ütemező megvalósítása

Készítsen egy programot Java vagy Python nyelven, amely egy összetett ütemező működését szimulálja!

A globálisan preemptív, statikus prioritásos ütemező az alábbi ütemezési algoritmusokat futtatja az egyes szinteken az előadáson ismertetett módon:

1. magas prioritású szint (prioritás = 1) RR ütemező, időszelet: 2
2. alacsony prioritású szint (prioritás = 0) SRTF ütemező

## Bemenet (standard input, stdin)
Soronként egy (max. 10) taszk adatai. Egy sor felépítése (vesszővel elválasztva):

- a taszk betűjele (A, B, C...)
- a taszk prioritása (0 vagy 1)
- a taszk indítási ideje (egész szám >= 0), a következő időszeletben már futhat (0: az ütemező indításakor már létezik), azonos ütemben beérkező új taszkok esetén az ABC-sorrend dönt
- a taszk CPU-löketideje (egész szám >= 1)

Példa:

A,0,0,6

B,0,1,5

C,1,5,2

D,1,10,1

A bemenet végét EOF jelzi (előtte soremelés biztosan van, és üres sor is előfordulhat).

## Kimenet (standard output, stdout)
A kimenet első sorában a taszkok futási sorrendje betűjeleikkel (csak betűk, szóközök nélkül).
A második sorban a teljes várakozási idő taszkonként, érkezésük (nem feltétlenül abc-) sorrendjében, az alábbi formában (vesszővel elválasztva, szóközök nélkül):

1. taszk betűjel:várakozási idő,2. betűjel:várakozási idő, ...

Példa (a fenti bemenetre adott válasz):

ACABDB

A:2,B:8,C:0,D:0
