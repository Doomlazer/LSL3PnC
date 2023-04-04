;;; Sierra Script 1.0 - (do not remove this comment)
(script# 984)
(include sci.sh)
(use Main)
(use Sight)
(use System)

(public
	SortedAdd 0
)

(procedure (SortedAdd param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13 temp14 temp15 temp16)
	(asm
		pushi    #add
		pushi    0
		pushi    23
		pushi    1
		lofss    {fl}
		pushi    #new
		pushi    0
		class    EventHandler
		send     4
		sat      temp10
		send     10
		pushi    #add
		pushi    0
		pushi    23
		pushi    1
		lofss    {ol}
		pushi    #new
		pushi    0
		class    EventHandler
		send     4
		sat      temp13
		send     10
		pushi    #add
		pushi    0
		pushi    23
		pushi    1
		lofss    {bl}
		pushi    #new
		pushi    0
		class    EventHandler
		send     4
		sat      temp16
		send     10
		pushi    #add
		pushi    5
		lst      temp10
		lst      temp13
		lsg      regions
		lsg      locales
		lst      temp16
		lap      param2
		sat      temp1
		send     14
		-ap      argc
code_0060:
		ldi      1
		bnt      code_01ea
		ldi      0
		sat      temp14
		sat      temp11
		sat      temp8
		ldi      32767
		sat      temp15
		sat      temp12
		sat      temp9
		ldi      1
		sat      temp0
code_007a:
		lst      temp0
		lap      argc
		lt?     
		bnt      code_01ae
		lat      temp0
		lapi     param2
		sat      temp2
		pushi    1
		pushi    #elements
		pushi    0
		lat      temp2
		send     4
		push    
		callk    FirstNode,  2
		sat      temp3
code_0096:
		lat      temp3
		bnt      code_01a9
		pushi    1
		pushi    1
		lst      temp3
		callk    NodeValue,  2
		sat      temp5
		push    
		callk    IsObject,  2
		bnt      code_01a9
		pushi    #firstTrue
		pushi    2
		pushi    101
		lst      temp5
		lat      temp1
		send     8
		bnt      code_00bb
		jmp      code_019e
code_00bb:
		pushi    #distanceTo
		pushi    1
		lst      temp5
		lap      param1
		send     6
		sat      temp4
		pushi    2
		pushi    #heading
		pushi    0
		lap      param1
		send     4
		push    
		pushi    4
		pushi    #x
		pushi    0
		lap      param1
		send     4
		push    
		pushi    #y
		pushi    0
		lap      param1
		send     4
		push    
		pushi    #x
		pushi    0
		lat      temp5
		send     4
		push    
		pushi    #y
		pushi    0
		lat      temp5
		send     4
		push    
		callk    GetAngle,  8
		push    
		calle    AngleDiff,  4
		sat      temp6
		pushi    2
		lst      temp6
		pushi    90
		calle    umod,  4
		push    
		ldi      0
		eq?     
		bnt      code_0111
		-at      temp6
code_0111:
		pushi    2
		lst      temp5
		lsp      param1
		calle    CantBeSeen,  4
		sat      temp7
		bnt      code_012c
		pushi    2
		lst      temp6
		lst      temp4
		callk    SinDiv,  4
		sat      temp4
		jmp      code_0152
code_012c:
		pushi    1
		lst      temp6
		callk    Abs,  2
		push    
		ldi      90
		gt?     
		bnt      code_0143
		ldi      89
		sat      temp6
		lst      temp4
		ldi      10
		mul     
		sat      temp4
code_0143:
		pushi    1
		pushi    2
		lst      temp6
		lst      temp4
		callk    CosDiv,  4
		push    
		callk    Abs,  2
		sat      temp4
code_0152:
		lst      temp4
		ldi      0
		lt?     
		bnt      code_015e
		ldi      32767
		sat      temp4
code_015e:
		lat      temp7
		bnt      code_0173
		lst      temp4
		lat      temp15
		le?     
		bnt      code_0171
		lat      temp4
		sat      temp15
		lat      temp5
		sat      temp14
code_0171:
		jmp      code_019e
code_0173:
		pushi    1
		lst      temp5
		calle    IsOffScreen,  2
		bnt      code_018f
		lst      temp4
		lat      temp12
		le?     
		bnt      code_018d
		lat      temp4
		sat      temp12
		lat      temp5
		sat      temp11
code_018d:
		jmp      code_019e
code_018f:
		lst      temp4
		lat      temp9
		le?     
		bnt      code_019e
		lat      temp4
		sat      temp9
		lat      temp5
		sat      temp8
code_019e:
		pushi    1
		lst      temp3
		callk    NextNode,  2
		sat      temp3
		jmp      code_0096
code_01a9:
		+at      temp0
		jmp      code_007a
code_01ae:
		lat      temp8
		bnt      code_01bb
		pushi    #addToEnd
		pushi    1
		lst      temp8
		lat      temp10
		send     6
code_01bb:
		lat      temp11
		bnt      code_01c8
		pushi    #addToEnd
		pushi    1
		lst      temp11
		lat      temp13
		send     6
code_01c8:
		lat      temp14
		bnt      code_01d5
		pushi    #addToEnd
		pushi    1
		lst      temp14
		lat      temp16
		send     6
code_01d5:
		lat      temp8
		bt       code_01e5
		lat      temp11
		bt       code_01e5
		lat      temp14
		bt       code_01e5
		jmp      code_01ea
		bnt      code_01e7
code_01e5:
		ldi      1
code_01e7:
		jmp      code_0060
code_01ea:
		ret     
	)
)
